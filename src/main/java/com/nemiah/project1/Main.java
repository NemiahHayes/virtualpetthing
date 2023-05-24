/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nemiah.project1;

import com.nemiah.project1.entitiesbase.Pet;
import com.nemiah.project1.entitiesbase.Player;
import static com.nemiah.project1.State.DUNGEON;
import static com.nemiah.project1.State.MENU;
import static com.nemiah.project1.State.PETROOM;
import static com.nemiah.project1.State.QUIT;
import static com.nemiah.project1.State.STARTUP;
import com.nemiah.project1.database.DBMain;
import com.nemiah.project1.database.DBParse;
import com.nemiah.project1.gui.MainFrame;
import java.io.IOException;

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
        //frame = new MainFrame();
        
        DBParse dbParse = new DBParse();
        dbParse.createTable();
        player = new Player("PlayerTest",0,10);
        dbParse.insertPlayer(player);
        Player qPlayer = dbParse.queryPlayer(player.getName());
        System.out.println(qPlayer.getName());
        //Start GUI
        //setPanel(State.STARTUP);
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
        return parser.loadPlayerSave();
    }

    //Load Pet from File
    public static Pet loadPet() {
        return parser.loadPetSave();
    }
    
    //Set Entities
    public static void mainSetEntities(Player player, Pet pet){
        mainSetPlayer(player);
        mainSetPet(pet);
    }
    
    private static void mainSetPlayer(Player player){
        player = player;
    }
    
    private static void mainSetPet(Pet pet){
        pet = pet;
    }

}
