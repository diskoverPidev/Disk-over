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
public class User {
    
     protected int id ; 
    protected String cin; 
    protected String nom;
    protected String prenom;
    protected String role;

    protected String email;
    protected String pwd;

    public int getId() {
        return id;
    }

    public String getCin() {
        return cin;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User(int id, String cin, String nom, String prenom, String role, String email, String pwd) {
        this.id = id;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.email = email;
        this.pwd = pwd;
    }

    public User(String cin, String nom, String prenom, String role, String email, String pwd) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.email = email;
        this.pwd = pwd;
    }

    public User() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.cin);
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
        final User other = (User) obj;
        if (!Objects.equals(this.cin, other.cin)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "{" + "id=" + id + ", cin=" + cin + ", nom=" + nom + ", prenom=" + prenom + ", role=" + role + ", email=" + email + ", pwd=" + pwd + '}';
    }

    

   
    
    
    
    
}
