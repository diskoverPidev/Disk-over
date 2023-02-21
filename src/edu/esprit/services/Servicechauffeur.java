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
         String req1 = "SELECT COUNT(*) FROM chauffeur WHERE cin = ?";
        PreparedStatement ps1 = cnx.prepareStatement(req1);
        ps1.setString(1, u.getCin());
        ResultSet rs = ps1.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        if (count > 0) {
            System.out.println("Un chauffeur avec le même numéro CIN existe déjà.");
            return;
        }
        // Validate input
        if (u.getCin().isEmpty() || u.getNom().isEmpty() || u.getPrenom().isEmpty() || u.getEmail().isEmpty() || u.getPwd().isEmpty()) {
            System.out.println("Veuillez saisir toutes les informations nécessaires.");
            return;
        }
        if (u.getPwd().length() < 8) {
            System.out.println("Le mot de passe doit contenir au moins 8 caractères.");
            return;
        }
        if (u.getCin().length() != 8) {
            System.out.println("Le numéro CIN doit contenir exactement 8 caractères.");
            return;
        }
        if (!isValidEmail(u.getEmail())) {
            System.out.println("Adresse email invalide du client.");
            return;
        }
        // Check if the chauffeur already exists
        
        // Add data to database
        String req2 = "INSERT INTO chauffeur (cin, nom, prenom, email, pwd) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps2 = cnx.prepareStatement(req2);
        ps2.setString(1, u.getCin());
        ps2.setString(2, u.getNom());
        ps2.setString(3, u.getPrenom());
        ps2.setString(4, u.getEmail());
        ps2.setString(5, u.getPwd());
        ps2.executeUpdate();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

private boolean isValidEmail(String email) {
    // Utiliser une expression régulière pour vérifier que l'adresse email est valide
    String regex = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
    return email.toUpperCase().matches(regex);
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
