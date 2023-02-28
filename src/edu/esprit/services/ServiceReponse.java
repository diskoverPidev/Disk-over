/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import static com.sun.org.apache.xpath.internal.XPath.SELECT;
import static com.sun.webkit.dom.EventImpl.SELECT;
import edu.esprit.entities.Reclamation;
import edu.esprit.entities.Reponse;
import edu.esprit.utils.Datasource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import static javax.swing.text.html.HTML.Tag.SELECT;

/**
 *
 * @author abidi
 */
public abstract class ServiceReponse implements IReponse<Reponse> {

    Connection cnx = Datasource.getInstance().getCnx();

    @Override
    public void ajouter(Reponse i) {

        if (i.getResultat().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Le champs Resultat est obligatoires.", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
        }
        if (!i.getResultat().matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(null, "Le champs Resultat ne peut contenir que des lettres.", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {

            String req = "INSERT INTO `reponse`(`idclient`, `idchauffeur`, `num`, `resultat`, `dateR`) VALUES (?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, i.getIdclient());
            ps.setInt(2, i.getIdchauffeur());
            ps.setInt(3, i.getNum());
            ps.setString(4, i.getResultat());
            ps.setDate(5, i.getDateR());
            ps.executeUpdate();
            System.out.println("reponse added ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Reponse i) {
    
            String req = "DELETE FROM reponse WHERE num = " +i.getNum();
        
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Reponse supprimee !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Reponse i) {
        try {
            String req = "UPDATE `reponse` SET `idclient` = '" + i.getIdclient() + "', `idchauffeur` = '" + i.getIdchauffeur() + "', `num` = '" + i.getNum() + "', `resultat` = '" + i.getResultat() + "', `dateR` = '" + i.getDateR() + "' WHERE `reponse`.`num` = " + i.getNum();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("reponse updated ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Reponse getOneById(int numR) {
        Reponse i = null;
        String reqs = "SELECT i.type,i.objet, i.message, i.date "
                + "FROM reclamation t "
                + "JOIN reponse i ON t.idJ = t.idJ "
                + "WHERE i.idJ = ?";
        try {
            String req = "Select * from reponse";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                i = new Reponse(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getDate(5));
                System.out.println("Reponse afficher !");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return i;
    }

    @Override
    public List<Reponse> getAll() {

        List<Reponse> list = new ArrayList<>();
        String reqs = "SELECT i.type,i.objet, i.message, i.date "
                + "FROM reclamation t "
                + "JOIN reponse i ON t.idJ = t.idJ "
                + "WHERE i.idJ = ?";
        try {
            String req = "Select * from reponse";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Reponse i = new Reponse(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getDate(5));
                list.add(i);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public static List<Reponse> trier(List<Reponse> listr) {
        return listr.stream()
                .sorted(Comparator.comparing((reponse) -> reponse.getDateR()))
                .collect(Collectors.toList());
    }

    public static List<Reponse> rechercher(List<Reponse> listc, String resultat) {

        return listc.stream()
                .filter(t -> t.getResultat().equalsIgnoreCase(resultat))
                .collect(Collectors.toList());
    }

  

}
