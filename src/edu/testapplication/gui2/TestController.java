/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.testapplication.gui2;

import edu.esprit.entities.Client;
import edu.esprit.services.Serviceclient;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class TestController implements Initializable {

    @FXML
    private Label cin;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label role;
    @FXML
    private Label email;
    @FXML
    private Label pwd;
    @FXML
    private VBox vbox;
    @FXML
    private ListView<String> listRes;
    Client fr = new Client();
    @FXML
    private AnchorPane ev;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
//        Client fr = new Client();
//        System.out.println(fr.toString());
//        List<Client> Res = new ArrayList<>();
//        Serviceclient sf = new Serviceclient();
//         System.out.println(sf.getall());
         
List<Client> c = new ArrayList();
        Serviceclient sc = new Serviceclient();
        listRes.getItems();
        c = sc.getall();
        System.out.println(c);
        //FormulaireR fr = new FormulaireR();
        
        //Res = sf.getOnebyId(fr.getId());
        
        int x = 0, y = 0;
        // listevent.getItems().addAll(even);
       // c.add(this.fr);
        for (Client e : c) {

            AnchorPane an = new AnchorPane();
            an.setLayoutX(x);
            an.setLayoutY(y);

            Label cin = new Label(e.getCin());
            cin.setLayoutX(x + 16);
            cin.setLayoutY(y + 24);
         
            Label nom = new Label(e.getNom());
            nom.setLayoutX(x + 114);
            nom.setLayoutY(y + 24);
            Label prenom = new Label(e.getPrenom());
            prenom.setLayoutX(x + 209);
            prenom.setLayoutY(y + 24);
            Label role = new Label(e.getRole());
            role.setLayoutX(x + 306);
            role.setLayoutY(y + 24);
            Label email = new Label(e.getEmail());
            email.setLayoutX(x + 420);
            email.setLayoutY(y + 24);
            Label pwd = new Label(e.getPwd());
            pwd.setLayoutX(x + 525);
            pwd.setLayoutY(y + 24);


           // Button btnafficher = new Button("Affichage");

            an.getChildren().addAll(cin, nom, prenom, role, email, pwd);
            ev.getChildren().addAll(an);

            vbox.getChildren().add(an);
        // TODO
    }    
    
    }
}
