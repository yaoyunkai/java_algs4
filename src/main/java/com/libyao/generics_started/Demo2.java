package com.libyao.generics_started;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型只在编译阶段有效。
 */
public class Demo2 {

    public static void main(String[] args) {
        List<String> stringArrayList = new ArrayList<String>();
        List<Integer> integerArrayList = new ArrayList<Integer>();

        Class classStringArrayList = stringArrayList.getClass();
        Class classIntegerArrayList = integerArrayList.getClass();

        if (classStringArrayList.equals(classIntegerArrayList)) {
            System.out.println("泛型测试, 类型相同");
        }
    }

}
