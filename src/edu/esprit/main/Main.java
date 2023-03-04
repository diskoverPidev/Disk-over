/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.main;

import edu.esprit.entities.Categorie;
import edu.esprit.entities.Mission;
import edu.esprit.entities.Vehicule;
import edu.esprit.services.ServiceCategorie;
import edu.esprit.services.ServiceMission;
import edu.esprit.services.ServiceVehicule;
import edu.esprit.utils.DataSource;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;



/**
 *
 * @author asus
 */
public class Main {
    public static void main(String[] args) {
        DataSource.getInstance();
       // Categorie c = new Categorie("moyenne", "128tu25", "wg");  
        // Categorie c1 = new Categorie("haute", "128tu25", "wg");   
        // Categorie c2 = new Categorie("moyenne", "128tu25", "wg");   
         java.sql.Date date = new java.sql.Date(120, 0, 20);
        Vehicule v1 = new Vehicule(1, 29,date, "bravo");    
        Categorie c = new Categorie("haute", "167tun2527", "porche", v1);
//             Categorie c1 = new Categorie("haute", "167tun2525", "ford3", v1);
       ServiceCategorie sc = new ServiceCategorie();
   //     System.out.println(sc.getALL());
//       List<Categorie> list = sc.getALL();
//       for (Categorie c1:list){
//           System.out.println("type"+c1.getType());
//                      System.out.println("marque"+c1.getMarque());
//           System.out.println("matricule"+c1.getMatricule());
//                      System.out.println("disponibilite"+c1.getV().getDisponibilite());
//                                            System.out.println("disponibilite"+c1.getV().getRes_entretien());



       
      // sc.ajouter(c);
      //  sc.modifier(c1);
     //   System.out.println(sc.getALL());
//       sc.supprimer(15);
//       sc.supprimer(16);
//       sc.supprimer(17);
//       sc.supprimer(18);
//       sc.supprimer(19);
//       sc.supprimer(20);
//       sc.supprimer(21);
        // sc.supprimer(22);
       ServiceVehicule sv = new ServiceVehicule();
//         Vehicule v1 = new Vehicule(0, 20, date, "good");
//           Categorie c1 = new Categorie("haute", "203tun2525", "BMW", v1);
//           sc.ajouter(c1);
    //    System.out.println(sc.getOneByid(23));
      //  System.out.println(sc.recherche("moyenne"));
       //    System.out.println(sc.tri("ford"));
     // sv.ajouter(v1);
      //sv.modifier(v1); 
      // sv.supprimer(1);
      //  System.out.println(sc.getOneByid(3).getId());
       // System.out.println(sv.getALL());
       // System.out.println(sv.getOneByid(5));
     
     //  Vehicule v2 = new Vehicule(1, 4, date, "good");
     //  Categorie c = new Categorie("haute", "203tun2525", "Mercedes", v2);
     //  Categorie c1 = new Categorie("moyenne", "123yu65", "toy", v1);
  //   Categorie c2 = new Categorie("moyenne", "123yu65", "wgg", v2);
    //    Vehicule v2 = new Vehicule(1, 5, date, "yess");
      // sv.ajouter(v1);
    //  sc.ajouter(c);
      // sc.ajouter(c2);
      // sc.modifier(c1);
       // sc.supprimer(14);
       // System.out.println(sc.getOneByid(16));
      // sc.recherche("toy");
       // System.out.println( sv.recherche(1));
       // System.out.println(sv.recherche(0));
      //  System.out.println(sc.recherche("toy"));
       // System.out.println(sv.tri(date));
       // sv.rapport();
       // System.out.println(sc.getOneByid(15));
     //    System.out.println(sc.getALL());
   //  sc.nb_vehicule();
  // sv.testUnique(3);
 //  Vehicule v2 = new Vehicule(1, 4, date, "good");

  //sv.ajouter(v2);
        ServiceMission sm = new  ServiceMission();
//////        System.out.println(sm.calculer_duree_mission("55"));
//////        System.out.println(sm.recherche("6"));
//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//        Calendar cal = Calendar.getInstance();
//        cal.setTimeInMillis(timestamp.getTime());
//        cal.add(Calendar.MINUTE, 30);
//        Timestamp timestamp2 = new Timestamp(cal.getTime().getTime());
//        Mission m = new Mission("6", "menzah", timestamp, timestamp2);
//       sm.ajouter(m);
              System.out.println(sm.calculer_duree_mission_mat("6"));

////          //  sm.supprimer(1);
//                Mission m1 = new Mission(3, "manar", timestamp, timestamp2);
//                sm.modifier(m1);
          //  System.out.println(sm.getOneByid(2));
        //  System.out.println(sm.getALL());
      //  System.out.println(sm.getbydate(date));
      
   
                }}
    

