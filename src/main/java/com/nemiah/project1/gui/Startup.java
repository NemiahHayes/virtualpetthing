/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nemiah.project1.gui;

import com.nemiah.project1.StartData;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author nemiah
 */
public class Startup extends RoomGUI{
    
    //Data Object for Room
    private final StartData startData;
    
    //Colors
    Color background;
    Color text;
    
    public Startup(){
        //Create Variables
        startData = new StartData();
        background = new Color(42,157,143);
        text = new Color(255,255,255);
        
        //Create Panel
        startPanel();
    }
    
    @Override
    protected JPanel initialize() {
        //Create Panel
        panel = new JPanel(new MigLayout("", "[center]", ""));

        //Design Panel
        panel.setBackground(background);

        //Initialize Elements
        JLabel title = setTitle();
        
        //User Prompt Text
        JLabel askText = setLabel("Please enter Player Name and Pet Name");
        
        //Player Name & Pet Name
        JLabel playerNameLabel = setLabel("Player Name : ");
        JTextField playerName = setTextField();
        
        //Pet Name
        JLabel petNameLabel = setLabel("Pet Name : ");
        JTextField petName = setTextField();

        //Design Button
        JButton confirmButton = setConfirmButton();
        JLabel confirmLabel = new JLabel("");
        
        //Confirm Button Listener
        confirmButton.addActionListener((ActionEvent e) -> {
            //Validate Input and Update User Prompt
            confirmLabel.setText(startData.validateInput(startData.validNames(playerName,petName)));
        });

        //Add Elements to Panel
        panel.add(title, "wrap, cell 1 0");
        panel.add(askText, "wrap, cell 1 1");
        panel.add(playerNameLabel, "right");
        panel.add(playerName, "wrap, left, grow");
        panel.add(petNameLabel, "right");
        panel.add(petName, "wrap, left, grow");
        panel.add(confirmButton, "wrap, cell 1 4");
        panel.add(confirmLabel, "cell 1 5");

        return panel;
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
        
    //Name Labels
    private JLabel setLabel(String nameLabel) {
        JLabel playerNameLabel = new JLabel(nameLabel);
        playerNameLabel.setForeground(text);
        return playerNameLabel;
    }
    
    //Text Fields
    private JTextField setTextField(){
        return new JTextField(15);
    }
    
    //Confirm Button
    private JButton setConfirmButton(){
        JButton confirmButton = new JButton("Confirm");
        confirmButton.setBorderPainted(true);
        confirmButton.setFocusPainted(false);
        confirmButton.setBackground(text);
        
        return confirmButton;
    }
}
