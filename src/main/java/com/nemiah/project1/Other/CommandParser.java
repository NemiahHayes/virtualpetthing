/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nemiah.project1.Other;

import com.nemiah.project1.State;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author nemiah
 */
public class CommandParser {

    private static final Set<Commands> commandList = new HashSet<>(EnumSet.allOf(Commands.class));

    //Get Command List for Specific Rooms
    public static Set<Commands> getCommandList(State room) {
        Set<Commands> roomCommandList = new HashSet();
        commandList.stream().map(c -> {
            if (c.state == room) {
                roomCommandList.add(c);
            }
            return c;
        }).filter(c -> (c.state == State.ALL)).forEachOrdered(c -> {
            roomCommandList.add(c);
        });
        return roomCommandList;
    }

    //Interpret Command when called
    public static Commands parseCommand(String cmd, State state) {
        Set<Commands> roomList;
        roomList = getCommandList(state);
        cmd = cmd.toLowerCase().trim();
        for (Commands c : roomList) {
            if (c.name.equals(cmd)) {
                return c;
            }
        }
        return Commands.UNKNOWN;
    }

    //Print Unknown Message
    public static void unknownError() {
        System.out.println("Unknown Command. Please Try again. Type 'help' to view commands.");
    }

}
