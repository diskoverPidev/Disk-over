/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Promotion;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface IPromotionService<Promotion> {
     public void ajouterPromotion(Promotion p); 
    public void supprimerPromotion(Promotion p); 
    public void modifierPromotion(Promotion p);
    public List<Promotion> afficherPromotions();
}
