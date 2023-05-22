/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nemiah.project1;

import com.nemiah.project1.Entities.Pet;
import com.nemiah.project1.Entities.Player;
import static com.nemiah.project1.State.DUNGEON;
import static com.nemiah.project1.State.MENU;
import static com.nemiah.project1.State.PETROOM;
import static com.nemiah.project1.State.QUIT;
import static com.nemiah.project1.State.STARTUP;
import com.nemiah.project1.database.DBMain;
import com.nemiah.project1.gui.MainFrame;
import java.io.IOException;

/**
 *
 * @author nemiah
 */
public class Main {

    private static final FileParser parser = new FileParser();
    private static MainFrame frame;

    public static void main(String[] args) throws IOException {

        Player player = loadPlayer();
        Pet pet = loadPet();
        pet.setPlayer(player);
        player.setPet(pet);
        
        DBMain dbMain = new DBMain(player, pet);
        frame = new MainFrame();
        //Start GUI
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

}
