/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nemiah.project1.database;

import com.nemiah.project1.entitiesbase.Pet;
import com.nemiah.project1.entitiesbase.Player;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

/**
 *
 * @author nemiah
 */
public class DBParse {

    private DBMain dbMain;
    private final String playerTable = "PLAYER";
    private final String petTable = "PET";

    public DBParse() {
        dbMain = new DBMain();
        createTables();
    }

    //Create Tables
    private void createTables() {
        createPlayerTable();
        createPetTable();
    }

    private void createPlayerTable() {
        try {
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

    //Query Pet
    public Pet queryPet(UUID uid) {
        String uidString = uid.toString();
        ResultSet resultSet;
        Pet pet = null;
        try {
            Statement statement = dbMain.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String petQuery = "SELECT * from PET "
                    + "WHERE ID = '" + uidString + "'";
            resultSet = statement.executeQuery(petQuery);

            //Create Pet from Result Set
            resultSet.beforeFirst();
            if (resultSet.next()) {
                //Create UID
                UUID petUid = createUID(resultSet.getString("ID"));
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
                UUID playerUid = createUID(resultSet.getString("ID"));
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

    //Create Player UID
    public UUID createUID(String uidString) {
        UUID uid = UUID.fromString(uidString);
        return uid;
    }
}
