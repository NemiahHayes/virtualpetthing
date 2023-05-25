/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nemiah.project1.gui;

import com.nemiah.project1.State;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
        //Remove Current Panes
        frame.getContentPane().removeAll();
        //Add Panel
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
        } else if (state.equals(State.PETROOM)){
            petRoom();
        }
    }
    
    //Start Startup menu
    private void startUp(){
        StartupGui startup = new StartupGui();
        updateRoom(startup.getPanel());
        frame.setVisible(true);
    }
    
    //Start startMenu
    private void startMenu(){
        StartMenuGui startMenu = new StartMenuGui();
        updateRoom(startMenu.getPanel());
        frame.setVisible(true);
    }
    
    //Start PetRoom
    private void petRoom(){
        PetRoomGui petRoom = new PetRoomGui();
        updateRoom(petRoom.getPanel());
        frame.setVisible(true);
    }
    
    public void dispose(){
        frame.dispose();
    }
}
