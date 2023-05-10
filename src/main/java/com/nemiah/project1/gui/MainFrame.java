/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nemiah.project1.gui;

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
    private final int WINDOW_HEIGHT = 800;
    public static JFrame frame;
    
    public MainFrame(){
        initialize();
    }
    
    public void update(){
        frame.repaint();
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
    
    public void startUp(){
        Startup startup = new Startup();
        frame.add(startup.getPanel(), BorderLayout.CENTER);
        frame.setVisible(true);
    }
    
    public void startMenu(){
        
    }
    
}
