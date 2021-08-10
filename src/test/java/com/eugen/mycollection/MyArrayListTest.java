package com.eugen.mycollection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyArrayListTest {
    MyArrayList<String> myArrayList = null;

    @BeforeEach
    void prepareArray(){
        myArrayList = new MyArrayList<>();
        myArrayList.add("A");
        myArrayList.add("B");
        myArrayList.add("C");
    }

    @Test
    @DisplayName("Checking size() Method")
    void testSize(){
        assertEquals(myArrayList.size(), 3);
    }

    @Test
    @DisplayName("Checking isEmpty() Method")
    void testIsEmpty(){
        assertFalse(myArrayList.isEmpty());
    }

    @Test
    @DisplayName("Checking contains() Method")
    void testContains(){
        assertTrue(myArrayList.contains("A"));
    }


    @Test
    @DisplayName("Checking get() Method")
    void testGet(){
        assertEquals(myArrayList.get(0), "A");
    }

    @Test
    @DisplayName("Checking get() with index correct exception")
    void testGetNegative(){
        try {
            myArrayList.get(5);
        }catch(IndexOutOfBoundsException exception){
            assertEquals(exception.getMessage(), "Index out of bounds");
        }
    }

    @Test
    @DisplayName("Checking add() Method")
    void testAdd(){
        myArrayList.add("D");
        assertEquals(myArrayList.get(3), "D");
    }

    @Test
    @DisplayName("Checking add() by index Method")
    void testAddElementByIndex(){
        myArrayList.add("D", 1);
        assertEquals(myArrayList.get(1), "D");
        assertEquals(myArrayList.get(2), "B");
        assertEquals(myArrayList.get(0), "A");
    }

    @Test
    @DisplayName("Checking add() with index correct exception")
    void testAddElementByIndexNegative(){
        try {
            myArrayList.add("D", 5);
        }catch(IndexOutOfBoundsException exception){
            assertEquals(exception.getMessage(), "Index out of bounds");
        }
    }

    @Test
    @DisplayName("Checking set() Method")
    void testSet(){
        myArrayList.set("D", 0);
        assertEquals(myArrayList.get(0), "D");
        assertEquals(myArrayList.get(2), "C");

    }

    @Test
    @DisplayName("Checking set() correct exception is thrown")
    void testSetNegative(){
        try {
            myArrayList.set("D", 5);
        }catch(IndexOutOfBoundsException exception){
            assertEquals(exception.getMessage(), "Index out of bounds");
        }
    }

    @Test
    @DisplayName("Checking remove() Method")
    void testRemove(){
        myArrayList.remove("C");
        assertEquals(myArrayList.get(0), "A");
        assertEquals(myArrayList.get(1), "B");
        assertNull(myArrayList.get(2));
    }

    @Test
    @DisplayName("Checking clear() Method")
    void testClear(){
        myArrayList.clear();
        assertTrue(myArrayList.isEmpty());
    }


    @Test
    @DisplayName("Checking indexOff() Method")
    void testIndexOff(){
        assertEquals(myArrayList.indexOf("C"), 2);
    }

    @Test
    @DisplayName("Checking lastIndexOff() Method")
    void testLastIndexOff(){
        myArrayList.add("A");
        assertEquals(myArrayList.lastIndexOf("A"), 3);
    }

    @Test
    @DisplayName("Checking toString() Method")
    void tesToString(){
        assertEquals(myArrayList.toString(), "A,B,C");
    }

    @Test
    @DisplayName("Checking hasNext() Iterator Method")
    void tesIteratorHasNext(){
        MyArrayList.CustomIterator customIterator =  myArrayList.new CustomIterator(0);
        assertTrue(customIterator.hasNext());
    }

    @Test
    @DisplayName("Checking hasNext() Iterator Method")
    void tesIteratorHasNextNegative() {
        MyArrayList.CustomIterator customIterator = myArrayList.new CustomIterator(2);
        assertFalse(customIterator.hasNext());
    }
    @Test
    @DisplayName("Checking hasNext() exceptionCatch")
    void tesIteratorHasNextException() {
        try{
            MyArrayList.CustomIterator customIterator = myArrayList.new CustomIterator(5);
        }catch (IndexOutOfBoundsException exception){
            assertEquals(exception.getMessage(), "Index out of bounds");
        }
    }

    @Test
    @DisplayName("Checking Iterator remove()")
    void tesIteratorRemove() {
        MyArrayList.CustomIterator customIterator = myArrayList.new CustomIterator(1);
        customIterator.remove();
        assertEquals(myArrayList.get(0), "A");
        assertEquals(myArrayList.get(1), "C");

    }

    @Test
    @DisplayName("Checking Iterator next()")
    void tesIteratorNext() {
        MyArrayList.CustomIterator customIterator = myArrayList.new CustomIterator(0);
        customIterator.next();
        assertEquals(myArrayList.get(customIterator.index), "B");

    }

}
