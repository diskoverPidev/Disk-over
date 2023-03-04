/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import com.stripe.Stripe;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import edu.esprit.entities.FormulaireR;
import edu.esprit.utils.DataSource;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author faten
 */
public class ServiceFormulaire implements IFormulaire<FormulaireR> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(FormulaireR t) {
        try {
            String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(t.getMail());
            if (!matcher.matches()) {
                throw new SQLException("L'adresse e-mail " + t.getMail() + " n'est pas valide.");
            }
            // String req = "INSERT INTO `reservation`(`numR`, `typeR`, `dateDeb`, `dateFin`, `nbrPers`) VALUES (?,?,?,?,?)";
            String req = "INSERT INTO `formulairer`(`nom`, `tlp`, `mail`, `nbr`, `type`, `categ`, `depart`, `destination`, `opt`) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setString(1, t.getNom());
            ps.setInt(2, t.getTlp());
            ps.setString(3, t.getMail());
            ps.setInt(4, t.getNbr());
            ps.setString(5, t.getType());
            ps.setString(6, t.getCateg());
            ps.setString(7, t.getDepart());
            ps.setString(8, t.getDestination());
            ps.setString(9, t.getOpt());

            ps.executeUpdate();
            System.out.println("Reservation created");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `formulairer` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);

            System.out.println("Reservation deleted");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(FormulaireR t) {
        try {

            //   String req = "UPDATE reservation SET `numR`= ?,`typeR`= ?,`dateDeb`= ?,`dateFin`= ?,`nbrPers`= ? WHERE id = 3";
            String req = "UPDATE `formulairer` SET `nom`= ?,`tlp`= ?,`mail`= ?,`nbr`= ?,`type`= ?,`categ`= ?,`depart`= ?,`destination`= ?,`opt`= ? WHERE id = 2";
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setString(1, t.getNom());
            ps.setInt(2, t.getTlp());
            ps.setString(3, t.getMail());
            ps.setInt(4, t.getNbr());
            ps.setString(5, t.getType());
            ps.setString(6, t.getCateg());
            ps.setString(7, t.getDepart());
            ps.setString(8, t.getDestination());
            ps.setString(9, t.getOpt());
            ps.executeUpdate();
            System.out.println("_____________________");
            System.out.println("row modified");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

   

  /* @Override
    public FormulaireR getOnebyId(int id) {
        FormulaireR fr =null;
        try {
            String req = "Select * from `formulairer` where id=" + id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

            fr = new FormulaireR(rs.getInt("id"), rs.getString("nom"),rs.getInt("tlp"), rs.getString("mail"), rs.getInt("nbr"), rs.getString("type"), rs.getString("categ"), rs.getString("depart"), rs.getString("destination"),rs.getString("opt"));
                //r = new Reservation(id, dateR, req, req, req, id, id, req, id, req, id, id, req, req)
                System.out.println("Reservation affichée");

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return fr;   
    }*/

    
   /* 
     @Override
    public List<FormulaireR> getOnebyID(int id) {
        List<FormulaireR> result = new ArrayList<>();
        try {
            String req = "Select * from formulairer where id=" + id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

          FormulaireR fr = new FormulaireR(rs.getInt("id"), rs.getString("nom"),rs.getInt("tlp"), rs.getString("mail"), rs.getInt("nbr"), rs.getString("type"), rs.getString("categ"), rs.getString("depart"), rs.getString("destination"),rs.getString("opt"));                //r = new Reservation(id, dateR, req, req, req, id, id, req, id, req, id, id, req, req)
                result.add(fr);
                System.out.println("Reservation affichée");

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return result;
        
    }
    
       
    */
    
    @Override
    public List<FormulaireR> getAll() {
        
        
                List<FormulaireR> result = new ArrayList<>();

        try {
            String req = "Select * from formulairer ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
            FormulaireR fr = new FormulaireR(rs.getInt("id"), rs.getString("nom"),rs.getInt("tlp"), rs.getString("mail"), rs.getInt("nbr"), rs.getString("type"), rs.getString("categ"), rs.getString("depart"), rs.getString("destination"),rs.getString("opt"));

//   Reservation r = new Reservation(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6));
                result.add(fr);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return result;
        
    }

    @Override
    public void notifier(String titre, String msg) {
        
        
        // Check if the system tray is supported
        if (!SystemTray.isSupported()) {
            System.out.println("System tray is not supported.");
            return;
        }

        // Get the system tray and create a tray icon
        SystemTray tray = SystemTray.getSystemTray();
        Image image = Toolkit.getDefaultToolkit().getImage("path/to/notification_icon.png");
        TrayIcon trayIcon = new TrayIcon(image, "Notification");

        // Add the tray icon to the system tray
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("Tray icon could not be added.");
            return;
        }

        // Show the notification
        trayIcon.displayMessage(titre, msg, TrayIcon.MessageType.INFO);
    }
    
    
  /*  
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
    

    @Override
    public List<FormulaireR> getOnebyId(int id) {
                List<FormulaireR> result = new ArrayList<>();
        try {
            String req = "Select * from formulairer where id=" + id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

          FormulaireR fr = new FormulaireR(rs.getInt("id"), rs.getString("nom"),rs.getInt("tlp"), rs.getString("mail"), rs.getInt("nbr"), rs.getString("type"), rs.getString("categ"), rs.getString("depart"), rs.getString("destination"),rs.getString("opt"));                //r = new Reservation(id, dateR, req, req, req, id, id, req, id, req, id, id, req, req)
                result.add(fr);
                System.out.println("Reservation affichée");

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return result;
    }
*/
    @Override
    public void StripePaiement()  {
        
         // Set your API key
        Stripe.apiKey = "sk_test_51Mdev4BkXviPD7IqckPNDsrV8KvxnSi8o6VEu8H8VOZBIxTnvVlCn9peUUOAIFo94JTfZHUhuSnh2LPnx1PEOqvj00Bj9fl98o";

        // Create a map with the parameters for the charge
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", 1000); // amount in cents
        chargeParams.put("currency", "usd");
        chargeParams.put("source", "tok_visa"); // replace with your test token
        
         try {
            //Charge charge = Charge.create(chargeParams);
            Charge charge = Charge.create(chargeParams);
            System.out.println(charge);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } /*catch (APIConnectionException e) {
            e.printStackTrace();
        }*/ catch (CardException e) {
            e.printStackTrace();
        }/* catch (APIException e) {
            e.printStackTrace();
        } */catch (StripeException ex) {
            System.err.println(ex.getMessage());
        }
    
    }

    @Override
    public List <FormulaireR> getOnebyId(int id) {
                List<FormulaireR> res = new ArrayList<>();
        try {
            String req = "Select * from formulairer where id=" + id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

          FormulaireR fr = new FormulaireR(rs.getInt("id"), rs.getString("nom"),rs.getInt("tlp"), rs.getString("mail"), rs.getInt("nbr"), rs.getString("type"), rs.getString("categ"), rs.getString("depart"), rs.getString("destination"),rs.getString("opt"));                //r = new Reservation(id, dateR, req, req, req, id, id, req, id, req, id, id, req, req)
                res.add(fr);
                System.out.println("Reservation affichée");

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return res;
    }
    

}
