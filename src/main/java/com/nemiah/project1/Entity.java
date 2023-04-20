/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nemiah.project1;

/**
 *
 * @author nemiah
 */
public abstract class Entity {
    //Name
    private String name;

    //Battle Stats
    private int health;
    private int attack;
    private int defense;
    private int specialAttack;
    private int specialDefense;
    private int luck;

    //Misc. Stats
    private int level;

    //Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public void setSpecialAttack(int specialAttack) {
        this.specialAttack = specialAttack;
    }

    public int getSpecialDefense() {
        return specialDefense;
    }

    public void setSpecialDefense(int specialDefense) {
        this.specialDefense = specialDefense;
    }

    public int getLuck() {
        return luck;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    //Set Default Variables
    protected void setDefault(){
        this.name = "name";
        this.level = 1;
        setNewHealth();
        setNewAttack();
        setNewDefense();
        setNewSpecialAttack();
        setNewSpecialDefense();
        this.luck = 20;
    }

    //Call on Level up
    protected void levelUp(){
        setNewHealth();
        setNewAttack();
        setNewDefense();
        setNewSpecialAttack();
        setNewSpecialDefense();
        setNewLuck();
    }

    //Level Up Statistics
    //Set Health Based on Level
    protected abstract void setNewHealth();

    //Set Attack Based on Level
    protected abstract void setNewAttack();

    //Set Defense Based on Level
    protected abstract void setNewDefense();

    //Set SpecialAttack Based on Level
    protected abstract void setNewSpecialAttack();

    //Set SpecialDefense Based on Level
    protected abstract void setNewSpecialDefense();

    //Set Luck Based on Level
    protected abstract void setNewLuck();
}
