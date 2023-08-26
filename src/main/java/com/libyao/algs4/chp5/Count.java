package com.libyao.algs4.chp5;

import edu.princeton.stdlib.StdIn;
import edu.princeton.stdlib.StdOut;

public class Count {

    // Do not instantiate.
    private Count() {
    }

    /**
     * Reads in text from standard input; calculates the frequency of
     * occurrence of each character over the alphabet specified as a
     * command-line argument; and prints the frequencies to standard
     * output.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Alphabet alphabet = new Alphabet(args[0]);
        final int R = alphabet.radix();
        int[] count = new int[R];
        while (StdIn.hasNextChar()) {
            char c = StdIn.readChar();
            if (alphabet.contains(c))
                count[alphabet.toIndex(c)]++;
        }
        for (int c = 0; c < R; c++)
            StdOut.println(alphabet.toChar(c) + " " + count[c]);
    }
}
