/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import com.mysql.jdbc.Connection;
import edu.esprit.entities.Client;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import edu.esprit.utils.datasource;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Comparator;


/**
 *
 * @author lenovo
 */
public class Serviceclient implements Iservice<Client> {
    Connection cnx = datasource.getInstance().getCnx() ;

    @Override
    public void ajouter(Client u) {
    try {
       
         String req1 = "SELECT COUNT(*) FROM client WHERE cin = ?";
        PreparedStatement ps1 = cnx.prepareStatement(req1);
        ps1.setString(1, u.getCin());
        ResultSet rs = ps1.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        if (count > 0) {
            System.out.println("Un client avec le même numéro CIN existe déjà.");
            return;
        }
        // Vérifier que tous les champs obligatoires sont remplis
        if (u.getCin().isEmpty() || u.getNom().isEmpty() || u.getPrenom().isEmpty() || u.getEmail().isEmpty() || u.getPwd().isEmpty()) {
            System.out.println("Veuillez remplir tous les champs obligatoires du client.");
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
        // Vérifier que l'adresse email est valide
        if (!isValidEmail(u.getEmail())) {
            System.out.println("Adresse email invalide du client .");
            return;
        }
        
        String req = "INSERT INTO client (cin, nom, prenom, email, pwd) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, u.getCin());
        ps.setString(2, u.getNom());
        ps.setString(3, u.getPrenom());
        ps.setString(4, u.getEmail());
        ps.setString(5, u.getPwd());
        ps.executeUpdate();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

// Fonction pour valider une adresse email
private boolean isValidEmail(String email) {
    // Utiliser une expression régulière pour vérifier que l'adresse email est valide
    String regex = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
    return email.toUpperCase().matches(regex);
}







    @Override
    public void supprimer(int id) {
       try {
            String req = "DELETE FROM client WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("client deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Client u) {
        try {
            String req = "UPDATE client SET nom = '" + u.getNom() + "', prenom = '" + u.getPrenom() + "' WHERE client.`id` = " + u.getId_Client();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("client updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

   public Client getOneById(int id) {
        String query = "SELECT * FROM client WHERE id = " + id + "";
        Client c = new Client();
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()){
                c.setCin(rs.getString("cin"));
                c.setNom(rs.getString("nom"));
                c.setPrenom(rs.getString("Prenom"));
                c.setEmail(rs.getString("Email"));
                c.setPwd(rs.getString("pwd"));
            }
        }
        catch (SQLException e){
            e.getMessage();
        }
        return c;
    }

    @Override
    public List<Client> getall() {
        List<Client> listeclient = new ArrayList<>();
        String query = "SELECT * FROM client ";
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()){
                Client c = new Client();
                c.setId_Client(rs.getInt("id")) ; 
                c.setCin(rs.getString("cin"));
                c.setNom(rs.getString("nom"));
                c.setPrenom(rs.getString("Prenom"));
                c.setEmail(rs.getString("Email"));
                c.setPwd(rs.getString("pwd"));
               
                listeclient.add(c);
            }
        }
        catch (SQLException e){
            e.getMessage();
        }
        return listeclient;
    }

    
    public static List<Client> trier(List<Client> listc) {
        return listc.stream()
                .sorted(Comparator.comparing(Client::getCin))
                .collect(Collectors.toList());
    }
   public static List<Client> rechercher(List<Client> listc,String nom, String prenom)
   {
       return (List<Client>) listc.stream()
        .filter(a -> a.getNom().equalsIgnoreCase(nom) || a.getPrenom().equalsIgnoreCase(prenom)).collect(Collectors.toList());
       
   }
    
    
}
