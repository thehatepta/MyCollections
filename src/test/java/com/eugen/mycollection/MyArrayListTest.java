package com.eugen.mycollection;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import com.eugen.mycollection.MyArrayList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MyArrayListTest {
    MyArrayList myArrayList = null;

    @Before
    void prepareTest(){

    }

    @BeforeEach
    void prepareArray(){
        myArrayList = new MyArrayList();
        myArrayList.add("A");
        myArrayList.add("B");
        myArrayList.add("C");
    }

    @Test
    void testSize(){
        assertEquals(myArrayList.size(), 3);
    }

    @Test
    void testIsEmpty(){
        assertEquals(myArrayList.isEmpty(), false);
    }

    @Test
    void testContains(){
        assertEquals(myArrayList.contains("A"), true);
    }


    @Test
    void testGet(){
        assertEquals(myArrayList.get(0), "A");
    }

    @Test
    void testAdd(){
        myArrayList.add("D");
        assertEquals(myArrayList.get(3), "D");
    }

    @Test
    void testAddElementByIndex(){
        myArrayList.add("D", 0);
        assertEquals(myArrayList.get(0), "D");
    }

    @Test
    void testSetElementByIndex(){
        myArrayList.set("D", 0);
        assertEquals(myArrayList.get(0), "D");
    }

    @Test
    void testRemove(){
        myArrayList.remove("C");
        assertEquals(myArrayList.get(0), "A");
        assertEquals(myArrayList.get(1), "B");
        assertNull(myArrayList.get(2));
    }

    @Test
    void testClear(){
        myArrayList.clear();
        assertEquals(myArrayList.isEmpty(), true);
    }


    @Test
    void testIndexOff(){
        assertEquals(myArrayList.indexOf("C"), 2);
    }

    @Test
    void testLastIndexOff(){
        myArrayList.add("A");
        assertEquals(myArrayList.lastIndexOf("A"), 3);
    }

    @Test
    void tesToString(){
        assertEquals(myArrayList.toString(), "A,B,C");
    }

}
