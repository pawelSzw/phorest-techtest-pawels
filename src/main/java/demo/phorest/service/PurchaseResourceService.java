package demo.phorest.service;

import demo.phorest.enity.AppointmentEntity;
import demo.phorest.enity.PurchaseEntity;
import demo.phorest.repository.AppointmentEntityRepository;
import demo.phorest.repository.PurchaseEntityRepository;
import demo.phorest.resource.PurchaseResource;
import demo.phorest.resource.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("PURCHASE")
public class PurchaseResourceService implements ResourceService{

    @Autowired
    private PurchaseEntityRepository purchaseRepository;

    @Autowired
    private AppointmentEntityRepository appointmentRepository;

    @Override
    public boolean createEntity(final Resource resource){
        boolean isEntityCreated = false;
        final PurchaseResource  purchaseResource = (PurchaseResource) resource;
        final PurchaseEntity purchaseEntity = new PurchaseEntity();
        final Optional<AppointmentEntity> appointmentEntityOptional = appointmentRepository.findById(purchaseResource.getAppointmentId());
        if(appointmentEntityOptional.isPresent()) {
            purchaseEntity.setId(purchaseResource.getId());
            purchaseEntity.setName(purchaseResource.getName());
            purchaseEntity.setPrice(purchaseResource.getPrice());
            purchaseEntity.setLoyaltyPoints(purchaseResource.getLoyaltyPoints());
            purchaseEntity.setAppointment(appointmentEntityOptional.get());
            purchaseRepository.save(purchaseEntity);
            isEntityCreated = true;
        }
        return isEntityCreated;
    }

    @Override
    public Class<? extends Resource> getResourceClass() {
        return PurchaseResource.class;
    }

}
