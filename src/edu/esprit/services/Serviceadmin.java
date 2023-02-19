/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import com.mysql.jdbc.Connection;
import edu.esprit.entities.Admin;
import edu.esprit.entities.Chauffeur;
import edu.esprit.entities.Client;
import edu.esprit.utils.datasource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author lenovo
 */
public class Serviceadmin implements Iservice<Admin> {
     Connection cnx = datasource.getInstance().getCnx() ;

   
public void ajouter(Admin u) {
    try {
        String req = "SELECT COUNT(*) FROM admin WHERE cin = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, u.getCin());
        ResultSet rs = ps.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        if (count == 0) {
            req = "INSERT INTO admin (cin, nom, prenom, email, pwd) VALUES (?,?,?,?,?)";
            ps = cnx.prepareStatement(req);
            ps.setString(1, u.getCin());
            ps.setString(2, u.getNom());
            ps.setString(3, u.getPrenom());
            ps.setString(4, u.getEmail());
            ps.setString(5, u.getPwd());
            ps.executeUpdate();
        } else {
            System.out.println("L'administrateur avec le même CIN existe déjà.");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

    @Override
    public void supprimer(int id) {
       try {
            String req = "DELETE FROM admin WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("admin deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Admin u) {
        try {
            String req = "UPDATE admin SET nom = '" + u.getNom() + "', prenom = '" + u.getPrenom() + "' WHERE admin.`id` = " + u.getId_Admin();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("admin updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

   public Admin getOneById(int id) {
        String query = "SELECT * FROM admin WHERE id = " + id + "";
        Admin a  = new Admin();
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()){
                a.setCin(rs.getString("cin"));
                a.setNom(rs.getString("nom"));
                a.setPrenom(rs.getString("Prenom"));
                a.setEmail(rs.getString("Email"));
                a.setPwd(rs.getString("pwd"));
            }
        }
        catch (SQLException e){
            e.getMessage();
        }
        return a;
    }

    
    public List<Admin> getall() {
        List<Admin> listeadmin = new ArrayList<>();
        String query = "SELECT * FROM admin ";
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()){
                Admin a = new Admin();
                a.setCin(rs.getString("cin"));
                a.setNom(rs.getString("nom"));
                a.setPrenom(rs.getString("Prenom"));
                a.setEmail(rs.getString("Email"));
                a.setPwd(rs.getString("pwd"));
               
                listeadmin.add(a);
            }
        }
        catch (SQLException e){
            e.getMessage();
        }
        return listeadmin;
    }
  public static List<Admin> trier(List<Admin> listc) {
        return listc.stream()
                .sorted(Comparator.comparing(Admin::getCin))
                .collect(Collectors.toList());
    }
   public static List<Admin> rechercher(List<Admin> listc,String nom, String prenom)
   {
       return (List<Admin>) listc.stream()
        .filter(a -> a.getNom().equalsIgnoreCase(nom) || a.getPrenom().equalsIgnoreCase(prenom)).collect(Collectors.toList());
       
   }
   

    
    
}
