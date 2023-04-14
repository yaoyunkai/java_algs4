package com.libyao.generics_started;

import java.util.ArrayList;
import java.util.List;

public class Demo3 {
    public static void main(String[] args) {
        // List list = new ArrayList();
        // list.add("Hello");
        // list.add("World");
        // String first = (String) list.get(0);
        // String second = (String) list.get(1);
        // System.out.println(first);
        // System.out.println(second);
        func1();
    }

    public static void func1() {
        List<Number> list = new ArrayList<Number>();
        list.add(123);
        list.add(12.34);
        Number first = list.get(0);
        Number second = list.get(1);
        System.out.println(first);
        System.out.println(second);
    }

}
