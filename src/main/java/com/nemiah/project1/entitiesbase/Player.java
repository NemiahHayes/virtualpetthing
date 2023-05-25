/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nemiah.project1.entitiesbase;

import java.util.UUID;

/**
 *
 * @author nemiah
 */
public class Player {
    private String name;
    private int dungeonLevel;
    private int food;
    private UUID uid;

    //Construct Player Class
    public Player(String name, int dungeonLevel, int food) {
        this.name = name;
        this.dungeonLevel = dungeonLevel;
        this.food = food;
        uid = UUID.randomUUID();
    }
    
    public Player(String name, int dungeonLevel, int food, UUID uid){
        this(name, dungeonLevel, food);
        this.uid = uid;
    }

    public Player() {
        this.setDefault();
        uid = UUID.randomUUID();
    }
    
    public Player(String name){
        this();
        this.name = name;
    }
    
    //Getters and Setters
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDungeonLevel() {
        return this.dungeonLevel;
    }

    public void setDungeonLevel(int dungeonLevel) {
        this.dungeonLevel = dungeonLevel;
    }

    public int getFood() {
        return this.food;
    }

    public void setFood(int food) {
        this.food = food;
    }
    
    public UUID getUid(){
        return uid;
    }
    
    public void setUid(UUID uid){
        this.uid = uid;
    }

    private void setDefault() {
        this.name = "player";
        this.dungeonLevel = 0;
        this.food = 10;
    }

}
