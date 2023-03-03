/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.discover.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ramzi
 */
public class AjouterReservationController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField nbrp;
    @FXML
    private Button reserver;
   
    @FXML
    private Button retourr;
    @FXML
    private Label mylabel;
    @FXML
    private ChoiceBox<String> mychoixbox;
    private final String[] event = {"online", "cinematic", "literature", "theatre", "salle_exposition_des_tableaux", "salle_exposition_des_sculpture"};
    @FXML
    private void listeres(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Evenement.fxml"));
        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) retourr.getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
    }
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       mychoixbox.getItems().addAll(event);
        mychoixbox.setOnAction(this::getEvent); 
        
    } 
    public void getEvent(ActionEvent event) {
        String myEvent = mychoixbox.getValue();
        mylabel.setText(myEvent);

    }
    
    
}
