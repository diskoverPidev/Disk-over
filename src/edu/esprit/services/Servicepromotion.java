/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.jdbc.Connection;
import edu.esprit.entities.Offre;
import edu.esprit.entities.Promotion;
import edu.esprit.utils.Datasource;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
 * @author Hp
 */
public class Servicepromotion implements IService<Promotion> {
    Connection cnx = Datasource.getInstance().getConnection() ;

   
  public void ajouter(Promotion u) {
    try {
        // Vérifier si le nom de la promotion est unique
        String reqCheck = "SELECT nomP FROM promotion WHERE nomP = ?";
        PreparedStatement psCheck = cnx.prepareStatement(reqCheck);
        psCheck.setString(1, u.getnom_promotion());
        ResultSet rs = psCheck.executeQuery();
        if (rs.next()) {
            throw new IllegalArgumentException("Le nom de la promotion doit être unique.");
        }

        // Vérifier que les champs ont une longueur minimale et maximale
        if (u.getnom_promotion().isEmpty()) {
            throw new IllegalArgumentException("Le nom de la promotion ne peut pas être vide ou nul.");
        }
        if (u.getnom_promotion().length() > 4) {
            throw new IllegalArgumentException("Le nom de la promotion ne peut pas dépasser 50 caractères.");
        }
        if (u.getduree_promotion() < 0 || u.getduree_promotion() > 30) {
            throw new IllegalArgumentException("La durée de la promotion doit être comprise entre 0 et 365 jours.");
        }
        if (u.getprix_avant() < 0) {
            throw new IllegalArgumentException("Le prix avant la promotion ne peut pas être négatif.");
        }
        if (u.getprix_apres() < 0) {
            throw new IllegalArgumentException("Le prix après la promotion ne peut pas être négatif.");
        }
        if (u.getprix_apres() > u.getprix_avant()) {
            throw new IllegalArgumentException("Le prix après la promotion doit être inférieur au prix avant la promotion.");
        }
        if (u.getpourcentage() < 0 || u.getpourcentage() > 100) {
            throw new IllegalArgumentException("Le pourcentage de réduction doit être compris entre 0 et 100.");
        }

        // Insérer la nouvelle promotion si les contrôles sont réussis
        String req = "INSERT INTO promotion (nomP, DureeP, PrixAvant, Pourcentage, PrixApres) VALUES (?,?,?,?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, u.getnom_promotion());
        ps.setInt(2, u.getduree_promotion());
        ps.setInt(3, u.getprix_avant());
        ps.setInt(4, u.getpourcentage());
        ps.setInt(5, u.getprix_apres());
        ps.executeUpdate();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    } catch (IllegalArgumentException ex) {
        System.out.println(ex.getMessage());
    }
}

    @Override
    public void supprimer(int id) {
       try {
            String req = "DELETE FROM promotion WHERE IdPro = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("promotion deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Promotion u) {
        try {
            String req = "UPDATE promotion SET idPro = '" + u.getid_promotion() + "', nomP = '" + u.getnom_promotion() + "' WHERE promotion.`idPro` = " + u.getid_promotion();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("promotion updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

   public Promotion getOneById(int id) {
        String query = "SELECT * FROM promotion WHERE idPro = " + id + "";
        Promotion o  = new Promotion();
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()){
                o.setid_promotion(rs.getInt("idPro"));
                o.setnom_promotion(rs.getString("nomP"));
                o.setduree_promotion(rs.getInt("DureeP"));
                o.setprix_avant(rs.getInt("PrixAvant"));
                o.setpourcentage(rs.getInt("Pourcentage"));
                                o.setprix_apres(rs.getInt("PrixApres"));


                
            }
        }
        catch (SQLException e){
            e.getMessage();
        }
        return o;
    }

    
    public List<Promotion> getall() {
        List<Promotion> listepromotion = new ArrayList<>();
        String query = "SELECT * FROM promotion ";
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()){
                Promotion o = new Promotion();
              
                o.setnom_promotion(rs.getString("nomP"));
                o.setduree_promotion(rs.getInt("DureeP"));
                o.setprix_avant(rs.getInt("PrixAvant"));
                o.setpourcentage(rs.getInt("Pourcentage"));
               o.setprix_apres(rs.getInt("PrixApres"));
                listepromotion.add(o);
            }
        }
        
        catch (SQLException e){
            e.getMessage();
        }
        return listepromotion;
        }
          public static List<Promotion> trier(List<Promotion> listc) {
        return (List<Promotion>) listc.stream()
                .sorted(Comparator.comparing(Promotion::getduree_promotion))
                .collect(Collectors.toList());
       
    }
         

    }
    

    

