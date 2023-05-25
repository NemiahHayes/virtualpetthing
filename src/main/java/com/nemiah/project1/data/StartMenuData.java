/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nemiah.project1.data;

import com.nemiah.project1.redundant.CommandParser;
import com.nemiah.project1.redundant.Commands;
import com.nemiah.project1.Main;
import com.nemiah.project1.State;
import com.nemiah.project1.entitiesbase.Pet;
import com.nemiah.project1.entitiesbase.Player;
import static com.nemiah.project1.redundant.Commands.HELP;
/**
 *
 * @author nemiah
 */
public class StartMenuData extends Room {

    public StartMenuData() {
        super(State.MENU);
    }

    //Go to Petroom - pet
    public void toPetRoom() {
        Main.setPanel(State.PETROOM);
        endRoom();
    }

    //Go to Dungeon - dungeon
    public void toDungeon() {
        Main.setPanel(State.DUNGEON);
        endRoom();
    }
}
