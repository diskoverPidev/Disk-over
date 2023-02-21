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
public class Offre {
    private int id_offre;
    private String description_offre;
    private String duree_offre;

    public Offre() {
    }

    public Offre(String description_offre, String duree_offre) {
        this.description_offre = description_offre;
        this.duree_offre = duree_offre;
    }

    
    
    
    
    public Offre(int id_offre, String description_offre, String duree_offre) {
        this.id_offre = id_offre;
        this.description_offre = description_offre;
        this.duree_offre = duree_offre;
    }

    public void setid_offre(int id_offre) {
        this.id_offre = id_offre;
    }

    public int getid_offre() {
        return id_offre;
    }


    public String getdescription_offre() {
        return description_offre;
    }

    public void setdescription_offre(String description_offre) {
        this.description_offre = description_offre;
    }

    public String getduree_offre() {
        return duree_offre;
    }

    public void setduree_offre(String duree_offre) {
        this.duree_offre = duree_offre;
    }
    
    
    

    @Override
    public String toString() {
        return "offre{" + "description_offre=" + description_offre + ", duree_offre=" + duree_offre + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.id_offre;
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
        final Offre other = (Offre) obj;
        if (this.id_offre != other.id_offre) {
            return false;
        }
        if (!Objects.equals(this.description_offre, other.description_offre)) {
            return false;
        }
        if (!Objects.equals(this.duree_offre, other.duree_offre)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
}
