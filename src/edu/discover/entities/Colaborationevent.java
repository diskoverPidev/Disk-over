/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.discover.entities;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author ramzi
 */
public class Colaborationevent {
    private int idevent;
    private String nomevent;
    private Date dateevent;
    private String adresseevent;
    private int nbrplacevehicule;
    private int prixvehiculeevent;
    public int getIdevent;

    
    
    public Colaborationevent() {
    }
    
    
//ajout
    public Colaborationevent(String nomevent, Date dateevent, String adresseevent, int nbrplacevehicule, int prixvehiculeevent) {
        this.nomevent = nomevent;
        this.dateevent = dateevent;
        this.adresseevent = adresseevent;
        this.nbrplacevehicule = nbrplacevehicule;
        this.prixvehiculeevent = prixvehiculeevent;
    }
    
    

    public Colaborationevent(int idevent, String nomevent, Date dateevent, String adresseevent, int nbrplacevehicule, int prixvehiculeevent) {
        this.idevent = idevent;
        this.nomevent = nomevent;
        this.dateevent = dateevent;
        this.adresseevent = adresseevent;
        this.nbrplacevehicule = nbrplacevehicule;
        this.prixvehiculeevent = prixvehiculeevent;
    }

    public Colaborationevent(String nomevent) {
        this.nomevent = nomevent;
    }

    
    
    
    
    public int getIdevent() {
        return idevent;
    }


    public String getNomevent() {
        return nomevent;
    }

    public void setNomevent(String nomevent) {
        this.nomevent = nomevent;
    }

    public Date getDateevent() {
        return dateevent;
    }

    public void setDateevent(Date dateevent) {
        this.dateevent = dateevent;
    }

    public String getAdresseevent() {
        return adresseevent;
    }

    public void setAdresseevent(String adresseevent) {
        this.adresseevent = adresseevent;
    }

    public int getNbrplacevehicule() {
        return nbrplacevehicule;
    }

    public void setNbrplacevehicule(int nbrplacevehicule) {
        this.nbrplacevehicule = nbrplacevehicule;
    }

    public int getPrixvehiculeevent() {
        return prixvehiculeevent;
    }

    public void setPrixvehiculeevent(int prixvehiculeevent) {
        this.prixvehiculeevent = prixvehiculeevent;
    }

    
    
    @Override
    public String toString() {
        return "Colaborationevent{" + "nomevent=" + nomevent + ", dateevent=" + dateevent + ", adresseevent=" + adresseevent + ", nbrplacevehicule=" + nbrplacevehicule + ", prixvehiculeevent=" + prixvehiculeevent + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.idevent;
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
        final Colaborationevent other = (Colaborationevent) obj;
        if (this.idevent != other.idevent) {
            return false;
        }
        if (!Objects.equals(this.nomevent, other.nomevent)) {
            return false;
        }
        return true;
    }

    public boolean getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
