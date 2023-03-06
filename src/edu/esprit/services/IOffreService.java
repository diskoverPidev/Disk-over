/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import static java.util.Collections.list;
import java.util.List;

/**
 *
 * @author Hp
 * @param <Offre>
 */
public interface IOffreService<Offre> {
    public void ajouterOffre(Offre o); 
    public void supprimerOffre(Offre o) ; 
    public void modifierOffre(Offre o) ;
    public List<Offre> afficherOffres();
    
    
}
