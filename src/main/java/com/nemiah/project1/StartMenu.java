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
public class StartMenu extends Room {

    private boolean valid = false;

    public StartMenu() {
        super(State.MENU);
    }

    //Main Menu Loop
    @Override
    protected void mainLoop() {
        while (this.getActive()) {
            printScreen();
            getInput();
        }
    }

    //Print Menu
    private void printScreen() {
        System.out.println();
        System.out.println("                     : Dungeon Pet :");
        System.out.println("    : Pet ");
        System.out.println("    : Dungeon ");
        System.out.println("    : Quit ");
        System.out.println("This App is navigated with commands. Type 'help' to view the command list.");
        System.out.println();
    }

    //Get Player Input
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
            case PET:
                toPetRoom();
                break;
            case DUNGEON:
                toDungeon();
                break;
            case OVERWRITE:
                toOverwrite();
                break;
            //All State Commands
            case STOP:
                stopGame();
                break;
            case QUIT:
                stopGame();
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

    //Command List
    //Go to Petroom - pet
    private void toPetRoom() {
        Main.setState(State.PETROOM);
        endRoom();
    }

    //Go to Dungeon - dungeon
    private void toDungeon() {
        Main.setState(State.DUNGEON);
        endRoom();
    }

    //Overwrite File - overwrite
    private void toOverwrite() {
        System.out.println("Generating new save file...");

        //Set Objects to Default
        Player newPlayer = new Player();
        setPlayer(newPlayer);
        Pet newPet = new Pet();
        setPet(newPet);
        System.out.println("New Save Generated. Sending to Startup Screen...");

        //Objects overwrite on Room exit
        Main.setState(State.STARTUP);
        endRoom();
    }

}
