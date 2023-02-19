/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

import java.util.Objects;

/**
 *
 * @author lenovo
 */
public class Chauffeur {
    private int id_Chauffeur ; 
    private String cin_Chauffeur;
    private String nom_Chauffeur;
    private String prenom_Chauffeur;
    private String email_Chauffeur;
    private String pwd_Chauffeur;

    public Chauffeur(String cin, String nom, String prenom, String email, String pwd) {
        
        this.cin_Chauffeur = cin;
        this.nom_Chauffeur = nom;
        this.prenom_Chauffeur = prenom;
        this.email_Chauffeur = email;
        this.pwd_Chauffeur = pwd;
    }
    public Chauffeur()
    {
        
    }

    public int getId_chauffeur() {
        return id_Chauffeur;
    }

    public String getCin() {
        return cin_Chauffeur;
    }

    public String getNom() {
        return nom_Chauffeur;
    }

    public String getPrenom() {
        return prenom_Chauffeur;
    }

    public String getEmail() {
        return email_Chauffeur;
    }

    public String getPwd() {
        return pwd_Chauffeur;
    }

    @Override
    public String toString() {
        return "chauffeur{" + "cin=" + cin_Chauffeur + ", nom=" + nom_Chauffeur + ", prenom=" + prenom_Chauffeur + ", email=" + email_Chauffeur + ", pwd=" + pwd_Chauffeur + '}';
    }

   

    public void setCin(String cin) {
        this.cin_Chauffeur = cin;
    }

    public void setNom(String nom) {
        this.nom_Chauffeur = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom_Chauffeur = prenom;
    }

    public void setEmail(String email) {
        this.email_Chauffeur = email;
    }

    public void setPwd(String pwd) {
        this.pwd_Chauffeur = pwd;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.cin_Chauffeur);
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
        final Chauffeur other = (Chauffeur) obj;
        if (!Objects.equals(this.cin_Chauffeur, other.getCin())) {
            return false;
        }
        return true;
    }

   

   
    
    
    
    
    
}
