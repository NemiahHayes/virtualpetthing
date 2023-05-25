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
                loadEntities(dbPlayer);
                return true;
            } else {
                //Create New Player
                //Check Pet String, Create new Pet
                if (!petName.isEmpty()) {
                    createEntities(playerName, petName);
                    return true;
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
        this.player = dbPlayer;
        System.out.println("StartData UID : " + this.player.getUid() + " Name : " + this.player.getName());
        
        //Query Pet
        this.pet = dbParse.queryPet(player.getUid());
        System.out.println("StartData Pet UID : " + this.pet.getUid() + " Name : " + this.pet.getName());
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
        setPet(newPet);
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
