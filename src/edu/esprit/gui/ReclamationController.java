/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.Reclamation;
import edu.esprit.services.ServiceReclamation;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author abidi
 */
public class ReclamationController implements Initializable {

    @FXML
    private Button btnValider;
    @FXML
    private TextField objetR;
    @FXML
    private TextField messageR;
    @FXML
    private TextField typeR;
    @FXML
    private DatePicker dateR;
    @FXML
    private Button menuRe;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ajouterReclamation(ActionEvent event) {
//        InputStream imgStream = getClass().getResourceAsStream("C:/Users/abidi/OneDrive/Images");

        String type = typeR.getText();
        String objet = objetR.getText();
        String message = messageR.getText();
        Date date = Date.valueOf(dateR.getValue());

        Reclamation t = new Reclamation(type, objet, message, date);
        ServiceReclamation sr = new ServiceReclamation();
        sr.ajouter(t);

        Alert a = new Alert(Alert.AlertType.ERROR, "reclamation ajouter", ButtonType.OK);
        a.showAndWait();

    }

    @FXML
    private void menuRe(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Consulte.fxml"));
        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) menuRe.getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
    }
}
