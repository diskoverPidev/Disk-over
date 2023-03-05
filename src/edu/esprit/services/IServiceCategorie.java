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
public interface IServiceCategorie<T> {
     public List<T> recherche(String type);
     public List<T> tri(String marque);
     public int nb_vehicule();
    public List<T> getALL(String type);

     
     
}
