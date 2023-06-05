/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.nemiah.project1.entitiesbase;

import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author nemiah
 */
public class PetTest {
    
    public PetTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    /**
     * Test of getExp method, of class Pet.
     */
    @Test
    public void testGetExpEqualsZero() {
        System.out.println("getExp");
        Pet instance = new Pet();
        int expResult = 0;
        int result = instance.getExp();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        System.out.println("testGetExp0 Test. Actual : " + result + " Expected : " + expResult);
    }
    
    @Test
    public void testGetExpEqualsFourtyNine() {
        System.out.println("getExp");
        Pet instance = new Pet();
        instance.setExp(49);
        int expResult = 49;
        int result = instance.getExp();
        assertEquals(expResult, result);
        System.out.println("testGetExp49 Test. Actual : " + result + " Expected : " + expResult);
    }

    @Test
    public void testGetExpEqualsOne() {
        System.out.println("getExp");
        Pet instance = new Pet();
        instance.setExp(50);
        int expResult = 0;
        int result = instance.getExp();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        System.out.println("testGetExp1 Test. Actual : " + result + " Expected : " + expResult);
    }
//
//    /**
//     * Test of setNewHealth method, of class Pet.
//     */
//    @Test
//    public void testSetNewHealth() {
//        System.out.println("setNewHealth");
//        Pet instance = new Pet();
//        instance.setNewHealth();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setNewAttack method, of class Pet.
//     */
//    @Test
//    public void testSetNewAttack() {
//        System.out.println("setNewAttack");
//        Pet instance = new Pet();
//        instance.setNewAttack();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setNewDefense method, of class Pet.
//     */
//    @Test
//    public void testSetNewDefense() {
//        System.out.println("setNewDefense");
//        Pet instance = new Pet();
//        instance.setNewDefense();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setNewSpecialAttack method, of class Pet.
//     */
//    @Test
//    public void testSetNewSpecialAttack() {
//        System.out.println("setNewSpecialAttack");
//        Pet instance = new Pet();
//        instance.setNewSpecialAttack();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setNewSpecialDefense method, of class Pet.
//     */
//    @Test
//    public void testSetNewSpecialDefense() {
//        System.out.println("setNewSpecialDefense");
//        Pet instance = new Pet();
//        instance.setNewSpecialDefense();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setNewLuck method, of class Pet.
//     */
//    @Test
//    public void testSetNewLuck() {
//        System.out.println("setNewLuck");
//        Pet instance = new Pet();
//        instance.setNewLuck();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
