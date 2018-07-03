package com.camark.proxyStudy;

import com.camark.Generic.dao.Dao;
import com.camark.Generic.dao.impl.IntegerDaoImpl;
import com.camark.annotationstudy.Transaction;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyStudy {




    public static void main(String args[]) {

        final Dao  dao = new IntegerDaoImpl();

        Dao pdao = (Dao)Proxy.newProxyInstance(dao.getClass().getClassLoader(), new Class[] {Dao.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object result = null;

                        if (method.isAnnotationPresent(Transaction.class)) {
                            System.out.println("statr transaction");
                            result = method.invoke(dao,args);
                            System.out.println("end transaction");
                        } else {
                            result = method.invoke(dao,args);
                        }

                        return result;
                    }
                });
        pdao.findOne(1);
        pdao.add(1);
        pdao.save(1);
    }
}
