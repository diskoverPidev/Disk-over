/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 *
 * @author asus
 */
public class Categorie {
    private int id_categorie;
    private String type;
    private String matricule;
    private String marque;
    private Vehicule v;

    public Categorie(int id_categorie, String type, String matricule, String marque) {
        this.id_categorie = id_categorie;
        this.type = type;
        this.matricule = matricule;
        this.marque = marque;
    }
    private String image;

    public String getImage() {
        return image;
    }
    public Categorie(int id_categorie, String type, String matricule, String marque, Vehicule v) {
        this.id_categorie = id_categorie;
        this.type = type;
        this.matricule = matricule;
        this.marque = marque;
        this.v = v;
    }

    public Categorie(String type, String matricule, String marque, Vehicule v) {
        this.type = type;
        this.matricule = matricule;
        this.marque = marque;
        this.v = v;
    }

    public Categorie() {
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Categorie(String type, String matricule, String marque) {
        this.type = type;
        this.matricule = matricule;
        this.marque = marque;
    }

   

    public void setV(Vehicule v) {
        this.v = v;
    }

    public Vehicule getV() {
        return v;
    }

    public int getId() {
        return id_categorie;
    }

    public String getType() {
        return type;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getMarque() {
        return marque;
    }
    public void setType(String type) {
        if(type.equals("moyenne") || type.equals("haute")) {
           this.type = type;
       } else {
           throw new IllegalArgumentException("Le type doit être moyenne ou haute");
       }
       
    }

    public void setMatricule(String matricule) {
        if(matricule.length() > 0 && matricule.length() < 255) {
             this.matricule = matricule;
       } else {
           throw new IllegalArgumentException("La matricule doit être compris entre 1 et 255 caractères");
       }
       
      
    }

    public void setMarque(String marque) {
        if(marque.length() > 0 && marque.length() < 255) {
            this.marque = marque;
       } else {
           throw new IllegalArgumentException("La marque doit être compris entre 1 et 255 caractères");
       }
      
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.id_categorie;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Categorie other = (Categorie) obj;
        if (this.id_categorie != other.id_categorie) {
            return false;
        }
        return true;
    }

   /* @Override
    public String toString() {
        return "Categorie{type=" + type + ", matricule=" + matricule + ", marque=" + marque + '}';
    }
    */

    @Override
    public String toString() {
        return "Categorie{" + "type=" + type + ", matricule=" + matricule + ", marque=" + marque + ", v=" + v + '}';
    }
    

 
}
