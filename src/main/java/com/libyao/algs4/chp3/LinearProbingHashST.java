package com.libyao.algs4.chp3;

import com.libyao.algs4.chp1.Queue;

/*
基于线性探测法的散列表

实现散列表的另一种方式就是用大小为M的数组保存N个键值对，其中M＞N。
我们需要依靠数组中的空位解决碰撞冲突。基于这种策略的所有方法被统称为开放地址散列表。

当碰撞发生时（当一个键的散列值已经被另一个不同的键占用），
我们直接检查散列表中的下一个位置（将索引值加1）。这样的线性探测可能会产生三种结果：
    命中，该位置的键和被查找的键相同；
    未命中，键为空（该位置没有键）；
    继续查找，该位置的键和被查找的键不同。

检查其中的键和被查找的键是否相同。
如果不同则继续查找（将索引增大，到达数组结尾时折回数组的开头），直到找到该键或者遇到一个空元素，

使用空（标记为null）来表示一簇键的结束。如果一个新键的散列值是一个空元素，那么就将它保存在那里；
如果不是，我们就顺序查找一个空元素来保存它。
要查找一个键，我们从它的散列值开始顺序查找，如果找到则命中，如果遇到空元素则未命中。

直接将该键所在的位置设为null是不行的，因为这会使得在此位置之后的元素无法被查找.

*/
public class LinearProbingHashST<Key, Value> {
    // must be a power of 2
    private static final int INIT_CAPACITY = 4;

    private int n;           // number of key-value pairs in the symbol table
    private int m;           // size of linear probing table
    private Key[] keys;      // the keys, 数组的所以既是hash
    private Value[] vals;    // the values

    public LinearProbingHashST() {
        this(INIT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public LinearProbingHashST(int capacity) {
        m = capacity;
        n = 0;
        keys = (Key[]) new Object[m];
        vals = (Value[]) new Object[m];
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

    private int hashTextbook(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    private int hash(Key key) {
        int h = key.hashCode();
        h ^= (h >>> 20) ^ (h >>> 12) ^ (h >>> 7) ^ (h >>> 4);
        return h & (m - 1);
    }

    private void resize(int capacity) {
        LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<Key, Value>(capacity);
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) {
                temp.put(keys[i], vals[i]);
            }
        }
        keys = temp.keys;
        vals = temp.vals;
        m = temp.m;
    }

    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");

        if (val == null) {
            delete(key);
            return;
        }

        // 数组大小大于2倍实际大小
        if (n >= m / 2) resize(2 * m);

        int i;

        // i位置是否为空, i位置的键是否相等
        for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        }
        keys[i] = key;
        vals[i] = val;
        n++;
    }

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");

        // 如果key存在 那么一定能找到
        // 如果i位置为null, 说明key不存在
        for (int i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                return vals[i];
            }
        }
        return null;
    }

    public void delete(Key key) {
        // 我们需要将簇中被删除键的右侧的所有键重新插入散列表。

        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key)) return;

        // find position i of key
        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % m;
        }

        // delete key and associated value
        keys[i] = null;
        vals[i] = null;

        // rehash all keys in same cluster
        i = (i + 1) % m;
        while (keys[i] != null) {
            // delete keys[i] and vals[i] and reinsert
            Key keyToRehash = keys[i];
            Value valToRehash = vals[i];
            keys[i] = null;
            vals[i] = null;
            n--;
            put(keyToRehash, valToRehash);
            i = (i + 1) % m;
        }

        n--;

        // halves size of array if it's 12.5% full or less
        if (n > 0 && n <= m / 8) resize(m / 2);

    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < m; i++)
            if (keys[i] != null) queue.enqueue(keys[i]);
        return queue;
    }

    public static void main(String[] args) {
        LinearProbingHashST<String, Integer> st = new LinearProbingHashST<>(16);
        String str = "SEARCHEXAMPLE";

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            st.put(String.valueOf(ch), i);
        }
        System.out.println(st);
    }

}


