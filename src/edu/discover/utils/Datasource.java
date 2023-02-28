/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.discover.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author ramzi
 */
public class Datasource {
    private Connection cnx ;
    private static Datasource instance;
    
    private final String USER= "root";
    private final String PWD= "";
    private final String URL= "jdbc:mysql://localhost:3306/diskover";

    
    private Datasource(){
        try {
            cnx = DriverManager.getConnection(URL, USER, PWD);
            System.err.println("connected to DB!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    //si instance null bch yaaml creation sinon mayaamlch
    public static Datasource getInstance(){
    if (instance == null)
        instance = new Datasource();
       return instance;
    }
    
    
    public Connection getCnx() {
        return cnx;
    }

    public Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



}
