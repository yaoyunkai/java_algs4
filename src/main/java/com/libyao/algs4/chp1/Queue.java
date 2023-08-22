package com.libyao.algs4.chp1;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class Queue<Item> implements Iterable<Item> {
    private Node<Item> first;    // beginning of queue
    private Node<Item> last;     // end of queue
    private int n;               // number of elements on queue

    // helper linked list class
    private static class Node<E> {
        private E item;
        private Node<E> next;
    }


    public Queue() {
        first = null;
        last = null;
        n = 0;
    }


    public boolean isEmpty() {
        return first == null;
    }


    public int size() {
        return n;
    }


    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return first.item;
    }


    public void enqueue(Item item) {
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;

        // 入队的时候不改变first，所以在初始化的时候需要特殊处理first
        if (isEmpty()) first = last;
        else oldlast.next = last;
        n++;
    }


    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = first.item;
        first = first.next;
        n--;

        // 当最后一个元素被dequeue时，first已经变为null了，此时需要对last特殊处理
        if (isEmpty()) last = null;   // to avoid loitering
        return item;
    }


    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }


    public Iterator<Item> iterator() {
        return new LinkedIterator(first);
    }

    // a linked-list iterator
    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> current;

        public LinkedIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }


    public static void main(String[] args) {
        Queue<String> queue = new Queue<>();
        queue.enqueue("b");
        queue.enqueue("c");
        queue.enqueue("d");
        queue.enqueue("e");
        System.out.println(queue);
    }
}
