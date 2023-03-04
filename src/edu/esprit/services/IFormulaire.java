/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.FormulaireR;
import java.util.List;

/**
 *
 * @author faten
 */
public interface IFormulaire <T> {
    public void ajouter(T t);
    public void supprimer(int id);
    public void modifier(T t);
    public List<T> getAll();
    public List<T> getOnebyId(int id);
   // public T getOnebyId(int id);
    public void notifier(String titre, String msg);
    public void StripePaiement();
    /*   public void ajouter(T t);
    public void supprimer(int id);
    public void modifier(T t);
    //public void refuserRes(T t);
    //public void accepterRes(T t);
    public void notifier(String titre, String msg);
    public T getOnebyID(int id);
    public List<T> getAll();
*/
    
}
