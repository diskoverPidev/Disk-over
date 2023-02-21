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
import java.util.logging.Logger;
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
                Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (t.getObjet().isEmpty() || t.getMessage().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Les champs Objet et Message sont obligatoires.", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
        }
        if (!t.getObjet().matches("[a-zA-Z]+") || !t.getMessage().matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(null, "Les champs Type, Objet et Message ne peuvent contenir que des lettres.", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String req = "INSERT INTO `reclamation`(`type`, `objet`, `message`, `date`) VALUES (?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);

            // Vérifier si le message contient des mots offensants
            boolean isOffensive = t.getMessage().contains("offensive") || t.getMessage().contains("insulte");

            if (isOffensive) {
                // Si le message contient des mots offensants, remplacer le contenu de l'objet par des étoiles
                t.setMessage("***");
            }

            ps.setString(1, t.getType());
            ps.setString(2, t.getObjet());
            ps.setString(3, t.getMessage());
            ps.setDate(4, t.getDate());
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
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimer(int idR) {
        try {
            String req = "DELETE FROM reclamation WHERE id = ? ";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, idR);
            st.executeUpdate();
            System.out.println("reclamation deleted");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Reclamation t) {
        try {
            String req = "UPDATE `reclamation` SET `id` = '" + t.getId() + "', `type` = '" + t.getType() + "', `objet` = '" + t.getObjet() + "', `message` = '" + t.getMessage() + "', `date` = '" + t.getDate() + "' WHERE `reclamation`.`id` = " + t.getId();;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("reclamation updated ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Reclamation getOneById(int id) {
        Reclamation t = null;
        try {
            String req = "Select * from reclamation";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                t = new Reclamation(rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5));
                System.out.println("Reclamation afficher !");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return t;
    }

    @Override
    public List<Reclamation> getAll() {
        List<Reclamation> list = new ArrayList<>();
        String reqs ="SELECT i.type,i.objet, i.message, i.date " +
                    "FROM reclamation t " +
                    "JOIN reponse i ON t.idJ = t.idJ " +
                    "WHERE i.idJ = ?";
        try {
            String req = "Select * from reclamation";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Reclamation t = new Reclamation(rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5));
                list.add(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public static List<Reclamation> trier(List<Reclamation> listc) {
        return listc.stream()
                .sorted(Comparator.comparing(reclamation -> reclamation.getId()))
                .collect(Collectors.toList());
    }

    public static List<Reclamation> rechercher(List<Reclamation> listc, String objet, String type) {

        return listc.stream()
                .filter(t -> t.getObjet().equalsIgnoreCase(objet) || t.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    private int isIdUnique(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
}

 





    


