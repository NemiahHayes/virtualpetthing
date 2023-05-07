/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nemiah.project1;

import com.nemiah.project1.gui.MainFrame;
import java.io.IOException;

/**
 *
 * @author nemiah
 */
public class Main {

    private static final FileParser parser = new FileParser();
    private static State state;

    public static void main(String[] args) throws IOException {

        MainFrame frame = new MainFrame();
        
        frame.startUp();
        /*
        //Set Loop Variable
        boolean play = true;

        //Set Startup State, Files loaded in STARTUP
        state = State.STARTUP;

        //Start Game
        while (play) {
            switch (state) {
                //Enter Startup
                case STARTUP:
                    Startup startup = new Startup();
                    startup.initializeGame();
                    break;
                //Enter Main Menu
                case MENU:
                    StartMenu startMenu = new StartMenu();
                    startMenu.startRoom();
                    break;
                //Enter Pet Room
                case PETROOM:
                    PetRoomFrame petRoom = new PetRoomFrame();
                    petRoom.startRoom();
                    break;
                //Enter Dungeon
                case DUNGEON:
                    Dungeon dungeon = new Dungeon();
                    dungeon.startRoom();
                    break;
                //Save and Quit
                case QUIT:
                    play = false;
                    break;
                default:
                    break;
            }
        }*/
    }

    //Set Game State
    public static void setState(State changeState) {
        state = changeState;
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
