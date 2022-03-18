package demo.phorest.service;

import demo.phorest.enity.AppointmentEntity;
import demo.phorest.enity.ServiceEntity;
import demo.phorest.repository.AppointmentEntityRepository;
import demo.phorest.repository.ServiceEntityRepository;
import demo.phorest.resource.Resource;
import demo.phorest.resource.ServiceResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("SERVICE")
public class ServiceResourceService implements ResourceService{

    @Autowired
    private ServiceEntityRepository serviceEntityRepository;

    @Autowired
    private AppointmentEntityRepository appointmentRepository;

    @Override
    public boolean createEntity(final Resource resource){
        boolean isEntityCreated = false;
        final ServiceResource serviceResource = (ServiceResource) resource;
        final ServiceEntity serviceEntity = new ServiceEntity();
        final Optional<AppointmentEntity> appointmentEntityOptional = appointmentRepository.findById(serviceResource.getAppointmentId());
        if(appointmentEntityOptional.isPresent()) {
            serviceEntity.setId(serviceResource.getId());
            serviceEntity.setName(serviceResource.getName());
            serviceEntity.setPrice(serviceResource.getPrice());
            serviceEntity.setLoyaltyPoints(serviceResource.getLoyaltyPoints());
            serviceEntity.setAppointment(appointmentEntityOptional.get());
            serviceEntityRepository.save(serviceEntity);
            isEntityCreated = true;
        }
        return isEntityCreated;
    }

    @Override
    public Class<? extends Resource> getResourceClass() {
        return ServiceResource.class;
    }

}
