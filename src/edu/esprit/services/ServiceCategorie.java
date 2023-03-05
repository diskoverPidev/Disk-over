/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Categorie;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author asus
 */
public class ServiceCategorie implements IService<Categorie>, IServiceCategorie<Categorie> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public List<Categorie> getALL() {
        List<Categorie> result = new ArrayList<>();
        try {
            //     String req= "SELECT  c.`type`, c.`matricule`, c.`marque`, v.`disponibilite` "+"FROM categorie c"+"JOIN vehicule v ON c.disponibilite=v.disponibilite";
            //  String req="SELECT  `type`, `matricule`, `marque`,`disponibilite` FROM categorie ";  
            // String req = "SELECT * FROM `categorie` JOIN vehicule ON categorie.disponibilite=vehicule.disponibilite";
            String req = "SELECT categorie.*, vehicule.* FROM categorie INNER JOIN vehicule ON categorie.num_entretien=vehicule.num_entretien";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                //   Categorie c = new Categorie(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                Vehicule v = new Vehicule(rs.getInt("disponibilite"), rs.getInt("num_entretien"), rs.getDate("date_entretien"), rs.getString("res_entretien"));
                //Vehicule v = new Vehicule (rs.getInt("id"),rs.getInt("disponibilite"),rs.getInt("num_entretien"), rs.getDate("date_entretien"), rs.getString("res_entretien"));
                Categorie c = new Categorie(rs.getString("type"), rs.getString("matricule"), rs.getString("marque"), v);

                result.add(c);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return result;
    }

//    @Override
//    public void ajouter(Categorie t) {
//            try {
//                // String req="INSERT INTO `categorie`(`type`, `matricule`, `marque`) VALUES ('"+t.getType()+"','"+t.getMatricule()+"','"+t.getMarque()+"')";
//              String req= "INSERT INTO `categorie`(`type`, `matricule`, `marque`, `disponibilite`) VALUES ('"+t.getType()+"','"+t.getMatricule()+"','"+t.getMarque()+"','"+t.getV().getDisponibilite()+"')";
//                Statement st = cnx.createStatement();
//                st.executeUpdate(req);
//                System.out.println("categorie created!");
//            } catch (SQLException ex) {
//                System.err.println(ex.getMessage());            }
//    }
    @Override
    public void ajouter(Categorie t) {
        String req1 = "SELECT COUNT(*) FROM categorie WHERE matricule = '" + t.getMatricule() + "'";
        String req = "INSERT INTO `categorie`(`type`, `matricule`, `marque`, `num_entretien`)  VALUES ('" + t.getType() + "','" + t.getMatricule() + "','" + t.getMarque() + "','" + t.getV().getNum_entretien() +"')";

        // sqldate = new java.sql.Date(23,02, 20);
        try {
            //   String req="INSERT INTO `vehicule`(`disponibilite`, `num_entretien`, `date_entretien`, `res_entretien`) VALUES ('"+t.getDisponibilite()+"','"+t.getNum_entretien()+"','"+date+"','"+t.getRes_entretien()+"') ON CONFLICT(disponibilite, date_entretien , res_entretien) DO NOTHING ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req1);
            rs.next();
            int count = rs.getInt(1);
            if (count > 0) {
                throw new Exception("Enregistrement avec la meme matricule existe déjà");
            }
            Statement st1 = cnx.createStatement();
            st1.executeUpdate(req);
            System.out.println("categorie created!");
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de l'enregistrement : " + e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `categorie` WHERE id_categorie=" + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @Override
    public void modifier(Categorie t) {
        try {
            //  String req="UPDATE `categorie` SET `type`='"+t.getType()+"',`matricule`='"+t.getMatricule()+"',`marque`='"+t.getMarque()+"' WHERE 3";
            String req = "UPDATE `categorie` SET `type`='" + t.getType() + "',`matricule`='" + t.getMatricule() + "',`marque`='" + t.getMarque() + "',`disponibilite`='" + t.getV().getDisponibilite() + "' WHERE id_categorie=24";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Categorie modified");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public Categorie getOneByid(int id) {
        Categorie c1 = null;
        try {
            String req = "SELECT * FROM categorie WHERE id_categorie=" + id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Vehicule v = new Vehicule(rs.getInt("disponibilite"));
                c1 = new Categorie(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), v);
                System.out.println("categorie affiche");
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return c1;
    }

    @Override
    public List<Categorie> recherche(String type) {
        List<Categorie> result = new ArrayList<>();
        try {
            //     String req= "Select * FROM categorie WHERE type = ?";
            String req = "SELECT categorie.*, vehicule.* FROM categorie INNER JOIN vehicule ON categorie.disponibilite=vehicule.disponibilite  WHERE type = ?";

            // Statement st = cnx.createStatement();
            // ResultSet rs = st.executeQuery(req);
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, type);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //   Vehicule v = new Vehicule(rs.getInt("disponibilite"));
                Vehicule v = new Vehicule(rs.getInt("disponibilite"), rs.getInt("num_entretien"), rs.getDate("date_entretien"), rs.getString("res_entretien"));

                Categorie c = new Categorie(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), v);
                result.add(c);
                System.out.println(result);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return result.stream().filter(rs -> rs.getType().contains(type)).collect(Collectors.toList());
    }

    @Override
    public List<Categorie> tri(String marque) {
        List<Categorie> result = new ArrayList<>();
        try {
            String req = "Select * FROM categorie ORDER BY marque ASC";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Vehicule v = new Vehicule(rs.getInt("disponibilite"));
                Categorie c = new Categorie(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), v);
                result.add(c);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return result.stream().sorted(Comparator.comparing(Categorie::getMarque)).collect(Collectors.toList());

    }

    @Override
    public int nb_vehicule() {
        int nb_vehicules = 0;
        try {
            String req = "SELECT COUNT(id_categorie) AS nb_vehicules FROM `categorie` ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                nb_vehicules = rs.getInt("nb_vehicules");

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return nb_vehicules;
    }

    @Override
    public List<Categorie> getALL(String type) {
        List<Categorie> result = new ArrayList<>();
        try {
            String req = "SELECT * FROM categorie WHERE type=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, type);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Categorie c = new Categorie(rs.getInt("id_categorie"), rs.getString("type"), rs.getString("matricule"), rs.getString("marque"));
                result.add(c);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return result;
    }

}
