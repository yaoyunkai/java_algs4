package com.libyao.leetcode.t0021;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}


/*
将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 

 */
class Solution {

    /*
    如果 l1 或者 l2 一开始就是空链表 ，那么没有任何操作需要合并，所以我们只需要返回非空链表。
    否则，我们要判断 l1 和 l2 哪一个链表的头节点的值更小，然后递归地决定下一个添加到结果里的节点。如果两个链表有一个为空，递归结束。

    
     */
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists1(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists1(l1, l2.next);
            return l2;
        }
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null) return null;

        ListNode root = new ListNode();
        ListNode last = null;

        ListNode leftNode = list1;
        ListNode rightNode = list2;

        while (leftNode != null || rightNode != null) {
            int tmp;

            if (leftNode != null && rightNode != null) {
                tmp = Math.min(leftNode.val, rightNode.val);
                if (tmp == leftNode.val) {
                    leftNode = leftNode.next;
                } else {
                    rightNode = rightNode.next;
                }
                ListNode node = new ListNode(tmp);
                if (last != null) {
                    last.next = node;
                } else {
                    root.next = node;
                }
                last = node;

            } else if (leftNode != null) {
                tmp = leftNode.val;
                leftNode = leftNode.next;
                ListNode node = new ListNode(tmp);
                if (last != null) {
                    last.next = node;
                } else {
                    root.next = node;
                }
                last = node;

            } else {
                tmp = rightNode.val;
                rightNode = rightNode.next;
                ListNode node = new ListNode(tmp);
                if (last != null) {
                    last.next = node;
                } else {
                    root.next = node;
                }
                last = node;
            }


        }

        return root.next;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(1);

        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(3);
        ListNode node6 = new ListNode(1);

        node3.next = node2;
        node2.next = node1;

        node6.next = node5;
        node5.next = node4;

        ListNode listNode = new Solution().mergeTwoLists(node3, node6);

    }
}
