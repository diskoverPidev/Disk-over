/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

import java.sql.Date;
import java.util.Objects;
import java.util.logging.Logger;

/**
 *
 * @author abidi
 */
public class Reponse {

    private String resultat;

    private int num;

    private Date dateR;
    private int reclamationId;

    private int cin;

    public Reponse(int cin, int idchauffeur, int num, String resultat, Date dateR) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Reponse(int cin, String resultat, int num, Date dateR) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setDateR(Date dateR) {
        this.dateR = dateR;
    }

    public Reponse(int cin, String resultat, int num, Date dateR, int reclamationId) {
        this.cin = cin;
        this.resultat = resultat;
        this.num = num;
        this.dateR = dateR;
        this.reclamationId = reclamationId;
    }
    private static final Logger LOG = Logger.getLogger(Reponse.class.getName());

    public int getCin() {
        return cin;
    }

    public String getResultat() {
        return resultat;
    }

    public int getNum() {
        return num;
    }

    public Date getDateR() {
        return dateR;
    }

    public int getReclamationId() {
        return reclamationId;
    }

    public static Logger getLOG() {
        return LOG;
    }

    public void setReclamationId(int reclamationId) {
        this.reclamationId = reclamationId;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.cin;
        hash = 37 * hash + Objects.hashCode(this.resultat);
        hash = 37 * hash + this.num;
        hash = 37 * hash + Objects.hashCode(this.dateR);
        hash = 37 * hash + Objects.hashCode(this.reclamationId);
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
        if (this.cin != other.cin) {
            return false;
        }
        if (this.num != other.num) {
            return false;
        }
        if (!Objects.equals(this.resultat, other.resultat)) {
            return false;
        }
        if (!Objects.equals(this.dateR, other.dateR)) {
            return false;
        }
        if (!Objects.equals(this.reclamationId, other.reclamationId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reponse{" + "cin=" + cin + ", resultat=" + resultat + ", num=" + num + ", dateR=" + dateR + ", reclamationId=" + reclamationId + '}';
    }

}
