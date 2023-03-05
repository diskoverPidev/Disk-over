package edu.esprit.services;

import edu.esprit.entities.Reclamation;
import edu.esprit.utils.Datasource;
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
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.runtime.Debug.id;
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
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author abidi
 */
public class ServiceReclamation implements IService<Reclamation> {

    Connection cnx = Datasource.getInstance().getCnx();

    @Override

    public void ajouter(Reclamation t) {

        if (!String.valueOf(t.getId()).matches("[a-zA-Z0-9]+")) {
            try {
                throw new Exception("L'identifiant ne doit contenir que des lettres et des chiffres");
            } catch (Exception ex) {
                System.out.println("erreur");
            }
        }

        if (t.getObjet().isEmpty() || t.getMessage().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Les champs Objet et Message sont obligatoires.", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
        }

        try {
            String req = "INSERT INTO `reclamation`(`cin`,`type`, `objet`, `message`, `date`) VALUES (?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);

            // Vérifier si le message contient des mots offensants
            boolean isOffensive = t.getMessage().contains("offensive") || t.getMessage().contains("insulte");

            if (isOffensive) {
                // Si le message contient des mots offensants, remplacer le contenu de l'objet par des étoiles
                t.setMessage("*******");
            }
            ps.setString(1, t.getCin());
            ps.setString(2, t.getType());
            ps.setString(3, t.getObjet());
            ps.setString(4, t.getMessage());
            ps.setDate(5, t.getDate());
            ps.executeUpdate();
            System.out.println("reclamation added ");
            // Afficher une notification système pour l'administrateur
            SystemTray tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().createImage("icon.png"); // chemin vers une icône pour la notification
            TrayIcon trayIcon = new TrayIcon(image, "Nouvelle réclamation");
            trayIcon.setImageAutoSize(true);
            trayIcon.setToolTip("Nouvelle réclamation");
            tray.add(trayIcon);
            trayIcon.displayMessage("Nouvelle réclamation ajoutée", "Une nouvelle réclamation a été ajoutée pour vous , veillez consulter ceci.", MessageType.INFO);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Une erreur s'est produite lors de l'ajout de la réclamation : " + ex.getMessage(), "Erreur de base de données", JOptionPane.ERROR_MESSAGE);
        } catch (AWTException ex) {
            System.out.println("essayer de nouveau");
        }
    }

    @Override
    public void supprimer(Reclamation t) {
     
            String req = "DELETE FROM reclamation WHERE cin="+t.getCin(); 
           try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Reclamation supprimee !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      
    }

    @Override
    public void modifier(Reclamation t) {
        try {
            String req = "UPDATE `reclamation` SET `id` = '" + t.getId() + "', `type` = '" + t.getType() + "', `objet` = '" + t.getObjet() + "', `message` = '" + t.getMessage() + "', `date` = '" + t.getDate() + "' WHERE `reclamation`.`cin` = " + t.getCin();;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("reclamation updated ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

//    @Override
//    public Reclamation getOneById(String cin) {
//        Reclamation t = null;
//        try {
//            String req = "Select * from reclamation";
//            Statement st = cnx.createStatement();
//            ResultSet rs = st.executeQuery(req);
//            while (rs.next()) {
//                t = new Reclamation(rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6));
//                System.out.println("Reclamation afficher !");
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//
//        return t;
//    }



//    public List<Reclamation> getonebycin(String cin) {
//        List<Reclamation> list = new ArrayList<>();
//        String reqs = "SELECT i.type,i.objet, i.message, i.date "
//                + "FROM reclamation t "
//                + "JOIN reponse i ON t.idJ = t.idJ "
//                + "WHERE i.idJ = ?";
//        try {
//            String req = "Select * from reclamation ";
//            Statement st = cnx.createStatement();
//            ResultSet rs = st.executeQuery(req);
//            while (rs.next()) {
//                Reclamation t = new Reclamation(rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6));
//                list.add(t);
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//
//        return list;
//    }
    
  
    
    
    
//    public Reclamation getOneById(String cin) {
//        String query = "SELECT * FROM reclamation WHERE cin = " + cin + "";
//        Reclamation c = new Reclamation();
//        try{
//            Statement ste = cnx.createStatement();
//            ResultSet rs = ste.executeQuery(query);
//            while (rs.next()){
//                c.setCin(rs.getString("cin"));
//                c.setType(rs.getString("type"));
//                c.setObjet(rs.getString("objet"));
//                                c.setMessage(rs.getString("message"));
//
//               c.setDate(rs.getDate("date"));
//
//                
//            }
//        }
//        catch (SQLException e){
//            e.getMessage();
//        }
//        return c;
//    }
    
    
    @Override
    public List<Reclamation> getAll() {
        List<Reclamation> liste = new ArrayList<>();
        String query = "SELECT * FROM reclamation ";
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()){
                Reclamation r = new Reclamation();

                r.setCin(rs.getString("cin"));
                r.setType(rs.getString("type"));
                r.setObjet(rs.getString("objet"));
                 r.setMessage(rs.getString("message"));
                  r.setDate(rs.getDate("date"));
               
               
                liste.add(r);
            }
        }
        catch (SQLException e){
            e.getMessage();
        }
        return liste;
    }
    @Override
    public List<Reclamation> getOneById(String cin) {
        List<Reclamation> liste = new ArrayList<>();
        String query = "SELECT * FROM reclamation WHERE cin = " + cin + "";
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()){
                Reclamation r = new Reclamation();

                r.setCin(rs.getString("cin"));
                r.setType(rs.getString("type"));
                r.setObjet(rs.getString("objet"));
                 r.setMessage(rs.getString("message"));
                  r.setDate(rs.getDate("date"));
               
               
                liste.add(r);
            }
        }
        catch (SQLException e){
            e.getMessage();
        }
        return liste;
    }
//    @Override
//    public List<Reclamation> getAll() {
//        List<Reclamation> list = new ArrayList<>();
//        String reqs = "SELECT i.type,i.objet, i.message, i.date "
//                + "FROM reclamation t "
//                + "JOIN reponse i ON t.idJ = t.idJ "
//                + "WHERE i.idJ = ?";
//        try {
//            String req = "Select * from reclamation ";
//            Statement st = cnx.createStatement();
//            ResultSet rs = st.executeQuery(req);
//            while (rs.next()) {
//                Reclamation t = new Reclamation(rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6));
//                list.add(t);
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//
//        return list;
//    }

    public static List<Reclamation> trier(List<Reclamation> listc) {
        return listc.stream()
                .sorted(Comparator.comparing(reclamation -> reclamation.getDate()))
                .collect(Collectors.toList());
    }

    public static List<Reclamation> rechercher(List<Reclamation> listc, String input) {

        return listc.stream()
                .filter(t -> t.getObjet().equalsIgnoreCase(input) || t.getType().equalsIgnoreCase(input))
                .collect(Collectors.toList());
    }
}
