/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Vehicule;
import edu.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
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
 * @author asus
 */
public class ServiceVehicule implements IService<Vehicule>, IServiceVehicule<Vehicule> {

    Connection cnx = DataSource.getInstance().getCnx();
    java.util.Date Date;
    java.sql.Date sqldate;

//    @Override
//    public void ajouter(Vehicule t) {
//        
//          // SimpleDateFormat sp = new SimpleDateFormat("dd/mm/yy");
//           // Date date_entretien = new Date();
//          
//           Date date = new Date(119, 0, 20);
//          // sqldate = new java.sql.Date(23,02, 20);
//           try {
//                String req="INSERT INTO `vehicule`(`disponibilite`, `num_entretien`, `date_entretien`, `res_entretien`) VALUES ('"+t.getDisponibilite()+"','"+t.getNum_entretien()+"','"+date+"','"+t.getRes_entretien()+"')";
//                Statement st = cnx.createStatement();
//                st.executeUpdate(req);
//                System.out.println("vehicule created!");
//            } catch (SQLException ex) {
//                System.err.println(ex.getMessage());            }
//    }
    @Override
    public void ajouter(Vehicule t) {

        // SimpleDateFormat sp = new SimpleDateFormat("dd/mm/yy");
        // Date date_entretien = new Date();
        Date date = new Date(119, 0, 20);
        String req1 = "SELECT COUNT(*) FROM vehicule WHERE num_entretien = '" + t.getNum_entretien() + "'";
        String req = "INSERT INTO `vehicule`(`disponibilite`, `num_entretien`, `date_entretien`, `res_entretien`) VALUES ('" + t.getDisponibilite() + "','" + t.getNum_entretien() + "','" + date + "','" + t.getRes_entretien() + "')";

        // sqldate = new java.sql.Date(23,02, 20);
        try {
            //   String req="INSERT INTO `vehicule`(`disponibilite`, `num_entretien`, `date_entretien`, `res_entretien`) VALUES ('"+t.getDisponibilite()+"','"+t.getNum_entretien()+"','"+date+"','"+t.getRes_entretien()+"') ON CONFLICT(disponibilite, date_entretien , res_entretien) DO NOTHING ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req1);
            rs.next();
            int count = rs.getInt(1);
            if (count > 0) {
                throw new Exception("Enregistrement avec le même num_entretien existe déjà");
            }
            Statement st1 = cnx.createStatement();
            st1.executeUpdate(req);
            System.out.println("vehicule created!");
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de l'enregistrement : " + e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `vehicule` WHERE id=" + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @Override
    public void modifier(Vehicule t) {
        try {
            String req = "UPDATE `vehicule` SET `disponibilite`='" + t.getDisponibilite() + "',`num_entretien`='" + t.getNum_entretien() + "',`date_entretien`='" + t.getDate_entretien() + "',`res_entretien`='" + t.getRes_entretien() + "' WHERE 1";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Vehicule modified");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public Vehicule getOneByid(int id) {
        Vehicule v1 = null;
        try {
            String req = "SELECT * FROM `vehicule` WHERE id=" + id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                v1 = new Vehicule(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4), rs.getString(5));
                System.out.println("vehicule affiche");
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return v1;
    }

    @Override
    public List<Vehicule> getALL() {
        List<Vehicule> result = new ArrayList<>();
        try {
            String req = "Select * FROM vehicule";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Vehicule v = new Vehicule(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4), rs.getString(5));
                result.add(v);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return result;
    }

    /*  @Override
    public List<Vehicule> filtrer() {
         List<Vehicule> result = new ArrayList<>();
         Date date = new Date(120, 0, 20);
        try {
             String req= "Select * FROM vehicule WHERE date_entretien < "+date;
            Statement st = cnx.createStatement(); 
            ResultSet rs = st.executeQuery(req);
             while (rs.next()){

                Vehicule v = new Vehicule(); 
             //   v.setDate_entretien(rs.getDate("date_entretien"));
                 result.add(v);
             }
        }catch (SQLException ex){
        System.err.println(ex.getMessage());
        }
        return result;
    }
     */
    @Override
    public List<Vehicule> recherche(int disponibilite) {
        List<Vehicule> result = new ArrayList<>();
        try {
            String req = "Select * FROM vehicule WHERE disponibilite = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, disponibilite);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vehicule v = new Vehicule(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4), rs.getString(5));
                result.add(v);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return result.stream().filter(rs -> rs.getDisponibilite() == disponibilite).collect(Collectors.toList());

    }

    @Override
    public List<Vehicule> tri(Date date_entretien) {
        List<Vehicule> result = new ArrayList<>();
        try {
            String req = "Select * FROM vehicule ORDER BY date_entretien ASC";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Vehicule v = new Vehicule(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4), rs.getString(5));
                result.add(v);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return result.stream().sorted(Comparator.comparing(Vehicule::getDate_entretien)).collect(Collectors.toList());

    }

    @Override
    public void rapport() {
        // List<Vehicule> result = new ArrayList<>();
        try {
            String req = "Select * FROM vehicule WHERE date_entretien >= '2018-01-01'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                //  Vehicule v = new Vehicule(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4), rs.getString(5));
                System.out.println(" l'entretien de la véhicule possède comme numéro d'entretien : " + rs.getInt(3) + " a été effectué le " + rs.getDate(4));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

//    public boolean testUnique (int num_entretien){
//        boolean isUnique = true;
//        try{
//            String req= "Select COUNT(*) FROM vehicule WHERE num_entretien = ?";
//              PreparedStatement ps = cnx.prepareStatement(req);
//              ps.setInt(1, num_entretien);
//              ResultSet rs = ps.executeQuery();
//        if (rs.next()){
//            int count = rs.getInt("num_entretien");
//            if (count >0 ){
//             isUnique = false;
//            }
//        }
//        }catch (SQLException ex){
//        System.err.println(ex.getMessage());
//        
//        }
//     return isUnique;
//    }
    @Override
    public void ajouterV(Vehicule t, Date date) {
  
        String req1 = "SELECT COUNT(*) FROM vehicule WHERE num_entretien = '" + t.getNum_entretien() + "'";
        String req = "INSERT INTO `vehicule`(`disponibilite`, `num_entretien`, `date_entretien`, `res_entretien`) VALUES ('" + t.getDisponibilite() + "','" + t.getNum_entretien() + "','" + date + "','" + t.getRes_entretien() + "')";

        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req1);
            rs.next();
            int count = rs.getInt(1);
            if (count > 0) {
                throw new Exception("Enregistrement avec le même num_entretien existe déjà");
            }
            Statement st1 = cnx.createStatement();
            st1.executeUpdate(req);
            System.out.println("vehicule créé !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de l'enregistrement : " + e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    

}
