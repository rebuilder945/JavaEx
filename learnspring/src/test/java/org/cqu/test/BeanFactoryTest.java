package org.cqu.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.cqu.dao.PersonDao;
import org.cqu.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class BeanFactoryTest {
    public static void main(String[] args) throws IOException {
        // BeanFactory方式
//        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
//        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
//        reader.loadBeanDefinitions("applicationContext.xml");

        // ApplicationContext方式（封装了BeanFactory构建类过程）
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        // UserService userService = (UserService) applicationContext.getBean("userService");

        // 查看DruidDataSource类定义，发现含有无参构造方法，因此可以直接定义bean
//        DruidDataSource druidDataSource = new DruidDataSource();
//        System.out.println(druidDataSource);
//        Object dataSource = applicationContext.getBean("dataSource");
//        System.out.println(dataSource);

//        Object date= applicationContext.getBean("date");
//        System.out.println(date);

        // 传统方式配置mybatis
//        // 静态工厂方法初始化
//        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
//        // 无参构造函数初始化
//        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
//        // 实例工厂方法初始化
//        SqlSessionFactory build = builder.build(in);
        //bean方式配置mybatis
        // SqlSessionFactory sqlSessionFactory = ((SqlSessionFactory) applicationContext.getBean("sqlSessionFactory"));
        // System.out.println(sqlSessionFactory);

        
        PersonDao personDao1 = (PersonDao) applicationContext.getBean("personDao1");
        PersonDao personDao1_1 = (PersonDao) applicationContext.getBean("personDao1");
        PersonDao personDao2 = (PersonDao) applicationContext.getBean("personDao2");
        PersonDao personDao2_1 = (PersonDao) applicationContext.getBean("personDao2");
        
        System.out.println();
        System.out.println(personDao1);
        System.out.println(personDao1_1);
        System.out.println(personDao2);
        System.out.println(personDao2_1);

    }
}
