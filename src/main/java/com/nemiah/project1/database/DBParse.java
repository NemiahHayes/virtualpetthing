/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nemiah.project1.database;

import com.nemiah.project1.entitiesbase.Pet;
import com.nemiah.project1.entitiesbase.Player;
import java.util.List;

/**
 *
 * @author nemiah
 */
public class DBParse {
    
    DBMain dbMain;
    Player player;
    Pet pet;
    
    public DBParse(){
        dbMain = new DBMain();
    }

    public List<Player> getPlayers(){
        return dbMain.queryAllPlayers();
    }
    
    //Call Player from DB
    public Player getPlayerDB(String playerName){
        return dbMain.queryPlayerName(playerName);
    }
    
    //Call Pet from DB
    public Pet getPetDB(int id){
        return dbMain.queryPetId(id);
    }
    
    //Getters and Setters
    public Player getPlayer(){
        return player;
    }
    
    public void setPlayer(Player player){
        this.player = player;
    }
    
    public Pet getPet(){
        return pet;
    }
    
    public void setPet(Pet pet){
        this.pet = pet;
    }
    
    public void loadEntities(){
        player = getPlayerDB(player.getName());
        pet = getPetDB(player.getId());
    }
    
    public void addEntities(){
        dbMain.addEntities(player, pet);
    }
}
