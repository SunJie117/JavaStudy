package com.camark.Generic.dao.impl;

import com.camark.Generic.dao.Dao;
import com.camark.annotationstudy.Transaction;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public class DaoImpl<T> implements Dao<T> {

    private Class clazz;//实体类型
    public DaoImpl(){
        //给clazz赋值：需要知道操作的是哪个实体类，从而知道操作那张表

        Type type = this.getClass().getGenericSuperclass();//得到当前实例的带有泛型类型的父类
        ParameterizedType ptype = (ParameterizedType)type;//因为父类型带有泛型信息，就可以转为ParameterizedType（参数化的泛型类型）
        clazz = (Class)ptype.getActualTypeArguments()[0];// Customer|Student.class

        /* 获得函数参数的实际泛型类型
        Method applyMethod = GenericTest.class.getMethod("applyVector", Vector.class);
        Type[] types = applyMethod.getGenericParameterTypes();
        ParameterizedType pType = (ParameterizedType)types[0];
        System.out.println(pType.getRawType());
        System.out.println(pType.getActualTypeArguments()[0]);

        public static void applyVector(Vector<Date> v1){

        }
        */

        System.out.println(clazz.getSimpleName());
    }

    @Override
    @Transaction
    public void add(T t) {
        System.out.println("run add");
    }

    @Override
    public T findOne(Serializable id) {
        System.out.println("run findOne");
        return null;
    }

    @Override
    public boolean save(T t) {
        System.out.println("run save");
        return true;
    }
}
