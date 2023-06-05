/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nemiah.project1.gui;

import com.nemiah.project1.data.StartData;
import com.nemiah.project1.State;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author nemiah
 */
public class StartupGui extends BaseGui{
    
    //Data
    StartData startData;
    
    public StartupGui(){
        //Create Variables
        super(State.STARTUP);
        startData = new StartData();
        
        //Create Panel
        startPanel();
    }
    
    @Override
    protected void initialize() {
        //Initialize Elements
        JLabel title = setTitle("Dungeon Pet Simulator");
        
        //User Prompt Text
        JLabel askText = setLabel("Please enter Player Name");
        
        //Player Name & Pet Name
        JLabel playerNameLabel = setLabel("Player Name : ");
        JTextField playerName = setTextField();
        
        //Pet Name
        JLabel petNameLabel = setLabel("Pet Name : ");
        JTextField petName = setTextField();

        //Design Button
        JLabel confirmLabel = new JLabel("");
        JButton confirmButton = setGenericButton("Confirm");
        
        //Confirm Button Listener
        confirmButton.addActionListener((ActionEvent e) -> {
            confirmLabel.setText(validateNames(playerName, petName));
        });

        //Add Elements to Panel
        panel.add(title, "wrap, cell 1 0");
        panel.add(askText, "wrap, cell 1 1");
        panel.add(playerNameLabel, "right");
        panel.add(playerName, "wrap, left, grow");
        panel.add(petNameLabel,"right");
        panel.add(petName, "wrap, left, grow");
        panel.add(confirmButton, "wrap, cell 1 4");
        panel.add(confirmLabel, "cell 1 5");
    }
        
    //Name Labels
    private JLabel setLabel(String nameLabel) {
        JLabel playerNameLabel = new JLabel(nameLabel);
        playerNameLabel.setForeground(textColour);
        return playerNameLabel;
    }
    
    //Text Fields
    private JTextField setTextField(){
        return new JTextField(15);
    }
    
    private String validateNames(JTextField playerName, JTextField petName){
        if (!startData.validateNames(playerName.getText(), petName.getText())){
            return "Please Enter Valid Names.";
        }
        return "";
    }
}
