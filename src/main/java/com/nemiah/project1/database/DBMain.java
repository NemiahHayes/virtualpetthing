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
    
    public DBMain(){
        establishConnection();
    }

    //Establish connection
    private void establishConnection() {
        //Establish a connection to Database
        String url = "jdbc:derby://localhost:1527/PetSimDB;create=true";
        String user = "pdc";
        String password = "pdc";
        try{
            conn = DriverManager.getConnection(url,user,password);
            System.out.println(url + " connected...");
        } catch(SQLException ex){
            System.err.print(ex);
        }
    }
    
    //Close Database Connection
    public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public ResultSet queryDB(String sql) {
        Connection connection = this.conn;
        ResultSet resultSet = null;
        Statement statement
;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resultSet;
    }

    public void updateDB(String sql) {

        Connection connection = this.conn;
        Statement statement;

        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    //Get and Set
    public Connection getConn(){
        return this.conn;
    }
    
}
