package com.libyao.algs4.chp3;


import com.libyao.algs4.chp1.Queue;

/*
基于链表的查找树


 */
public class SequentialSearchST<Key, Value> {
    private int n = 0;
    private Node first = null;

    private class Node {
        private Key key;
        private Value val;
        private Node next;

        public Node(Key k, Value v, Node next) {
            this.key = k;
            this.val = v;
            this.next = next;
        }

    }

    public SequentialSearchST() {
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key))
                return x.val;
        }
        return null;
    }

    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (val == null) {
            delete(key);
            return;
        }

        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        // 头插法
        first = new Node(key, val, first);
        n++;
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        first = delete(first, key);
    }

    // delete key in linked list beginning at Node x
    // warning: function call stack too large if table is large
    private Node delete(Node x, Key key) {
        /*
        匹配到key的时候 返回x.next ， 这时就会去掉一个node
        未匹配时, 未对链表做改变。仅在递归中取下一个node
        
         */
        if (x == null) return null;
        if (key.equals(x.key)) {
            n--;
            return x.next;
        }
        // 这里的 .next才推动了指针的移动
        x.next = delete(x.next, key);
        return x;
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (Node x = first; x != null; x = x.next)
            queue.enqueue(x.key);
        return queue;
    }

    public static void main(String[] args) {
        SequentialSearchST<String, Integer> st = new SequentialSearchST<>();

        st.put("a", 1);
        st.put("b", 2);
        st.put("c", 3);
        st.put("d", 4);
        st.put("e", 5);
        st.put("f", 6);
        st.put("a", 7);
        st.delete("e");
    }

}
