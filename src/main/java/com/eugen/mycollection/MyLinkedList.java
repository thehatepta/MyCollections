package com.eugen.mycollection;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;
import java.util.function.Consumer;

public class MyLinkedList<V> implements List<V>{

    int size = 0;
    Node <V> head= null;
    Node <V> tail = null;


    MyLinkedList(){

    }

    @Override
    public void add(V value) {
        if(head == null){
            head = tail = new Node<>(value);
            size ++;
        }else{
            Node<V> node = new Node<>(tail, value, null );
            tail.next = node;
            tail = node;
            size++;
        }

    }

    @Override
    public void add(V value, int index) {
        checkForIndexBonds(index);
        Node<V> nodeIndex = findNodeByIndex(index);
        Node<V> newNode = new Node<>(nodeIndex.prev, value, nodeIndex);
        if(nodeIndex.prev == null){
            head = newNode;
        }else{
            nodeIndex.prev.next = newNode;
        }

        nodeIndex.prev = newNode;
        size++;
    }

    @Override
    public void remove(int index) {
        checkForIndexBonds(index);
        remove(findNodeByIndex(index));
    }

    public void remove(Node<V> node) {
        if(size == 1){
            head=tail=null;
        }else if(node.prev == null){
            head.next.prev = null;
            head = head.next;
        }else if(node.next == null){
            tail.prev.next = null;
            tail = tail.prev;
        }else{
            node.next = null;
            node.prev = null;
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
        Node<V> node = findNodeByIndex(index);
        Node<V> newNode = new Node<>(node.prev, value, node.next);
        node.prev.next = node.next.prev = newNode;
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
        Node<V> node = head;
        if(value == null){
            for(int i = 0; i < size; i++) {
                if (node.value == null) {
                     return i;
                }
                node = node.next;
            }
        }else{
            for(int i = 0; i < size; i++){
                if(value.equals(node.value)){
                    return i;
                }
                node = node.next;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(V value) {
        int index = -1;
        Node<V> node = head;
        if(value == null){
            for(int i = 0; i < size; i++) {
                if (node.value == null) {
                    index = i;
                }
                node = node.next;
            }
        }else{
            for(int i = 0; i < size; i++){
                if(value.equals(node.value)){
                    index = i;
                }
                node = node.next;
            }
        }

        return index;
    }

    private class CustomIterator<V> implements Iterator<V> {

        private MyLinkedList.Node next;
        private Node<V> prev;
        int index = -1;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        private CustomIterator(int index){
            if(index >= 0){
               next = findNodeByIndex(index);
                this.index = index;
            }
        }
        @Override
        public V next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }

            prev = next;
            next = next.next;
            index++;
            return prev.value;
        }

        @Override
        public void remove() {
            if(prev == null){
                throw new IllegalStateException();
            }
            MyLinkedList.this.remove(index);
            prev = null;
        }

        @Override
        public void forEachRemaining(Consumer<? super V> action) {
            Iterator.super.forEachRemaining(action);
        }
    }

    private class Node<V> {

        V value;
        Node <V> next;
        Node <V> prev;

        Node(V value){
            this(value, null);

        }

        Node(V value, Node<V> next){
            this(null, value, next);

        }

        Node(Node<V> prev, V value, Node<V> next){
            this.value = value;
            this.next = next;
            this.prev = prev;
        }


    }

    Node<V> findNodeByIndex(int index) {
        Node<V>  node;
        if (index < (size / 2)) {
            node = head;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;

        } else {
            node = tail;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
            return node;
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
        CustomIterator<V> customIterator = new CustomIterator<>(0);
        while(customIterator.hasNext()){
            stringJoiner.add(customIterator.next().toString());
        }
        return stringJoiner.toString();
    }
}
