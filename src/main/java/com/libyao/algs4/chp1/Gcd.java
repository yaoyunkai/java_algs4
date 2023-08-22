/*
最大公约数


*/

package com.libyao.algs4.chp1;

public class Gcd {

    public static int gcd(int p, int q) {
        if (q == 0) return p;
        int r = p % q;
        return gcd(q, r);
    }

    public static void main(String[] args) {
        int gcd = gcd(3000, 2000);
        System.out.println(gcd);
    }

}
