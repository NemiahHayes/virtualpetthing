/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nemiah.project1;

import static com.nemiah.project1.Commands.HELP;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author nemiah
 */
public class PetRoom extends Room {

    private boolean valid = false;

    //Initialize Petroom State
    public PetRoom() {
        super(State.PETROOM);
    }

    //Main Loop
    @Override
    protected void mainLoop() {
        while (this.getActive()) {
            printScreen();
            getInput();
        }
    }

    //Print Menu
    private void printScreen() {
        System.out.println("                     : Pet Room :");
        petScreen();
    }

    //Print Pet Screen
    private void petScreen() {
        System.out.println("   z  z");
        System.out.println("(  - u -)");
        System.out.println("---------------------");
        petStats();
    }

    //Print Pet Stats
    private void petStats() {
        System.out.println("Food : " + getPlayer().getFood());
        System.out.println();
        System.out.println("        Pet");
        System.out.println("---------------------");
        System.out.println("Name   : " + getPet().getName());
        System.out.println("Level  : " + getPet().getLevel());
        System.out.println("Exp    : " + getPet().getExp());
        System.out.println("Hunger : " + getPet().getHunger());
        System.out.println("Mood   : " + getPet().getMood());
        System.out.println("---------------------");
        System.out.println("Attack         : " + getPet().getAttack());
        System.out.println("Defense        : " + getPet().getDefense());
        System.out.println("Spc. Attack    : " + getPet().getSpecialAttack());
        System.out.println("Spc. Defense   : " + getPet().getSpecialDefense());
        System.out.println("Luck           : " + getPet().getLuck());
    }

    //Get & Translate Player Input
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

    //Translate Command 
    private void parseInput(Commands cmd) {
        switch (cmd) {
            //Room Specific
            case PLAY:
                playPet();
                break;
            case FEED:
                feedPet();
                break;
            case RENAME:
                namePet();
                break;
            //All State Commands
            case STOP:
                stopGame();
                break;
            case QUIT:
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

    //Commands List
    //Rename Pet - "rename"
    private void namePet() {
        Scanner scanner = new Scanner(System.in);
        boolean validName = false;
        String name = "";
        System.out.println("Please Enter Pet Name: ");
        while (!validName) {
            try {
                name = scanner.nextLine();
                if (name.length() > 0) {
                    validName = true;
                }
            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("Input Mismatch. Try Again.");
                validName = false;
            }

            if (!validName) {
                System.out.println("Please Enter Pet Name: ");
            }
        }
        getPet().setName(name);
    }

    //Play with Pet - "play"
    private void playPet() {
        if (getPet().getMood() >= 100) {
            System.out.println("Pet can't be any happier!");
        } else if (getPet().getHunger() <= 0) {
            System.out.println("Pet is too hungry to play!");
        } else {
            //Set Mood and Hunger
            int moodAmount = getPet().getLevel() * 2;
            getPet().setMood(getPet().getMood() + moodAmount);
            getPet().setHunger(getPet().getHunger() - 1);
            //Give EXP
            int expAmount = getPet().getLevel() * 25;
            getPet().setExp(getPet().getExp() + expAmount);
            //Print Message
            System.out.println(getPet().getName() + " is Happier!");
        }
    }

    //Feed Pet - "feed"
    private void feedPet() {
        if (getPet().getHunger() >= 100) {
            System.out.println("Pet is Already Full!");
        } else if (getPlayer().getFood() > 0) {
            //Give Food
            int foodAmount = getPet().getHunger() + getPet().getLevel() * 10;
            //Make Sure Hunger doesn't go Over 100
            if (foodAmount > 100){
                int returns = foodAmount - 100;
                getPlayer().setFood(getPlayer().getFood() + returns);
                getPet().setHunger(100);
            } else {
                getPet().setHunger(foodAmount);
            }
            //Give EXP
            int expAmount = getPet().getLevel() * 25;
            getPet().setExp(getPet().getExp() + expAmount);
            //Take Food From Player
            getPlayer().setFood(getPlayer().getFood() - 1);
            //Print Message
            System.out.println(getPet().getName() + " has Eaten!");
        } else {
            System.out.println("You have no Food! Enter the Dungeon to get more.");
        }
    }
}
