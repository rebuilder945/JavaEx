package org.cqu.service.impl;

import org.cqu.dao.UserDao;
import org.cqu.service.UserService;
import org.springframework.beans.factory.InitializingBean;

public class UesrServiceImpl implements UserService, InitializingBean {

    private UserDao userDao;

    public UesrServiceImpl() {
        System.out.println("UesrServiceImpl实例化");
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
        System.out.println("nihao \n" + userDao.toString());
    }
}
