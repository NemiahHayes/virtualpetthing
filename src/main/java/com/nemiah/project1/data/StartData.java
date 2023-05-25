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
import java.util.UUID;

/**
 *
 * @author nemiah
 */
public class StartData extends Room {

    DBParse dbParse;

    public StartData(){
        super(State.STARTUP);
        //Create Database
        dbParse = new DBParse();
    }
    
    //Validate Names
    public boolean validateNames(String playerName, String petName){
        //Get Player
        if (!playerName.isEmpty()){
            Player dbPlayer = queryPlayer(playerName);
            //Player Exists, Skip Pet Check
            if (dbPlayer != null){
                System.out.println("Loading Player...");
                loadEntities(dbPlayer);
                updateEntity();
                toMenu();
            } else {
                //Create New Player
                //Check Pet String, Create new Pet
                if (!petName.isEmpty()) {
                    System.out.println("Creating Player...");
                    createEntities(playerName, petName);
                    toMenu();
                } else {
                    //If Pet String is Empty, return false.
                    return false;
                }
            }
        }
        return false;
    }
    
    //Load old Entities
    private void loadEntities(Player dbPlayer){
        //Player already Queried
        setPlayer(dbPlayer);
        System.out.println("StartData UID : " + getPlayer().getUid() + " Name : " + getPlayer().getName());
        
        //Query Pet
        setPet(dbParse.queryPet(getPlayer().getUid()));
        System.out.println("StartData Pet UID : " + getPet().getUid() + " Name : " + getPet().getName());
    }
    
    //Create new Entities
    private void createEntities(String playerName, String petName) {
        Player newPlayer = new Player(playerName);
        System.out.println("New Player UID : " + newPlayer.getUid());

        Pet newPet = new Pet(petName, newPlayer.getUid());
        System.out.println("New Pet UID : " + newPet.getUid());

        dbParse.insertPlayer(newPlayer);
        dbParse.insertPet(newPet);

        setPlayer(newPlayer);
        System.out.println(newPlayer);
        setPet(newPet);
        System.out.println(newPet);
        
        //Update
        updateEntity();
    }

    //Query Player
    public Player queryPlayer(String playerName){
        return dbParse.queryPlayer(playerName);
    }
    
    //Query Petname
    public Pet queryPet(String petName){
        return dbParse.queryPet(petName);
    }
    //Query Pet UID
    public Pet queryPet(UUID uid){
        return dbParse.queryPet(uid);
    }

}
