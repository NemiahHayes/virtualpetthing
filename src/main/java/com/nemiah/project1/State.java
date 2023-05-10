/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nemiah.project1;

/**
 *
 * @author nemiah
 */
public enum State {
    //STARTUP - Load Files, New Player & Pet Creation
    //MENU - Main Menu, Access Other Rooms, Overwite, Etc
    //PETROOM - Manage Pet
    //DUNGEON - Battle 
    //QUIT - End Game State
    //ALL - Command Specific, For Global Commands 
    STARTUP {
        @Override
        public Room getData() {
            return new StartData();
        }
    }, MENU {
        @Override
        public Room getData() {
            return new StartMenuData();
        }
    }, PETROOM {
        @Override
        public Room getData() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }, DUNGEON {
        @Override
        public Room getData() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }, QUIT {
        @Override
        public Room getData() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }, ALL {
        @Override
        public Room getData() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    };
    
    public abstract Room getData();
        
}
