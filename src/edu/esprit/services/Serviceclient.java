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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author lenovo
 */
public class Serviceclient implements Iservice<Client> {
    Connection cnx = datasource.getInstance().getCnx() ;

    @Override
    public void ajouter(Client u) {
    try {
       
         String req1 = "SELECT COUNT(*) FROM user WHERE cin = ?";
        PreparedStatement ps1 = cnx.prepareStatement(req1);
        ps1.setString(1, u.getCin());
        ResultSet rs = ps1.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        if (count > 0) {
            System.out.println("Un client avec le même numéro CIN existe déjà.");
            return;
        }
//         String req2 = "SELECT COUNT(*) FROM user WHERE email = ?";
//        PreparedStatement ps2 = cnx.prepareStatement(req1);
//        ps2.setString(5, u.getEmail());
//        ResultSet rs2 = ps2.executeQuery();
//        rs2.next();
//        int count2 = rs2.getInt(1);
//        if (count2 > 0) {
//            System.out.println("Un client avec le même numéro CIN existe déjà.");
//            return;
//        }
       
        
        
        // Vérifier que tous les champs obligatoires sont remplis
        if (u.getCin().isEmpty() || u.getNom().isEmpty() || u.getPrenom().isEmpty() || u.getEmail().isEmpty() || u.getPwd().isEmpty() || u.getRole().isEmpty()) {
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
        
        String req = "INSERT INTO user (cin, nom, prenom,role, email, pwd) VALUES (?, ?, ?,?, ?, ?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, u.getCin());
        ps.setString(2, u.getNom());
        ps.setString(3, u.getPrenom());
                ps.setString(4, u.getRole());

        ps.setString(5, u.getEmail());
       ps.setString(6, hashPassword(u.getPwd()));
        ps.executeUpdate();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }   catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Serviceclient.class.getName()).log(Level.SEVERE, null, ex);
        }
}
     private String hashPassword(String password) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    byte[] hash = md.digest(password.getBytes());
    StringBuilder sb = new StringBuilder();
    for (byte b : hash) {
        sb.append(String.format("%02x", b));
    }
    return sb.toString();
}

// Fonction pour valider une adresse email
private boolean isValidEmail(String email) {
    // Utiliser une expression régulière pour vérifier que l'adresse email est valide
    String regex = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
    return email.toUpperCase().matches(regex);
}







   
    public void supprimer(String cin) {
       try {
            String req = "DELETE FROM user WHERE cin = " + cin;
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
            String req = "UPDATE user SET cin = '" + u.getCin() + "',nom = '" + u.getNom() + "', prenom = '" + u.getPrenom() + "', email = '" + u.getEmail() + "', pwd = '" + u.getPwd() + "', role = '" + u.getRole() + "' WHERE user.`cin` = " + u.getCin();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("client updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void modifierpwd(Client u) throws NoSuchAlgorithmException {
    try {
        String req = "UPDATE user SET pwd = ? WHERE email = ?";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setString(1, hashPassword(u.getPwd())); 
        st.setString(2, u.getEmail());
        st.executeUpdate();
        System.out.println("client updated !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
    
    

   public Client getOneById(String cin) {
        String query = "SELECT * FROM user WHERE cin = " + cin + "";
        Client c = new Client();
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()){
                c.setCin(rs.getString("cin"));
                c.setNom(rs.getString("nom"));
                c.setPrenom(rs.getString("Prenom"));
                                c.setRole(rs.getString("role"));

                c.setEmail(rs.getString("Email"));
                c.setPwd(rs.getString("pwd"));
            }
        }
        catch (SQLException e){
            e.getMessage();
        }
        return c;
    }
   public String getPwdByEmail(String email) {
    String query = "SELECT pwd FROM user WHERE email = '" + email + "'";
    String pwd = null;
    try {
        Statement ste = cnx.createStatement();
        ResultSet rs = ste.executeQuery(query);
        if (rs.next()) {
            pwd = rs.getString("pwd");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return pwd;
}
   public String getidBycin(String cin) {
    String query = "SELECT id FROM user WHERE cin = '" + cin + "'";
    String cinn = null;
    try {
        Statement ste = cnx.createStatement();
        ResultSet rs = ste.executeQuery(query);
        if (rs.next()) {
            cinn = rs.getString("cin");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return cinn;
}

    @Override
    public List<Client> getall() {
        List<Client> listeclient = new ArrayList<>();
        String query = "SELECT * FROM user ";
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()){
                Client c = new Client();

                c.setCin(rs.getString("cin"));
                c.setNom(rs.getString("nom"));
                c.setPrenom(rs.getString("Prenom"));
                                c.setRole(rs.getString("role"));

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
   public static List<Client> rechercher(List<Client> listc,String recherche)
   {
       return (List<Client>) listc.stream()
        .filter(a -> a.getNom().equalsIgnoreCase(recherche) || a.getPrenom().equalsIgnoreCase(recherche)).collect(Collectors.toList());
       
   }
    
    
}
