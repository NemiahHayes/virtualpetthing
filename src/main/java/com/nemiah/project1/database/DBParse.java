/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nemiah.project1.database;

import com.nemiah.project1.Pet;
import com.nemiah.project1.Player;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author nemiah
 */
public class DBParse {

    private final String playerTableCreation = "CREATE  TABLE PLAYER  (NAME  VARCHAR(50), DUNGEONLEVEL INT, FOOD INT, PRIMARY KEY (NAME))";
    private final String petTableCreation = "CREATE TABLE PET (NAME VARCHAR(50), HEALTH INT, ATTACK INT, DEFENSE INT, SPECIALATTACK INT, SPECIALDEFENSE INT, LUCK INT, LEVEL INT, EXP INT, HUNGER INT, MOOD INT, PLAYERID VARCHAR(50), FOREIGN KEY (PLAYERID) REFERENCES PLAYER(NAME))";
    private final DBMain dbMain = new DBMain();
    private final Connection conn;
    private Statement statement;
    private ResultSet resultSet;

    //Entities
    Player player;
    Pet pet;
    
    public DBParse() {
        //Establish Connection within dbMain
        conn = dbMain.getConn();
        createTables();
    }
    
    public void setEntities(Player player, Pet pet){
        checkPlayer(player);
        checkPet(pet);
    }
    
    public Player getPlayer(){
        return this.player;
    }
    
    public Pet getPet(){
        return this.pet;
    }

    private void createTables() {
        try {
            statement = conn.createStatement();

            //Create Player Table
            statement.execute(playerTableCreation);

            //Create Pet Table
            statement.execute(petTableCreation);

            //Close
            statement.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("ERROR : " + ex.getMessage());
        }
    }

    //Add Player to Database
    public void checkPlayer(Player player) {
        try {
            statement = conn.createStatement();

            String playerName = player.getName();
            String findPlayer = "SELECT COUNT(*) FROM PLAYER WHERE NAME = '" + playerName + "'";
            statement.execute(findPlayer);
            resultSet.next();
            int count = resultSet.getInt(1);
            resultSet.close();
            
            //Create Player if Player doesn't exist
            if (count == 0) {
                //Create and Execute Statement
                String toAdd = "INSERT INTO PLAYER (NAME,DUNGEONLEVEL, FOOD) VALUES ('"
                        + player.getName() + "',"
                        + player.getDungeonLevel() + ","
                        + player.getFood()
                        + ")";
                statement.execute(toAdd);
            } else {
                //Player Exists
                System.out.println("Player already Exists.");
                loadPlayer(player.getName());
            }

            //close
            statement.close();
        } catch (SQLException ex) {
            System.out.println("ERROR : " + ex.getMessage());
        }
    }

    //Load Player from DataBase
    private void loadPlayer(String playerName) {
        try {
            statement = conn.createStatement();

            //Create and Execute Statement
            String getPlayer = "SELECT (*) FROM PLAYER WHERE NAME = '" + playerName + "'";
            resultSet = statement.executeQuery(getPlayer);

            if (resultSet.first()) {
                String name = resultSet.getString("NAME");
                int dungeonLevel = resultSet.getInt("DUNGEONLEVEL");
                int food = resultSet.getInt("FOOD");

                this.player = new Player(name, dungeonLevel, food);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR : " + ex.getMessage());
        }
    }

    //Add Pet to Database
    public void checkPet(Pet pet) {
        try {
            statement = conn.createStatement();
            
            String playerName = player.getName();
            String findPlayer = "SELECT COUNT(*) FROM PLAYER WHERE NAME = '" + playerName + "'";
            statement.execute(findPlayer);
            resultSet.next();
            int count = resultSet.getInt(1);
            resultSet.close();

            //Create Player if Player doesn't exist
            if (count == 0) {
                //Create and Execute Statement
                String toAdd = "INSERT INTO PET (NAME, HEALTH, ATTACK, DEFENSE, SPECIALATTACK, SPECIALDEFENSE, LUCK, LEVEL, EXP, HUNGER, MOOD, PLAYERID) VALUES ('"
                        + pet.getName() + "',"
                        + pet.getAttack() + ","
                        + pet.getDefense() + ","
                        + pet.getSpecialAttack() + ","
                        + pet.getSpecialDefense() + ","
                        + pet.getLuck() + ","
                        + pet.getLevel() + ","
                        + pet.getExp() + ","
                        + pet.getHunger() + ","
                        + pet.getMood() + ","
                        + pet.getID() + ")";
                statement.execute(toAdd);
            } else {
                //Player Exists
                System.out.println("Player already Exists.");
                loadPet(player.getName());
            }

            //close
            statement.close();
        } catch (SQLException ex) {
            System.out.println("ERROR : " + ex.getMessage());
        }
    }
    //Load Pet from DataBase
    private void loadPet(String playerName) {
        try {
            statement = conn.createStatement();

            //Create and Execute Statement
            String getPet = "SELECT (*) FROM PET WHERE PLAYERID = '" + playerName + "'";
            resultSet = statement.executeQuery(getPet);
            //Create Pet Object
            if (resultSet.first()) {
                String name = resultSet.getString("NAME");
                int health = resultSet.getInt("HEALTH");
                int attack = resultSet.getInt("ATTACK");
                int defense = resultSet.getInt("DEFENSE");
                int specialAttack = resultSet.getInt("SPECIALATTACK");
                int specialDefense = resultSet.getInt("SPECIALDEFENSE");
                int luck = resultSet.getInt("LUCK");
                int level = resultSet.getInt("LEVEL");
                int exp = resultSet.getInt("EXP");
                int hunger = resultSet.getInt("HUNGER");
                int mood = resultSet.getInt("MOOD");
                String id = resultSet.getString("PLAYERID");
                this.pet = new Pet(name,health,attack,defense,specialAttack,specialDefense,luck,level,exp,hunger,mood,id);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR : " + ex.getMessage());
        }
    }
}
