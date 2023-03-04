/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 *
 * @author faten
 */
public class FormulaireR {
    private int id;
    private String nom;
    private int tlp;
    private String mail;
    private int nbr;
    private String type;
    private String categ;
    private String depart;
    private String destination;
    private String opt;

    public FormulaireR() {
    }

    public FormulaireR(int id, String nom, int tlp, String mail, int nbr, String type, String categ, String depart, String destination, String opt) {
        this.id = id;
        this.nom = nom;
        this.tlp = tlp;
        this.mail = mail;
        this.nbr = nbr;
        this.type = type;
        this.categ = categ;
        this.depart = depart;
        this.destination = destination;
        this.opt = opt;
    }

    public FormulaireR(String nom, int tlp, String mail, int nbr, String type, String categ, String depart, String destination, String opt) {
        this.nom = nom;
        this.tlp = tlp;
        this.mail = mail;
        this.nbr = nbr;
        this.type = type;
        this.categ = categ;
        this.depart = depart;
        this.destination = destination;
        this.opt = opt;
    }

    public int getId() {
        return id;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getTlp() {
        return tlp;
    }

    public void setTlp(int tlp) {
        this.tlp = tlp;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getNbr() {
        return nbr;
    }

    public void setNbr(int nbr) {
        this.nbr = nbr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCateg() {
        return categ;
    }

    public void setCateg(String categ) {
        this.categ = categ;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.id;
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
        final FormulaireR other = (FormulaireR) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FormulaireR{" + "nom=" + nom + ", tlp=" + tlp + ", mail=" + mail + ", nbr=" + nbr + ", type=" + type + ", categ=" + categ + ", depart=" + depart + ", destination=" + destination + ", opt=" + opt + '}';
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}


