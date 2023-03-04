/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

import java.util.Date;



/**
 *
 * @author asus
 */
public class Vehicule {
    private int id;
    private int disponibilite;
    private int num_entretien;
    private Date date_entretien;
    private String res_entretien;

    public Vehicule() {
    }
    

    public Vehicule(int disponibilite, int num_entretien, Date date_entretien, String res_entretien) {
        this.disponibilite = disponibilite;
        this.num_entretien = num_entretien;
        this.date_entretien = date_entretien;
        this.res_entretien = res_entretien;
    }

    public Vehicule(int disponibilite) {
        this.disponibilite = disponibilite;
    }

    public Vehicule(int disponibilite, int num_entretien) {
        this.disponibilite = disponibilite;
        this.num_entretien = num_entretien;
    }

   

  

    public Vehicule(int id, int disponibilite, int num_entretien, Date date_entretien, String res_entretien) {
        this.id = id;
        this.disponibilite = disponibilite;
        this.num_entretien = num_entretien;
        this.date_entretien = date_entretien;
        this.res_entretien = res_entretien;
    }

    public int getId() {
        return id;
    }

    public int getDisponibilite() {
        return disponibilite;
    }

    public int getNum_entretien() {
        return num_entretien;
    }

    public Date getDate_entretien() {
        return date_entretien;
    }

    public String getRes_entretien() {
        return res_entretien;
    }

    public void setDisponibilite(int disponibilite) {
         if(disponibilite == 0 || disponibilite == 1) {
            this.disponibilite = disponibilite;
       } else {
           throw new IllegalArgumentException("La disponibilité de la véhicule doit être 0 ou 1");
       }
       
    }

    public void setNum_entretien(int num_entretien) {
        if(num_entretien > 0 ) {
        this.num_entretien = num_entretien;
       } else {
           throw new IllegalArgumentException("Le num d'entretien doit être positive");
       }
       
    }

    public void setDate_entretien(Date date_entretien) {
         if (date_entretien != null) {
        Date maxDate = new Date(1999, 11, 31);
        if (date_entretien.after(maxDate)) {
              this.date_entretien = date_entretien;
        } else {
            throw new IllegalArgumentException("La date d'entretien doit être postérieure au 31 décembre 1999");
        }
    } else {
        throw new IllegalArgumentException("La date d'entretien ne doit pas être nulle");
    }
      
    }

    public void setRes_entretien(String res_entretien) {
         if(res_entretien.length() > 0 && res_entretien.length() < 255) {
               this.res_entretien = res_entretien;
       } else {
           throw new IllegalArgumentException("Le resultat d'entretien doit être compris entre 1 et 255 caractères");
       }
       
     
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id;
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
        final Vehicule other = (Vehicule) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "vehicule{" + "disponibilite=" + disponibilite + ", num_entretien=" + num_entretien + ", date_entretien=" + date_entretien + ", res_entretien=" + res_entretien + '}';
    }
    
    
}
