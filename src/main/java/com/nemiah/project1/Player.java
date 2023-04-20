/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nemiah.project1;

/**
 *
 * @author nemiah
 */
public class Player {

    private String name;
    private int dungeonLevel;
    private int food;

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

    private void setDefault() {
        this.name = "player";
        this.dungeonLevel = 0;
        this.food = 10;
    }

}
