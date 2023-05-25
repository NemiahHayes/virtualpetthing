/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nemiah.project1.data;

import com.nemiah.project1.redundant.CommandParser;
import com.nemiah.project1.redundant.Commands;
import com.nemiah.project1.redundant.FileParser;
import com.nemiah.project1.Main;
import com.nemiah.project1.State;
import com.nemiah.project1.entitiesbase.Pet;
import com.nemiah.project1.entitiesbase.Player;
import com.nemiah.project1.database.DBParse;
import java.util.Set;

/**
 *
 * @author nemiah
 */
public abstract class Room {

    private boolean active;
    private Player player;
    private Pet pet;
    private Set<Commands> commandList;
    protected State state;
    protected final DBParse dbParse;

    public Room(State state) {
        this.active = false;
        this.dbParse = new DBParse();
        this.player = Main.getMainPlayer();
        System.out.println(Main.getMainPlayer());
        this.pet = Main.getMainPet();
        System.out.println(Main.getMainPet());
        setState(state);
    }

    //Starts Room
    public void startRoom() {
        setActive(true);
    }

    //Getter and Setter Methods
    public State getState() {
        return this.state;
    }

    private void setState(State state) {
        this.state = state;
    }

    public Set getCommands() {
        return this.commandList;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Player getPlayer() {
        return this.player;
    }

    protected void setPlayer(Player player) {
        this.player = player;
        System.out.println(player);
    }

    public Pet getPet() {
        return this.pet;
    }

    protected void setPet(Pet pet) {
        this.pet = pet;
    }

    //Stop Game
    public void stopGame() {
        endRoom();
        dbParse.close();
        Main.setPanel(State.QUIT);
        this.setActive(false);
    }

    //Initialize Command List
    //CLI Only
//    private void initializeCommands() {
//        commandList = CommandParser.getCommandList(this.getState());
//    }

//    //Print Command List
//    public void printCommands() {
//        System.out.println(" " + getCommands().toString().replaceAll("\\[|\\]", "").replaceAll(",", "\n"));
//    }

//    //Write to File
//    private void updateSave() {
//        FileParser parser = new FileParser(this.player, this.pet);
//        parser.writeSave();
//    }
    
    //Write to Database
    private void updateDB() {
        //Update in Database
        dbParse.updateEntities(player, pet);
    }
    
    //Update Main Entities
    protected void updateEntity(){
        Main.setMainPlayer(player);
        Main.setMainPet(pet);
    }

    //Returns toMenu
    protected void toMenu() {
        Main.setPanel(State.MENU);
        endRoom();
    }

    //End Room
    protected void endRoom() {
        updateDB();
        updateEntity();
        this.setActive(false);
    }

    //Rooms Main loop 
    //protected abstract void mainLoop();

}
