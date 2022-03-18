package demo.phorest.web;

import demo.phorest.enums.ResourceTypeEnum;
import demo.phorest.exception.ResourceNotFoundException;
import demo.phorest.resource.ClientResource;
import demo.phorest.service.ClientResourceService;
import demo.phorest.service.TopClientsService;
import demo.phorest.service.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@Api(value = "/salon-manager", produces = "application/json")
public class SalonManagerController {

    private static final String TAG = "salon-manager-api";

    private static final Logger logger = LoggerFactory.getLogger(SalonManagerController.class);

    @Autowired
    private UploadService uploadService;

    @Autowired
    private TopClientsService topClientsService;

    @Autowired
    private ClientResourceService clientResourceService;


    @Operation(summary = "Upload csv file ",
            tags = { TAG },
            parameters = {
                    @Parameter(in = ParameterIn.DEFAULT, name = "file", description = "CSV file", required = true),
                    @Parameter(in = ParameterIn.DEFAULT, name = "resourceType", required = true, description = "Type of resource. Could be one of: CLIENT, APPOINTMENT, PURCHASE, SERVICE", allowEmptyValue = false, schema = @Schema(implementation = ResourceTypeEnum.class))
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UploadResponse.class))),
                    @ApiResponse(responseCode = "400", description = "ERROR", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "500", description = "INTERNAL ERROR", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            })
    @PostMapping(path = "/upload-csv-file", consumes = {"multipart/form-data"} )
    @ApiOperation(value = "Uploads csv file", response = UploadResponse.class)
    @CrossOrigin(origins="*")
    public ResponseEntity<UploadResponse> uploadCSVFile(@RequestParam("file") InputStreamSource file, @RequestParam("resourceType")  final ResourceTypeEnum resourceTypeEnum) throws IOException {
        UploadResponse uploadResponse =uploadService.uploadResources(file, resourceTypeEnum);
        return new ResponseEntity(uploadResponse,HttpStatus.OK);
    }

    @Operation(summary = "Retrieve clients",
            tags = { TAG },
            parameters = {
                    @Parameter(in = ParameterIn.DEFAULT, name = "top", description = "Top clients with most loyalty_points"),
                    @Parameter(in = ParameterIn.DEFAULT, name = "fromDate",  description = "Start date", allowEmptyValue = false, schema = @Schema(implementation = ResourceTypeEnum.class))
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = List.class))),
                    @ApiResponse(responseCode = "400", description = "ERROR", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "500", description = "INTERNAL ERROR", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            })
    @GetMapping(path = "/clients" )
    @ApiOperation(value = "Return clients", response = List.class)
    @CrossOrigin(origins="*")
    public ResponseEntity<List<ClientResource>> query(@RequestParam("top") final int top, @RequestParam("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") final Date fromDate) {
        final List<ClientResource> clients = topClientsService.getTopClients(top, fromDate);
        return new ResponseEntity(clients,HttpStatus.OK);
    }


    @Operation(summary = "Retrieve client by client id",
            tags = { TAG },
            parameters = {
                    @Parameter(in = ParameterIn.DEFAULT, name = "clientId", description = "Client identifier"),},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ClientResource.class))),
                    @ApiResponse(responseCode = "400", description = "ERROR", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "500", description = "INTERNAL ERROR", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            })
    @GetMapping(path = "/clients/{clientId}" )
    @ApiOperation(value = "Return clients", response = ClientResource.class)
    @CrossOrigin(origins="*")
    public ResponseEntity<ClientResource> getSingle(@PathVariable("clientId") final UUID clientId) {
        final ClientResource client = clientResourceService.getSingle(clientId);
        return new ResponseEntity(client,HttpStatus.OK);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleException(
            final ResourceNotFoundException exception) {
        logger.error("Handling  Exception", exception);
        return new ErrorResponse(404,"API error NOT FOUND", exception.getMessage());
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleException(
            final Exception exception) {
        logger.error("Handling  Exception", exception);
        return new ErrorResponse(400,"API error", exception.getMessage());
    }


}
