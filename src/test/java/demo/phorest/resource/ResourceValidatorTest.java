package demo.phorest.resource;

import demo.phorest.enums.GenderEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;


public class ResourceValidatorTest {

    private ResourceValidator testedClass = new ResourceValidator();

    @Test
    public void validate_returns_true_for_correct_resource_test(){
        final ClientResource clientResource = new ClientResource();
        clientResource.setId(UUID.randomUUID());
        clientResource.setBanned(false);
        clientResource.setEmail("email@email.com");
        clientResource.setPhoneNumber("3344433");
        clientResource.setGender(GenderEnum.Male);
        clientResource.setFirstName("Paul");
        clientResource.setLastName("Long");

        Assertions.assertTrue(testedClass.isValid(clientResource));
    }

    @Test
    public void validate_returns_false_for_incorrect_resource_test(){
        final ClientResource clientResource = new ClientResource();
        clientResource.setId(UUID.randomUUID());
        clientResource.setBanned(false);
        clientResource.setPhoneNumber("3344433");
        clientResource.setGender(GenderEnum.Male);
        clientResource.setLastName("Long");

        Assertions.assertFalse(testedClass.isValid(clientResource));
    }
}
