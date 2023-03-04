/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author asus
 */
public interface IServiceVehicule<T> {
     public List<T> recherche(int disponibilite);
     public List<T> tri(Date date_entretien);
     public void rapport();
              public void ajouterV(T t, Date date);

}
