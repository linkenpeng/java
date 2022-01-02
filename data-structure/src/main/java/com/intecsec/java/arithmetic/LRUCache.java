package com.intecsec.java.arithmetic;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: peter.peng
 * @create: 2022-01-02 22:51
 **/
public class LRUCache {

    class DoubleLinkedNode {
        int key;
        int value;
        DoubleLinkedNode pre;
        DoubleLinkedNode next;
        DoubleLinkedNode () {

        }
        DoubleLinkedNode(int _key, int _value) {
            key = _key;
            value = _value;
        }
    }

    int capacity;
    int size;
    DoubleLinkedNode head, tail;
    Map<Integer, DoubleLinkedNode> cache = new HashMap<>();

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        head = new DoubleLinkedNode();
        tail = new DoubleLinkedNode();
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        DoubleLinkedNode node = cache.get(key);
        if(node == null) {
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    public void moveToHead(DoubleLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    public void removeNode(DoubleLinkedNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    public void addToHead(DoubleLinkedNode node) {
        node.pre = head;
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
    }

    public DoubleLinkedNode removeTail() {
        DoubleLinkedNode node = tail.pre;
        removeNode(node);
        return node;
    }

    public void put(int key, int val) {
        DoubleLinkedNode exists = cache.get(key);
        if(exists == null) {
            DoubleLinkedNode putNode = new DoubleLinkedNode(key, val);
            cache.put(key, putNode);
            addToHead(putNode);
            ++size;
            if(size > capacity) {
                DoubleLinkedNode node = removeTail();
                cache.remove(node.key);
                --size;
            }
        } else {
            exists.value = val;
            moveToHead(exists);
        }
    }
}
