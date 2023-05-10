/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nemiah.project1.gui;

import javax.swing.JPanel;

/**
 *
 * @author nemiah
 */
public abstract class RoomGUI {
    
    protected JPanel panel;
    
    public RoomGUI(){
    }
    
    public JPanel getPanel(){
        return this.panel;
    }
    
    protected void startPanel(){
        initialize();
    }
    protected abstract JPanel initialize();
    
}
