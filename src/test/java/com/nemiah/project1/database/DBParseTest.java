        /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.nemiah.project1.database;

import com.nemiah.project1.entitiesbase.Pet;
import com.nemiah.project1.entitiesbase.Player;
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
public class DBParseTest {
    
    private static DBParse instance;
    private static Pet pet;
    private static Player player;
    
    @BeforeAll
    public static void setUpClass() {
        instance = new DBParse();
    }
    
    @AfterAll
    public static void tearDownClass() {
        instance.close();
    }
    
    @BeforeEach
    public void setUp() {
        pet = new Pet();
        player = new Player();
        instance.deletePet(pet.getUid());
        instance.deletePlayer(player.getUid());
        instance.deletePet(pet.getName());
        instance.deletePlayer(player.getName());
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of queryPet method, of class DBParse.
     */
    @Test
    public void testQueryPet_UUID() {
        System.out.println("queryPet UUID");
        
        UUID uid = pet.getUid();
        instance.insertPet(pet);
        
        UUID expResult = pet.getUid();
        UUID result = instance.queryPet(uid).getUid();
        assertEquals(expResult, result);
    }

    /**
     * Test of queryPet method, of class DBParse.
     */
    @Test
    public void testQueryPet_String() {
        System.out.println("queryPet String");
        
        String name = pet.getName();
        instance.insertPet(pet);
        
        String expResult = pet.getName();
        String result = instance.queryPet(name).getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of updateEntities method, of class DBParse.
     */
    @Test
    public void testUpdateEntitiesPlayer() {
        System.out.println("updateEntities - Player");
        
        instance.insertPlayer(player);
        player.setFood(100);
        instance.updateEntities(player, pet);
        int expResult = 100;
        
        player = instance.queryPlayer(player.getName());
        int result = player.getFood();
        assertEquals(expResult,result);
    }
    
    @Test
    public void testUpdateEntitiesPet() {
        System.out.println("updateEntities - Pet");
        
        instance.insertPet(pet);
        pet.setAttack(11);
        instance.updateEntities(player, pet);
        int expResult = 11;
        
        pet = instance.queryPet(pet.getName());
        int result = pet.getAttack();
        assertEquals(expResult,result);
    }

    /**
     * Test of insertPet method, of class DBParse.
     */
    @Test
    public void testInsertPetString() {
        System.out.println("insertPet string");
        
        String expResult = "Pet Test";
        pet.setName(expResult);
        instance.insertPet(pet);
        String result = instance.queryPet(pet.getName()).getName();
        assertEquals(expResult,result);
    }
    
    @Test
    public void testInsertPetUUID() {
        System.out.println("insertPet uuid");

        UUID expResult = pet.getUid();
        instance.insertPet(pet);
        UUID result = instance.queryPet(pet.getName()).getUid();
        assertEquals(expResult, result);
    }

    /**
     * Test of insertPlayer method, of class DBParse.
     */
    @Test
    public void testInsertPlayerString() {
        System.out.println("insertPlayer string");

        String expResult = player.getName();
        instance.insertPlayer(player);
        String result = instance.queryPlayer(player.getName()).getName();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testInsertPlayerUUID() {
        System.out.println("insertPlayer uuid");

        UUID expResult = player.getUid();
        instance.insertPlayer(player);
        UUID result = instance.queryPlayer(player.getName()).getUid();
        assertEquals(expResult, result);
    }

    /**
     * Test of queryPlayer method, of class DBParse.
     */
    @Test
    public void testQueryPlayerString() {
        System.out.println("queryPlayer String");
        
        player.setName("August 1123");
        instance.insertPlayer(player);
        
        String expResult = player.getName();
        String result = instance.queryPlayer(player.getName()).getName();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testQueryPlayerUuid() {
        System.out.println("queryPlayer UUID");

        instance.insertPlayer(player);

        UUID expResult = player.getUid();
        UUID result = instance.queryPlayer(player.getName()).getUid();
        assertEquals(expResult, result);
    }
}
