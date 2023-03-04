/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

import java.sql.Timestamp;

/**
 *
 * @author asus
 */
public class Mission {
    private int id_mission;
    private String Matricule;
    private String description;
    private Timestamp heure_debut;
    private Timestamp heure_fin;

    public Mission(int id_mission, String Matricule, String description, Timestamp heure_debut, Timestamp heure_fin) {
        this.id_mission = id_mission;
        this.Matricule = Matricule;
        this.description = description;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
    }

    public Mission(String Matricule, String description, Timestamp heure_debut, Timestamp heure_fin) {
        this.Matricule = Matricule;
        this.description = description;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
    }

    public int getId_mission() {
        return id_mission;
    }


    public String getDescription() {
        return description;
    }

    public Timestamp getHeure_debut() {
        return heure_debut;
    }

    public Timestamp getHeure_fin() {
        return heure_fin;
    }

    public void setId_mission(int id_mission) {
        this.id_mission = id_mission;
    }

    

    public void setDescription(String description) {
        if(description.length() > 0 && description.length() < 255) {
              this.description = description;
       } else {
           throw new IllegalArgumentException("La description de la mission doit être compris entre 1 et 255 caractères");
       }
        
    }

    public void setHeure_debut(Timestamp heure_debut) {
          if (heure_debut != null) {
        // Récupérer la date et l'heure courante
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        if (heure_debut.before(currentTimestamp)) {
            this.heure_debut = heure_debut;
        } else {
            throw new IllegalArgumentException("L'heure_debut de la mission doit être antérieur au timestamp courant");
        }
    } else {
        throw new IllegalArgumentException("L'heure_debut de la mission ne doit pas être nul");
    }
        
    }

    public void setHeure_fin(Timestamp heure_fin) {
          if (heure_fin != null) {
        // Récupérer la date et l'heure courante
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        if (heure_fin.before(currentTimestamp)) {
                  this.heure_fin = heure_fin;

        } else {
            throw new IllegalArgumentException("Le L'heure_fin de la mission doit être antérieur au timestamp courant");
        }
    } else {
        throw new IllegalArgumentException("L'heure_fin de la mission ne doit pas être nul");
    }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id_mission;
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
        final Mission other = (Mission) obj;
        if (this.id_mission != other.id_mission) {
            return false;
        }
        return true;
    }

    public String getMatricule() {
        return Matricule;
    }

    public void setMatricule(String Matricule) {
        this.Matricule = Matricule;
    }

    @Override
    public String toString() {
        return "Mission{" + "Matricule=" + Matricule + ", description=" + description + ", heure_debut=" + heure_debut + ", heure_fin=" + heure_fin + '}';
    }

    
}
