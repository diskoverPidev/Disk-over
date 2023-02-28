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
public class Admin extends User {

    public Admin(int id, String cin, String nom, String prenom, String role, String email, String pwd) {
        super(id, cin, nom, prenom, role, email, pwd);
    }

    public Admin(String cin, String nom, String prenom, String role, String email, String pwd) {
        super(cin, nom, prenom, role, email, pwd);
    }

    public Admin() {
    }

    
   
    

   
   
}
