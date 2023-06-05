/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nemiah.project1.data;

import com.nemiah.project1.redundant.FileParser;
import com.nemiah.project1.State;
import com.nemiah.project1.entitiesbase.Enemy;
import com.nemiah.project1.entitiesbase.Pet;
import static com.nemiah.project1.data.Commands.ATTACK;
import static com.nemiah.project1.data.Commands.DEFEND;
import static com.nemiah.project1.data.Commands.QUIT;
import static com.nemiah.project1.data.Commands.SPECIAL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author nemiah
 */
public class DungeonData extends Room {

    //Final Objects and Lists
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
    private int tempDefense;
    private int enemyTempDefense;

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
        tempDefense = getPet().getDefense();
        curEnemy = pullEnemy(getPlacing());
        enemyTempDefense = curEnemy.getDefense();
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
        tempDefense = dungeonPet.getDefense();
        enemyTempDefense = curEnemy.getDefense();
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
                return enemyAttack();
            case DEFEND:
                return enemyDefend();
            case SPECIAL:
                return enemySpecial();
            default:
                System.out.println("null");
                break;
        }
        return null;
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
    
    public Enemy getCurEnemy(){
        return this.curEnemy;
    }
    
    public Pet getDungeonPet(){
        return dungeonPet;
    }
    
    public boolean getEndDungeon(){
        return endDungeon;
    }
    
    public int getSpecialCounter(){
        return specialCounter;
    }
  
    public int getSpecialCounterLimit(){
        return specialCounterLimit;
    }

    //Translate Input
    public String parseInput(Commands cmd) {
        switch (cmd) {
            //Room Specific
            case ATTACK:
                return playerAttack();
            case DEFEND:
                return playerDefend();
            case SPECIAL:
                return playerSpecial();
            case QUIT:
                toMenu();
                break;
        }
        return null;
    }

    //Commands

    //Player Attack - ATTACK
    private String playerAttack() {
        //Compute Damage
        int hploss = calculatePlayerAttack();
        curEnemy.setHealth(curEnemy.getHealth() - hploss);
        return printBattleResult(dungeonPet.getName(), hploss, Enemy.Action.ATTACK);
    }
    
    //Calculate Player Attack
    private int calculatePlayerAttack(){
        int hploss = 0;
        //Get Attack Damage
        if (dungeonPet.getAttack() < enemyTempDefense) {
            hploss = 1;
        } else if (dungeonPet.getAttack() == enemyTempDefense) {
            //Deal Luck, if roll lands deal half damage
            if (rollLuck()) {
                hploss += (dungeonPet.getAttack() - enemyTempDefense) / 2;
            }
        } else {
            hploss += dungeonPet.getAttack() - enemyTempDefense;
            //Deal Luck, if roll lands double damage
            if (rollLuck()) {
                hploss += dungeonPet.getAttack() - enemyTempDefense;
            }
        }
        return hploss;
    }

    //Player Defend - DEFEND
    private String playerDefend() {
        //Increase Defense by 1.5x
        tempDefense = calculatePlayerDefend();
        return printBattleResult(dungeonPet.getName(), 0, Enemy.Action.DEFEND);
    }
    
    private int calculatePlayerDefend() {
        return dungeonPet.getDefense() + (dungeonPet.getDefense() / 2);
    }

    //Player Special - SPECIAL
    private String playerSpecial() {
        //Deal Special Damage, only if Special is availabe
        if (checkSpecial()) {
            //Compute Special Damage
            int hploss = calculatePlayerSpecial();
            //Print Message
            return printBattleResult(dungeonPet.getName(), hploss, Enemy.Action.SPECIAL);
        } else {
            //If Special is Unavailable, let Player input again
            return "Unable To Attack! Must wait to charge.";
        }
    }

    public boolean checkSpecial() {
        return specialCounter >= specialCounterLimit;
    }

    //Calculate Player Special Damage
    private int calculatePlayerSpecial() {
        int defense = curEnemy.getSpecialDefense() / 10;
        int hploss;
        if (dungeonPet.getSpecialAttack() <= defense) {
            hploss = 1;
        } else {
            hploss = (dungeonPet.getSpecialAttack() - defense);
        }
        //Compute Damge
        curEnemy.setHealth(curEnemy.getHealth() - hploss);
        //Reset Special Counter
        specialCounter = 0;
        return hploss;
    }

    //Process Enemy Actions
    //If Enemy Attack
    private String enemyAttack() {
        int hploss = calculateEnemyAttack();
        //Compute Damage
        dungeonPet.setHealth(dungeonPet.getHealth() - hploss);
        //Print Message
        return printBattleResult(curEnemy.getName(), hploss, Enemy.Action.ATTACK);
    }
    
    private int calculateEnemyAttack(){
        int hploss = 0;
        //Get Damage Dealt
        if (curEnemy.getAttack() < tempDefense) {
            hploss = 1;
        } else if (curEnemy.getAttack() == tempDefense) {
            //Deal Luck, if roll lands deal half damage
            if (!rollLuck()) {
                hploss += (curEnemy.getAttack() - tempDefense) / 2;
            }
        } else {
            hploss += curEnemy.getAttack() - tempDefense;
            //Deal Luck, if roll lands double damage
            if (!rollLuck()) {
                hploss += (curEnemy.getAttack() - tempDefense);
            }
        }
        return hploss;
    }

    //If Enemy Defends
    private String enemyDefend() {
        //Compute Defense
        enemyTempDefense = calculateEnemyDefend();
        //Print Message
        return printBattleResult(curEnemy.getName(), 0, Enemy.Action.DEFEND);
    }
    
    private int calculateEnemyDefend(){
        return curEnemy.getDefense() + (curEnemy.getDefense() / 2);
    }

    //If Enemy Special
    private String enemySpecial() {
        //Compute Damage
        int hploss = calculateEnemySpecial();
        dungeonPet.setHealth(dungeonPet.getHealth() - hploss);
        //Print Message
        return printBattleResult(curEnemy.getName(), hploss, Enemy.Action.SPECIAL);
    }
    
    private int calculateEnemySpecial() {
        //Compute Special Damage
        int defense = dungeonPet.getSpecialDefense() / 10;
        int hploss = 0;
        if (curEnemy.getSpecialAttack() < defense) {
            hploss = 1;
        } else {
            hploss = (curEnemy.getSpecialAttack() - defense);
        }
        return hploss;
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

    //Roll Luck for Damage Step
    private boolean rollLuck() {
        int playerRoll = rand.nextInt(dungeonPet.getLuck());
        int enemyRoll = rand.nextInt(curEnemy.getLuck());
        return playerRoll > enemyRoll;
    }
}
