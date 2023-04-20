/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nemiah.project1;

import static com.nemiah.project1.Commands.ATTACK;
import static com.nemiah.project1.Commands.DEFEND;
import static com.nemiah.project1.Commands.HELP;
import static com.nemiah.project1.Commands.QUIT;
import static com.nemiah.project1.Commands.SPECIAL;
import static com.nemiah.project1.Commands.STOP;
import static com.nemiah.project1.Commands.UNKNOWN;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author nemiah
 */
public class Dungeon extends Room {

    //Final Objects and Lists
    private final HashMap<Integer, Enemy> enemyMap = new HashMap();
    private ArrayList<String> enemyNames = new ArrayList();
    private final Random rand = new Random();
    private final FileParser parser = new FileParser();

    //Dungeon Level
    private final int dungeonLevel;
    private boolean valid = false;
    private boolean roundStart = false;
    private final int specialCounterLimit = 5;
    private Enemy curEnemy;
    private int placing;
    private int specialCounter;

    //Temp Pet Stats
    private final Pet dungeonPet;
    private int tempDefense;
    private boolean analyze;
    private int enemyTempDefense;

    //Initialize Room with DUNGEON state
    public Dungeon() {
        super(State.DUNGEON);
        //Store Dungeon Level and Pet
        dungeonLevel = getPlayer().getDungeonLevel();
        dungeonPet = new Pet();
        setDungeonPet();
        //Set Temp Variables and Generate Enemies
        setPlacing(0);
        setAnalyze(false);
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

    //Room Main Loop
    @Override
    protected void mainLoop() {
        while (this.getActive()) {
            //Initialize Pet and Enemy
            tempDefense = getPet().getDefense();
            setAnalyze(false);
            curEnemy = pullEnemy(getPlacing());
            enemyTempDefense = curEnemy.getDefense();

            //Start Battle
            roundStart = true;
            //Print Dialog
            System.out.println("Battle Started! " + dungeonPet.getName() + " is battling " + curEnemy.getName());
            while (roundStart) {
                printScreen();
                //Player Goes First
                getInput();
                //Enemy Goes Second, Reset Enemy Defense
                enemyTempDefense = curEnemy.getDefense();
                enemyInput();
                //Reset Round Variables
                resetRound();

                //Check Win Conditions
                if (dungeonPet.getHealth() <= 0) {
                    playerLoseRound();
                }

                if (curEnemy.getHealth() <= 0) {
                    playerWinRound();
                }
            }
        }
    }

    //Draw Screen
    private void printScreen() {
        //Print Enemy
        System.out.println("---------------------");
        System.out.println("             ,   ,");
        System.out.println("          ( a _ a  )");
        System.out.println("---------------------");
        System.out.println(curEnemy.getName());
        System.out.println("HP : " + curEnemy.getHealth() + "  |  Lvl : " + curEnemy.getLevel());
        //If Player Enters ANALYZE command
        if (getAnalyze()) {
            printEnemyStats();
        }
        //Print Player
        System.out.println("---------------------");
        System.out.println("    1  1");
        System.out.println(" (       )");
        System.out.println("---------------------");
        System.out.println(getPet().getName());
        System.out.println("HP : " + dungeonPet.getHealth() + "  |  Lvl : " + dungeonPet.getLevel());
        System.out.println("HP : " + getPet().getHealth() + "  |  Lvl : " + getPet().getLevel());
        System.out.print("ATTACK  |   DEFEND  |   SPECIAL ATTACK ");
        //Print Special Charge
        for (int i = 0; i < specialCounter; i++) {
            System.out.print("[]");
        }
        if (specialCounter >= specialCounterLimit) {
            System.out.print(" CHARGED");
        }
        System.out.println();
    }

    //Draw Enemy Stats
    private void printEnemyStats() {
        System.out.println("ATK : " + curEnemy.getAttack() + "  |  DEF : " + curEnemy.getDefense());
        System.out.println("SPCATK : " + curEnemy.getSpecialAttack() + "  |  SPCDEF : " + curEnemy.getSpecialDefense());
        System.out.println("LCK : " + curEnemy.getLuck());
    }

    //End of Round Rewards
    private void playerWinRound() {
        //Print Victory Message
        System.out.println(curEnemy.getName() + " has been slain!");
        //Give Victory Rewards
        int foodAmount = curEnemy.getLevel() * 5;
        getPlayer().setFood(getPlayer().getFood() + foodAmount);
        int expAmount = curEnemy.getLevel() * 25;
        getPet().setExp(getPet().getExp() + expAmount);
        //Increase Dungeon Place
        setPlacing(getPlacing() + 1);

        //Increase Dungeon Level and Exit Dungeon
        if (getPlacing() >= 5) {
            System.out.println();
            System.out.println("Dungeon Completed on this Difficulty. Returning to Main Menu...");
            getPlayer().setDungeonLevel(getPlayer().getDungeonLevel() + 1);
            parseInput(Commands.QUIT);
        } else {
            //Else Restart to next Round
            System.out.println();
            System.out.println("Finding new Opponent...");
            setRoundStart(false);
        }
    }

    //If Player HP = 0
    private void playerLoseRound() {
        System.out.println("HP 0! Returning to Menu...");
        getPet().setMood(getPet().getMood() - 5);
        parseInput(Commands.QUIT);
    }

    //Reset Round Buffs
    private void resetRound() {
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

    //Enemy Behaviour
    private void enemyInput() {
        switch (curEnemy.getAction()) {
            case ATTACK:
                enemyAttack();
                break;
            case DEFEND:
                enemyDefend();
                break;
            case SPECIAL:
                enemySpecial();
                break;
            default:
                System.out.println("null");
                break;
        }
    }

    //Get and Set Methods
    private int getDungeonLevel() {
        return this.dungeonLevel;
    }

    private boolean getAnalyze() {
        return this.analyze;
    }

    private void setAnalyze(boolean analyze) {
        this.analyze = analyze;
    }

    private void setPlacing(int placing) {
        this.placing = placing;
    }

    private int getPlacing() {
        return this.placing;
    }

    //Get User Input
    private void getInput() {
        valid = false;
        Scanner scanner = new Scanner(System.in);
        while (!valid) {
            valid = true;
            String input = "";
            try {
                input = scanner.nextLine();
            } catch (InputMismatchException e) {

            }
            Commands cmd = CommandParser.parseCommand(input, getState());
            parseInput(cmd);
        }
    }

    //Translate Input
    private void parseInput(Commands cmd) {
        switch (cmd) {
            //Room Specific
            case ATTACK:
                playerAttack();
                break;
            case DEFEND:
                playerDefend();
                break;
            case SPECIAL:
                playerSpecial();
                break;
            case ANALYZE:
                playerAnalyze();
                break;
            //All State Commands
            case STOP:
                setRoundStart(false);
                stopGame();
                break;
            case QUIT:
                setRoundStart(false);
                toMenu();
                break;
            case HELP:
                printCommands();
                valid = false;
                break;
            case UNKNOWN:
                CommandParser.unknownError();
                valid = false;
                break;
        }
    }

    //Stop and Start Rounds
    private void setRoundStart(boolean roundStart) {
        this.roundStart = roundStart;
    }

    //Commands
    //Player Analyze - ANALYZE
    private void playerAnalyze() {
        //Set Analyze True, Now Render Enemy Stats
        setAnalyze(true);
    }

    //Player Attack - ATTACK
    private void playerAttack() {
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
        //Compute Damage
        curEnemy.setHealth(curEnemy.getHealth() - hploss);
        printBattleResult(dungeonPet.getName(), hploss, Enemy.Action.ATTACK);
    }

    //Player Defend - DEFEND
    private void playerDefend() {
        //Increase Defense by 1.5x
        tempDefense = dungeonPet.getDefense() + (dungeonPet.getDefense() / 2);
        printBattleResult(dungeonPet.getName(), 0, Enemy.Action.DEFEND);
    }

    //Player Special - SPECIAL
    private void playerSpecial() {
        //Deal Special Damage, only if Special is availabe
        if (specialCounter >= specialCounterLimit) {
            //Compute Special Damage
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
            //Print Message
            printBattleResult(dungeonPet.getName(), hploss, Enemy.Action.SPECIAL);
        } else {
            //If Special is Unavailable, let Player input again
            valid = false;
            System.out.println("Unable To Attack! Must wait to charge.");
        }
    }

    //Process Enemy Actions
    //If Enemy Attack
    private void enemyAttack() {
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
        //Compute Damage
        dungeonPet.setHealth(dungeonPet.getHealth() - hploss);
        //Print Message
        printBattleResult(curEnemy.getName(), hploss, Enemy.Action.ATTACK);
    }

    //If Enemy Defends
    private void enemyDefend() {
        //Compute Defense
        enemyTempDefense = curEnemy.getDefense() + (curEnemy.getDefense() / 2);
        //Print Message
        printBattleResult(curEnemy.getName(), 0, Enemy.Action.DEFEND);
    }

    //If Enemy Special
    private void enemySpecial() {
        //Compute Special Damage
        int defense = dungeonPet.getSpecialDefense() / 10;
        int hploss;
        if (curEnemy.getSpecialAttack() < defense) {
            hploss = 1;
        } else {
            hploss = (curEnemy.getSpecialAttack() - defense);
        }
        //Compute Damage
        dungeonPet.setHealth(dungeonPet.getHealth() - hploss);
        //Print Message
        printBattleResult(curEnemy.getName(), hploss, Enemy.Action.SPECIAL);
    }

    //Display Damage and Turn Damage Results
    private void printBattleResult(String name, int damage, Enemy.Action action) {
        String result = "";
        switch (action) {
            case ATTACK:
                result = name + " attacks! " + damage + " damage dealt.";
                break;
            case DEFEND:
                result = name + " defends!";
                break;
            case SPECIAL:
                result = name + " does the special attack! Dealing " + damage + " damage!";
                break;
            default:
                break;
        }
        System.out.println(result.toUpperCase());
    }

    //Roll Luck for Damage Step
    private boolean rollLuck() {
        int playerRoll = rand.nextInt(dungeonPet.getLuck());
        int enemyRoll = rand.nextInt(curEnemy.getLuck());
        return playerRoll > enemyRoll;
    }
}
