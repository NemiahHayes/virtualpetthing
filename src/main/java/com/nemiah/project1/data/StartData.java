/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nemiah.project1.data;

import com.nemiah.project1.entitiesbase.Pet;
import com.nemiah.project1.entitiesbase.Player;
import com.nemiah.project1.State;
import com.nemiah.project1.State;
import com.nemiah.project1.database.DBParse;

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
    
    public boolean validateNames(String playerName, String petName){
        //Get Player
        if (!playerName.isEmpty()){
            Player dbPlayer = queryPlayer(playerName);
            //Player Exists, Skip Pet Check
            if (dbPlayer != null){
                this.player = dbPlayer;
                System.out.println("StartData UID : " + this.player.getUid().toString());
            } else {
                //New Player
                //Check Pet String, Create new Pet
                
                
                //If Pet String is Empty, return false.
            }
        }
        return false;
    }
    
    public Player queryPlayer(String playerName){
        return dbParse.queryPlayer(playerName);
    }
    
    public void queryPet(String petName){
        
    }

}
