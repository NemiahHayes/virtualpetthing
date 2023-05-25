/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nemiah.project1;

import com.nemiah.project1.other.FileParser;
import com.nemiah.project1.entitiesbase.Pet;
import com.nemiah.project1.entitiesbase.Player;
import static com.nemiah.project1.State.DUNGEON;
import static com.nemiah.project1.State.MENU;
import static com.nemiah.project1.State.PETROOM;
import static com.nemiah.project1.State.QUIT;
import static com.nemiah.project1.State.STARTUP;
import com.nemiah.project1.gui.MainFrame;
import java.io.IOException;
import java.util.UUID;
import com.nemiah.project1.database.DBMain;
import com.nemiah.project1.database.DBParse;

/**
 *
 * @author nemiah
 */
public class Main {

    private static final FileParser parser = new FileParser();
    private static MainFrame frame;
    private static Player player;
    private static Pet pet;

    public static void main(String[] args) throws IOException {    
        
        dbTest();
//        //Initialize 
//        frame = new MainFrame();
//        //Start GUI
//        setPanel(State.STARTUP);
    }
    
    private static void dbTest(){
        DBParse dbParse = new DBParse();
        Player testPlayer = new Player("Update Twice",13,100, UUID.fromString("ee90ee18-1d08-4015-9922-2aa62bbe1281"));
        dbParse.updatePlayer(testPlayer);
        
        Pet testPet = new Pet("Update Twice", UUID.fromString("ee90ee18-1d08-4015-9922-2aa62bbe1281"));
        dbParse.updatePet(testPet);
//        player = new Player("PlayerTest",0,10);
//        dbParse.insertPlayer(player);
//        Player qPlayer = dbParse.queryPlayer(player.getName());
//        System.out.println(qPlayer.getName() + " Successful" + " Player UID : " + qPlayer.getUid());
//        
//        pet = new Pet("Pet Test",100,10,10,10,10,10,10,10,10,10,player.getUid());
//        dbParse.insertPet(pet);
//        Pet qPet = dbParse.queryPet(pet.getUid());
//        System.out.println(qPet.getName() + " Successful");
//        
//        //Update
//        player.setName("Update Test");
//        player.setDungeonLevel(3);
//        player.setFood(24);
//        dbParse.updatePlayer(player);
    }
    
    public static void setPanel(State state){
        switch (state) {
            //Enter Startup
            case STARTUP:
                frame.changePanel(State.STARTUP);
                break;
            //Enter Main Menu
            case MENU:
                frame.changePanel(State.MENU);
                break;
            //Enter Pet Room
            case PETROOM:
                //Null
                break;
            //Enter Dungeon
            case DUNGEON:
                //Null
                break;
            //Save and Quit
            case QUIT:
                frame.dispose();
                System.exit(0);
                break;
            default:
                break;
        }
    }

    //Load Player from File
    public static Player loadPlayer() {
        return player;
    }

    //Load Pet from File
    public static Pet loadPet() {
        return pet;
    }
    
    //Set Entities
    public static void mainSetEntities(Player player, Pet pet){
        mainSetPlayer(player);
        mainSetPet(pet);
    }
    
    public static void mainSetPlayer(Player player){
        player = player;
    }
    
    private static void mainSetPet(Pet pet){
        pet = pet;
    }

}
