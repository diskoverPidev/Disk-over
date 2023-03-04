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
import java.util.ArrayList;

import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.property.ObjectProperty;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class VehiculeController implements Initializable {

    @FXML
    private Button ajoutid;

    @FXML
    private Button rechercheid;
    @FXML
    private TextField inputrechercheid;

    @FXML
    private Button afficherid;
    @FXML
    private ListView<Categorie> liste;
    @FXML
    private Button mission;
    @FXML
    private Label nb;
    @FXML
    private AnchorPane an;
    @FXML
    private VBox vbox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Affichertable();

        ServiceCategorie sc = new ServiceCategorie();
        int number = sc.nb_vehicule();
        nb.setText("Nombre de véhicules : " + number);

        // TODO
    }


    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Ajout.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void rechercher(ActionEvent event) throws IOException {
        String type = inputrechercheid.getText();
        ServiceCategorie sc = new ServiceCategorie();
          FXMLLoader loader = new FXMLLoader(getClass().getResource("rechercheVehicule.fxml"));
        Parent root = loader.load();
        RechercheVehiculeController vehiculeController = loader.getController();
        vehiculeController.setType(type);
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        }

    

    @FXML
    public void afficherCategorie() {

        List<Categorie> vehicule = new ArrayList();
        ServiceCategorie sc = new ServiceCategorie();
        liste.getItems();
        vehicule = sc.getALL();
        System.out.println(vehicule);
        int x = 0, y = 0;

        // Set VBox alignment to vertical

        for (Categorie c : vehicule) {

            AnchorPane an = new AnchorPane();
            an.setLayoutX(x);
            an.setLayoutY(y);

            Label name = new Label(c.getType());
            name.setLayoutX(x + 20);
            name.setLayoutY(y + 22);
            String d = String.valueOf(c.getMatricule());
            Label date = new Label(d);
            date.setLayoutX(x + 85);
            date.setLayoutY(y + 22);
            String u1 = String.valueOf(c.getMarque());
            Label location = new Label(u1);
            location.setLayoutX(x + 190);
            location.setLayoutY(y + 22);
            String u = String.valueOf(c.getV().getDisponibilite());
            Label user = new Label(u);
            user.setLayoutX(x + 300);
            user.setLayoutY(y + 22);
            String u2 = String.valueOf(c.getV().getNum_entretien());
            Label user1 = new Label(u2);
            user1.setLayoutX(x + 420);
            user1.setLayoutY(y + 22);
            String u3 = String.valueOf(c.getV().getDate_entretien());
            Label user3 = new Label(u3);
            user3.setLayoutX(x + 530);
            user3.setLayoutY(y + 22);
            String u4 = String.valueOf(c.getV().getRes_entretien());
            Label user4 = new Label(u4);
            user4.setLayoutX(x + 690);
            user4.setLayoutY(y + 22);
            an.getChildren().addAll(name, date, location, user, user1, user3, user4);

            vbox.getChildren().add(an);
        }
    }

    @FXML
    private void mission(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionMission.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

}
