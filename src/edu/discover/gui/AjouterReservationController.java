/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.discover.gui;

import edu.discover.entities.Colaborationevent;
import edu.discover.entities.Reservationevent;
import edu.discover.services.Crudcolaboration;
import edu.discover.services.Crudreservation;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    //private final String[] event = {"RAMZI", "jouini", "ahlem", "bochra", "kamilia", "housem"};

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
        
        Crudcolaboration cr=new Crudcolaboration();
        List<String> even =cr.getNom();
        ObservableList<String> observableColaborationevents = FXCollections.observableList(even);
        mychoixbox.setItems(observableColaborationevents);
        mychoixbox.getSelectionModel().select(0);
        
//        mychoixbox.getItems().addAll(event);
//        mychoixbox.setOnAction(this::getEvent);

    }

    public void getEvent(ActionEvent event) {
     
//        String myEvent = mychoixbox.getValue();
//        mylabel.setText(myEvent);

    }

    @FXML
    private void add(ActionEvent event) {
        
        String nomclient = nom.getText();
        int nbrclient = Integer.parseInt(nbrp.getText());//Integer.parseInt pour forcer la conversion en int
        //String Colaborationevent = mychoixbox.getValue().getNomevent();
        String Colaborationevent = mychoixbox.getValue();

        //mena
        if(Colaborationevent.isEmpty()){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("Nom event doit etre remplir !!");
        alert.showAndWait();
        }else{
                Reservationevent c = new Reservationevent();
                c.setNomclient(nomclient);
                c.setNbrclient(nbrclient);
                c.setNomevnet(Colaborationevent);
                Crudreservation cc = new Crudreservation();
                cc.ajout(c);

        }
        
        
        
        
        
//        Reservationevent c = new Reservationevent(nomclient, nbrclient, nomev);
//        Crudreservation cc = new Crudreservation();
//        cc.ajout(c);
//
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Event Registration");
//        alert.setHeaderText("Event Registration");
//        alert.setContentText("Event Added!");
//        alert.showAndWait();

    }

}
