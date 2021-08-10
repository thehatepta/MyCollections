package com.eugen.mycollection;


import java.util.NoSuchElementException;
import java.util.StringJoiner;


public class MyLinkedList<V> implements List<V>{

    int size = 0;
    LinkedListNode head= null;
    LinkedListNode tail = null;


    MyLinkedList(){

    }

    @Override
    public void add(V value) {
        if(head == null){
            head = tail = new LinkedListNode(value);
            size ++;
        }else{
            LinkedListNode linkedListNode = new LinkedListNode(tail, value, null );
            tail.next = linkedListNode;
            tail = linkedListNode;
            size++;
        }

    }

    @Override
    public void add(V value, int index) {
        checkForIndexBonds(index);
        LinkedListNode linkedListNodeIndex = findNodeByIndex(index);
        LinkedListNode newLinkedListNode = new LinkedListNode(linkedListNodeIndex.prev, value, linkedListNodeIndex);
        if(linkedListNodeIndex.prev == null){
            head = newLinkedListNode;
        }else{
            linkedListNodeIndex.prev.next = newLinkedListNode;
        }

        linkedListNodeIndex.prev = newLinkedListNode;
        size++;
    }

    @Override
    public void remove(int index) {
        checkForIndexBonds(index);
        remove(findNodeByIndex(index));
    }

    public void remove(LinkedListNode linkedListNode) {
        if(size == 1){
            head=tail=null;
        }else if(linkedListNode.prev == null){
            head.next.prev = null;
            head = head.next;
        }else if(linkedListNode.next == null){
            tail.prev.next = null;
            tail = tail.prev;
        }else{
            linkedListNode.next.prev = linkedListNode.prev;
            linkedListNode.prev.next = linkedListNode.next;
            linkedListNode.next = null;
            linkedListNode.prev = null;
        }

        size --;
    }

    @Override
    public V get(int index) {
        return findNodeByIndex(index).value;
    }

    @Override
    public void set(V value, int index) {
        checkForIndexBonds(index);
        LinkedListNode linkedListNode = findNodeByIndex(index);
        LinkedListNode newLinkedListNode = new LinkedListNode(linkedListNode.prev, value, linkedListNode.next);
        linkedListNode.prev.next = linkedListNode.next.prev = newLinkedListNode;
    }


    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return !(size > 0);
    }

    @Override
    public boolean contains(V value) {
        return indexOf(value) > -1;
    }

    @Override
    public int indexOf(V value) {
        LinkedListNode linkedListNode = head;
        if(value == null){
            for(int i = 0; i < size; i++) {
                if (linkedListNode.value == null) {
                     return i;
                }
                linkedListNode = linkedListNode.next;
            }
        }else{
            for(int i = 0; i < size; i++){
                if(value.equals(linkedListNode.value)){
                    return i;
                }
                linkedListNode = linkedListNode.next;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(V value) {
        int index = -1;
        LinkedListNode linkedListNode = head;
        if(value == null){
            for(int i = 0; i < size; i++) {
                if (linkedListNode.value == null) {
                    index = i;
                }
                linkedListNode = linkedListNode.next;
            }
        }else{
            for(int i = 0; i < size; i++){
                if(value.equals(linkedListNode.value)){
                    index = i;
                }
                linkedListNode = linkedListNode.next;
            }
        }

        return index;
    }

    public class CustomIterator{

        private LinkedListNode next;
        private LinkedListNode prev;
        int index = -1;


        public boolean hasNext() {
            return index < size-1;
        }

        public CustomIterator(int index) {
            checkForIndexBonds(index);
            prev = findNodeByIndex(index);
            next = prev.next;
            this.index = index;

        }

        public V next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            prev = next;
            next = next.next;
            index++;
            return prev.value;
        }

        public void remove() {
            if(prev == null){
                throw new IllegalStateException();
            }
            MyLinkedList.this.remove(index);
            prev = null;
        }
    }



    private LinkedListNode findNodeByIndex(int index) {
        LinkedListNode linkedListNode;
        if (index < (size / 2)) {
            linkedListNode = head;
            for (int i = 0; i < index; i++) {
                linkedListNode = linkedListNode.next;
            }
            return linkedListNode;

        } else {
            linkedListNode = tail;
            for (int i = size - 1; i > index; i--) {
                linkedListNode = linkedListNode.prev;
            }
            return linkedListNode;
        }
    }

    private void checkForIndexBonds(int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(",");
        LinkedListNode node = head;
        while(node != null){
            stringJoiner.add(node.value.toString());
            node = node.next;
        }
        return stringJoiner.toString();
    }

    public class LinkedListNode {

        V value;
        LinkedListNode next;
        LinkedListNode prev;

        LinkedListNode(V value){
            this(value, null);

        }

        LinkedListNode(V value, LinkedListNode next){
            this(null, value, next);

        }

        LinkedListNode(LinkedListNode prev, V value, LinkedListNode next){
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }

}
