/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.FactureR;
import edu.esprit.entities.FormulaireR;
import edu.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author faten
 */
public class ServiceFacture implements IFacture<FactureR> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(FactureR t) {
        try {

            // String req = "INSERT INTO `reservation`(`numR`, `typeR`, `dateDeb`, `dateFin`, `nbrPers`) VALUES (?,?,?,?,?)";
            String req = "INSERT INTO `facturer`(`date_facture`, `statut`, `notes`, `id`) VALUES ('" + t.getDate_Facture() + "','" + t.getStatut() + "','" + t.getNotes() + "','" + t.getFormulaire().getId() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            //PreparedStatement ps = cnx.prepareStatement(req);
            /*  ps.setInt(1, t.getNum_Facture());
            // ps.setInt(3, t.getR().getNum());
            ps.setDate(2, t.getDate_Facture());
            ps.setString(3, t.getStatut());
            ps.setString(4, t.getNote());
            ps.setInt(5, t.getReservation().getNum());

            ps.executeUpdate();*/

            System.out.println("Facture created !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @Override
    public void supprimer(int id) {

        try {
            String req = "DELETE FROM `facturer` WHERE id_facture = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);

            System.out.println("Facture deleted !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(FactureR t) {
        try {

            //   String req = "UPDATE reservation SET `numR`= ?,`typeR`= ?,`dateDeb`= ?,`dateFin`= ?,`nbrPers`= ? WHERE id = 3";
            String req = "UPDATE `facturer` SET `date__Facture`= ? ,`statut`=?,`notes`=? ,`id`=? WHERE id_facture = 2";

            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setDate(2, t.getDate_Facture());
            ps.setString(3, t.getStatut());
            ps.setString(4, t.getNotes());
            ps.setInt(5, t.getFormulaire().getId());

            ps.executeUpdate();
            System.out.println("_____________________");
            System.out.println("row modified");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<FactureR> getAll() {

        List<FactureR> result = new ArrayList<>();

        try {
            //String req = "SELECT f.*, r.* FROM facture f JOIN reservation r ON f.num_Facture = r.num";
            String req = "SELECT facturer.*, formulairer.* FROM facturer INNER JOIN formulairer ON facturer.id_facture=formulairer.id";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

                FormulaireR fr = new FormulaireR(rs.getString("nom"), rs.getInt("tlp"), rs.getString("mail"), rs.getInt("nbr"), rs.getString("type"), rs.getString("categ"), rs.getString("depart"), rs.getString("destination"), rs.getString("opt"));
                FactureR f = new FactureR(rs.getDate("date_Facture"), rs.getString("statut"), rs.getString("notes"), fr);

                result.add(f);

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return result;

    }

    @Override
    public FactureR getOnebyId(int id_facture) {
         FactureR f = null;
        try {
            String req = "SELECT facturer.*, formulairer.* FROM facturer INNER JOIN formulairer ON facturer.id_facture=formulairer.id";
           // String req1 = "SELECT facturer.*, formulairer.* FROM";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
            FormulaireR fr = new FormulaireR(rs.getString("nom"), rs.getInt("tlp"), rs.getString("mail"), rs.getInt("nbr"), rs.getString("type"), rs.getString("categ"), rs.getString("depart"), rs.getString("destination"), rs.getString("opt"));

             f = new FactureR(rs.getInt("id_facture"),rs.getDate("date_facture"), rs.getString("statut"), rs.getString("notes"), fr);

               
                System.out.println("Reservation affichée");
                
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return f;
    }

}
