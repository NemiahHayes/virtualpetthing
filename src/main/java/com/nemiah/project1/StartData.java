/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nemiah.project1;

import com.nemiah.project1.FileParser;
import com.nemiah.project1.Main;
import com.nemiah.project1.Pet;
import com.nemiah.project1.Player;
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
        //Load Local Saves
        player = Main.loadPlayer();
        pet = Main.loadPet();
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

}
