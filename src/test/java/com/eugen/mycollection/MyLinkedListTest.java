package com.eugen.mycollection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyLinkedListTest {
    MyLinkedList<String> myLinkedList = null;

    @BeforeEach
    void prepareArray() {
        myLinkedList = new MyLinkedList<>();
        myLinkedList.add("A");
        myLinkedList.add("B");
        myLinkedList.add("C");
    }

    @Test
    void testAdd() {
        myLinkedList.add("D");
        assertEquals(myLinkedList.get(3), "D");
    }

    @Test
    void testGet() {
        assertEquals(myLinkedList.get(0), "A");
    }

    @Test
    void testSize() {
        assertEquals(myLinkedList.size(), 3);
    }

    @Test
    void testIsEmpty() {
        assertFalse(myLinkedList.isEmpty());
    }

    @Test
    void testContains() {
        assertTrue(myLinkedList.contains("A"));
    }

    @Test
    void testAddElementByIndex() {
        myLinkedList.add("D", 1);
        assertEquals(myLinkedList.get(1), "D");
        assertEquals(myLinkedList.get(0), "A");
        assertEquals(myLinkedList.get(2), "B");
    }

    @Test
    void testAddByIndexNegative() {
        try {
            myLinkedList.add("D", 5);
        }catch(IndexOutOfBoundsException exception){
            assertEquals(exception.getMessage(), "Index out of bounds");
        }
    }

    @Test
    void testSet() {
        myLinkedList.set("D", 1);
        assertEquals(myLinkedList.get(1), "D");
        assertEquals(myLinkedList.get(0), "A");
        assertEquals(myLinkedList.get(2), "C");
    }

    @Test
    void testSetNegative() {
        try {
            myLinkedList.set("D", 5);
        }catch(IndexOutOfBoundsException exception){
            assertEquals(exception.getMessage(), "Index out of bounds");
        }
    }

    @Test
    void testRemove() {
        myLinkedList.remove(0);
        assertEquals(myLinkedList.get(0), "B");
        assertEquals(myLinkedList.get(1), "C");
        myLinkedList.add("D");
        assertEquals(myLinkedList.get(2), "D");
        myLinkedList.remove(2);
        assertEquals(myLinkedList.get(0), "B");
        assertEquals(myLinkedList.get(1), "C");
        myLinkedList.add("D");
        assertEquals(myLinkedList.get(2), "D");
        myLinkedList.remove(1);
        assertEquals(myLinkedList.get(0), "B");
        assertEquals(myLinkedList.get(1), "D");
    }

    @Test
    void testClear() {
        myLinkedList.clear();
        assertTrue(myLinkedList.isEmpty());
    }


    @Test
    @DisplayName("Checking indexOff() Method")
    void testIndexOff() {
        assertEquals(myLinkedList.indexOf("C"), 2);
    }

    @Test
    @DisplayName("Checking lastIndexOff() Method")
    void testLastIndexOff() {
        myLinkedList.add("A");
        assertEquals(myLinkedList.lastIndexOf("A"), 3);
    }

    @Test
    @DisplayName("Checking toString() Method")
    void tesToString() {
        assertEquals(myLinkedList.toString(), "A,B,C");

    }

    @Test
    @DisplayName("Checking hasNext() Iterator Method")
    void tesIteratorHasNext() {
        MyLinkedList.CustomIterator customIterator = myLinkedList.new CustomIterator(1);
        assertTrue(customIterator.hasNext());
    }

    @Test
    @DisplayName("Checking hasNext() Iterator Method")
    void tesIteratorHasNextNegative() {
        MyLinkedList.CustomIterator customIterator = myLinkedList.new CustomIterator(2);
        assertFalse(customIterator.hasNext());
    }

    @Test
    @DisplayName("Checking hasNext() exceptionCatch")
    void tesIteratorHasNextException() {
        try{
            MyLinkedList.CustomIterator customIterator = myLinkedList.new CustomIterator(5);
        }catch (IndexOutOfBoundsException exception){
            assertEquals(exception.getMessage(), "Index out of bounds");
        }
    }

    @Test
    @DisplayName("Checking Iterator remove()")
    void tesIteratorRemove() {
        MyLinkedList.CustomIterator customIterator = myLinkedList.new CustomIterator(1);
        customIterator.remove();
        assertEquals(myLinkedList.get(0), "A");
        assertEquals(myLinkedList.get(1), "C");
    }

    @Test
    @DisplayName("Checking Iterator next()")
    void tesIteratorNext() {
        MyLinkedList.CustomIterator customIterator = myLinkedList.new CustomIterator(0);
        customIterator.next();
        assertEquals(myLinkedList.get(customIterator.index), "B");

    }
}
