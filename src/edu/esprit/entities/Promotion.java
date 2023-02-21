/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

import java.util.Objects;

/**
 *
 * @author Hp
 */
public class Promotion {
    private int id_promotion;
    private String nom_promotion;
    private int duree_promotion;
    private int prix_avant;
    private int pourcentage;
    private int prix_apres;
    

    public Promotion() {
    }

    
    
    public Promotion(int id_promotion, String nom_promotion, int duree_promotion, int prix_avant, int pourcentage, int prixa_pres, int id_offre) {
        this.id_promotion = id_promotion;
        this.nom_promotion = nom_promotion;
        this.duree_promotion = duree_promotion;
        this.prix_avant = prix_avant;
        this.pourcentage = pourcentage;
        this.prix_apres = prix_apres;
        
    }

   
    public Promotion(String nom_promotion, int duree_promotion, int prix_avant, int pourcentage, int prix_apres   ) {
        this.nom_promotion = nom_promotion;
        this.duree_promotion = duree_promotion;
        this.prix_avant = prix_avant;
        this.pourcentage = pourcentage;
        this.prix_apres = prix_apres;
        
    }

    public int getid_promotion() {
        return id_promotion;
    }


    public String getnom_promotion() {
        return nom_promotion;
    }
 public void setid_promotion(int id_promotion) {
        this.id_promotion = id_promotion;
    }
    public void setnom_promotion(String nom_promotion) {
        this.nom_promotion = nom_promotion;
    }

    public int getduree_promotion() {
        return duree_promotion;
    }

    public void setduree_promotion(int duree_promotion) {
        this.duree_promotion = duree_promotion;
    }

    public int getprix_avant() {
        return prix_avant;
    }

    public void setprix_avant(int prix_avant) {
        this.prix_avant = prix_avant;
    }

    public int getpourcentage() {
        return pourcentage;
    }

    public void setpourcentage(int pourcentage) {
        this.pourcentage = pourcentage;
    }

    public int getprix_apres() {
        return prix_apres;
    }

    public void setprix_apres(int prix_apres) {
        this.prix_apres = prix_apres;
    }

    @Override
    public String toString() {
        return "promotion{" + "id_promotion=" + id_promotion + ", nom_promotion=" + nom_promotion + ", duree_promotion=" + duree_promotion + ", prix_avant=" + prix_avant + ", pourcentage=" + pourcentage + ", prix_apres=" + prix_apres + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.id_promotion;
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
        final Promotion other = (Promotion) obj;
        if (this.id_promotion != other.id_promotion) {
            return false;
        }
        if (this.duree_promotion != other.duree_promotion) {
            return false;
        }
        if (this.pourcentage != other.pourcentage) {
            return false;
        }
        if (!Objects.equals(this.nom_promotion, other.nom_promotion)) {
            return false;
        }
        return true;
    }

    
    

   
    
    
    
}
