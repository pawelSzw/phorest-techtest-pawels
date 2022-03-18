package demo.phorest.service;

import demo.phorest.enity.ClientEntity;
import demo.phorest.repository.ClientEntityRepository;
import demo.phorest.resource.ClientResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopClientsService {

    @Autowired
    private ClientEntityRepository clientRepository;

    @Autowired
    private ClientResourceService clientResourceService;

    public List<ClientResource> getTopClients(final int top, final Date fromDate){
        final List<ClientEntity> entityList = clientRepository.findTopClients(top, fromDate);
        final List<ClientResource> clientResources = entityList.stream().map(entity->clientResourceService.mapToResource(entity)).collect(Collectors.toList());
        return  clientResources;
    }

}
