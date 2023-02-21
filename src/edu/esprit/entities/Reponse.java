/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

import java.sql.Date;

/**
 *
 * @author abidi
 */
public class Reponse {

    private final int idclient;
    private final int idchauffeur;
    private final int num;
    private final String resultat;
    private final Date dateR;
    private Reclamation idJ;

    public Reponse(int idclient, int idchauffeur, int num, String resultat, Date dateR) {
        this.idclient = idclient;
        this.idchauffeur = idchauffeur;
        this.num = num;
        this.resultat = resultat;
        this.dateR = dateR;
    }

    public void setId(Reclamation id) {
        this.idJ = id;
    }

    public Reclamation getId() {
        return idJ;
    }

    public Reponse(int idclient, int idchauffeur, int num, String resultat, Date dateR, Reclamation id) {
        this.idclient = idclient;
        this.idchauffeur = idchauffeur;
        this.num = num;
        this.resultat = resultat;
        this.dateR = dateR;
        this.idJ = id;
    }

    public Reponse(int aInt, int aInt0, String string, Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getIdclient() {
        return idclient;
    }

    public int getIdchauffeur() {
        return idchauffeur;
    }

    public int getNum() {
        return num;
    }

    public String getResultat() {
        return resultat;
    }

    public Date getDateR() {
        return dateR;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.idclient;
        hash = 89 * hash + this.idchauffeur;
        hash = 89 * hash + this.num;
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
        final Reponse other = (Reponse) obj;
        if (this.idclient != other.idclient) {
            return false;
        }
        if (this.idchauffeur != other.idchauffeur) {
            return false;
        }
        return this.num == other.num;
    }

    @Override
    public String toString() {
        return "Reponse{" + "idclient=" + idclient + ", idchauffeur=" + idchauffeur + ", num=" + num + ", resultat=" + resultat + ", dateR=" + dateR + '}';
    }

    public Object getIdR() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public class getResultat {

     
        public getResultat() {
        }
    }

}
