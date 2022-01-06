package com.intecsec.java.arithmetic;

import java.util.*;

/**
 * @description: 打印树
 * @author: peter.peng
 * @create: 2022-01-02 18:59
 **/
public class TreePrint {

    public static void main(String[] args) {
        // [3,9,20,10,null,15,7],
        TreeNode root = new TreeNode(3);
        TreeNode l1 = new TreeNode(9);
        TreeNode r1 = new TreeNode(20);
        TreeNode l11 = new TreeNode(10);
        l1.left = l11;
        TreeNode l2 = new TreeNode(15);
        TreeNode r2 = new TreeNode(7);
        root.left = l1;
        root.right = r1;
        r1.left = l2;
        r1.right = r2;

        printTopToBottom(root);
        printTopToBottomLine(root);
        printTopToBottomLineReverse(root);
    }

    public static void printTopToBottom(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if(cur != null) {
                list.add(cur.val);
                if(cur.left != null) {
                    queue.add(cur.left);
                }
                if(cur.right != null) {
                    queue.add(cur.right);
                }
            }
        }

        PrintUtil.printMsg(list);
    }

    public static void printTopToBottomLine(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList();
        if(root != null) {
            queue.add(root);
        }

        List<List<Integer>> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            List<Integer> levelList = new ArrayList<>();
            for(int i = queue.size(); i > 0; --i) {
                TreeNode cur = queue.poll();
                if(cur != null) {
                    levelList.add(cur.val);
                    if(cur.left != null) {
                        queue.add(cur.left);
                    }
                    if(cur.right != null) {
                        queue.add(cur.right);
                    }
                }
            }

            list.add(levelList);

            PrintUtil.printMsg(levelList);
        }

    }

    public static void printTopToBottomLineReverse(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList();
        if(root != null) {
            queue.add(root);
        }

        List<List<Integer>> list = new ArrayList<>();
        int j = 0;
        while (!queue.isEmpty()) {
            LinkedList<Integer> levelList = new LinkedList<>();
            for(int i = queue.size(); i > 0; --i) {
                TreeNode cur = queue.poll();
                if(cur != null) {
                    if(j % 2 == 0) {
                        levelList.addFirst(cur.val);
                    } else {
                        levelList.addLast(cur.val);
                    }
                    if(cur.left != null) {
                        queue.add(cur.left);
                    }
                    if(cur.right != null) {
                        queue.add(cur.right);
                    }
                }
            }

            list.add(levelList);

            PrintUtil.printMsg(levelList);
        }
    }

    // 二叉搜索树转链表
    class TreeToDoublyList {
        TreeNode pre, head;
        public TreeNode treeToDoublyList(TreeNode root) {
            if(root == null) return null;
            dfs(root);
            head.left = pre;
            pre.right = head;
            return head;
        }
        void dfs(TreeNode cur) {
            if(cur == null) return;
            dfs(cur.left);
            if(pre != null) pre.right = cur;
            else head = cur;
            cur.left = pre;
            pre = cur;
            dfs(cur.right);
        }
    }

    // 找到倒排的第K大数据
    class Solution {
        int res, k;
        public int kthLargest(TreeNode root, int k) {
            this.k = k;
            dfs(root);
            return res;
        }
        void dfs(TreeNode root) {
            if(root == null) return;
            dfs(root.right);
            if(k == 0) return;
            if(--k == 0) res = root.val;
            dfs(root.left);
        }
    }

}


