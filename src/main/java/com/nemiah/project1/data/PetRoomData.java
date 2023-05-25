/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nemiah.project1.data;

import com.nemiah.project1.State;
import static com.nemiah.project1.Other.Commands.HELP;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author nemiah
 */
public class PetRoomData extends Room {

    private boolean valid = false;

    //Initialize Petroom State
    public PetRoomData() {
        super(State.PETROOM);
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

    //Commands List
    //Rename Pet - "rename"
    private void namePet() {

    }

    //Play with Pet - "play"
    private void playPet() {

    }

    //Feed Pet - "feed"
    private void feedPet() {

    }
}
