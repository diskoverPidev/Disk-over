/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import com.mysql.jdbc.Connection;
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
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 *
 * @author lenovo
 */
public class Servicechauffeur implements Iservice<Chauffeur>{

   Connection cnx = datasource.getInstance().getCnx() ;

   
    public void ajouter(Chauffeur u) {
       try {
         String req = "INSERT INTO chauffeur (cin, nom,prenom,email,pwd) VALUES (?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1,u.getCin());
            ps.setString(2, u.getNom());
            ps.setString(3, u.getPrenom());
            ps.setString(4, u.getEmail());
            ps.setString(5, u.getPwd());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
    }

   
    public void supprimer(int id) {
       try {
            String req = "DELETE FROM chauffeur WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("chauffeur deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Chauffeur u) {
        try {
            String req = "UPDATE chauffeur SET nom = '" + u.getNom() + "', prenom = '" + u.getPrenom() + "' WHERE chauffeur.`id` = " + u.getId_chauffeur();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("chauffeur updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

   public Chauffeur getOneById(int id) {
        String query = "SELECT * FROM chauffeur WHERE id = " + id + "";
        Chauffeur ch = new Chauffeur();
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()){
                ch.setCin(rs.getString("cin"));
                ch.setNom(rs.getString("nom"));
                ch.setPrenom(rs.getString("Prenom"));
                ch.setEmail(rs.getString("Email"));
                ch.setPwd(rs.getString("pwd"));
            }
        }
        catch (SQLException e){
            e.getMessage();
        }
        return ch;
    }

    
    public List<Chauffeur> getall() {
        List<Chauffeur> listechauffeur = new ArrayList<>();
        String query = "SELECT * FROM chauffeur ";
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()){
                Chauffeur ch = new Chauffeur();
                ch.setCin(rs.getString("cin"));
                ch.setNom(rs.getString("nom"));
                ch.setPrenom(rs.getString("Prenom"));
                ch.setEmail(rs.getString("Email"));
                ch.setPwd(rs.getString("pwd"));
               
                listechauffeur.add(ch);
            }
        }
        catch (SQLException e){
            e.getMessage();
        }
        return listechauffeur;
    }
     public static List<Chauffeur> trier(List<Chauffeur> listc) {
        return listc.stream()
                .sorted(Comparator.comparing(Chauffeur::getCin))
                .collect(Collectors.toList());
    }
     public static List<Chauffeur> rechercher(List<Chauffeur> listc,String nom, String prenom)
   {
       return (List<Chauffeur>) listc.stream()
        .filter(a -> a.getNom().equalsIgnoreCase(nom) || a.getPrenom().equalsIgnoreCase(prenom)).collect(Collectors.toList());
       
   }
   

    
}
