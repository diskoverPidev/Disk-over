/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.discover.services;

import edu.discover.entities.Colaborationevent;
import edu.discover.entities.Reservationevent;
import edu.discover.utils.Datasource;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.sql.Connection;
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
 * @author ramzi
 */
public class Crudreservation implements IService<Reservationevent> {
    Connection cnx = Datasource.getInstance().getCnx();

    @Override
    public void ajout(Reservationevent t) {
 try {
        if (t.getNomclient().isEmpty()) {
            System.err.println("Remplir votre nom !! ");
            return;
        }
        
        // Afficher une notification système pour l'administrateur
            SystemTray tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().createImage("icon.png"); // chemin vers une icône pour la notification
            TrayIcon trayIcon = new TrayIcon(image, "Nouvelle reservation");
            trayIcon.setImageAutoSize(true);
            trayIcon.setToolTip("Nouvelle reservation");
            tray.add(trayIcon);
            trayIcon.displayMessage("Nouvelle reservation ajoutée", "Une nouvelle reservation a été ajoutée pour vous , veillez consulter ceci.", MessageType.INFO);
        
        // Check if the client name already exists in the database
        String checkQuery = "SELECT COUNT(*) FROM `reservationvehiculee` WHERE NomClient=?";
        PreparedStatement checkStatement = cnx.prepareStatement(checkQuery);
        checkStatement.setString(1, t.getNomclient());
        ResultSet checkResult = checkStatement.executeQuery();
        checkResult.next();
        int count = checkResult.getInt(1);
        if (count > 0) {
            System.err.println("Vous avez deja reservé !! ");
            return;
        }

        String req = "INSERT INTO `reservationvehiculee`(`NomClient`, `NbrClient`,`NomEvent`) VALUES (?, ?,?)";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setString(1, t.getNomclient());
        st.setInt(2, t.getNbrclient());
        st.setString (3, t.getColab().getNomevent());
        st.executeUpdate();
        System.out.println("Reservation ajouter!");
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }   catch (AWTException ex) {  
            Logger.getLogger(Crudreservation.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }

    
    public void supprimer(Reservationevent t) {
        try {
            String req = "DELETE FROM `reservationvehiculee` WHERE NomClient = ? ";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, t.getNomclient());
            st.executeUpdate();
            System.out.println("_______________________");
            System.out.println("Colab deleted!!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Reservationevent t,String s) {
        try {

            String req = "UPDATE `reservationvehiculee` SET `NomClient`= ?,`NbrClient`= ? ,`NomEvent`= ? WHERE IdReservationv = 13 ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getNomclient());
            ps.setInt(2, t.getNbrclient());
            ps.setString(3,  t.getColab().getNomevent());
            ps.executeUpdate();
            System.out.println("_______________________");
            System.out.println("row modified");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public Reservationevent getOneById(int id) {
Reservationevent c1 = null;
        try {
            String req = "SELECT * FROM `reservationvehiculee` WHERE IdReservationv=" + id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
           Colaborationevent col =new Colaborationevent(rs.getString("nomevent"));
                c1 = new Reservationevent(
                        rs.getString("NomClient"),
                        rs.getInt("NbrClient"),
                        col);
                System.out.println("_______________________");
                System.out.println("Reservation affichée :");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return c1;    }

    @Override
    public List<Reservationevent> getAll() {
List<Reservationevent> resercation = new ArrayList<>();
        try {
                    String req ="SELECT  reservationvehiculee.*,colaborationevent.* FROM reservationvehiculee INNER JOIN colaborationevent ON reservationvehiculee.NomEvent=colaborationevent.NomEvent";
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                
                Colaborationevent col =new Colaborationevent(result.getString("nomevent"),result.getDate("dateevent"),result.getString("adresseevent"),result.getInt("nbrplacevehicule"),result.getInt("prixvehiculeevent"));
                Reservationevent rec = new Reservationevent(
                        result.getString("NomClient"),
                        result.getInt("NbrClient"),
                        col
                );
                resercation.add(rec);
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
               
        return resercation;    }
    
    
    
     public static List<Reservationevent> trier(List<Reservationevent> listc) {
        return listc.stream()
                .sorted(Comparator.comparing(Reservationevent::getNbrclient))
                .collect(Collectors.toList());
    }
    
    
     
     public static List<Reservationevent> rechercher(List<Reservationevent> listc,String nomclient)
   {
       return (List<Reservationevent>) listc.stream()
        .filter(a -> a.getNomclient().equalsIgnoreCase(nomclient)).collect(Collectors.toList());
       
       
   }
     
     
     
     
     
    
    
    
    
}
