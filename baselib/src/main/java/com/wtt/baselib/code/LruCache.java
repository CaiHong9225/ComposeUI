package com.wtt.baselib.code;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wangzhan on 2022/6/6
 * 利用Map存储Key + Node ,双向链表存储Lru元素 顺序由双向链表维护
 *
 * @descr
 */
class LruCache {
    private Map<Integer, DLinkNode> map = new HashMap<>();

    private int capacity = 0;
    private DLinkNode head;
    private DLinkNode tail;


    public LruCache(int capacity) {
        this.capacity = capacity;
        head = new DLinkNode();
        tail = new DLinkNode();
        head.next = tail;
        tail.pre = head;
    }


    public int get(int key){
        DLinkNode node = map.get(key);
        if (node == null){
            return -1;
        }
        //移动到链表头部
        removeToHead(node);
        return node.value;
    }

    public void put(int key,int value){
        DLinkNode dLinkNode = map.get(key);
        if(dLinkNode==null){
            dLinkNode = new DLinkNode(key,value);
            addToHead(dLinkNode);
            map.put(key,dLinkNode);
            if(map.size()>capacity){
                //移除最后一个
                DLinkNode removeNode = removeTail();
                map.remove(removeNode.key);
            }
        }else {
            dLinkNode.value = value;
            removeToHead(dLinkNode);
        }
    }

    /**
     * 移除当前节点 转移到头部
     * @param node
     */
    private void removeToHead(DLinkNode node) {
        //移除节点
        removeNode(node);
        //添加到头部
        addToHead(node);
    }

    //断链 当前Node
    private void removeNode(DLinkNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    private void addToHead(DLinkNode node) {
        node.pre = head;
        node.next = head.next;
        node.next.pre = node;
        head.next = node;
    }

    /**
     *  删除最后一个节点
     */
    private DLinkNode removeTail() {
        removeNode(tail.pre);
        return tail.pre;
    }

    static class DLinkNode {
        int key;
        int value;

        DLinkNode pre;
        DLinkNode next;

        public DLinkNode() {
        }

        public DLinkNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

}
