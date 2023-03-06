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
public class Serviceoffre implements IOffreService<Offre> {
     Connection cnx = Datasource.getInstance().getConnection() ;

   
   public void ajouterOffre(Offre o) {
 try {
            String requete= "INSERT INTO offre (DescO,DureeO)"
                    + "VALUES (?,?)";
            PreparedStatement pst = Datasource.getInstance().getConnection()
                    .prepareStatement(requete);
           
            pst.setString(1, o.getdescription_offre());
            pst.setString(2, o.getduree_offre());
            
           
            pst.executeUpdate();
            System.out.println("Offre ajoutée");
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
}

    @Override
    public void supprimerOffre(Offre o) {
        try {
            String requete = "DELETE FROM offre where idO=?";
            PreparedStatement pst = Datasource.getInstance().getConnection()
                    .prepareStatement(requete);
            pst.setInt(1, o.getid_offre());
            pst.executeUpdate();
            System.out.println("offre supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierOffre(Offre u) {
        try {
            String req = "UPDATE offre SET DescO = '" + u.getdescription_offre()+ "', DureeO = '" + u.getduree_offre()+ "' WHERE offre.`idO` = " + u.getid_offre();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("offre updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

    
    
    
    
    
    
    
    
    
  

    
     @Override
    public List<Offre>afficherOffres() {
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
                System.out.println(o.toString());
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

         public void pdf(Offre o) throws FileNotFoundException, DocumentException {
        try {
        String file_name="C:\\Users\\Hp\\Desktop\\pdf\\nadejoffre.pdf";
        Document doc =new Document();
        PdfWriter.getInstance(doc, new FileOutputStream(file_name));
        doc.open();
        PreparedStatement ps=null;
        ResultSet rs=null;
         String querry="SELECT * FROM offre";

            ps=cnx.prepareStatement(querry);
            rs=ps.executeQuery();
            while(rs.next()) {
                Paragraph para=new Paragraph(rs.getInt("idO")+" "+rs.getString("DescO")+" "+rs.getString("DureeO")+" ");
                doc.add(para);
                doc.add(new Paragraph(" "));

            }
            doc.close();



        }catch(Exception E){
            System.err.println(E);

        }
        
}

    public List<Offre> findoffreByDescOAndDureeO(List<Offre> listo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}   
        
        
    
    
    
   
    
    
    
    
    
    
    
    
    
    
    
    
