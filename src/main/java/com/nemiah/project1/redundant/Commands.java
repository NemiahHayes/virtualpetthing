/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nemiah.project1.redundant;

import com.nemiah.project1.State;

/**
 *
 * @author nemiah
 */
public enum Commands {
    //User Commands
    HELP("help", "Displays all Commands", State.ALL),
    STOP("stop", "Saves and Quits Session. Can do anytime during play.", State.ALL),
    QUIT("quit", "Quits current Room. Saves and Quits if in Main Menu.", State.ALL),
    OVERWRITE("overwrite", "Overwrites Save file. Clears all current progress.", State.MENU),
    PET("pet", "Enter Pet Room.", State.MENU),
    DUNGEON("dungeon", "Enter Dungeon", State.MENU),
    PLAY("play", "Play with pet. increases mood", State.PETROOM),
    FEED("feed", "Feed pet. Decreases hunger.", State.PETROOM),
    RENAME("rename", "Rename pet.", State.PETROOM),
    SWAP("swap", "Swap pet for use in Battle.", State.PETROOM),
    ATTACK("attack", "Deals Damage to Enemy.", State.DUNGEON),
    DEFEND("defend", "Pet takes less Damage during next turn.", State.DUNGEON),
    SPECIAL("special", "Pet commits Special Attack. Only Available at certain times.", State.DUNGEON),
    ANALYZE("analyze", "Analyze Enemy. View their stats.", State.DUNGEON),
    UNKNOWN("unknown", "unknown", State.STARTUP);

    //Name, Description, and State that Command can be used during. 
    String name;
    String description;
    State state;

    private Commands(String name, String description, State state) {
        this.name = name;
        this.description = description;
        this.state = state;
    }

    //User Help String
    @Override
    public String toString() {
        String output = this.name + " | " + this.description;

        return output;
    }

}
