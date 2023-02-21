/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.utils;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Hp
 */
public class Datasource {
    
    private  final String url = "jdbc:mysql://localhost:3306/diskover";
    private  final String user = "root";
    private  final String password = "";
  
    // JDBC  managing connection
    private  Connection con;
   
    private  static Datasource instance = null;

    private Datasource() {
        try {
            // opening database
            con = (Connection) DriverManager.getConnection(url, user, password);

            System.out.println("Successfully connected to DB");
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx.getMessage());
            
        }
    }

    public static Datasource getInstance() {
        if (instance == null) {
            instance = new Datasource();
        }
        return instance;
    }

    public Connection getConnection() {
        return con;
    }

   
    public void closeConnection() {
        try {
            con.close();
            instance = null;
            System.out.println("DB Closed");
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx.getMessage());
            
        }
    }
}
