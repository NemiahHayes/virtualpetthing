/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nemiah.project1.gui;

import com.nemiah.project1.data.Room;
import com.nemiah.project1.State;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author nemiah
 */
public abstract class RoomGUI {
    
    protected JPanel panel;
    protected final Color backgroundColour;
    protected final Color textColour;
    
    public RoomGUI(State state){
        //Set Master Data
        //Set Panels
        panel = new JPanel(new MigLayout("", "[center]", ""));
        //Set Colours
        backgroundColour = new Color(42,157,143);
        textColour = new Color(255,255,255);
    }
    
    public JPanel getPanel(){
        return this.panel;
    }
    
    protected void startPanel(){
        initialize();
    }
    protected abstract void initialize();

    //Generic Button
    protected JButton setGenericButton(String buttonText){
        JButton confirmButton = new JButton(buttonText);
        confirmButton.setBorderPainted(true);
        confirmButton.setFocusPainted(false);
        confirmButton.setBackground(textColour);
        
        return confirmButton;
    }

    protected JLabel setGenericLabel(String text) {
        //Set Label Text
        JLabel genericText = new JLabel(text);
        Font genericFont = new Font(Font.SERIF, Font.PLAIN, 12);
        genericText.setForeground(textColour);

        return genericText;
    }
}
