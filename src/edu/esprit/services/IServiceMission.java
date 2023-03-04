/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author asus
 */
public interface IServiceMission<T> {
    public int calculer_duree_mission(String matricule);
     public List<T> recherche(String Matricule);
     public List<T> getbydate (Date date);
                   public Map<Date,Integer> calculer_duree_mission_mat(String matricule);


     
    
}
