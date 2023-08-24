package com.libyao.algs4.chp2;

import edu.princeton.stdlib.In;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/*
优先队列  topN 问题

优先队列最重要的操作就是删除最大元素和插入元素。

堆的定义:

---- 堆和树, 完全二叉树

在二叉堆的数组中，每个元素都要保证大于等于另两个特定位置的元素。
相应地，这些位置的元素又至少要大于等于数组中的另两个元素，以此类推。

当一棵二叉树的每个结点都大于等于它的两个子结点时，它被称为堆有序。

根结点是堆有序的二叉树中的最大结点。

完全二叉树: 可以由数组来表示
完全二叉树只用数组而不需要指针就可以表示。具体方法就是将二叉树的结点按照层级顺序放入数组中，
根结点在位置1，它的子结点在位置2和3，而子结点的子结点则分别在位置4、5、6和7，以此类推。


结点的位置关系(不使用第一个数组元素)
k的父节点为 k/2
k的子结点为 2k 2k+1

一棵大小为N的完全二叉树的高度为[lgN]。

我们用长度为N+1的私有数组pq[]来表示一个大小为N的堆，我们不会使用pq[0]，堆元素放在pq[1]至pq[N]中。

---------------------------------------------------------------

在有序化的过程中我们会遇到两种情况。
当某个结点的优先级上升（或是在堆底加入一个新的元素）时，我们需要由下至上恢复堆的顺序。
    上浮: 和父结点比较大小, 交换位置,
    但这个结点仍然可能比它现在的父结点更大。
    我们可以一遍遍地用同样的办法恢复秩序，将这个结点不断向上移动直到我们遇到了一个更大的父结点。

当某个结点的优先级下降（例如，将根结点替换为一个较小的元素）时，我们需要由上至下恢复堆的顺序
    如果堆的有序状态因为某个结点变得比它的两个子结点或是其中之一更小了而被打破了，
    那么我们可以通过将它和它的两个子结点中的较大者交换来恢复堆。
    交换可能会在子结点处继续打破堆的有序状态，
    因此我们需要不断地用相同的方式将其修复，将结点向下移动直到它的子结点都比它更小或是到达了堆的底部。
    


插入元素。我们将新元素加到数组末尾，增加堆的大小并让这个新元素上浮到合适的位置
删除最大元素。我们从数组顶端删去最大的元素并将数组的最后一个元素放到顶端，减小堆的大小并让这个元素下沉到合适的位置


----    元素的不可变性


 */
public class MaxPQ<E extends Comparable<E>> implements Iterable<E> {
    private E[] pq;
    private int n;

    public MaxPQ(int capacity) {
        pq = (E[]) new Comparable[capacity + 1];
        n = 0;
    }

    public MaxPQ() {
        this(1);
    }

    public MaxPQ(E[] keys) {
        n = keys.length;
        pq = (E[]) new Comparable[keys.length + 1];
        for (int i = 0; i < n; i++)
            pq[i + 1] = keys[i];

        // 从中间往前开始sink 即可构建堆
        for (int k = n / 2; k >= 1; k--)
            sink(k);
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public E max() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }

    public void insert(E x) {
        if (n == pq.length - 1) resize(2 * pq.length);
        pq[++n] = x;
        swim(n);
    }

    public E delMax() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        E max = pq[1];
        exch(1, n--);
        sink(1);
        pq[n + 1] = null;
        if ((n > 0) && (n == (pq.length - 1) / 4)) resize(pq.length / 2);
        return max;
    }

    private boolean isMaxHeap() {
        for (int i = 1; i <= n; i++) {
            if (pq[i] == null) return false;
        }
        for (int i = n + 1; i < pq.length; i++) {
            if (pq[i] != null) return false;
        }
        if (pq[0] != null) return false;
        return isMaxHeapOrdered(1);
    }

    // is subtree of pq[1..n] rooted at k a max heap?
    private boolean isMaxHeapOrdered(int k) {
        if (k > n) return true;
        int left = 2 * k;
        int right = 2 * k + 1;
        if (left <= n && less(k, left)) return false;
        if (right <= n && less(k, right)) return false;
        return isMaxHeapOrdered(left) && isMaxHeapOrdered(right);
    }

    private void resize(int capacity) {
        assert capacity > n;
        E[] temp = (E[]) new Comparable[capacity];
        for (int i = 1; i <= n; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            // 如果不是最后一个选择子结点中较大的，如果是最后一个直接和k比较
            if (j < n && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        E swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    @Override
    public Iterator<E> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<E> {

        // create a new pq
        private final MaxPQ<E> copy;

        // add all items to copy of heap
        // takes linear time since already in heap order so no keys move
        public HeapIterator() {
            copy = new MaxPQ<E>(size());
            for (int i = 1; i <= n; i++)
                copy.insert(pq[i]);
        }

        public boolean hasNext() {
            return !copy.isEmpty();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.delMax();
        }
    }

    public static void main(String[] args) {
        In in = new In("data/tinyText.txt");
        Integer[] allowlist = in.readAllIntegers();
        MaxPQ<Integer> pq = new MaxPQ<>(allowlist);
        // System.out.println(pq);

        pq.insert(100);
        Integer i = pq.delMax();
        System.out.println(i);
        // System.out.println(Arrays.toString(pq.pq));

    }

}
