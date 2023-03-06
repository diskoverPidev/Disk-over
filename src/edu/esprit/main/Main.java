/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.main;

import com.itextpdf.text.DocumentException;
import edu.esprit.entities.Offre;
import edu.esprit.entities.Promotion;
import edu.esprit.services.IOffreService;
import edu.esprit.services.IPromotionService;
import edu.esprit.services.Serviceoffre;
import edu.esprit.services.Servicepromotion;
import edu.esprit.utils.Datasource;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Hp
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException, DocumentException {
        Connection cnx=Datasource.getInstance().getConnection();
        
        Serviceoffre so = new Serviceoffre () ;
        
        Offre o1 = new Offre (45,"jouini","3mois");
        Offre o2 =new Offre ("ete","3mois");
        
        
        IOffreService os = new Serviceoffre();
         
        
       // os.ajouterOffre(o1);
       //os.supprimerOffre(o1);
        //os.afficherOffres();
        
        
        
        Promotion p = new Promotion(1,"aze",12,12,12,12,3);
        
        
        
        IPromotionService ps = new Servicepromotion();
      
       // ps.ajouterPromotion(p);
       // ps.supprimerPromotion(p);
        //ps.afficherPromotions();
       // ps.modifierPromotion(ps);
               
        
        
        
        
        
        
    }

}
