/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.discover.gui;

import edu.discover.entities.Reservationevent;
import edu.discover.services.Crudreservation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
    private final String[] event = {"RAMZI", "jouini", "ahlem", "bochra", "kamilia", "housem"};

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

    @FXML
    private void add(ActionEvent event) {
        String nomclient = nom.getText();
        int nbrclient = Integer.parseInt(nbrp.getText());//Integer.parseInt pour forcer la conversion en int
        String nomev = mychoixbox.getValue();

        Reservationevent c = new Reservationevent(nomclient, nbrclient, nomev);
        Crudreservation cc = new Crudreservation();
        cc.ajout(c);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Event Registration");
        alert.setHeaderText("Event Registration");
        alert.setContentText("Event Added!");
        alert.showAndWait();
    }

}
