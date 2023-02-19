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
 * @author lenovo
 */
public class datasource {
    
    private Connection cnx;
    private static datasource instance;
    
    private final String USER = "root";
    private final String PWD = "";
    private final String URL = "jdbc:mysql://localhost:3306/diskover";

    private datasource() {
        try {
            cnx = (Connection) DriverManager.getConnection(URL, USER, PWD);
            System.out.println("connected !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static datasource getInstance() {
        if(instance == null)
            instance = new datasource();
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }

   
    
}
