/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;




/**
 *
 * @author abidi
 */
public class Reclamation {

    private int id;
    private String type;
    private String objet;
    private String message;
    private Date date;

    public Reclamation(int id, String type, String objet, String message) {
        this.id = id;
        this.type = type;
        this.objet = objet;
        this.message = message;
    }

    public Reclamation(String type, String objet, String message, Date date) {
        this.type = type;
        this.objet = objet;
        this.message = message;
        this.date = date;
    }

    public Reclamation(String string, String string0, String string1, Date date, String string2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getObjet() {
        return objet;
    }

    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return date;
    }

    public void setId(int id) {
        if (id > 0 && id < 10) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("il faut saisir un identifiant entre 10 et 20");
        }
    }

   

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.id;
        hash = 11 * hash + Objects.hashCode(this.type);
        hash = 11 * hash + Objects.hashCode(this.objet);
        hash = 11 * hash + Objects.hashCode(this.message);
        hash = 11 * hash + Objects.hashCode(this.date);
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
        final Reclamation other = (Reclamation) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.objet, other.objet)) {
            return false;
        }
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        return Objects.equals(this.date, other.date);
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", type=" + type + ", objet=" + objet + ", message=" + message + ", date=" + date + '}';
    }

    public void setMessage(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
