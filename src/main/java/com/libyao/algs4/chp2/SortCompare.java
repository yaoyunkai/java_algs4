package com.libyao.algs4.chp2;

import edu.princeton.stdlib.StdOut;
import edu.princeton.stdlib.StdRandom;
import edu.princeton.stdlib.Stopwatch;

public class SortCompare {

    public static <T extends Comparable<T>> double time(String alg, T[] a) {
        Stopwatch timer = new Stopwatch();
        if (alg.equals("Insertion")) Insertion.sort(a);
        if (alg.equals("Selection")) Selection.sort(a);
        if (alg.equals("Shell")) Shell.sort(a);
        return timer.elapsedTime();
    }

    public static double timeRandomInput(String alg, int N, int T) {
        double total = 0.0;

        Double[] a = new Double[N];
        for (int t = 0; t < T; t++) {
            for (int i = 0; i < N; i++) {
                a[i] = StdRandom.uniformDouble();
            }
            total += time(alg, a);

        }
        return total;
    }

    public static void main(String[] args) {
        String alg1 = "Insertion";
        String alg2 = "Shell";

        int N = 10000;
        int T = 10;
        double t1 = timeRandomInput(alg1, N, T);
        double t2 = timeRandomInput(alg2, N, T);

        StdOut.println("t1 cost: " + t1);
        StdOut.println("t2 cost: " + t2);

        // StdOut.printf("For %d random Doubles\n    %s is", N, alg1);
        // StdOut.printf(" %.1f times faster than %s\n", t2 / t1, alg2);

    }

}
