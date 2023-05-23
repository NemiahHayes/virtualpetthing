/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nemiah.project1.entitiesbase;

import java.io.Serializable;
import javax.persistence.*;
/**
 *
 * @author nemiah
 */
@Entity
@Table(name="players")
public class Player implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String name;
    private int dungeonLevel;
    private int food;
    @OneToOne(mappedBy = "player", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Pet pet;

    //Construct Player Class
    public Player(String name, int dungeonLevel, int food) {
        this.name = name;
        this.dungeonLevel = dungeonLevel;
        this.food = food;
    }

    public Player() {
        this.setDefault();
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
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public Pet getPet(){
        return pet;
    }
    
    public void setPet(Pet pet){
        this.pet = pet;
    }

    private void setDefault() {
        this.name = "player";
        this.dungeonLevel = 0;
        this.food = 10;
    }

}
