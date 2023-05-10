/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nemiah.project1.gui;

import com.nemiah.project1.State;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author nemiah
 */
public class MainFrame {
    
    private final int WINDOW_WIDTH = 400;
    private final int WINDOW_HEIGHT = 600;
    public static JFrame frame;
    
    public MainFrame(){
        initialize();
    }
    
    public void updateRoom(JPanel panel){
        frame.add(panel, BorderLayout.CENTER);
    }
    
    private void initialize(){
        
        String windowTitle = "Dungeon Pet Simulator";
        
        frame = new JFrame();
        frame.setTitle(windowTitle);   
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        frame.setResizable(false);     
     }
    
    public void changePanel(State state){
        if (state.equals(State.STARTUP)){
            startUp();
        } else if (state.equals(State.MENU)){
            startMenu();
        }
    }
    
    private void startUp(){
        Startup startup = new Startup();
        updateRoom(startup.getPanel());
        frame.setVisible(true);
    }
    
    public void startMenu(){
        StartMenu startMenu = new StartMenu();
        updateRoom(startMenu.getPanel());
        frame.setVisible(true);
    }
    
    public void dispose(){
        frame.dispose();
    }
}
