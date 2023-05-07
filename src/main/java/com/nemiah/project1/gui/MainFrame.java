/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nemiah.project1.gui;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author nemiah
 */
public class MainFrame {
    
    private final int WINDOW_WIDTH = 400;
    private final int WINDOW_HEIGHT = 800;
    
    public MainFrame(){
        initialize();
    }
    
    public void initialize(){
        
        String windowTitle = "Dungeon Pet Simulator";
        
        JFrame frame = new JFrame();
        frame.setTitle(windowTitle);   
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        
        JPanel mainPanel = new JPanel(new MigLayout());
        
        mainPanel.add(new JButton("Button"));
        mainPanel.setVisible(true);
        
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
        
    }
    
}
