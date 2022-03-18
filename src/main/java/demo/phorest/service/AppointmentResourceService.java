package demo.phorest.service;

import demo.phorest.enity.AppointmentEntity;
import demo.phorest.enity.ClientEntity;
import demo.phorest.repository.AppointmentEntityRepository;
import demo.phorest.repository.ClientEntityRepository;
import demo.phorest.resource.AppointmentResource;
import demo.phorest.resource.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("APPOINTMENT")
public class AppointmentResourceService implements ResourceService{

    @Autowired
    private AppointmentEntityRepository appointmentRepository;

    @Autowired
    private ClientEntityRepository clientRepository;

    @Override
    public boolean createEntity(final Resource resource){
        boolean isEntityCreated = false;
        final AppointmentResource appointmentResource= (AppointmentResource) resource;
        //don't persist if when appointment not found
        final Optional<ClientEntity> clientEntityOptional = clientRepository.findById(appointmentResource.getClientId());
        if(clientEntityOptional.isPresent()) {
            final AppointmentEntity appointmentEntity = new AppointmentEntity();
            appointmentEntity.setId(appointmentResource.getId());
            appointmentEntity.setStartTime(appointmentResource.getStartTime());
            appointmentEntity.setEndTime(appointmentResource.getEndTime());
            appointmentEntity.setClient(clientEntityOptional.get());
            appointmentRepository.save(appointmentEntity);
            isEntityCreated = true;
        }
        return isEntityCreated;
    }

    @Override
    public Class<? extends Resource> getResourceClass() {
        return AppointmentResource.class;
    }


}
