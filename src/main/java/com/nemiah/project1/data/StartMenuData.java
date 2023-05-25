/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nemiah.project1.data;

import com.nemiah.project1.Other.CommandParser;
import com.nemiah.project1.Other.Commands;
import com.nemiah.project1.Main;
import com.nemiah.project1.State;
import com.nemiah.project1.entitiesbase.Pet;
import com.nemiah.project1.entitiesbase.Player;
import static com.nemiah.project1.Other.Commands.HELP;
/**
 *
 * @author nemiah
 */
public class StartMenuData extends Room {

    private boolean valid = false;

    public StartMenuData() {
        super(State.MENU);
    }

    //Translate Command 
    private void parseInput(Commands cmd) {
        switch (cmd) {
            //Room Specific
            case PET:
                toPetRoom();
                break;
            case DUNGEON:
                toDungeon();
                break;
            case OVERWRITE:
                toOverwrite();
                break;
            //All State Commands
            case STOP:
                stopGame();
                break;
            case QUIT:
                stopGame();
                break;
            case HELP:
                printCommands();
                valid = false;
                break;
            case UNKNOWN:
                CommandParser.unknownError();
                valid = false;
                break;
        }
    }

    //Command List
    //Go to Petroom - pet
    public void toPetRoom() {
        Main.setPanel(State.PETROOM);
        endRoom();
    }

    //Go to Dungeon - dungeon
    public void toDungeon() {
        Main.setPanel(State.DUNGEON);
        endRoom();
    }

    //Overwrite File - overwrite
    private void toOverwrite() {
        System.out.println("Generating new save file...");

        //Set Objects to Default
        Player newPlayer = new Player();
        setPlayer(newPlayer);
        Pet newPet = new Pet();
        setPet(newPet);
        System.out.println("New Save Generated. Sending to Startup Screen...");

        //Objects overwrite on Room exit
        Main.setPanel(State.STARTUP);
        endRoom();
    }

}
