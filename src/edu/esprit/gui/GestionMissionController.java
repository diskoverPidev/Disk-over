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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class GestionMissionController implements Initializable {

    @FXML
    private ListView<Mission> liste;
    @FXML
    private Button butnvehicule;
    @FXML
    private TextField inputrechercheid;
    @FXML
    private Button rechercheid;

    @FXML
    private Button ajoutid;
    @FXML
    private Button btndurr;
    @FXML
    private AnchorPane an;
    @FXML
    private VBox vbox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    private void afficher(ActionEvent event) {

        List<Mission> mission = new ArrayList();
        ServiceMission sm = new ServiceMission();
        liste.getItems();
        mission = sm.getALL();
        System.out.println(mission);
        int x = 0, y = 0;
        // listevent.getItems().addAll(even);
        for (Mission m : mission) {

            AnchorPane an = new AnchorPane();
            an.setLayoutX(x);
            an.setLayoutY(y);

            Label name = new Label(m.getMatricule());
            name.setLayoutX(x + 30);
            name.setLayoutY(y + 22);
            String d = String.valueOf(m.getDescription());
            Label date = new Label(d);
            date.setLayoutX(x + 85);
            date.setLayoutY(y + 22);
            String u1 = String.valueOf(m.getHeure_debut());
            Label location = new Label(u1);
            location.setLayoutX(x + 180);
            location.setLayoutY(y + 22);
            String u = String.valueOf(m.getHeure_fin());
            Label user = new Label(u);
            user.setLayoutX(x + 340);
            user.setLayoutY(y + 22);

            an.getChildren().addAll(name, date, location, user);
            vbox.getChildren().add(an);

        }
    }

  
    @FXML
    private void vehicules(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Vehicule.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void rechercher(ActionEvent event) {
        String matricule = inputrechercheid.getText();
        ServiceMission sm = new ServiceMission();

        List<Mission> resultatsRecherche = sm.recherche(matricule);
        if (resultatsRecherche.isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Aucun résultat trouvé", ButtonType.OK);
            a.showAndWait();
        } else {
            liste.setItems(FXCollections.observableArrayList(resultatsRecherche));
        }

    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutMission.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void duree(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("calcul.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
