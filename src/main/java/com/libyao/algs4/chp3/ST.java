package com.libyao.algs4.chp3;

/*
查找树

每个键只对应着一个值（表中不允许存在重复的键）
当用例代码向表中存入的键值对和表中已有的键（及关联的值）冲突时，新的值会替代旧的值。
键不能为空

任何不在表中的键关联的值都是空
第一，我们可以用get()方法是否返回空来测试给定的键是否存在于符号表中；
第二，我们可以将空值作为put()方法的第二个参数存入表中来实现删除，



 */

import edu.princeton.stdlib.StdIn;
import edu.princeton.stdlib.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeMap;


public class ST<Key extends Comparable<Key>, Value> implements Iterable<Key> {

    private final TreeMap<Key, Value> st;


    public ST() {
        st = new TreeMap<Key, Value>();
    }


    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("calls get() with null key");
        return st.get(key);
    }


    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("calls put() with null key");
        if (val == null) st.remove(key);
        else st.put(key, val);
    }


    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("calls delete() with null key");
        st.remove(key);
    }


    public void remove(Key key) {
        if (key == null) throw new IllegalArgumentException("calls remove() with null key");
        st.remove(key);
    }


    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("calls contains() with null key");
        return st.containsKey(key);
    }


    public int size() {
        return st.size();
    }


    public boolean isEmpty() {
        return size() == 0;
    }


    public Iterable<Key> keys() {
        return st.keySet();
    }


    @Deprecated
    public Iterator<Key> iterator() {
        return st.keySet().iterator();
    }


    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return st.firstKey();
    }


    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
        return st.lastKey();
    }


    public Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
        Key k = st.ceilingKey(key);
        if (k == null) throw new NoSuchElementException("argument to ceiling() is too large");
        return k;
    }


    public Key floor(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to floor() is null");
        Key k = st.floorKey(key);
        if (k == null) throw new NoSuchElementException("argument to floor() is too small");
        return k;
    }


    public static void main(String[] args) {
        ST<String, Integer> st = new ST<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
    }
}
