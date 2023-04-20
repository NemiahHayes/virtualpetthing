/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nemiah.project1;

import java.util.Random;

/**
 *
 * @author nemiah
 */
public class Enemy extends Entity {

    //Info
    private Type type;

    //AI Variables
    private int attackChance;
    private int defenseChance;
    private int specialAttackChance;

    //Other Variables
    private final Random rand = new Random();

    //Constructers
    public Enemy() {
        setDefault();
    }

    public Enemy(String name, int level) {
        setNewName(name);
        setLevel(level);
        setEnemy();
    }

    //Type Enum, to be Suffixed to Names
    public enum Type {
        SHAWARMA("Shawarma"), FALAFEL("Falafel"), BABA("Baba"), BAKLAVA("Baklava");

        String name;

        private Type(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    //Enum for Possible Actions
    public enum Action {
        ATTACK, DEFEND, SPECIAL;
    }

    //Enemy Logic
    public Action getAction() {
        boolean verified = false;
        Action action = null;
        //Get Action
        while (!verified) {
            //Higher int will have higher chance of being chosen
            int atk = rand.nextInt(attackChance);
            int def = rand.nextInt(defenseChance);
            int spc = rand.nextInt(specialAttackChance);

            //Get Highest Integer of the 3
            if (atk > def && atk > spc) {
                verified = true;
                action = Action.ATTACK;
            } else if (def > atk && def > spc) {
                verified = true;
                action = Action.DEFEND;
            } else if (spc > atk && spc > def) {
                verified = true;
                action = Action.SPECIAL;
            }
        }
        return action;
    }

    //Set Enemies Based on Level
    private void setEnemy() {
        setNewHealth();
        setNewAttack();
        setNewDefense();
        setNewSpecialAttack();
        setNewSpecialDefense();
        setNewLuck();
        setNewChances();
    }

    //Stat Formula : base * level + temp value
    //Set Health Based on Level
    @Override
    protected void setNewHealth() {
        //Random Var
        int base = 100;
        //Set Health
        int bounds = this.getLevel() * 25;
        int temp = rand.nextInt(bounds);
        //Floor to nearest 10
        int modTemp = temp % 10;
        temp -= modTemp;
        this.setHealth(base * getLevel() + temp);
    }

    //Set Attack Based on Level
    @Override
    protected void setNewAttack() {
        int base = 4;
        int bounds = this.getLevel() * 10;
        int temp = rand.nextInt(bounds) + (bounds / 2);
        this.setAttack(base * getLevel() * 13 + temp);
    }

    //Set Defense Based on Level
    @Override
    protected void setNewDefense() {
        int base = 2;
        int bounds = this.getLevel() * base;
        int temp = rand.nextInt(bounds) + (bounds / 2);
        this.setAttack(base * getLevel() * 10 + temp);
    }

    //Set SpecialAttack Based on Level
    @Override
    protected void setNewSpecialAttack() {
        int base = 3;
        int bounds = this.getLevel() * 15;
        int temp = rand.nextInt(bounds) + (bounds / 2);
        this.setSpecialAttack(base * getLevel() + temp);
    }

    //Set SpecialDefense Based on Level
    @Override
    protected void setNewSpecialDefense() {
        int base = 3;
        int bounds = this.getLevel() * 15;
        int temp = rand.nextInt(bounds) + (bounds / 2);
        this.setSpecialDefense(base * getLevel() + temp);
    }

    //Set Luck Based on Level
    @Override
    protected void setNewLuck() {
        int maxBound = 100;
        int minBound = 20;
        this.setLuck(rand.nextInt(maxBound) + minBound);
    }

    //Set Random AI Behaviour
    private void setNewChances() {
        int maxBound = 100;
        //Minimum Bounds
        int attackBound = 75;
        int defendBound = 05;
        int maxDefendBound = 50;
        int specialBound = 15;
        //Set Rand Chances
        this.setAttackChance(rand.nextInt(maxBound) + attackBound);
        this.setDefenseChance(rand.nextInt(maxDefendBound) + defendBound);
        this.setSpecialAttackChance(rand.nextInt(specialBound) + 1);
    }

    //Set Name
    private void setNewName(String name) {
        String newName;
        //If Type is Null, set Type
        if (getType() == null) {
            setNewType();
        }
        String nameType = getType().toString();
        if (!name.isEmpty()) {
            newName = name + " the " + nameType;
        } else {
            newName = "The " + nameType;
        }
        this.setName(newName.toUpperCase());
    }

    //Get Random Type for Naming
    private void setNewType() {
        Type[] types = Type.values();
        this.setType(types[rand.nextInt(Type.values().length)]);
    }

    //Get and Set Methods
    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getAttackChance() {
        return attackChance;
    }

    public void setAttackChance(int attackChance) {
        this.attackChance = attackChance;
    }

    public int getDefenseChance() {
        return defenseChance;
    }

    public void setDefenseChance(int defenseChance) {
        this.defenseChance = defenseChance;
    }

    public int getSpecialAttackChance() {
        return specialAttackChance;
    }

    public void setSpecialAttackChance(int specialAttackChance) {
        this.specialAttackChance = specialAttackChance;
    }

    //Set Default Variables
    @Override
    protected void setDefault() {
        this.type = Type.SHAWARMA;
        this.setNewName("");
        setHealth(0);
        setAttack(0);
        setDefense(0);
        setSpecialAttack(0);
        setSpecialDefense(0);
        setLuck(0);
        setLevel(1);
        setAttackChance(75);
        setDefenseChance(20);
        setSpecialAttackChance(05);
    }

}
