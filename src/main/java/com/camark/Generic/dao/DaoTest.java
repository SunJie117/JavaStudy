package com.camark.Generic.dao;

import com.camark.Generic.dao.impl.DaoImpl;
import com.camark.Generic.dao.impl.IntegerDaoImpl;

public class DaoTest {
    public static void main(String[] args) {
        IntegerDaoImpl di = new IntegerDaoImpl();
        di.findOne(1);
    }
}
