package demo.phorest.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import demo.phorest.enums.ResourceTypeEnum;
import demo.phorest.resource.*;
import demo.phorest.web.UploadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class UploadService {

    private ResourceServiceFactory resourceServiceFactory;

    private CsvSchema schema;

    private CsvMapper csvMapper;

    @Autowired
    private ResourceValidator resourceValidator;

    public UploadService(final ResourceServiceFactory resourceServiceFactory){
         this.resourceServiceFactory = resourceServiceFactory;
         schema = CsvSchema.emptySchema().withSkipFirstDataRow(true).withHeader();
         csvMapper = new CsvMapper();
         csvMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
         csvMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
         JavaTimeModule javaTimeModule = new JavaTimeModule();
         csvMapper.registerModule(javaTimeModule);
    }

    public UploadResponse uploadResources(final InputStreamSource file, final ResourceTypeEnum resourceTypeEnum) throws IOException {
        int totalRecords = 0;
        int recordsLoaded = 0;
        final BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
        final ResourceService resourceService = resourceServiceFactory.getResourceService(resourceTypeEnum);
        final MappingIterator<Resource> iterator = csvMapper.readerFor(resourceService.getResourceClass()).with(schema).readValues(fileReader);
        while(iterator.hasNext()) {
            totalRecords++;
            final Resource resource = iterator.next();
            if(resourceValidator.isValid(resource)) {
                boolean isRecordLoaded = resourceService.createEntity(resource);
                if (isRecordLoaded) {
                    recordsLoaded++;
                }
            }
        }
        return new UploadResponse(recordsLoaded, totalRecords);
    }


}
