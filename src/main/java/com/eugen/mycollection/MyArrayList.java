package com.eugen.mycollection;

import java.util.StringJoiner;

public class MyArrayList implements List {

    private static final int DEFAULT_CAPACITY = 10;

    Object list[];
    int size = 0;
    int capacity = 0;

    MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    MyArrayList(int capacity) {
        list = new Object[capacity];
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
    public void add(Object o) {

        if (list.length == size) {
            int newSize = (int) (size * 1.5);
            Object newArrayOfElements = new Object[newSize];
            System.arraycopy(list, 0, newArrayOfElements, 0, size);
        }
        list[size] = o;
        size++;
    }

    @Override
    public void add(Object o, int index) {
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
    public Object get(int index) {
        checkForIndexBonds(index);
        return list[index];
    }

    @Override
    public void set(Object o, int index) {
        checkForIndexBonds(index);
        list[index] = o;

    }

    @Override
    public void clear() {
        list = new Object[DEFAULT_CAPACITY];
        this.capacity = DEFAULT_CAPACITY;
        size = 0;
    }

    public void remove(Object o) {
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
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        for (Object object :
                list) {
            if (object != null && object.equals(o)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
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
        for (Object object :
                list) {
            if (object != null) {
                stringJoiner.add(object.toString());
            }

        }
        return stringJoiner.toString();
    }

    private void checkForIndexBonds(int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException();
        }
    }

}
