
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
 * @param <T>
 */
public interface IService<T> {
    public void ajouter(T t);
    public void supprimer (int idR);
    public void modifier (T t);
    public List<T> getAll();
   
 
    /**
     *
     * @param idRe
     * @return
     */
    public T getOneById (int idRe);
 
    
}
