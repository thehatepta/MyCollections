package com.eugen.mycollection;

import java.util.*;
import java.util.function.Consumer;

public class MyArrayList<V> implements List<V>{

    private static final int DEFAULT_CAPACITY = 10;

    V list[];
    int size = 0;
    int capacity;

    MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    MyArrayList(int capacity) {
        list =(V[]) new Object[capacity];
        this.capacity = capacity;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public void add(V o) {

        if (list.length == size) {
            int newSize = (int) (size * 1.5);
            Object newArrayOfElements = new Object[newSize];
            System.arraycopy(list, 0, newArrayOfElements, 0, size);
        }
        list[size] = o;
        size++;
    }

    @Override
    public void add(V o, int index) {
        checkForIndexBonds(index);
        System.arraycopy(list, index, list, index + 1, size - index);
        list[index] = o;
        size++;

    }

    @Override
    public void remove(int index) {
        checkForIndexBonds(index);
        System.arraycopy(list, index + 1, list, index, size - index);
        list[size - 1] = null;
        size--;
    }

    @Override
    public V get(int index) {
        checkForIndexBonds(index);
        return list[index];
    }

    @Override
    public void set(V o, int index) {
        checkForIndexBonds(index);
        list[index] = o;

    }

    @Override
    public void clear() {
        list = (V[])new Object[DEFAULT_CAPACITY];
        this.capacity = DEFAULT_CAPACITY;
        size = 0;
    }

    public void remove(V o) {
        int index = 0;
        for (Object object :
                list) {
            if (object != null && object.equals(o)) {
                System.arraycopy(list, index + 1, list, index, size);
            }
            index++;
        }
    }

    @Override
    public boolean contains(V o) {
        return indexOf(o) != -1;
    }

    @Override
    public int indexOf(V o) {
        int index = 0;
        for (V v :
                list) {
            if (v != null && v.equals(o)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(V o) {
        int lastIndex = -1;

        for (int i = 0; i < size; i++) {
            if (list[i] != null && list[i].equals(o)) {
                lastIndex = i;
            }
        }
        return lastIndex;
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(",");
        for (V v :
                list) {
            if (v != null) {
                stringJoiner.add(v.toString());
            }

        }
        return stringJoiner.toString();
    }

    private void checkForIndexBonds(int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }

    private class CustomIterator<v> implements Iterator<V> {

        int index = -1;
        boolean canBeRemoved = false;

        @Override
        public boolean hasNext() {
            return index < size-1;
        }

        @Override
        public V next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            index++;
            canBeRemoved = true;
            return list[index];
        }

        @Override
        public void remove() {
            if(!canBeRemoved){
                throw new IllegalStateException();
            }
            MyArrayList.this.remove(index);
        }

        @Override
        public void forEachRemaining(Consumer<? super V> action) {
            Iterator.super.forEachRemaining(action);
        }
    }


}
