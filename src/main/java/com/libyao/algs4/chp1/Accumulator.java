package com.libyao.algs4.chp1;

import edu.princeton.stdlib.In;
import edu.princeton.stdlib.StdOut;

public class Accumulator {
    private int n = 0;          // number of data values
    private double sum = 0.0;   // sample variance * (n-1)
    private double mu = 0.0;    // sample mean

    /**
     * Initializes an accumulator.
     */
    public Accumulator() {
    }

    /**
     * Adds the specified data value to the accumulator.
     *
     * @param x the data value
     */
    public void addDataValue(double x) {
        n++;
        double delta = x - mu;
        mu += delta / n;
        sum += (double) (n - 1) / n * delta * delta;
    }

    /**
     * Returns the mean of the data values.
     *
     * @return the mean of the data values
     */
    public double mean() {
        return mu;
    }

    /**
     * Returns the sample variance of the data values.
     *
     * @return the sample variance of the data values
     */
    public double var() {
        if (n <= 1) return Double.NaN;
        return sum / (n - 1);
    }

    /**
     * Returns the sample standard deviation of the data values.
     *
     * @return the sample standard deviation of the data values
     */
    public double stddev() {
        return Math.sqrt(this.var());
    }

    /**
     * Returns the number of data values.
     *
     * @return the number of data values
     */
    public int count() {
        return n;
    }

    /**
     * Returns a string representation of this accumulator.
     *
     * @return a string representation of this accumulator
     */
    public String toString() {
        return "n = " + n + ", mean = " + mean() + ", stddev = " + stddev();
    }

    /**
     * Unit tests the {@code Accumulator} data type.
     * Reads in a stream of real number from standard input;
     * adds them to the accumulator; and prints the mean,
     * sample standard deviation, and sample variance to standard
     * output.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Accumulator stats = new Accumulator();

        In in = new In("data/tinyAllowlist.txt");
        int[] allowlist = in.readAllInts();

        for (int i : allowlist) {
            stats.addDataValue(i);
        }

        StdOut.printf("n      = %d\n", stats.count());
        StdOut.printf("mean   = %.5f\n", stats.mean());
        StdOut.printf("stddev = %.5f\n", stats.stddev());
        StdOut.printf("var    = %.5f\n", stats.var());
        StdOut.println(stats);
    }
}
