/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nemiah.project1.data;

import com.nemiah.project1.FileParser;
import com.nemiah.project1.State;
import com.nemiah.project1.entitiesbase.Enemy;
import com.nemiah.project1.entitiesbase.Pet;
import static com.nemiah.project1.data.Commands.ATTACK;
import static com.nemiah.project1.data.Commands.DEFEND;
import static com.nemiah.project1.data.Commands.QUIT;
import static com.nemiah.project1.data.Commands.SPECIAL;
import com.nemiah.project1.entitiesbase.EntityBase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author nemiah
 */
public class DungeonData extends Room {

    //Final Objects and Lists
    private final DungeonBattleData dungeonBattleData = new DungeonBattleData();
    private final HashMap<Integer, Enemy> enemyMap = new HashMap();
    private ArrayList<String> enemyNames = new ArrayList();
    private final Random rand = new Random();
    private final FileParser parser = new FileParser();
    
    //Dungeon Variables
    private final int dungeonLevel;
    private boolean endDungeon;
    private final int specialCounterLimit = 5;
    private Enemy curEnemy;
    private int placing;
    private int specialCounter;

    //Temp Entity Stats
    private final Pet dungeonPet;
    private int originalDefense;
    private int enemyOriginalDefense;

    //Initialize Room with DUNGEON state
    public DungeonData() {
        super(State.DUNGEON);
        //Store Dungeon Level and Pet
        dungeonLevel = getPlayer().getDungeonLevel();
        dungeonPet = new Pet();
        setDungeonPet();
        //Set Temp Variables and Generate Enemies
        setPlacing(0);
        endDungeon = false;
        enemyNames = parser.loadEnemyList();
        generateEnemies();
    }
    
    //Set Dungeon Pet
    private void setDungeonPet(){
        dungeonPet.setName(getPet().getName());
        dungeonPet.setHealth(getPet().getHealth());
        dungeonPet.setAttack(getPet().getAttack());
        dungeonPet.setDefense(getPet().getDefense());
        dungeonPet.setSpecialAttack(getPet().getSpecialAttack());
        dungeonPet.setSpecialDefense(getPet().getSpecialDefense());
        dungeonPet.setLuck(getPet().getLuck());
        dungeonPet.setLevel(getPet().getLevel());
        dungeonPet.setExp(1);
        dungeonPet.setHunger(getPet().getHunger());
        dungeonPet.setMood(getPet().getMood());
    }

    public void initializeRound() {
        //Initialize Pet and Enemy
        originalDefense = dungeonPet.getDefense();
        curEnemy = pullEnemy(getPlacing());
        enemyOriginalDefense = curEnemy.getDefense();
    }
    
    public int checkConditions(){
        if (dungeonPet.getHealth() <= 0) {
            playerLoseRound();
            return -1;
        }
        if (curEnemy.getHealth() <= 0) {
            playerWinRound();
            return 1;
        }
        return 0;
    }

    //End of Round Rewards
    private void playerWinRound() {
        //Print Victory Message
        playerVictoryRewards();
        //Increase Dungeon Place
        setPlacing(getPlacing() + 1);
        checkDungeonLevel();
    }
    
    private void playerVictoryRewards() {
        //Give Victory Rewards
        int foodAmount = curEnemy.getLevel() * 5;
        getPlayer().setFood(getPlayer().getFood() + foodAmount);
        int expAmount = curEnemy.getLevel() * 25;
        getPet().setExp(getPet().getExp() + expAmount);
    }

    //Increase Dungeon Level and Exit Dungeon
    private void checkDungeonLevel() {
        if (getPlacing() >= 5) {
            getPlayer().setDungeonLevel(getPlayer().getDungeonLevel() + 1);
            endDungeon = true;
        } else {
            //Else Restart to next Round
            setPlacing(getPlacing() + 1);
            curEnemy = pullEnemy(getPlacing());
        }
    }

    //If Player HP = 0
    private String playerLoseRound() {
        getPet().setMood(getPet().getMood() - 5);
        return dungeonPet.getName() + " can no longer continue to fight.";
    }

    //Reset Round Buffs
    public void resetRound() {
        dungeonPet.setDefense(originalDefense);
        curEnemy.setDefense(enemyOriginalDefense);
        if (specialCounter < specialCounterLimit) {
            specialCounter++;
        }
    }

    //Generate Enemy Hashmap
    private void generateEnemies() {
        int id = 0;
        while (id < 5) {
            enemyMap.put(id, newEnemy(id));
            id++;
        }
    }

    //Generate New Enemy
    private Enemy newEnemy(int id) {
        //Enemy Level = Pet Lvl. + Dungeon Level + (0 : id + 1)
        int level = getPet().getLevel() + this.getDungeonLevel() + rand.nextInt(id + 1);
        String name = getEnemyName();
        return new Enemy(name, level);
    }

    //Get Enemy Names from Namelist File
    private String getEnemyName() {
        int bounds = enemyNames.size();
        int randName = rand.nextInt(bounds);
        return enemyNames.get(randName);
    }

    //Pull Enemy for Dungeon
    private Enemy pullEnemy(int id) {
        return enemyMap.get(id);
    }
    
    //GUI Behaviour
    public String getEnemyInput(){
        return enemyInput();
    }

    //Enemy Behaviour
    private String enemyInput() {
        switch (curEnemy.getAction()) {
            case ATTACK:
                return attack(getCurEnemy(),getDungeonPet());
            case DEFEND:
                return defend(getCurEnemy());
            case SPECIAL:
                return special(getCurEnemy(),getDungeonPet());
        }
        return null;
    }

    //Translate Input
    public String parseInput(Commands cmd) {
        switch (cmd) {
            case ATTACK:
                return attack(getDungeonPet(),getCurEnemy());
            case DEFEND:
                return defend(getDungeonPet());
            case SPECIAL:
                return special(getDungeonPet(), getCurEnemy());
            case QUIT:
                toMenu();
                break;
        }
        return null;
    }

    //Attack Action
    private String attack(EntityBase attacker, EntityBase defender) {
        //Compute Damage
        int hploss = dungeonBattleData.attack(attacker,defender);
        defender.setHealth(defender.getHealth() - hploss);
        return printBattleResult(attacker.getName(), hploss, Enemy.Action.ATTACK);
    }
    
    //Defense Action
    private String defend(EntityBase entity){
        entity.setDefense(dungeonBattleData.calculateDefense(entity));
        return printBattleResult(entity.getName(), 0, Enemy.Action.DEFEND);
    }
    
    //Special Action
    private String special(EntityBase attacker, EntityBase defender) {
        //Player Specific Check
        if (attacker.getClass().equals(this.getDungeonPet())){
            if (!checkSpecial()){
                return "Unable To Attack! Must wait to charge.";
            } else {
                specialCounter = 0;
            }
        }
        int hploss = dungeonBattleData.special(attacker,defender);
        defender.setHealth(defender.getHealth() - hploss);
        return printBattleResult(attacker.getName(),hploss,Enemy.Action.SPECIAL);
    }

    //Display Damage and Turn Damage Results
    private String printBattleResult(String name, int damage, Enemy.Action action) {
        String result = "";
        switch (action) {
            case ATTACK:
                result = name + " attacks! " + damage + " damage dealt.";
                break;
            case DEFEND:
                result = name + " defends!";
                break;
            case SPECIAL:
                result = name + " special attack deals " + damage + " damage!";
                break;
            default:
                break;
        }
        return result;
    }

    public boolean checkSpecial() {
        return specialCounter >= specialCounterLimit;
    }

    //Get and Set Methods
    private int getDungeonLevel() {
        return this.dungeonLevel;
    }

    private void setPlacing(int placing) {
        this.placing = placing;
    }

    private int getPlacing() {
        return this.placing;
    }

    public Enemy getCurEnemy() {
        return this.curEnemy;
    }

    public Pet getDungeonPet() {
        return dungeonPet;
    }

    public boolean getEndDungeon() {
        return endDungeon;
    }

    public int getSpecialCounter() {
        return specialCounter;
    }

    public int getSpecialCounterLimit() {
        return specialCounterLimit;
    }

}
