package com.libyao.algs4.chp5;

public class LSD {

    private LSD() {
    }

    public static void sort(String[] a, int w) {
        int n = a.length;
        int R = 256;

        String[] aux = new String[n];

        for (int d = w - 1; d >= 0; d--) {
            int[] count = new int[R + 1];


            for (String s : a) {
                count[s.charAt(d) + 1]++;
            }

            for (int r = 0; r < R; r++) {
                count[r + 1] += count[r];
            }

            for (String s : a) {
                aux[count[s.charAt(d)]++] = s;
            }

            System.arraycopy(aux, 0, a, 0, n);
        }

    }

    public static void main(String[] args) {
    }

}
