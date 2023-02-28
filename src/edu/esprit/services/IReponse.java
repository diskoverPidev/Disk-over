/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import java.util.List;

/**
 *
 * @author abidi
 * @param <I>
 */
public interface IReponse<I> {
    public void ajouter(I i);
    public void supprimer (I i);
    public void modifier (I i);
    public I getOneById (int numR);
    public List<I> getAll();
 
    
}


