package demo.phorest.service;

import demo.phorest.enity.ClientEntity;
import demo.phorest.exception.ResourceNotFoundException;
import demo.phorest.repository.ClientEntityRepository;
import demo.phorest.resource.ClientResource;
import demo.phorest.resource.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("CLIENT")
public class ClientResourceService implements ResourceService{

    @Autowired
    private ClientEntityRepository clientRepository;

    @Override
    public boolean createEntity(final Resource resource){
        final ClientResource clientResource = (ClientResource) resource;
        final ClientEntity clientEntity = new ClientEntity();
        clientEntity.setId(clientResource.getId());
        clientEntity.setBanned(clientResource.getBanned());
        clientEntity.setEmail(clientResource.getEmail());
        clientEntity.setPhoneNumber(clientResource.getPhoneNumber());
        clientEntity.setGender(clientResource.getGender());
        clientEntity.setFirstName(clientResource.getFirstName());
        clientEntity.setLastName(clientResource.getLastName());
        clientRepository.save(clientEntity);
        return true;
    }

    public ClientResource mapToResource(final ClientEntity clientEntity){
        final ClientResource clientResource = new ClientResource();
        clientResource.setId(clientEntity.getId());
        clientResource.setBanned(clientEntity.getBanned());
        clientResource.setEmail(clientEntity.getEmail());
        clientResource.setPhoneNumber(clientEntity.getPhoneNumber());
        clientResource.setGender(clientEntity.getGender());
        clientResource.setFirstName(clientEntity.getFirstName());
        clientResource.setLastName(clientEntity.getLastName());
        return clientResource;
    }

    public ClientResource getSingle(final UUID clientId){
       final ClientEntity clientEntity = clientRepository.findById(clientId).orElseThrow(()->new ResourceNotFoundException("Cannot find client with client ID: "+clientId));
        return mapToResource(clientEntity);
    }

    @Override
    public Class<? extends Resource> getResourceClass() {
        return ClientResource.class;
    }

}
