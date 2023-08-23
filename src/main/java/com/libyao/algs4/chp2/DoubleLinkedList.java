package com.libyao.algs4.chp2;

import java.util.NoSuchElementException;


public class DoubleLinkedList<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int n;  // size of linkedList

    private static class Node<E> {
        private E item;
        private Node<E> next;
        private Node<E> prev;
    }

    public DoubleLinkedList() {
        n = 0;
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public void add(Item item) {
        Node<Item> oldlast = last;

        last = new Node<>();
        last.item = item;
        last.next = null;

        if (isEmpty()) {
            first = last;
        } else {
            oldlast.next = last;
            last.prev = oldlast;
        }
        n++;
    }

    public void leftAdd(Item item) {
        Node<Item> oldfirst = first;

        first = new Node<>();
        first.item = item;
        first.prev = null;

        if (isEmpty()) {
            first = last;
        } else {
            first.next = oldfirst;
            oldfirst.prev = first;
        }
        n++;
    }

    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return last.item;
    }

    public Item pop() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = last.item;
        last = last.prev;
        last.next = null;
        n--;
        if (isEmpty()) {
            first = null;
        }
        return item;
    }

    public Item leftPop() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = first.item;
        first = first.next;
        first.prev = null;
        n--;
        if (isEmpty()) {
            last = null;
        }
        return item;
    }

    public void reversePrint() {
        if (isEmpty()) {
            System.out.println("blank list");
            return;
        }
        for (Node<Item> x = last; x != null; x = x.prev) {
            System.out.print(x.item);
            System.out.print(" ");
        }
        System.out.println();
    }

    public void sort() {
        int n = size();
    }

    public static void main(String[] args) {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.add(4);
        list.add(2);
        list.add(3);
        list.add(6);
        list.add(7);
        list.leftAdd(8);
        list.leftAdd(9);
        list.leftAdd(1);
        // Integer i1 = list.leftPop();
        // Integer i2 = list.pop();

        list.reversePrint();
    }

}
