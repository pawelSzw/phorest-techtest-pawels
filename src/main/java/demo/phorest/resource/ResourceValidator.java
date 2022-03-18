package demo.phorest.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Service
public class ResourceValidator {

    private static final Logger logger = LoggerFactory.getLogger(ResourceValidator.class);

    private Validator validator;

    public ResourceValidator(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    /**
     * Validates Resource structure.
     */
    public boolean isValid(final Resource resource){
        boolean isValid = true;
        final Set<ConstraintViolation<Resource>> constraints = this.validator.validate(resource);
        if(!constraints.isEmpty()){
            //just log
            logger.warn("Invalid resource with id "+resource.getId()+" "+constraints);
            isValid = false;
        }
        return isValid;
    }
}
