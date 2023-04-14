package com.libyao.generics_started;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class IntPair extends Pair<Integer> {

    public IntPair(Integer first, Integer last) {
        super(first, last);
    }

    public static void main(String[] args) {
        System.out.println("------ this is IntPair ------------");

        Class<IntPair> clazz = IntPair.class;
        Type t = clazz.getGenericSuperclass();

        if (t instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) t;
            Type[] types = pt.getActualTypeArguments();
            Type firstType = types[0];
            Class<?> typeClass = (Class<?>) firstType;
            System.out.println(typeClass);
        }
    }

}
