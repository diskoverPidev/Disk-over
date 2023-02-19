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
public class Client {
    private int id_Client ; 
    private String cin_Client;
    private String nom_Client;
    private String prenom_Client;
    private String email_Client;
    private String pwd_Client;

    public Client(String cin, String nom, String prenom, String email, String pwd) {
       
        this.cin_Client = cin;
        this.nom_Client = nom;
        this.prenom_Client = prenom;
        this.email_Client = email;
        this.pwd_Client =pwd ; 
    }

    public Client() {
        
    }

    public int getId_Client() {
        return id_Client;
    }

    

    

    public String getCin() {
        return cin_Client;
    }

    public String getNom() {
        return nom_Client;
    }

    public String getPrenom() {
        return prenom_Client;
    }

    public String getEmail() {
        return email_Client;
    }

    public String getPwd() {
        return pwd_Client;
    }

    @Override
    public String toString() {
        return "client{" + "cin=" + cin_Client + ", nom=" + nom_Client + ", prenom=" + prenom_Client + ", email=" + email_Client + ", pwd=" + pwd_Client + '}';
    }

    public void setCin(String cin) {
        this.cin_Client = cin;
    }

    public void setId_Client(int id_Client) {
        this.id_Client = id_Client;
    }

   
    

    public void setNom(String nom) {
        this.nom_Client = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom_Client = prenom;
    }

    public void setEmail(String email) {
        this.email_Client = email;
    }

    public void setPwd(String pwd) {
        this.pwd_Client = pwd;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.cin_Client);
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
        if (!Objects.equals(this.cin_Client, other.getCin())) {
            return false;
        }
        return true;
    }

    
}
