/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nemiah.project1;

import com.nemiah.project1.FileParser;
import com.nemiah.project1.Main;
import static com.nemiah.project1.Main.mainSetEntities;
import com.nemiah.project1.entitiesbase.Pet;
import com.nemiah.project1.entitiesbase.Player;
import com.nemiah.project1.Room;
import com.nemiah.project1.State;
import com.nemiah.project1.database.DBParse;
import javax.swing.JTextField;

/**
 *
 * @author nemiah
 */
public class StartData extends Room {

    private Player player;
    private Pet pet;
    DBParse dbParse;

    public StartData(){
        super(State.STARTUP);
        //Create Database
        dbParse = new DBParse();
    }

    public boolean validateNames(String playerName, String petName) {
        //Validate Names
        if (playerName.isEmpty() || petName.isEmpty()) {
            //Error
            return false;
        } else {
            //Continue to Update
            setNames(playerName, petName);
            return true;
        }
    }
    
    //Check if Player Name Exists
    public boolean validPlayerName(JTextField playerName) {
        String playerInput = playerName.getText().toUpperCase();

        return validatePlayerExists(playerInput);
    }

    //Check for Player Name Input
    public boolean validatePlayerExists(String playerName) {
        //Update
        if (playerName.isEmpty()) {
            return false;
        } else {
            player = dbParse.getPlayerDB(playerName);
            return player != null;
        }
    }

    //Check for Player Name in DB
    private void setNames(String playerName, String petName) {
        //Set Names
        player.setName(playerName);
        pet.setName(petName);
        //Save Names
        updateDB();
        updateSave();
        //Set to Menu
        toMenu();
    }

    private void updateSave() {
        FileParser parser = new FileParser(this.player, this.pet);
        parser.writeSave();
        System.out.println("Saved");
    }

    //Change confirm button based on recieved input
    public String validateInput(boolean inputBoolean) {
        String confirmText;
        if (!inputBoolean) {
            confirmText = "Must Enter Player and Pet Name.";
        } else {
            confirmText = "Confirmed. Loading Save File...";
        }
        return confirmText;
    }
    
    //Validate User Input
    public boolean validNames(JTextField playerName, JTextField petName) {
        String playerInput = playerName.getText().toUpperCase();
        String petInput = petName.getText().toUpperCase();
        
        return validateNames(playerInput, petInput);
    }
    
    //Load Related Pet from Database
    public void loadEntities(){
        dbParse.loadEntities();
        player = dbParse.getPlayer();
        pet = dbParse.getPet();
    }
    
    //Insert Entities
    public void insertEntities(){
        dbParse.addEntities();
    }
    
    //Called if Player Exists
    public void playerExists(){
        loadEntities();
        mainSetEntities(player,pet);
    }

}
