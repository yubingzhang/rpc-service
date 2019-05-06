package com.rpc.proxy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * BeanDefinitionRegistryPostProcessor:动态装配bean到容器
 */
@Component
public class RpcScannerConfigurer implements BeanDefinitionRegistryPostProcessor {
    String basePackage = " com.rpc.api";


    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        ClassPathRpcScanner scanner = new ClassPathRpcScanner(beanDefinitionRegistry);
        scanner.setAnnotationClass(null);
        scanner.registerFilters();
        scanner.scan(StringUtils.tokenizeToStringArray(this.basePackage, ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS));
    }

    /*public BeanDefinitionRegistryPostProcessor beanDefinitionRegistryPostProcessor(Environment environment){
        return new BeanDefinitionRegistryPostProcessor() {
            @Override
            public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
                BeanDefinition beanDe = BeanDefinitionBuilder.rootBeanDefinition(Datasoure.class)
                        .addConstructorArgValue("datasoure1").getBeanDefinition();
                registry.registerBeanDefinition("soure1", beanDe);
            }

            @Override
            public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

            }
        };
    }*/

    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
    }
}
