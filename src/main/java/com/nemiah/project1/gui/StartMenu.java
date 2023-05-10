/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nemiah.project1.gui;

import com.nemiah.project1.StartMenuData;
import com.nemiah.project1.State;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author nemiah
 */
public class StartMenu extends RoomGUI {

    //Data
    StartMenuData startMenuData;
    
    public StartMenu() {
        super(State.MENU);
        startMenuData = new StartMenuData();
        
        //Show Panel
        startPanel();
    }

    @Override
    protected void initialize() {
        //Design Panel
        panel.setBackground(background);
        
        //Manage Layout
        MigLayout layout = new MigLayout("insets 10 75 10 25", "", "");
        panel.setLayout(layout);
        
        //Title
        JLabel title = setTitle();
        
        //Dungeon Button
        JButton dungeonButton = setGenericButton("Dungeon");
        //DungeonButton Action
        dungeonButton.addActionListener((ActionEvent e) -> {
            dungeonButtonAction();
        });
        
        //PetRoom Button
        JButton petRoomButton = setGenericButton("Pet Room");
        //PetRoom Action
        petRoomButton.addActionListener((ActionEvent e) -> {
            petRoomButtonAction();
        });
        
        //Exit Button
        JButton exitButton = setGenericButton("Exit");
        //Exit Action
        exitButton.addActionListener((ActionEvent e) -> {
            exitButtonAction();
        });
        
        //Add Elements to Panel
        panel.add(title, "cell 1 17, width 100, right");
        panel.add(dungeonButton, "wrap, cell 1 21, width 150, center");
        panel.add(petRoomButton, "wrap, cell 1 22, width 150, center");
        panel.add(exitButton, "wrap, cell 1 23, width 150, center");
    }
    
    //Button Actions
    private void dungeonButtonAction(){
        startMenuData.toDungeon();
    }
    
    private void petRoomButtonAction(){
        startMenuData.toPetRoom();
    }
    
    private void exitButtonAction(){
        startMenuData.stopGame();
    }
    
    //Set Main Title
    private JLabel setTitle(){
        //Set Title Text
        JLabel title = new JLabel("Dungeon Pet Simulator");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 20);
        title.setForeground(text);
        title.setFont(titleFont);
        
        return title;
    }
    
}
