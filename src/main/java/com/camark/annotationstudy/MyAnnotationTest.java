package com.camark.annotationstudy;

import java.lang.reflect.Method;

public class MyAnnotationTest {
    @MyAnnotation(value = 1,test = true)
    public void test() {
        System.out.println("test()");
    }

    public static void main(String[] args) {
        MyAnnotationTest ma = new MyAnnotationTest();

        Class clazz = MyAnnotationTest.class;


        Method[] ms = clazz.getMethods();

        MyAnnotation myAnnotation;

        for(Method m:  ms) {

            if (m.isAnnotationPresent(MyAnnotation.class)) {
                myAnnotation = m.getAnnotation(MyAnnotation.class);

                if (myAnnotation.test()) {
                    System.out.println(myAnnotation.value());
                    ma.test();

                }
            }


        }


    }
}
