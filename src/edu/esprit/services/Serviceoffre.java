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

import edu.esprit.utils.Datasource;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Hp
 */
public class Serviceoffre implements IService<Offre> {
     Connection cnx = Datasource.getInstance().getConnection() ;

   
   public void ajouter(Offre u) {
try {
if ( u.getdescription_offre().isEmpty()) {
throw new IllegalArgumentException("La description de l'offre ne peut pas être vide ou nulle.");
}
if (u.getdescription_offre().length() > 8) {
throw new IllegalArgumentException("La description de l'offre ne peut pas dépasser 8 caractères.");
}
if ( u.getduree_offre().isEmpty()) {
throw new IllegalArgumentException("La durée de l'offre ne peut pas être vide ou nulle.");
}


String req = "INSERT INTO offre (DescO, DureeO) VALUES (?,?)";
PreparedStatement ps = cnx.prepareStatement(req);

ps.setString(1, u.getdescription_offre());
ps.setString(2, u.getduree_offre());

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
            String req = "DELETE FROM offre WHERE idO = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("offre deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Offre u) {
        try {
            String req = "UPDATE offre SET DureeO = '" + u.getduree_offre() + "', DescO = '" + u.getdescription_offre() + "' WHERE offre.`idO` = " + u.getid_offre();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("offre updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

   public Offre getOneById(int id) {
        String query = "SELECT * FROM offre WHERE idO = " + id + "";
        Offre o  = new Offre();
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()){
                o.setid_offre(rs.getInt("idO"));
                o.setdescription_offre(rs.getString("DescO"));
                o.setduree_offre(rs.getString("DureeO"));
                
            }
        }
        catch (SQLException e){
            e.getMessage();
        }
        return o;
    }

    
    public List<Offre> getall() {
        List<Offre> listeoffre = new ArrayList<>();
        String query = "SELECT * FROM offre ";
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()){
                Offre o = new Offre();
                 o.setid_offre(rs.getInt("idO"));
                o.setdescription_offre(rs.getString("DescO"));
                o.setduree_offre(rs.getString("DureeO"));
                listeoffre.add(o);
            }
        }
        catch (SQLException e){
            e.getMessage();
        }
        return listeoffre;
    }

    public static List<Offre> trier(List<Offre> listc) {
        return listc.stream()
                .sorted(Comparator.comparing(Offre::getduree_offre))
                .collect(Collectors.toList());
    }
    
   public static List<Offre> rechercher(List<Offre> listc, String description_offre, String duree_offre) {
    return listc.stream()
        .filter(a -> a.getdescription_offre().equalsIgnoreCase(description_offre) || a.getduree_offre().equalsIgnoreCase(duree_offre))
        .collect(Collectors.toList());
}

            
        
        
    }
    
    
   
    
    
    
    
    
    
    
    
    
    
    
    
