/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

import java.security.MessageDigest;
import java.util.Objects;

/**
 *
 * @author lenovo
 */
public class Admin {
    private int id_Admin ; 
    private String cin_Admin; 
    private String nom_Admin;
    private String prenom_Admin;
    private String email_Admin;
    private String pwd_Admin;

    public Admin(String cin, String nom, String prenom, String email, String pwd) {
        this.cin_Admin = cin;
        this.nom_Admin = nom;
        this.prenom_Admin = prenom;
        this.email_Admin = email;
        this.pwd_Admin = pwd;
    }
    public Admin()
    {
        
    }

   

    public String getCin() {
        return cin_Admin;
    }

    public String getNom() {
        return nom_Admin;
    }

    public String getPrenom() {
        return prenom_Admin;
    }

    public String getEmail() {
        return email_Admin;
    }

    public int getId_Admin() {
        return id_Admin;
    }

    public String getPwd() {
        return pwd_Admin;
    }

    @Override
    public String toString() {
        return "admin{" + "cin=" + cin_Admin + ", nom=" + nom_Admin + ", prenom=" + prenom_Admin + ", email=" + email_Admin + ", pwd=" + pwd_Admin + '}';
    }

    public void setCin(String cin) {
        this.cin_Admin = cin;
    }

    public void setNom(String nom) {
        this.nom_Admin = nom;
    }

   

   
    

    public void setPrenom(String prenom) {
        this.prenom_Admin = prenom;
    }

    public void setEmail(String email) {
        this.email_Admin = email;
    }

    public void setPwd(String pwd) {
        this.pwd_Admin = pwd;
         
    }
   
            

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.cin_Admin);
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
        if (!Objects.equals(this.cin_Admin, other.getCin())) {
            return false;
        }
        return true;
    }

}
