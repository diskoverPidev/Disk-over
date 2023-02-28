/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.main;

import edu.esprit.entities.Admin;
import edu.esprit.entities.Chauffeur;
import edu.esprit.entities.Client;
import edu.esprit.services.Serviceadmin;
import edu.esprit.services.Servicechauffeur;
import edu.esprit.services.Serviceclient;
import edu.esprit.utils.datasource;
import static java.util.Collections.list;
import java.util.List;


/**
 *
 * @author lenovo
 */
public class Main {
    public static void main(String[] args) {
        datasource.getInstance();
     Client c =new Client(); 
              
               Client c2 =new Client("12345555","dd","hj888hj","ff","mohamed.jouini@esprit.tn","jk66kddj" );
                             Admin a =new Admin("12345558","dd","hj888hj","admin","mohamed.jouini@esprit.tn","jk66kddj" );


     Serviceclient sp =new Serviceclient() ; 
     Servicechauffeur sp2 = new Servicechauffeur();
     Serviceadmin sp3 = new Serviceadmin() ;    
     
     Chauffeur ch= new Chauffeur("12541238","dd","hj888hj","ff","mohamed.jouini@esprit.tn","jk66kddj") ; 
        // Chauffeur ch2= new Chauffeur("15","","hj8d88hj","jjk","jk66kj") ; 

     
   // sp2.ajouter(ch);
    //  sp2.ajouter(ch2);
    System.out.println(sp2.getall()) ;
     // System.out.println(sp2.getOneById(15)) ;
   //  sp2.supprimer("15") ; 
    // sp.ajouter(c2) ;
   sp3.ajouter(a) ; 
   
          //sp2.ajouter(ch2) ;
          //sp3.ajouter(a);
          sp2.ajouter(ch);

//          sp.ajouter(c3) ;

     

       // sp2.ajouter(ch) ;   
    //   sp2.ajouter(ch2) ;

        
       // sp2.supprimer(1234);
       
     //sp.modifier(c2);
     
  /// System.out.println(sp.getall()) ;
 //  System.out.println(sp.getOneById(1)) ;
List<Client> listc=sp.getall() ; 
List<Chauffeur> listch=sp2.getall() ; 
List<Admin> lista=sp3.getall() ;
List<Client> tri_client=sp.trier(listc) ;    
System.out.println(tri_client);
List<Client> recherche_client=sp.rechercher(listc,"","hj888hj") ; 
System.out.println(recherche_client);
List<Chauffeur> tri_chauffeur=sp2.trier(listch) ;    
System.out.println(tri_chauffeur);
List<Chauffeur> recherche_chauffeur=sp2.rechercher(listch,"","hj888hj") ; 
System.out.println(recherche_chauffeur);
List<Admin> tri_admin=sp3.trier(lista) ;    
System.out.println(tri_admin);
List<Admin> recherche_admin=sp3.rechercher(lista,"","hj888hj") ; 
System.out.println(recherche_admin);



    }
} 
