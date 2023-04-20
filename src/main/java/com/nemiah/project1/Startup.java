/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nemiah.project1;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author nemiah
 */
public class Startup {

    private final Player player;
    private final Pet pet;
    private final Scanner scanner = new Scanner(System.in);

    public Startup() {
        this.player = Main.loadPlayer();
        this.pet = Main.loadPet();
    }

    public void initializeGame() {
        //Check if new Player
        if (this.player.getDungeonLevel() == 0) {
            setPlayerName();
            setPetName();
            this.player.setDungeonLevel(1);
            updateSave();
        }

        //Go to Main Menu
        Main.setState(State.MENU);
    }

    //Update Save File
    private void updateSave() {
        FileParser parser = new FileParser(this.player, this.pet);
        parser.writeSave();
    }

    //Get Player Name
    private void setPlayerName() {
        boolean valid = false;
        String name = "";
        String inputMessage = "Please Enter Player Name : ";
        //Print Input Message
        System.out.println(inputMessage);
        while (!valid) {
            try {
                name = scanner.nextLine();
                //Name Length Must be > 0
                if (name.length() > 0) {
                    valid = true;
                } else {
                    System.out.println("ERROR : Please Input Characters for Player Name.");
                }
                //Catch Mismatch Input
            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("ERROR : Input Type Mismatch. Please Try Again.");
                valid = false;
            }
            //If not valid, print message again
            if (!valid) {
                System.out.println(inputMessage);
            }
        }
        //Set Player Name
        this.player.setName(name);
    }

    //Get Pet Name
    private void setPetName() {
        boolean valid = false;
        String name = "";
        String inputMessage = "Please Enter Pet Name : ";
        //Print Input Message
        System.out.println(inputMessage);
        while (!valid) {
            try {
                name = scanner.nextLine();
                //Name Length Must be > 0
                if (name.length() > 0) {
                    valid = true;
                } else {
                    System.out.println("ERROR : Input Type Mismatch. Please Try Again.");
                }
                //Catch Mismatch Input
            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("ERROR : Input Mismatch. Try Again.");
                valid = false;
            }
            //If not valid, print message again
            if (!valid) {
                System.out.println(inputMessage);
            }
        }
        //Set Pet Name
        this.pet.setName(name);
    }
}
