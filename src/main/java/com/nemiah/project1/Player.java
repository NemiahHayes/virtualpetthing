/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nemiah.project1;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
/**
 *
 * @author nemiah
 */
@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int dungeonLevel;
    @Column(nullable = false)
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
