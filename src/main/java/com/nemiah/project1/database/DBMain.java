/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nemiah.project1.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author nemiah
 */
public class DBMain {
    
    private Connection conn;
    private final String dbAddress = "jdbc:derby:derbyDB;";
    private final String username = "pdc";
    private final String password = "pdc";
  
    public DBMain() {
        connectDB();
    }
    
    //Getters and Setters
    public Connection getConnection(){
        return this.conn;
    }

    //Get Database Connection
    public void connectDB() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(dbAddress + "create=true");
                System.out.println("Connected.");
                //DriverManager.getConnection(dbAddress + "shutdown=true");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public ResultSet Query(String query){
        
        Connection connect = conn;
        Statement statement;
        ResultSet resultSet = null;
        
        try{
            statement = connect.createStatement();
            resultSet = statement.executeQuery(query);
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return resultSet;
    }
    
    public void Update(String update){
        
        Connection connect = conn;
        Statement statement;
        ResultSet resultSet;
        
        try{
            statement = connect.createStatement();
            statement.executeUpdate(update);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public void closeConnection(){
        try{
            conn.close();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    
}
