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
    STARTUP, MENU, PETROOM, DUNGEON, QUIT, ALL;
}
