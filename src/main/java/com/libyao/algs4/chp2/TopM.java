package com.libyao.algs4.chp2;

import edu.princeton.stdlib.StdIn;

public class TopM {

    // This class should not be instantiated.
    private TopM() {
    }

    /**
     * Reads a sequence of transactions from standard input; takes a
     * command-line integer m; prints to standard output the m largest
     * transactions in descending order.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        int m = Integer.parseInt(args[0]);
        MinPQ<Person> pq = new MinPQ<Person>(m + 1);

        while (StdIn.hasNextLine()) {
            // Create an entry from the next line and put on the PQ.
            String line = StdIn.readLine();
            Person transaction = new Person("O", 13);
            pq.insert(transaction);

            // remove minimum if m+1 entries on the PQ
            if (pq.size() > m)
                pq.delMin();
        }   // top m entries are on the PQ

        // print entries on PQ in reverse order

    }
}