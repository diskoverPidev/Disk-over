/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.discover.main;

import edu.discover.entities.Colaborationevent;
import edu.discover.entities.Reservationevent;
import edu.discover.services.Crudcolaboration;
import edu.discover.services.Crudreservation;
import edu.discover.utils.Datasource;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author ramzi
 */
public class Main {
  public static void main(String[] args){
      
      //*****************************************************************************COLABORATION EVENT*****************************************************************************
//   Colaborationevent c = new Colaborationevent("",Date.valueOf("2017-02-28"),"ben arous",5,70);
//      Crudcolaboration cc = new Crudcolaboration();
//      cc.ajout(c);
//      
//      cc.supprimer(2);
//      
//      Colaborationevent m = new Colaborationevent("ghasen",Date.valueOf("2019-02-28"),"marsa",5,70);
//      cc.modifier(m);
//       
//        System.out.println(cc.getOneById(1));
//                
//        System.out.println("_______________________");
//         System.out.println("Affichage Tous event :");
//        System.out.println(cc.getAll());
//      
//      System.out.println("_______________________");
//       System.out.println("Tri ASC :");
//      List<Colaborationevent> listc=cc.getAll() ; 
//       List<Colaborationevent> list_tri=cc.trier(listc) ; 
//       System.out.println(list_tri);
//       
//      System.out.println("_______________________");
//       System.out.println("Recherche :");
//       List<Colaborationevent> listr=cc.getAll() ;
//       List<Colaborationevent> listr_recherche=cc.rechercher(listr,"","sqdqd") ; 
//              System.out.println(listr_recherche);

              
       //**********************************************************************RESERVATION*****************************************************************************    
     Colaborationevent col = new Colaborationevent("eya", Date.valueOf("2017-02-28"), "aaaa", 2, 1);
       Reservationevent r = new Reservationevent("azi",5,col);
      Crudreservation cr = new Crudreservation();
      Crudcolaboration cl = new  Crudcolaboration();
      //cl.ajout(col);
      //cr.ajout(r);
      cr.supprimer(r);
      
 
      Colaborationevent col2 =new Colaborationevent("eyaaaaaaaaaaaaaa", Date.valueOf("2017-02-28"), "aaaa", 2, 1);
      Reservationevent rr = new Reservationevent("azi",5,col2);
     cl.modifier(col2,"iheb");
      cr.modifier(rr,"Iheb");
    
      System.out.println(cr.getOneById(41));
    
          
      
      
      
      
      System.out.println("_______________________");
       System.out.println("Affichage Tous reservation :");
        System.out.println(cr.getAll());
        
         System.out.println("_______________________");
       System.out.println("Tri ASC :");
      List<Reservationevent> listc=cr.getAll() ; 
       List<Reservationevent> list_tri=cr.trier(listc) ; 
       System.out.println(list_tri);
      
       System.out.println("_______________________");
       System.out.println("Recherche :");
       List<Reservationevent> listr=cr.getAll() ;
       List<Reservationevent> listr_recherche=cr.rechercher(listr,"lotfi") ; 
              System.out.println(listr_recherche);
      
  }  
}
