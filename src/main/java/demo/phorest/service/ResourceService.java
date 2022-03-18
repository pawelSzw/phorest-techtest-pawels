package demo.phorest.service;

import demo.phorest.resource.Resource;
import org.springframework.transaction.annotation.Transactional;

/**
 * Resource service interface
 */
public interface ResourceService {

     @Transactional
     boolean createEntity(final Resource resource);

     Class<? extends Resource> getResourceClass();

}
