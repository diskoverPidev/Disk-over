/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.Categorie;
import edu.esprit.entities.Vehicule;
import edu.esprit.services.ServiceCategorie;
import edu.esprit.services.ServiceVehicule;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AjoutController implements Initializable {

    @FXML
    private TextField marqueid;
    @FXML
    private TextField resid;
    @FXML
    private TextField numid;
        private ObjectProperty<LocalDate> dateSelectionnee;

    @FXML
    private ChoiceBox<Integer> dispoid;
     ObservableList<Integer> choicesD = FXCollections.observableArrayList(
            1,
            0
    );
    @FXML
    private TextField matriculeid;
    @FXML
    private ChoiceBox<String> categorieid;
     ObservableList<String> choices = FXCollections.observableArrayList(
            "moyenne",
            "haute"
    );
    @FXML
    private DatePicker dateid;
    @FXML
    private Button ajoutid;
    @FXML
    private Button suppid;
    @FXML
    private Button modifid;
    @FXML
    private Button prevbutton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                categorieid.setItems(choices);
        dispoid.setItems(choicesD);
        dateSelectionnee = dateid.valueProperty();
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) {
               int num = Integer.parseInt(numid.getText());
        LocalDate localDate = dateSelectionnee.get();
        java.sql.Date date = java.sql.Date.valueOf(localDate);
        String categorieSelectionnee = categorieid.getValue();
        Vehicule v = new Vehicule(dispoid.getValue(), num, null, resid.getText());
        Categorie c1 = new Categorie(categorieSelectionnee, matriculeid.getText(), marqueid.getText(), v);
        ServiceCategorie sc = new ServiceCategorie();
        ServiceVehicule sv = new ServiceVehicule();
        sv.ajouterV(v, date);
        sc.ajouter(c1);
        Alert a = new Alert(Alert.AlertType.ERROR, "vehicule ajouté", ButtonType.OK);
        a.showAndWait();
    }

    @FXML
    private void supprimer(ActionEvent event) {
              int num = Integer.parseInt(numid.getText());
        LocalDate localDate = dateSelectionnee.get();
        java.sql.Date date = java.sql.Date.valueOf(localDate);
        Vehicule v = new Vehicule(dispoid.getValue(), num, null, resid.getText());
        Categorie c1 = new Categorie(categorieid.getTypeSelector(), matriculeid.getText(), marqueid.getText(), v);
        ServiceCategorie sc = new ServiceCategorie();
        ServiceVehicule sv = new ServiceVehicule();
        sc.supprimer(24);
        sv.supprimer(5);
        Alert a = new Alert(Alert.AlertType.ERROR, "vehicule supprimé", ButtonType.OK);
        a.showAndWait();
    }

    @FXML
    private void modifier(ActionEvent event) {
                int num = Integer.parseInt(numid.getText());
        LocalDate localDate = dateSelectionnee.get();
        java.sql.Date date = java.sql.Date.valueOf(localDate);
        Vehicule v = new Vehicule(dispoid.getValue(), num, null, resid.getText());
        Categorie c1 = new Categorie(categorieid.getTypeSelector(), matriculeid.getText(), marqueid.getText(), v);
        ServiceCategorie sc = new ServiceCategorie();
        ServiceVehicule sv = new ServiceVehicule();
        sc.modifier(c1);
        sv.modifier(v);
        Alert a = new Alert(Alert.AlertType.ERROR, "vehicule modifié", ButtonType.OK);
        a.showAndWait();
    }

    @FXML
    private void prev(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("Vehicule.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
    
}
