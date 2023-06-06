/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nemiah.project1.data;

import com.nemiah.project1.entitiesbase.EntityBase;
import java.util.Random;

/**
 *
 * @author nemiah
 */
public class DungeonBattleData {
    
    private final Random rand;
    EntityBase attacker, defender;
    
    public DungeonBattleData(){
        rand = new Random();
    }
    
    private void setEntities(EntityBase attacker, EntityBase defender){
        this.attacker = attacker;
        this.defender = defender;
    }
    
    public int attack(EntityBase attacker, EntityBase defender){
        setEntities(attacker, defender);
        int hploss = calculateAttack();
        return hploss;
    }

    //Calculate Player Attack
    private int calculateAttack() {
        int hploss = 0;
        //Get Attack Damage
        if (attacker.getAttack() < defender.getDefense()) {
            hploss = 1;
        } else if (attacker.getAttack() == defender.getDefense()) {
            //Deal Luck, if roll lands deal half damage
            if (rollLuck()) {
                hploss += (attacker.getAttack() - defender.getDefense()) / 2;
            }
        } else {
            hploss += attacker.getAttack() - defender.getDefense();
            //Deal Luck, if roll lands double damage
            if (rollLuck()) {
                hploss += attacker.getAttack() - defender.getDefense();
            }
        }
        return hploss;
    }
    
    public int special(EntityBase attacker, EntityBase defender){
        setEntities(attacker, defender);
        int hploss = calculateSpecial();
        return hploss;
    }
    
    private int calculateSpecial() {
        //Compute Special Damage
        int defense = defender.getSpecialDefense() / 10;
        int hploss = 0;
        if (attacker.getSpecialAttack() < defense) {
            hploss = 1;
        } else {
            hploss = (attacker.getSpecialAttack() - defense);
        }
        return hploss;
    }
    
    public int calculateDefense(EntityBase entity){
        return entity.getDefense() + (entity.getDefense() / 2);
    }
    
    //Roll Luck for Damage Step
    private boolean rollLuck() {
        int attackerRoll = rand.nextInt(attacker.getLuck());
        int defenderRoll = rand.nextInt(defender.getLuck());
        return attackerRoll > defenderRoll;
    }

}
