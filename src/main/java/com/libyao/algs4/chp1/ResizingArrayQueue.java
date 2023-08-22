package com.libyao.algs4.chp1;

import edu.princeton.stdlib.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class ResizingArrayQueue<Item> implements Iterable<Item> {
    // initial capacity of underlying resizing array
    private static final int INIT_CAPACITY = 4;

    private Item[] q;       // queue elements
    private int n;          // number of elements on queue
    private int first;      // index of first element of queue, 为了出队用的
    private int last;       // index of next available slot, 循环使用覆盖数据

    public ResizingArrayQueue() {
        q = (Item[]) new Object[INIT_CAPACITY];
        n = 0;
        first = 0;
        last = 0;
    }


    public boolean isEmpty() {
        return n == 0;
    }


    public int size() {
        return n;
    }

    // resize the underlying array
    private void resize(int capacity) {
        assert capacity >= n;
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = q[(first + i) % q.length]; // 取余的意义????
        }
        q = copy;
        first = 0;
        last = n;
    }


    public void enqueue(Item item) {
        // 入队不改变first
        if (n == q.length) resize(2 * q.length);
        q[last++] = item;
        if (last == q.length) last = 0; // 空间用完之后又从第一个位置开始，循环使用
        n++;
    }

    public Item dequeue() {
        // dequeue 只会改变first，当时数组前面会有空缺

        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = q[first];
        q[first] = null;                            // to avoid loitering
        n--;
        first++;

        // queue中 item已经出队完了，re-use
        if (first == q.length) first = 0;           // wrap-around
        // shrink size of array if necessary
        if (n > 0 && n == q.length / 4) resize(q.length / 2);
        return item;
    }


    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return q[first];
    }


    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    // an array iterator, from first to last-1
    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;

        public boolean hasNext() {
            return i < n;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = q[(i + first) % q.length];
            i++;
            return item;
        }
    }


    public static void main(String[] args) {
        ResizingArrayQueue<String> queue = new ResizingArrayQueue<String>();
        queue.enqueue("a");
        queue.enqueue("aa");
        queue.enqueue("ab");
        queue.enqueue("ac");
        queue.enqueue("ad");
        queue.enqueue("ae");
        queue.enqueue("ae3");

        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();

        StdOut.println("(" + queue.size() + " left on queue)");
    }

}
