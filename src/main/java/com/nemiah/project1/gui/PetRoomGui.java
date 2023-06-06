/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nemiah.project1.gui;

import com.nemiah.project1.State;
import com.nemiah.project1.data.Commands;
import com.nemiah.project1.data.PetRoomData;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author nemiah
 */
public class PetRoomGui extends BaseGui {

    PetRoomData petRoomData;

    //JLabels
    JLabel petName, petHealth, petAttack, petDefense, petSPAttack,
            petSPDefense, petLuck, petLevel, petHunger, petMood,
            playerName, playerFood;

    public PetRoomGui() {
        super(State.PETROOM);
        petRoomData = new PetRoomData();
        startPanel();
    }

    @Override
    protected void initialize() {
        //Room Title
        JLabel roomTitle = setTitle("Pet Room");
        panel.add(roomTitle, "cell 0 0, left, wrap");

        //Error Text
        JLabel error = setGenericLabel("");

        //Render Stats
        renderStats();

        //Buttons
        //Quit Button
        JButton quitPet = setGenericButton("Back");
        quitPet.addActionListener((ActionEvent e) -> {
            petRoomData.callCommand(Commands.QUIT);
        });

        //Play Pet
        JButton playPet = setGenericButton("Play");
        playPet.addActionListener((ActionEvent e) -> {
            error.setText(playAction());
            updateStats();
        });

        //Feed Pet
        JButton feedPet = setGenericButton("Feed");
        feedPet.addActionListener((ActionEvent e) -> {
            error.setText(feedAction());
            updateStats();
        });

        panel.add(quitPet, "cell 0 10, width 150, center");
        panel.add(playPet, "cell 1 10, width 150, center");
        panel.add(feedPet, "cell 2 10, width 150, center, wrap");
        panel.add(error, "span");
    }

    private String feedAction() {
        boolean validFeed = petRoomData.callCommand(Commands.FEED);
        if (validFeed) {
            return petRoomData.getPet().getName() + " has been fed!";
        } else {
            return "You have no food!";
        }
    }

    private String playAction() {
        boolean validFeed = petRoomData.callCommand(Commands.PLAY);
        if (validFeed) {
            return petRoomData.getPet().getName() + " has been playing!";
        } else {
            return petRoomData.getPet().getName() + " has no energy!";
        }
    }

    private void updateStats() {
        petName.setText("Pet Name : " + petRoomData.getPet().getName());
        petHealth.setText("Health : " + petRoomData.getPet().getHealth());
        petAttack.setText("Attack : " + petRoomData.getPet().getAttack());
        petDefense.setText("Defense : " + petRoomData.getPet().getDefense());
        petSPAttack.setText("SP Atk : " + petRoomData.getPet().getSpecialAttack());
        petSPDefense.setText("SP Def : " + petRoomData.getPet().getSpecialDefense());
        petLuck.setText("Luck : " + petRoomData.getPet().getLuck());
        petLevel.setText("Level : " + petRoomData.getPet().getLevel());

        //Pet Emotions
        petHunger.setText("Hunger : " + petRoomData.getPet().getHunger());
        petMood.setText("Mood : " + petRoomData.getPet().getMood());

        //Player
        playerName.setText("Player Name : " + petRoomData.getPlayer().getName());
        playerFood.setText("Food : " + petRoomData.getPlayer().getFood());
    }

    //Render Stats
    private void renderStats() {
        //Pet Stats
        petName = setGenericLabel("Pet Name : " + petRoomData.getPet().getName());
        petHealth = setGenericLabel("Health : " + petRoomData.getPet().getHealth());
        petAttack = setGenericLabel("Attack : " + petRoomData.getPet().getAttack());
        petDefense = setGenericLabel("Defense : " + petRoomData.getPet().getDefense());
        petSPAttack = setGenericLabel("SP Atk : " + petRoomData.getPet().getSpecialAttack());
        petSPDefense = setGenericLabel("SP Def : " + petRoomData.getPet().getSpecialDefense());
        petLuck = setGenericLabel("Luck : " + petRoomData.getPet().getLuck());
        petLevel = setGenericLabel("Level : " + petRoomData.getPet().getLevel());

        //Pet Emotions
        petHunger = setGenericLabel("Hunger : " + petRoomData.getPet().getHunger());
        petMood = setGenericLabel("Mood : " + petRoomData.getPet().getMood());

        //Player
        playerName = setGenericLabel("Player Name : " + petRoomData.getPlayer().getName());
        playerFood = setGenericLabel("Food : " + petRoomData.getPlayer().getFood());

        //Stats
        panel.add(petName, "cell 0 2, left, wrap");
        panel.add(petHealth, "left");
        panel.add(petAttack, "left");
        panel.add(petDefense, "left, wrap");
        panel.add(petSPAttack, "left");
        panel.add(petSPDefense, "left");
        panel.add(petLuck, "left, wrap");
        panel.add(petLevel, "left");

        //Add Emotions
        panel.add(petHunger, "left");
        panel.add(petMood, "left, wrap");

        //Player
        panel.add(playerName, "cell 0 9, left");
        panel.add(playerFood, "left, wrap");
    }

    @Override
    protected JLabel setGenericLabel(String text) {
        //Set Label Text
        JLabel genericText = new JLabel(text);
        Font genericFont = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
        genericText.setFont(genericFont);
        genericText.setForeground(textColour);

        return genericText;
    }

}
