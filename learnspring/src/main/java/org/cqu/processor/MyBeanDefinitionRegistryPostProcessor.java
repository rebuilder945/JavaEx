package org.cqu.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;

// 专用于向BeandefinitionMap注册新的类
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor{

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        
        BeanDefinition myBeanDefinition = new RootBeanDefinition();
        myBeanDefinition.setBeanClassName("org.cqu.dao.impl.PersonDaoImpl");
        myBeanDefinition.setScope("prototype");
        registry.registerBeanDefinition("personDao2", myBeanDefinition);
        System.out.println(myBeanDefinition);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    }

    

}
