/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.Mission;
import edu.esprit.services.ServiceMission;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AjoutMissionController implements Initializable {

    @FXML
    private TextField idchauff;
    @FXML
    private TextField desc;
    @FXML
    private TextField heuredeb;
    @FXML
    private TextField heurefin;
    @FXML
    private Button ajout;
    @FXML
    private Button modif;
    @FXML
    private Button supprim;
    @FXML
    private Button btnprev;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp.getTime());
        cal.add(Calendar.MINUTE, 15);
        Timestamp timestamp2 = new Timestamp(cal.getTime().getTime());
        String idchauffeur = idchauff.getText();
      
        Mission m = new Mission(idchauffeur, desc.getText(), timestamp, timestamp2);
        ServiceMission sm = new ServiceMission();
        sm.ajouter(m);
         Alert a = new Alert(Alert.AlertType.ERROR, "mission ajouté", ButtonType.OK);
        a.showAndWait();

    }

    @FXML
    private void modifier(ActionEvent event) {
           Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp.getTime());
        cal.add(Calendar.MINUTE, 15);
        Timestamp timestamp2 = new Timestamp(cal.getTime().getTime());
        String idchauffeur = idchauff.getText();
       
        Mission m = new Mission(idchauffeur, desc.getText(), timestamp, timestamp2);
        ServiceMission sm = new ServiceMission();
        sm.modifier(m);
         Alert a = new Alert(Alert.AlertType.ERROR, "mission modifié", ButtonType.OK);
        a.showAndWait();
    }

    @FXML
    private void supprimer(ActionEvent event) {
                     Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp.getTime());
        cal.add(Calendar.MINUTE, 15);
        Timestamp timestamp2 = new Timestamp(cal.getTime().getTime());
        String idchauffeur = idchauff.getText();
        Mission m = new Mission(idchauffeur, desc.getText(), timestamp, timestamp2);
        ServiceMission sm = new ServiceMission();
        sm.supprimer(2);
         Alert a = new Alert(Alert.AlertType.ERROR, "mission supprimé", ButtonType.OK);
        a.showAndWait();
    }

    @FXML
    private void prev(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionMission.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
    
}
