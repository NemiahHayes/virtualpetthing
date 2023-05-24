/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nemiah.project1.entitiesbase;

/**
 *
 * @author nemiah
 */
public class Pet extends EntityBase {
    //Misc. Stats
    private int id;
    private int exp, hunger, mood;

    public Pet(String name, int health, int attack, int defense, int specialAttack, int specialDefense, int luck, int level, int exp, int hunger, int mood) {
        setName(name);
        setHealth(health);
        setAttack(attack);
        setDefense(defense);
        setSpecialAttack(specialAttack);
        setSpecialDefense(specialDefense);
        setLuck(luck);
        setLevel(level);
        this.exp = exp;
        this.hunger = hunger;
        this.mood = mood;
    }

    public Pet() {
        super();
        this.exp = 0;
        this.hunger = 0;
        this.mood = 0;
    }

    //Getters and Setters
    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public int getExp() {
        return exp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //Set EXP value
    public void setExp(int exp) {
        //Compare with EXPLimit
        if (exp < getExpLimit()) {
            this.exp = exp;
            //If Over or Equal with EXPLimit, change limit and reset EXP to remainder
        } else if (exp >= getExpLimit()) {
            exp -= getExpLimit();
            this.setLevel(getLevel() + 1);
            this.exp = exp;
            levelUp();
        }
    }

    //Get EXPLimit based on Pet Level
    private int getExpLimit() {
        int n = getLevel() * (getLevel() + 1) / 2;
        return n * 50;
    }

    //Level Up Statistics
    //Set Health Based on Level
    @Override
    protected void setNewHealth() {
        //Base Var
        int base = 100;
        //Set Health
        this.setHealth((getLevel() * (getLevel() + 1) / 2) * base);
    }

    //Set Attack Based on Level
    @Override
    protected void setNewAttack() {
        int base = 5;
        this.setAttack(base * (getLevel() * 13));
    }

    //Set Defense Based on Level
    @Override
    protected void setNewDefense() {
        int base = 2;
        this.setDefense(base * (getLevel() * 5));
    }

    //Set SpecialAttack Based on Level
    @Override
    protected void setNewSpecialAttack() {
        int base = 3;
        int bounds = this.getLevel() * 15;
        this.setSpecialAttack(base * getLevel() + bounds);
    }

    //Set SpecialDefense Based on Level
    @Override
    protected void setNewSpecialDefense() {
        int base = 3;
        int bounds = this.getLevel() * 15;
        this.setSpecialDefense(base * getLevel() + bounds);
    }

    //Set Luck Based on Level
    @Override
    protected void setNewLuck() {
        if (this.getLuck() <= 80) {
            this.setLuck(this.getLuck() + 5);
        }
    }

}
