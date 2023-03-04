/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.services.ServiceMission;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class InterfaceChauffeurController implements Initializable {

    @FXML
    private TextField id;
    @FXML
    private Button confirbutton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void confimer(ActionEvent event) throws IOException {
       String matricule = id.getText();
      
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Chauffeur.fxml"));
    Parent root = loader.load();
    ChauffeurController chauffeurController = loader.getController();
    chauffeurController.setMatricule(matricule);
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
    
}
