/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import java.util.List;

/**
 *
 * @author faten
 */
public interface IFacture  <T> {
    public void ajouter(T t);
    public void supprimer(int id);
    public void modifier(T t);
    public List<T> getAll();
   // public T getOnebyId(int id);
    public T getOnebyId(int id);
    
}
