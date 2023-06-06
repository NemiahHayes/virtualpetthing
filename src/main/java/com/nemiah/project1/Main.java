/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nemiah.project1;

import com.nemiah.project1.entitiesbase.Pet;
import com.nemiah.project1.entitiesbase.Player;
import static com.nemiah.project1.State.QUIT;
import com.nemiah.project1.gui.MainFrame;
import java.io.IOException;

/**
 *
 * @author nemiah
 */
public class Main {

    private static MainFrame frame;
    private static Player player;
    private static Pet pet;

    public static void main(String[] args) throws IOException {    
        //Initialize 
        frame = new MainFrame();
        setPanel(State.STARTUP);
    }

    public static void setPanel(State state) {
        if (state == QUIT) {
            frame.dispose();
            System.exit(0);
        } else {
            frame.changePanel(state);
        }
    }

    //Setters and Getters
    public static Player getMainPlayer() {
        System.out.println("Returning : " + player);
        return player;
    }

    public static Pet getMainPet() {
        return pet;
    }

    public static void setMainPlayer(Player updatePlayer) {
        player = updatePlayer;
        System.out.println("Updated : " + player);
    }
    
    public static void setMainPet(Pet updatePet){
        pet = updatePet;
    }
    
}
