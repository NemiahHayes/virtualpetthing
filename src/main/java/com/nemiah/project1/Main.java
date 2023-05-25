/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nemiah.project1;

import com.nemiah.project1.redundant.FileParser;
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
//        //Initialize 
        
        frame = new MainFrame();
        
        //Testing
//        DBParse dbP = new DBParse();
//        player = dbP.queryPlayer("mystical");
//        pet = dbP.queryPet(player.getUid());
//        //Start GUI
        setPanel(State.STARTUP);
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
                frame.changePanel(PETROOM);
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

    //Setters and Getters
    public static Player getMainPlayer() {
        System.out.println("Returning : " + player);
        return player;
    }

    public static Pet getMainPet() {
        return pet;
    }

    public static void setMainPlayer(Player updatePlayer) {
        player = updatePlayer;
        System.out.println("Updated : " + player);
    }
    
    public static void setMainPet(Pet updatePet){
        pet = updatePet;
    }
    
}
