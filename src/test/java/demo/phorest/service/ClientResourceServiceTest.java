package demo.phorest.service;

import demo.phorest.enity.ClientEntity;
import demo.phorest.enums.GenderEnum;
import demo.phorest.repository.ClientEntityRepository;
import demo.phorest.resource.ClientResource;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import java.util.UUID;


@RunWith(JMockit.class)
public class ClientResourceServiceTest {

    @Tested
    private ClientResourceService clientResourceService;

    @Injectable
    private ClientEntityRepository clientRepository;

    @Test
    public void createEntityTest(){
        final ClientResource clientResource = new ClientResource();
        clientResource.setId(UUID.randomUUID());
        clientResource.setBanned(false);
        clientResource.setPhoneNumber("3344433");
        clientResource.setGender(GenderEnum.Male);
        clientResource.setLastName("Long");

        Assertions.assertTrue(clientResourceService.createEntity(clientResource));
    }

    @Test
    public void mapResourceTest(){
        //GIVEN
        final UUID uuid = UUID.randomUUID();
        boolean banned = false;
        String email = "email@email.com";
        String phone = "3344433";
        String firstName = "Paul";
        String lastName = "Long";


        final ClientEntity clientEntity = new ClientEntity();

        clientEntity.setId(uuid);
        clientEntity.setBanned(banned);
        clientEntity.setEmail(email);
        clientEntity.setPhoneNumber(phone);
        clientEntity.setGender(GenderEnum.Male);
        clientEntity.setFirstName(firstName);
        clientEntity.setLastName(lastName);

        //WHEN
        ClientResource clientResource = clientResourceService.mapToResource(clientEntity);

        //THEN
        Assertions.assertEquals(uuid, clientResource.getId());
        Assertions.assertFalse(clientResource.getBanned());
        Assertions.assertEquals(email, clientResource.getEmail());
        Assertions.assertEquals(phone, clientResource.getPhoneNumber());
        Assertions.assertEquals(firstName, clientResource.getFirstName());
        Assertions.assertEquals(lastName, clientResource.getLastName());
    }
}
