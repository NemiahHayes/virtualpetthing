/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nemiah.project1.database;

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
    
    public DBParse(){
        dbMain = new DBMain();
    }
    
    public void createTable(){
        try{
            Statement statement = dbMain.getConnection().createStatement();
            
            //Create Table
            String sqlPlayer = "create table " + playerTable + " (ID varchar(256),"
                    + "NAME varchar(36),"
                    + "DUNGEONLEVEL int,"
                    + "FOOD int)";
            statement.executeUpdate(sqlPlayer);
            System.out.println("Player Table Created.");
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    //Insert Player into Table
    public void insertPlayer(Player player){
        try{
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
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public Player queryPlayer(String name){
        ResultSet resultSet;
        Player player = null;
        try{
            Statement statement = dbMain.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            String playerQuery = "SELECT * from PLAYER "
                    + "WHERE NAME = '" + name + "'";
            
            resultSet = statement.executeQuery(playerQuery);
            resultSet.beforeFirst();
            if (resultSet.next()){
                //Create UID
                UUID playerUid = createUID(resultSet.getString("ID"));
                System.out.println("Player UID : " + playerUid);
                //Create Player Instance
                String playerName = resultSet.getString("NAME");
                int playerDungeonLevel = resultSet.getInt("DUNGEONLEVEL");
                int playerFood = resultSet.getInt("FOOD");
                
                player = new Player(playerName, playerDungeonLevel, playerFood, playerUid);
            }
            
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return player;
    }
    
    //Create Player UID
    public UUID createUID(String uidString){
        UUID uid = UUID.fromString(uidString);
        return uid;
    }
}
