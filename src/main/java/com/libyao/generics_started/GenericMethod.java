package com.libyao.generics_started;


public class GenericMethod {

    public static <T extends Comparable<T>> T findMax(T[] array) {
        T max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i].compareTo(max) > 0) {
                max = array[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Integer[] nums = {1, 3, 5, 6, 7};
        Integer max = findMax(nums);
        System.out.println(max);
    }

}
