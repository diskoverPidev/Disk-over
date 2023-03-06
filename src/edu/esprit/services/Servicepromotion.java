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
public class Servicepromotion implements IPromotionService<Promotion> {
    Connection cnx = Datasource.getInstance().getConnection() ;

   
  public void ajouterPromotion(Promotion p) {
   
               try {
            String requete= "INSERT INTO promotion (nomP,DureeP,PrixAvant,Pourcentage,PrixApres,idO)"
                    + "VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = Datasource.getInstance().getConnection()
                    .prepareStatement(requete);
           
            pst.setString(1, p.getnom_promotion());
            pst.setInt(2, p.getduree_promotion());
            pst.setInt(3,p.getprix_avant());
            pst.setInt(4,p.getpourcentage());
            pst.setInt(5,p.getprix_apres());
            pst.setInt(6,p.getIdO());
            
           
            pst.executeUpdate();
            System.out.println("promotion ajoutée");
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
}

    @Override
    public void supprimerPromotion(Promotion p) {
       try {
            String requete = "DELETE FROM promotion where IdPro=?";
            PreparedStatement pst = Datasource.getInstance().getConnection()
                    .prepareStatement(requete);
            pst.setInt(1, p.getid_promotion());
            pst.executeUpdate();
            System.out.println("promotion supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierPromotion(Promotion u) {
        try {
            String req = "UPDATE promotion SET idPro = '" + u.getid_promotion() + "', nomP = '" + u.getnom_promotion() +  "', DureeP = '" + u.getduree_promotion() +  "', PrixAvant = '" +   u.getprix_avant() + "', Pourcentage = '" +u.getpourcentage() + "', PrixApres = '"  + u.getprix_apres() + "', idO = '"+   u.getIdO()                          + "' WHERE promotion.`idPro` = " + u.getid_promotion();
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
                                o.setIdO(rs.getInt("IdO"));



                
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
                o.setIdO(rs.getInt("IdO"));

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
         
public void pdf(Promotion p) throws FileNotFoundException, DocumentException {
        try {
        String file_name="C:\\Users\\Hp\\Desktop\\pdf\\nadejpromotion.pdf";
        Document doc =new Document();
        PdfWriter.getInstance(doc, new FileOutputStream(file_name));
        doc.open();
        PreparedStatement ps=null;
        ResultSet rs=null;
         String querry="SELECT * FROM promotion";

            ps=cnx.prepareStatement(querry);
            rs=ps.executeQuery();
            while(rs.next()) {
                Paragraph para=new Paragraph(rs.getInt("IdPRO")+" "+rs.getString("nomP")+" "+rs.getInt("DureeP")+" "+rs.getInt("PrixAvant")+" "+rs.getInt("Pourcentage")+" "+rs.getInt("PrixApres")+" ");
                doc.add(para);
                doc.add(new Paragraph(" "));

            }
            doc.close();



        }catch(Exception E){
            System.err.println(E);

        }
    
    }

 List<Offre> getoffrebyIdPro(int idPro) throws SQLException{
        List<Offre> arr = new ArrayList<>();
         try {
        PreparedStatement pre = cnx.prepareStatement("SELECT DescO , DureeO from offre o , promotion p where p.idO=o.idO and p.idPro=?;"); //ORDER BY P asc
         pre.setInt(1, idPro);
         ResultSet rs = pre.executeQuery();

             while(rs.next()){
                     String DescO = rs.getString("DescO");
                       String DureeO = rs.getString("DureeO");
                        
                    
                     Offre m=new Offre(DescO,DureeO);
                    arr.add(m);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return arr;
    }






 public List<Promotion>afficherPromotions() {
        List<Promotion> listepromotion = new ArrayList<>();
        String query = "SELECT * FROM promotion ";
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()){
                Promotion p = new Promotion();
                 p.setid_promotion(rs.getInt("IdPro"));
                p.setnom_promotion(rs.getString("nomP"));
                p.setduree_promotion(rs.getInt("DureeP"));
                p.setprix_avant(rs.getInt("PrixAvant"));
                p.setpourcentage(rs.getInt("Pourcentage"));
                p.setprix_apres(rs.getInt("PrixApres"));
                p.setIdO(rs.getInt("idO"));
                
                System.out.println(p.toString());
                listepromotion.add(p);
            }
        }
        catch (SQLException e){
            e.getMessage();
        }
        return listepromotion;
    }




 
 
 












}

    

