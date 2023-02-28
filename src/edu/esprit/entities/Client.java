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
public class Client extends User {

    public Client(int id, String cin, String nom, String prenom, String role, String email, String pwd) {
        super(id, cin, nom, prenom, role, email, pwd);
    }

    public Client(String cin, String nom, String prenom, String role, String email, String pwd) {
        super(cin, nom, prenom, role, email, pwd);
    }

    public Client() {
    }

    
   
}
