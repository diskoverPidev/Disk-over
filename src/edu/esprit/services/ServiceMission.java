/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Mission;
import edu.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author asus
 */
public class ServiceMission implements IService<Mission>, IServiceMission {
         Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Mission t) {
               try {
                String req="INSERT INTO `mission`(`Matricule`, `description`, `heure_debut`, `heure_fin`) VALUES ('"+t.getMatricule()+"','"+t.getDescription()+"','"+t.getHeure_debut()+"','"+t.getHeure_fin()+"')";
                Statement st = cnx.createStatement();
                st.executeUpdate(req);
                System.out.println("mission created!");
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());            }
    }

    @Override
    public void supprimer(int id) {
          try {
                String req="DELETE FROM `mission` WHERE id_mission="+id;
                Statement st= cnx.createStatement();
                st.executeUpdate(req);
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        
    }

    @Override
    public void modifier(Mission t) {
        try {
                String req = "UPDATE `mission` SET `id_chauffeur`='"+t.getMatricule()+"',`description`='"+t.getDescription()+"',`heure_debut`='"+t.getHeure_debut()+"',`heure_fin`='"+t.getHeure_fin()+"' WHERE id_mission=3";   
                Statement st= cnx.createStatement();
                st.executeUpdate(req);
                System.out.println("Mission modified");
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
    }

    @Override
    public Mission getOneByid(int id) {
          Mission m1= null;
        try{
        String req = "SELECT * FROM `mission` WHERE id_mission="+id;
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while(rs.next()){
        m1 = new Mission(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4), rs.getTimestamp(5));
            System.out.println("Mission affiche");
        }
        
        }catch (SQLException ex){
        System.err.println(ex.getMessage());
        }
        return m1;
    }

    @Override
    public List<Mission> getALL() {
          List<Mission> result = new ArrayList<>();
        try{
            String req= "SELECT * FROM `mission`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()){
            Mission m = new Mission(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4), rs.getTimestamp(5));
            result.add(m);
            
            }
           
        }catch (SQLException ex){
        System.err.println(ex.getMessage());
        }
        return result;
    }




    @Override
    public List recherche(String Matricule) {
          List<Mission> result = new ArrayList<>();
        try{
            String req= "Select * FROM mission WHERE Matricule = ?";
              PreparedStatement ps = cnx.prepareStatement(req);
              ps.setString(1, Matricule);
              ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Mission m = new Mission(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4), rs.getTimestamp(5));
            result.add(m);
            }
           
        }catch (SQLException ex){
        System.err.println(ex.getMessage());
        }
              return result.stream().filter(rs-> rs.getMatricule().equals(Matricule)).collect(Collectors.toList());

    }

    @Override
    public List getbydate(Date date) {
           List<Mission> result = new ArrayList<>();
        try{
            String req= "Select * FROM mission WHERE DATE(heure_debut)= '" + date.toString() + "'";
             Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()){
            Mission m = new Mission(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4), rs.getTimestamp(5));
            result.add(m);
            
            }
           
        }catch (SQLException ex){
        System.err.println(ex.getMessage());
        }
        return result;
    }

    @Override
    public int calculer_duree_mission(String matricule) {
              int duree_moy =0;
         try {
//            String req = "SELECT\n"
//                    + "    AVG(\n"
//                    + "        TIMESTAMPDIFF(SECOND, `heure_debut`, `heure_fin`)\n"
//                    + "    ) AS duree_moy\n"
//                    + "FROM\n"
//                    + "    `mission`";
            String req = "SELECT\n"
                    + "    AVG(\n"
                    + "        TIMESTAMPDIFF(SECOND, `heure_debut`, `heure_fin`)\n"
                    + "    ) AS duree_moy\n"
                    + "FROM\n"
                    + "    `mission` WHERE `Matricule` = '"+ matricule +"'";
                   
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
               duree_moy = rs.getInt("duree_moy");
                
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         return duree_moy;
    }

    @Override
    public Map calculer_duree_mission_mat(String matricule) {
    Map<Date, Integer> dureesParJour = new HashMap<>();
    try {
        String req = "SELECT DATE(heure_debut) AS Jour, SUM(TIMESTAMPDIFF(SECOND, `heure_debut`, `heure_fin`)) AS total_duree FROM `mission` WHERE Matricule = ? GROUP BY Jour";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, matricule);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Date jour = rs.getDate("Jour");
            int duree = rs.getInt("total_duree");
            dureesParJour.put(jour, duree);
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return dureesParJour;
}

    }



    

  
    

