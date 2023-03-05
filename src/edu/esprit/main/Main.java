/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.main;

import edu.esprit.entities.FactureR;
import edu.esprit.entities.FormulaireR;
import edu.esprit.services.ServiceFacture;
import edu.esprit.services.ServiceFormulaire;
import edu.esprit.utils.DataSource;
import java.sql.Date;

/**
 *
 * @author faten
 */
public class Main {

    public static void main(String[] args) {
        DataSource.getInstance();
        //Reservation r = new Reservation(3,"Intra","4022023","5022023",3);
         //Reservation r1 = new Reservation(3,"Intra","4022023","5022023",4);
        //Reservation r = new Reservation();

        ServiceFormulaire sform = new ServiceFormulaire();
        FormulaireR fr1 = new FormulaireR("faten", 27046924, "faten.benabdelaziz@esprit.tn", 1, "Intra", "moyenne_gamme", "manar2", "Ariana", "rien");
        FormulaireR fr2 = new FormulaireR("Eya", 27046924, "ketata.eya@esprit.tn", 2, "Intra", "moyenne_gamme", "Ariana", "El Naser", "rien");

        sform.ajouter(fr2);
        sform.notifier("Nouvelle notif", "Reservation acceptée");
        
        ServiceFacture sfact = new ServiceFacture();
        
        FactureR fact1 = new FactureR(Date.valueOf("2001-01-31"),"payé","notes",fr1);
        FactureR fact2 = new FactureR(Date.valueOf("2001-06-18"),"payé","notes",fr2);
        
       // sfact.ajouter(fact1);
       // sfact.ajouter(fact2);
        //System.out.println(sfact.getAll());
        //System.out.println(sfact.getOnebyId(2));
        //sform.modifier(fr1);
        //sform.supprimer(2);
        
        
    
}
}