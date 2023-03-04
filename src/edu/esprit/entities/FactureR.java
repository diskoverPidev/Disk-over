/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author faten
 */
public class FactureR {

    private int id_facture;
    private Date date_Facture;
    private String statut;
    private String notes;
    private FormulaireR formulaire;

    public FactureR() {
    }

    public FactureR(int id_facture, Date date_Facture, String statut, String notes) {
        this.id_facture = id_facture;
        this.date_Facture = date_Facture;
        this.statut = statut;
        this.notes = notes;
    }

    public FactureR(int id_facture, Date date_Facture, String statut, String notes, FormulaireR formulaire) {
        this.id_facture = id_facture;
        this.date_Facture = date_Facture;
        this.statut = statut;
        this.notes = notes;
        this.formulaire = formulaire;
    }

    public FactureR(Date date_Facture, String statut, String notes) {
        this.date_Facture = date_Facture;
        this.statut = statut;
        this.notes = notes;
    }

    public FactureR(Date date_Facture, String statut, String notes, FormulaireR formulaire) {
        this.date_Facture = date_Facture;
        this.statut = statut;
        this.notes = notes;
        this.formulaire = formulaire;
    }

    public FactureR(LocalDate date_facture, String statut, String notes, FormulaireR fr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public FactureR(LocalDate date_facture, String statut, String notes, int selectedId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId_facture() {
        return id_facture;
    }

    public void setId_facture(int id_facture) {
        this.id_facture = id_facture;
    }

    public Date getDate_Facture() {
        return date_Facture;
    }

    public void setDate_Facture(Date date_Facture) {
        this.date_Facture = date_Facture;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public FormulaireR getFormulaire() {
        return formulaire;
    }

    public void setFormulaire(FormulaireR formulaire) {
        this.formulaire = formulaire;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.id_facture;
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
        final FactureR other = (FactureR) obj;
        if (this.id_facture != other.id_facture) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FactureR{" + "date_Facture=" + date_Facture + ", statut=" + statut + ", notes=" + notes + ", formulaire=" + formulaire + '}';
    }
    
    
    
    
    
    

}



