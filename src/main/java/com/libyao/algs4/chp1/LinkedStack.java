package com.libyao.algs4.chp1;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
链表的遍历

for (Node x = first; x ! = null; x = x.next)
{
    // 处理x.item
}


*/
public class LinkedStack<Item> implements Iterable<Item> {
    private int n;          // size of the stack
    private Node first;     // top of stack

    // helper linked list class
    private class Node {
        private Item item;
        private Node next;
    }

    public LinkedStack() {
        first = null;
        n = 0;
        // assert check();
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public void push(Item item) {
        // 头插法,最后插入的一定是first
        // 将next指向上次插入的node
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        n++;
        // assert check();
    }

    public Item pop() {
        /*
        从first取出node，然后将first指向first.next
        
         */
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = first.item;        // save item to return
        first = first.next;            // delete first node
        n--;
        // assert check();
        return item;                   // return the saved item
    }

    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        return first.item;
    }


    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) s.append(item).append(" ");
        return s.toString();
    }


    public Iterator<Item> iterator() {
        return new LinkedIterator();
    }

    // a linked-list iterator
    private class LinkedIterator implements Iterator<Item> {
        private Node current = first;

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


    // check internal invariants
    private boolean check() {

        // check a few properties of instance variable 'first'
        if (n < 0) {
            return false;
        }
        if (n == 0) {
            if (first != null) return false;
        } else if (n == 1) {
            if (first == null) return false;
            if (first.next != null) return false;
        } else {
            if (first == null) return false;
            if (first.next == null) return false;
        }

        // check internal consistency of instance variable n
        int numberOfNodes = 0;
        for (Node x = first; x != null && numberOfNodes <= n; x = x.next) {
            numberOfNodes++;
        }
        if (numberOfNodes != n) return false;

        return true;
    }


    public static void main(String[] args) {
        LinkedStack<String> stack = new LinkedStack<String>();
        // while (!StdIn.isEmpty()) {
        //     String item = StdIn.readString();
        //     if (!item.equals("-"))
        //         stack.push(item);
        //     else if (!stack.isEmpty())
        //         StdOut.print(stack.pop() + " ");
        // }

        stack.push("b");
        stack.push("c");
        stack.push("d");
        stack.push("e");
        // StdOut.println("(" + stack.size() + " left on stack)");
        System.out.println(stack);
    }
}
