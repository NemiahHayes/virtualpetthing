/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nemiah.project1.database;

import com.nemiah.project1.Pet;
import com.nemiah.project1.Player;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author nemiah
 */
public class DBParse {

    private final DBMain dbMain;
    private final Connection conn;
    private Statement statement;
    private ResultSet resultSet;

    //Entities
    Player player;
    Pet pet;

    public DBParse() {
        //Establish Connection within dbMain
        dbMain = new DBMain();
        conn = dbMain.getConn();
        
        //Create Tables
        createTables();
    }

    public void setEntities(Player player, Pet pet) {
        this.player = player;
        this.pet = pet;
        checkPlayer(player);
        checkPet(pet);
    }
    
    private void createTables() {
        createPlayerTable();
        createPetTable();
    }
    
    public void updateEntities(Player updatePlayer, Pet updatePet){
        this.player = updatePlayer;
        this.pet = updatePet;
        updatePlayer();
        updatePet();
    }
    
    public Pet getPet() {
        return this.pet;
    }

    public Player getPlayer() {
        return this.player;
    }

    private void createPetTable() {
        try {
            // Create Pet Table
            String createPetTable = "CREATE TABLE PET (NAME VARCHAR(50), HEALTH INT, ATTACK INT, DEFENSE INT, SPECIALATTACK INT, "
                    + "SPECIALDEFENSE INT, LUCK INT, LEVEL INT, EXP INT, HUNGER INT, MOOD INT, PLAYERID VARCHAR(50), FOREIGN KEY (PLAYERID) REFERENCES PLAYER(NAME), CONSTRAINT FK_PET_PLAYERID FOREIGN KEY (PLAYERID) REFERENCES PLAYER(NAME), CONSTRAINT UK_PET_NAME UNIQUE (NAME))";
            PreparedStatement petTableStatement = conn.prepareStatement(createPetTable);
            System.out.println("Creating Database");
            petTableStatement.execute();
        } catch (SQLException ex) {
            System.out.println("Error : " + ex);
            if ("X0Y32".equals(ex.getSQLState())) {
                return;
            }
        }
    }
 
    private void createPlayerTable() {
        try {
            // Create Player Table
            String createPlayerTable = "CREATE TABLE PLAYER (NAME VARCHAR(50), DUNGEONLEVEL INT, FOOD INT, PRIMARY KEY (NAME), PRIMARY KEY (NAME), CONSTRAINT UK_PLAYER_NAME UNIQUE (NAME))";
            PreparedStatement playerTableStatement = conn.prepareStatement(createPlayerTable);
            playerTableStatement.execute();
        } catch (SQLException ex) {
            System.out.println("Error : " + ex);
            if ("X0Y32".equals(ex.getSQLState())) {
                return;
            }
        }
    }

    //Add Player to Database
    public void checkPlayer(Player player) {
        try {
            statement = conn.createStatement();

            String playerName = player.getName();
            String findPlayer = "SELECT COUNT(*) FROM PLAYER WHERE NAME = '" + playerName + "'";
            resultSet = statement.executeQuery(findPlayer);

            //Check if Player != 0
            int count = 0;
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
            resultSet.close();

            //Create Player if Player doesn't exist
            if (count == 0) {
                //Create and Execute Statement
                String toAdd = "INSERT INTO PLAYER (NAME,DUNGEONLEVEL, FOOD) VALUES ('"
                        + player.getName() + "',"
                        + player.getDungeonLevel() + ","
                        + player.getFood()
                        + ")";
                statement.executeUpdate(toAdd);
                System.out.println("loadPlayer : " + player.getName());
                loadPlayer(player.getName());
            } else {
                //Player Exists
                this.player = player;
                updatePlayer();
                loadPlayer(player.getName());
            }

            //close
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Player ERROR : " + ex.getMessage());
        }
    }

    //Update Player in Database -- Given by CHATGPT, Modified parts
    private void updatePlayer() {
        try {
            // Create and execute the update statement
            String updateQuery = "UPDATE PLAYER SET NAME = ?, DUNGEONLEVEL = ?, FOOD = ? WHERE NAME = ?";
            try (PreparedStatement updateStatement = conn.prepareStatement(updateQuery)) {
                updateStatement.setString(1, player.getName());
                updateStatement.setInt(2, player.getDungeonLevel());
                updateStatement.setInt(3, player.getFood());
                updateStatement.setString(4,player.getName());
                updateStatement.executeUpdate();
                conn.commit();
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    //Load Player from DataBase
    private void loadPlayer(String playerName) {
        try {
            statement = conn.createStatement();

            //Create and Execute Statement
            String getPlayer = "SELECT * FROM PLAYER WHERE NAME = '" + playerName + "'";
            resultSet = statement.executeQuery(getPlayer);

            if (resultSet.next()) {
                String name = resultSet.getString("NAME");
                int dungeonLevel = resultSet.getInt("DUNGEONLEVEL");
                int food = resultSet.getInt("FOOD");

                this.player = new Player(name, dungeonLevel, food);
            }
        } catch (SQLException ex) {
            System.out.println("Player Load ERROR : " + ex.getMessage());
        }
    }

    //Add Pet to Database
    public void checkPet(Pet pet) {
        try {
            statement = conn.createStatement();

            String petName = pet.getName();
            String findPet = "SELECT COUNT(*) FROM PET WHERE NAME = '" + petName + "'";
            resultSet = statement.executeQuery(findPet);

            //Check if Pet exists
            int count = 0;
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
            resultSet.close();

            //Create Player if Player doesn't exist
            if (count == 0) {
                //Create and Execute Statement
//                            String createPetTable = "CREATE TABLE PET (NAME VARCHAR(50), HEALTH INT, ATTACK INT, DEFENSE INT, SPECIALATTACK INT, "
//                    + "SPECIALDEFENSE INT, LUCK INT, LEVEL INT, EXP INT, HUNGER INT, MOOD INT, PLAYERID VARCHAR(50), FOREIGN KEY (PLAYERID) REFERENCES PLAYER(NAME))";
                String toAdd = "INSERT INTO PET (NAME, HEALTH, ATTACK, DEFENSE, SPECIALATTACK, SPECIALDEFENSE, LUCK, LEVEL, EXP, HUNGER, MOOD, PLAYERID) VALUES ('"
                        + pet.getName() + "', "
                        + pet.getHealth() + ", "
                        + pet.getAttack() + ", "
                        + pet.getDefense() + ", "
                        + pet.getSpecialAttack() + ", "
                        + pet.getSpecialDefense() + ", "
                        + pet.getLuck() + ", "
                        + pet.getLevel() + ", "
                        + pet.getExp() + ", "
                        + pet.getHunger() + ", "
                        + pet.getMood() + ", "
                        + " '"+pet.getID() + "')";
                statement.executeUpdate(toAdd);
                System.out.println("loadPet : " + pet.getName());
                loadPet(player.getName());
            } else {
                //Player Exists
                System.out.println("Pet already Exists.");
                this.pet = pet;
                updatePet();
                loadPet(player.getName());
            }

            //close
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Pet ERROR : " + ex.getMessage());
        }
    }

    //Load Pet from DataBase
    private void loadPet(String playerName) {
        try {
            statement = conn.createStatement();

            //Create and Execute Statement
            String getPet = "SELECT * FROM PET WHERE PLAYERID = '" + playerName + "'";
            resultSet = statement.executeQuery(getPet);
            //Create Pet Object
            if (resultSet.next()) {
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

                //Set Pet
                this.pet = new Pet(name, health, attack, defense, specialAttack, specialDefense, luck, level, exp, hunger, mood, id);
            }
        } catch (SQLException ex) {
            System.out.println("Pet Load ERROR : " + ex.getMessage());
        }
    }
    
    //Given by ChatGPT and modified
    private void updatePet() {
        try {
            // Create and execute the update statement
            String updateQuery = "UPDATE PET SET NAME = ?, HEALTH = ?, ATTACK = ?, DEFENSE = ?, SPECIALATTACK = ?, SPECIALDEFENSE = ?, LUCK = ?, LEVEL = ?, EXP = ?, HUNGER = ?, MOOD = ? WHERE PLAYERID = ?";
            try (PreparedStatement updateStatement = conn.prepareStatement(updateQuery)) {
                updateStatement.setString(1, pet.getName());
                updateStatement.setInt(2, pet.getHealth());
                updateStatement.setInt(3, pet.getAttack());
                updateStatement.setInt(4, pet.getDefense());
                updateStatement.setInt(5, pet.getSpecialAttack());
                updateStatement.setInt(6, pet.getSpecialDefense());
                updateStatement.setInt(7, pet.getLuck());
                updateStatement.setInt(8, pet.getLevel());
                updateStatement.setInt(9, pet.getExp());
                updateStatement.setInt(10, pet.getHunger());
                updateStatement.setInt(11, pet.getMood());
                updateStatement.setString(12, player.getName());
                updateStatement.executeUpdate();
                conn.commit();
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
    
    //Close Connection
    public void closeConnection(){
        try{
            conn.close();
            dbMain.closeConnections();
        } catch(SQLException ex){
            System.out.println("Error : " + ex);
        }
    }
}
