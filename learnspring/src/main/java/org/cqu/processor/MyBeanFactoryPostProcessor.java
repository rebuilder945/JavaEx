package org.cqu.processor;

import org.cqu.dao.UserDao;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor{

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {        
        // 修改现有beandefinition
        // BeanDefinition userDao = (BeanDefinition) beanFactory.getBeanDefinition("userDao");
        // userDao.setBeanClassName("org.cqu.service.impl.UesrServiceImpl");
        // System.out.println(userDao);
        
        // 注册新的beandefinition
        // 1、实例化一个BanDefinition，setClassname
        BeanDefinition myBeanDefinition = new RootBeanDefinition();
        myBeanDefinition.setBeanClassName("org.cqu.dao.impl.PersonDaoImpl");
        // 2、向beanFactory中注册所实例化的BeanDefinition
        // 由于ConfigurableListableBeanFactory为顶级接口，registerBeanDefinition是在其
        // 被实现的子类中定义的，因此要进行强制转换
        DefaultListableBeanFactory myliListableBeanFactory = (DefaultListableBeanFactory) beanFactory;
        myliListableBeanFactory.registerBeanDefinition("personDao1", myBeanDefinition);
        System.out.println("MyBeanFactoryPostProcessor 的 personDao1 注册");
        
    }

}
