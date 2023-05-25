/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nemiah.project1.database;

import com.nemiah.project1.entitiesbase.Pet;
import com.nemiah.project1.entitiesbase.Player;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

/**
 *
 * @author nemiah
 */
public class DBParse {

    private final DBMain dbMain;
    private final String playerTable = "PLAYER";
    private final String petTable = "PET";

    public DBParse() {
        dbMain = new DBMain();
        createTables();
    }

    //Create Tables
    private void createTables() {
        try{
            createPlayerTable();
            createPetTable();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private void createPlayerTable() {
        try {
            System.out.println(dbMain.getConnection());
            Statement statement = dbMain.getConnection().createStatement();

            //Create Player Table
            String sqlPlayer = "create table " + playerTable + " (ID varchar(36),"
                    + "NAME varchar(256),"
                    + "DUNGEONLEVEL int,"
                    + "FOOD int)";
            statement.executeUpdate(sqlPlayer);
            System.out.println("Player Table Created.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void createPetTable() {
        try {
            Statement statement = dbMain.getConnection().createStatement();

            //Create Pet Table
            String sqlPet = "create table " + petTable + "(ID varchar(36),"
                    + "NAME varchar(256),"
                    + "HEALTH int,"
                    + "ATTACK int,"
                    + "DEFENSE int,"
                    + "SPECIAL_ATTACK int,"
                    + "SPECIAL_DEFENSE int,"
                    + "LUCK int,"
                    + "LEVEL int,"
                    + "EXP int,"
                    + "HUNGER int,"
                    + "MOOD int)";
            statement.executeUpdate(sqlPet);
            System.out.println("Pet Table Created.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //Query Pet on UUID
    public Pet queryPet(UUID uid) {
        String stringUid = uid.toString();
        ResultSet resultSet;
        Pet pet = null;
        try {
            Statement statement = dbMain.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String petQuery = "SELECT * from PET "
                    + "WHERE ID = '" + stringUid + "'";
            resultSet = statement.executeQuery(petQuery);

            //Create Pet from Result Set
            resultSet.beforeFirst();
            if (resultSet.next()) {
                //Create UID
                UUID petUid = UUID.fromString(resultSet.getString("ID"));
                System.out.println("PET UID : " + petUid + " GIVEN UID : " + uid);
                //Create Pet Instance
                String petName = resultSet.getString("NAME");
                int petHealth = resultSet.getInt("HEALTH");
                int petAttack = resultSet.getInt("ATTACK");
                int petDefense = resultSet.getInt("DEFENSE");
                int petSPAttack = resultSet.getInt("SPECIAL_ATTACK");
                int petSPDefense = resultSet.getInt("SPECIAL_DEFENSE");
                int petLuck = resultSet.getInt("LUCK");
                int petLevel = resultSet.getInt("LEVEL");
                int petExp = resultSet.getInt("EXP");
                int petHunger = resultSet.getInt("HUNGER");
                int petMood = resultSet.getInt("MOOD");

                pet = new Pet(petName, petHealth, petAttack, petDefense, petSPAttack, petSPDefense, petLuck, petLevel, petExp, petHunger, petMood, petUid);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return pet;
    }
    
    //Query Pet on String
    public Pet queryPet(String name) {
        ResultSet resultSet;
        Pet pet = null;
        try {
            Statement statement = dbMain.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String petQuery = "SELECT * from PET "
                    + "WHERE NAME = '" + name + "'";
            resultSet = statement.executeQuery(petQuery);

            //Create Pet from Result Set
            resultSet.beforeFirst();
            if (resultSet.next()) {
                //Create UID
                UUID petUid = UUID.fromString(resultSet.getString("ID"));
                //Create Pet Instance
                String petName = resultSet.getString("NAME");
                int petHealth = resultSet.getInt("HEALTH");
                int petAttack = resultSet.getInt("ATTACK");
                int petDefense = resultSet.getInt("DEFENSE");
                int petSPAttack = resultSet.getInt("SPECIAL_ATTACK");
                int petSPDefense = resultSet.getInt("SPECIAL_DEFENSE");
                int petLuck = resultSet.getInt("LUCK");
                int petLevel = resultSet.getInt("LEVEL");
                int petExp = resultSet.getInt("EXP");
                int petHunger = resultSet.getInt("HUNGER");
                int petMood = resultSet.getInt("MOOD");

                pet = new Pet(petName, petHealth, petAttack, petDefense, petSPAttack, petSPDefense, petLuck, petLevel, petExp, petHunger, petMood, petUid);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return pet;
    }
    
    //Update Pet
    public void updatePet(Pet pet){
        PreparedStatement pStatement;
        String petSQL = "UPDATE PET SET NAME=?, HEALTH=?, ATTACK=?, DEFENSE =?, SPECIAL_ATTACK=?, SPECIAL_DEFENSE=?,"
                + "LUCK=?, LEVEL=?, EXP=?, HUNGER=?, MOOD=? WHERE ID=?";
        try{
            pStatement = dbMain.getConnection().prepareStatement(petSQL);
            pStatement.setString(1,pet.getName());
            pStatement.setInt(2, pet.getHealth());
            pStatement.setInt(3, pet.getAttack());
            pStatement.setInt(4, pet.getDefense());
            pStatement.setInt(5, pet.getSpecialAttack());
            pStatement.setInt(6, pet.getSpecialDefense());
            pStatement.setInt(7, pet.getLuck());
            pStatement.setInt(8, pet.getLevel());
            pStatement.setInt(9, pet.getExp());
            pStatement.setInt(10, pet.getHunger());
            pStatement.setInt(11, pet.getMood());
            pStatement.setString(12, pet.getUid().toString());
            
            int rowsUpdated = pStatement.executeUpdate();
            System.out.println("Rows Affected : " + rowsUpdated);
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    //Update Player
    public void updatePlayer(Player player){
        PreparedStatement pStatement;
        String playerSQL = "UPDATE PLAYER SET NAME=?, DUNGEONLEVEL=?, FOOD=? WHERE ID=?";
        try{
            pStatement = dbMain.getConnection().prepareStatement(playerSQL);
            pStatement.setString(1, player.getName());
            pStatement.setInt(2, player.getDungeonLevel());
            pStatement.setInt(3, player.getFood());
            pStatement.setString(4, player.getUid().toString());
            
            int rowsUpdated = pStatement.executeUpdate();
            System.out.println("Rows Affected : " + rowsUpdated);
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public void insertPet(Pet pet) {
        try {
            Statement statement = dbMain.getConnection().createStatement();

            String uid = pet.getUid().toString();
            //Insert into Table
            String petSQL = "insert into " + petTable + " values('"
                    + uid + "'"
                    + ", " + "'" + pet.getName() + "',"
                    + pet.getHealth() + ","
                    + pet.getAttack() + ","
                    + pet.getDefense() + ","
                    + pet.getSpecialAttack() + ","
                    + pet.getSpecialDefense() + ","
                    + pet.getLuck() + ","
                    + pet.getLevel() + ","
                    + pet.getExp() + ","
                    + pet.getHunger() + ","
                    + pet.getMood() + ")";
            System.out.println(petSQL);
            statement.executeUpdate(petSQL);
            
            System.out.println("Pet Inserted.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //Insert Player into Table
    public void insertPlayer(Player player) {
        try {
            Statement statement = dbMain.getConnection().createStatement();

            String uid = player.getUid().toString();
            //Insert into Table
            String playerSQL = "insert into " + playerTable + " values('"
                    + uid + "'"
                    + ", " + "'" + player.getName() + "', "
                    + player.getDungeonLevel() + ", "
                    + player.getFood() + ")";
            System.out.println(playerSQL);
            statement.executeUpdate(playerSQL);

            System.out.println("Player Inserted.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //Query Player
    public Player queryPlayer(String name) {
        ResultSet resultSet;
        Player player = null;
        try {
            Statement statement = dbMain.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String playerQuery = "SELECT * from PLAYER "
                    + "WHERE NAME = '" + name + "'";
            resultSet = statement.executeQuery(playerQuery);

            resultSet.beforeFirst();
            if (resultSet.next()) {
                //Create UID
                UUID playerUid = UUID.fromString(resultSet.getString("ID"));
                System.out.println("Player UID : " + playerUid);
                //Create Player Instance
                String playerName = resultSet.getString("NAME");
                int playerDungeonLevel = resultSet.getInt("DUNGEONLEVEL");
                int playerFood = resultSet.getInt("FOOD");

                player = new Player(playerName, playerDungeonLevel, playerFood, playerUid);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return player;
    }

}
