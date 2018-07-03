package com.camark.enumstudy;

public class MyEnumTest {
    public static void main(String[] args) {
        MyEnum me = MyEnum.GREEN;


        System.out.println(me.name());
        System.out.println(me.next().name());
        System.out.println(me.next().getTime());

        me = MyEnum.valueOf("YELLOW");
        System.out.println(me.name());

        me = MyEnum.valueOf("RED");
        System.out.println(me.name());
    }
}
