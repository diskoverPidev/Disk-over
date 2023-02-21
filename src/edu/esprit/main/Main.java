/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.main;

import com.itextpdf.text.DocumentException;
import edu.esprit.entities.Offre;
import edu.esprit.entities.Promotion;
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
        
        Offre o1 = new Offre ("nadej","2mois");
        Offre o2 =new Offre ("ete","3mois");
        
        
        
         Servicepromotion mo =new Servicepromotion ();
        Promotion k1 = new Promotion ("hiver",100,50,50,20);
        Promotion k2 = new Promotion("vacance",90,40,30,20);
        
        so.ajouter(o1);
        /*
        mo.ajouter(k1);
        so.modifier (o2);
        so.supprimer (2);
        System.out.println(so.getOneById(1)) ;
        System.out.println(so.getall()) ;
        
        mo.modifier (k2);
        mo.supprimer(3);
        System.out.println(mo.getOneById(1));
        System.out.println(mo.getall()) ;

     
        List <Offre> listo = so.getall();
        List <Offre> listo_tri = so.trier(listo);
        System.out.println(listo_tri);

       
        List <Promotion>listp = mo.getall();
        List<Promotion>listp_tri = mo.trier(listp);
        System.out.println(listp_tri);

        List<Offre> listc=so.getall() ;

        List<Offre> recherche_offre=so.rechercher(listc,"nadej","2") ; 
        System.out.println(recherche_offre);
        
        mo.ajouter(k1);
*/
        
       
       
     
        
        
        
        
        
        
        
        
        
        
    }

}
