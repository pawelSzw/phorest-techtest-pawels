package demo.phorest.service;

import demo.phorest.enums.ResourceTypeEnum;

/**
 * ResourceService for Autowiring ResourceServices
 */
public interface ResourceServiceFactory {
    ResourceService getResourceService(final ResourceTypeEnum resourceTypeEnum);
}
