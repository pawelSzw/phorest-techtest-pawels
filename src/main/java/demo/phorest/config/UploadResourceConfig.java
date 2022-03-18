package demo.phorest.config;

import demo.phorest.service.ResourceServiceFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ServiceLocator for ResourceServices
 */
@Configuration
public class UploadResourceConfig {

    @Bean("resourceServiceFactory")
    public FactoryBean serviceLocatorFactoryBean() {
        ServiceLocatorFactoryBean factoryBean = new ServiceLocatorFactoryBean();
        factoryBean.setServiceLocatorInterface(ResourceServiceFactory.class);
        return factoryBean;
    }
}
