package com.libyao.algs4.chp3;

import com.libyao.algs4.chp1.Queue;
import edu.princeton.stdlib.StdOut;

import java.util.NoSuchElementException;

/*
一棵二叉查找树（BST）是一棵二叉树，
其中每个结点都含有一个Comparable的键（以及相关联的值）
且每个结点的键都大于其左子树中的任意结点的键而小于右子树的任意结点的键。

递归：
可以将递归调用前的代码想象成沿着树向下走：它会将给定的键和每个结点的键相比较并根据结果向左或者向右移动到下一个结点。
然后可以将递归调用后的代码想象成沿着树向上爬

非递归实现: get1 put1

--------------------------------------------------------------

前序遍历
中序遍历:
    我们应该先打印出根结点的左子树中的所有键（根据二叉查找树的定义它们应该都小于根结点的键）
    然后打印出根结点的键，
    最后打印出根结点的右子树中的所有键（根据二叉查找树的定义它们应该都大于根结点的键）


深度优先
广度优先

 */
public class BST<Key extends Comparable<Key>, Value> {
    private Node root = null;

    private class Node {
        private final Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees
        private int size;          // number of nodes in subtree

        public Node(Key key, Value val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    public BST() {
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.size;
    }

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    public Value get1(Key key) {
        // 非递归版本
        // 退出循环的条件: 结点为null, key == k
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");
        Node x = root;

        while (true) {
            if (x == null) {
                return null;
            }
            int cmp = key.compareTo(x.key);

            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.val;
        }
    }

    private Value get(Node x, Key key) {
        /*
        先看node == null
        再看node.key == key  -> return 
        
        < node.key  -> node.left
        > node.right -> node.right        
        
         */
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }

    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("calls put() with a null key");
        if (val == null) {
            delete(key);
            return;
        }
        root = put(root, key, val);
        // assert check();
    }

    public void put1(Key key, Value val) {

        // 非递归版本不能维护size
        if (key == null) throw new IllegalArgumentException("calls put() with a null key");
        if (val == null) {
            delete(key);
            return;
        }

        Node newNode = new Node(key, val, 1);

        if (root == null) {
            root = newNode;
            return;
        }

        Node parent = null, x = root;
        while (x != null) {
            parent = x;
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else {
                x.val = val;
                return;
            }
        }
        int cmp = key.compareTo(parent.key);
        if (cmp < 0) parent.left = newNode;
        else parent.right = newNode;
    }

    private Node put(Node x, Key key, Value val) {
        /*
        最终 x != null 还是返回x
        x == null 构造一个新的结点
        如果子树不为null会一直向下寻找, 比较每个子树的key
        
        key == k  update v
        key < k  x.left
        key > k  x.right 
        
         */

        if (x == null) {
            return new Node(key, val, 1);
        }

        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
        root = deleteMin(root);
        // assert check();
    }

    private Node deleteMin(Node x) {
        /*
        对于deleteMin()，我们要不断深入根结点的左子树中直至遇见一个空链接，
        然后将指向该结点的链接指向该结点的右子树（只需要在递归调用中返回它的右链接即可）
         */
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
        root = deleteMax(root);
        // assert check();
    }

    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("calls delete() with a null key");
        root = delete(root, key);
        // assert check();
    }

    private Node delete(Node x, Key key) {
        /*
        对于只有一个子结点的删除方式，类似于 deleteMin or deleteMax
        当找到某个结点时, 子结点可能得情况 left right all null
                                      left right all not null  对于1,2种情况 if 一定成立
                                      left or right null
        
        对于有两个子结点的删除方式:
        在删除结点x后用它的后继结点填补它的位置。
        因为x有一个右子结点，因此它的后继结点就是其右子树中的最小结点。
        这样的替换仍然能够保证树的有序性，因为x.key和它的后继结点的键之间不存在其他的键。
        
        */

        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            // 只有一个子结点的情况, 巧妙的排他法
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;

            // 在右子树中寻找最小的, 删除最小的，并把这个最小的填补到当前x的位置
            Node t = x;

            // 现在x为右子树中最小的结点
            x = min(t.right);

            // 现在 t.right 看作root, 因为最小结点即将变换位置, 所以删除他 从右子树中移除
            // 然后把右子树放回到 x.right
            x.right = deleteMin(t.right);

            // 连上要被删除的结点的左结点
            x.left = t.left;
        }
        x.size = size(x.left) + size(x.right) + 1;
        // important
        return x;
    }


    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        else return min(x.left);
    }

    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        else return max(x.right);
    }

    public Key floor(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to floor() is null");
        if (isEmpty()) throw new NoSuchElementException("calls floor() with empty symbol table");
        Node x = floor(root, key);
        if (x == null) throw new NoSuchElementException("argument to floor() is too small");
        else return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        Node t = floor(x.right, key);

        if (t != null) return t;
        else return x;
    }

    public Key floor2(Key key) {
        Key x = floor2(root, key, null);
        if (x == null) throw new NoSuchElementException("argument to floor() is too small");
        else return x;

    }

    private Key floor2(Node x, Key key, Key best) {
        if (x == null) return best;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return floor2(x.left, key, best);
        else if (cmp > 0) return floor2(x.right, key, x.key);
        else return x.key;
    }

    public Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
        if (isEmpty()) throw new NoSuchElementException("calls ceiling() with empty symbol table");
        Node x = ceiling(root, key);
        if (x == null) throw new NoSuchElementException("argument to ceiling() is too large");
        else return x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) {
            Node t = ceiling(x.left, key);
            if (t != null) return t;
            else return x;
        }
        return ceiling(x.right, key);
    }

    public Key select(int rank) {
        if (rank < 0 || rank >= size()) {
            throw new IllegalArgumentException("argument to select() is invalid: " + rank);
        }
        return select(root, rank);
    }

    // Return key in BST rooted at x of given rank.
    // Precondition: rank is in legal range.
    private Key select(Node x, int rank) {
        if (x == null) return null;
        int leftSize = size(x.left);
        if (leftSize > rank) return select(x.left, rank);
        else if (leftSize < rank) return select(x.right, rank - leftSize - 1);
        else return x.key;
    }

    public int rank(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to rank() is null");
        return rank(key, root);
    }

    // Number of keys in the subtree less than key.
    private int rank(Key key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
    }

    public Iterable<Key> keys() {
        if (isEmpty()) return new Queue<Key>();
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }

    public int size(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to size() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to size() is null");

        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else return rank(hi) - rank(lo);
    }

    public int height() {
        return height(root);
    }

    private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }

    public Iterable<Key> levelOrder() {
        Queue<Key> keys = new Queue<Key>();
        Queue<Node> queue = new Queue<Node>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node x = queue.dequeue();
            if (x == null) continue;
            keys.enqueue(x.key);
            queue.enqueue(x.left);
            queue.enqueue(x.right);
        }
        return keys;
    }

    private boolean check() {
        if (!isBST()) StdOut.println("Not in symmetric order");
        if (!isSizeConsistent()) StdOut.println("Subtree counts not consistent");
        if (!isRankConsistent()) StdOut.println("Ranks not consistent");
        return isBST() && isSizeConsistent() && isRankConsistent();
    }

    private boolean isBST() {
        return isBST(root, null, null);
    }

    private boolean isBST(Node x, Key min, Key max) {
        if (x == null) return true;
        if (min != null && x.key.compareTo(min) <= 0) return false;
        if (max != null && x.key.compareTo(max) >= 0) return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }

    private boolean isSizeConsistent() {
        return isSizeConsistent(root);
    }

    private boolean isSizeConsistent(Node x) {
        if (x == null) return true;
        if (x.size != size(x.left) + size(x.right) + 1) return false;
        return isSizeConsistent(x.left) && isSizeConsistent(x.right);
    }

    private boolean isRankConsistent() {
        for (int i = 0; i < size(); i++)
            if (i != rank(select(i))) return false;
        for (Key key : keys())
            if (key.compareTo(select(rank(key))) != 0) return false;
        return true;
    }

    public void prettyPrint() {
        prettyPrint(root, "", true);
    }

    // 递归打印节点及其子树
    private void prettyPrint(Node node, String prefix, boolean isLeft) {
        if (node != null) {
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + node.key);

            // 递归打印右子树，如果有的话
            prettyPrint(node.right, prefix + (isLeft ? "│   " : "    "), false);

            // 递归打印左子树，如果有的话
            prettyPrint(node.left, prefix + (isLeft ? "│   " : "    "), true);
        }
    }

    public static void main(String[] args) {
        // a c e h r s x

        BST<Integer, String> st = new BST<>();
        st.put(23, "a");
        st.put(12, "a");
        st.put(45, "abc");
        st.put(11, "a");
        st.put(16, "a");
        st.put(22, "a");
        st.put(26, "a");
        st.put(5, "a");
        st.put(67, "ggc");
        st.put(8, "a");
        st.put(44, "a");
        st.put(13, "a");
        st.put(14, "a");
        // st.delete(16);

        // st.deleteMin();
        // st.prettyPrint();

        Iterable<Integer> keys = st.keys();
        System.out.println(keys);

    }

}
