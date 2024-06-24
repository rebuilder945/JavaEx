package org.cqu.dao.impl;

import org.cqu.dao.PersonDao;

public class PersonDaoImpl implements PersonDao{
    private int num_;

    public PersonDaoImpl() {
        System.out.println(this);
    }

    public int getNum_() {
        return num_;
    }

    public void setNum_(int num_) {
        this.num_ = num_;
    }

    @Override
    public String toString() {
        return "PersonDaoImpl [num_=" + num_ + "]";
    }
}
