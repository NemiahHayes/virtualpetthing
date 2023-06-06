/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nemiah.project1.data;

import com.nemiah.project1.State;

/**
 *
 * @author nemiah
 */
public class PetRoomData extends Room {

    //Initialize Petroom State
    public PetRoomData() {
        super(State.PETROOM);
    }

    //Commands List
    public boolean callCommand(Commands command) {
        switch (command) {
            case PLAY:
                return playPet();
            case FEED:
                return feedPet();
            case QUIT:
                quitPet();
                break;
            default:
                break;
        }
        return false;
    }

    //Play with Pet - "play"
    private boolean playPet() {
        if (getPet().getHunger() > 0) {
            getPet().setMood(getPet().getMood() + 1);
            getPet().setHunger(getPet().getHunger() - 1);
            return true;
        } else {
            return false;
        }
    }

    //Feed Pet - "feed"
    private boolean feedPet() {
        if (getPlayer().getFood() > 0) {
            getPet().setHunger(getPet().getHunger() + 1);
            getPlayer().setFood(getPlayer().getFood() - 1);
            return true;
        } else {
            return false;
        }
    }

    //Quit Pet = "quit"
    private void quitPet() {
        toMenu();
    }
}
