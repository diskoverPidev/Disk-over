/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.discover.gui;

import edu.discover.entities.Colaborationevent;
import edu.discover.services.Crudcolaboration;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ramzi
 */
public class AffichereservationController implements Initializable {

    @FXML
    private TextField nomi;
    @FXML
    private TextField adressei;
    @FXML
    private TextField nbri;
    @FXML
    private TextField prixi;
    @FXML
    private DatePicker datei;
    @FXML
    private TableColumn<Colaborationevent, String> namecol;
    @FXML
    private TableColumn<Colaborationevent, Date> datecol;
    @FXML
    private TableColumn<Colaborationevent, String> adresscol;
    @FXML
    private TableColumn<Colaborationevent, Integer> nbrcol;
    @FXML
    private TableColumn<Colaborationevent, Integer> prixcol;
    @FXML
    private Button btnadd;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;
    @FXML
    private TableView<Colaborationevent> tableView;
    @FXML
    private Button btnaffiche;
    @FXML
    private Button lister;

    @FXML
    private TextField search;

    @FXML
    private void listeres(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeReservation.fxml"));
        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) lister.getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
    }

    /**
     *
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnaffiche.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //    showsEvent();
                //  Crudcolaboration sr = new Crudcolaboration();
                Crudcolaboration sr = new Crudcolaboration();
                ObservableList<Colaborationevent> observableClients;
                observableClients = FXCollections.observableArrayList();

                namecol.setCellValueFactory(new PropertyValueFactory<Colaborationevent, String>("NomEvent"));
                datecol.setCellValueFactory(new PropertyValueFactory<Colaborationevent, Date>("DateEvent"));
                adresscol.setCellValueFactory(new PropertyValueFactory<Colaborationevent, String>("AdresseEvent"));
                nbrcol.setCellValueFactory(new PropertyValueFactory<Colaborationevent, Integer>("NbrPlaceVehicule"));
                prixcol.setCellValueFactory(new PropertyValueFactory<Colaborationevent, Integer>("PrixVehiculeEvent"));
                observableClients.addAll(sr.getAll());

                System.out.println(observableClients);
                tableView.setItems(observableClients);
            }
        });

    }

    private void GoResrvationListe(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) lister.getScene().getWindow();
        currentStage.hide();
        Parent root = FXMLLoader.load(getClass().getResource("ListeReservation.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    public void showsEvent(ActionEvent event) {
        //   Crudcolaboration sr = new Crudcolaboration();
        Crudcolaboration sr = new Crudcolaboration();
        ObservableList<Colaborationevent> observableClients;
        observableClients = FXCollections.observableArrayList();

        namecol.setCellValueFactory(new PropertyValueFactory<Colaborationevent, String>("NomEvent"));
        datecol.setCellValueFactory(new PropertyValueFactory<Colaborationevent, Date>("DateEvent"));
        adresscol.setCellValueFactory(new PropertyValueFactory<Colaborationevent, String>("AdresseEvent"));
        nbrcol.setCellValueFactory(new PropertyValueFactory<Colaborationevent, Integer>("NbrPlaceVehicule"));
        prixcol.setCellValueFactory(new PropertyValueFactory<Colaborationevent, Integer>("PrixVehiculeEvent"));
        observableClients.addAll(sr.getAll());
        System.out.println(sr.getAll());
        tableView.setItems(observableClients);

    }

    @FXML
    private void add(ActionEvent event) {

        String nomevent = nomi.getText();
        Date dateevent = Date.valueOf(datei.getValue());
        String adresseevent = adressei.getText();
        int nbrplacevehicule = Integer.parseInt(nbri.getText());//Integer.parseInt pour forcer la conversion en int
        int prixvehiculeevent = Integer.parseInt(prixi.getText());

        Colaborationevent c = new Colaborationevent(nomevent, dateevent, adresseevent, nbrplacevehicule, prixvehiculeevent);
        Crudcolaboration cc = new Crudcolaboration();
        cc.ajout(c);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Event Registation");

        alert.setHeaderText("Event Registation");
        alert.setContentText("Event Added!");

        alert.showAndWait();

    }

    @FXML
    private void update(ActionEvent event) {
        String nomevent = nomi.getText();
        Date dateevent = Date.valueOf(datei.getValue());
        String adresseevent = adressei.getText();
        int nbrplacevehicule = Integer.parseInt(nbri.getText());
        int prixvehiculeevent = Integer.parseInt(prixi.getText());

        Colaborationevent c = new Colaborationevent(nomevent, dateevent, adresseevent, nbrplacevehicule, prixvehiculeevent);
        Crudcolaboration cc = new Crudcolaboration();
        cc.modifier(c, search.getText());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Event Update");
        alert.setHeaderText("Event Update");
        alert.setContentText("Event Updated!");
        alert.showAndWait();

    }

    @FXML
    private void delete(ActionEvent event) {
        String nomevent = nomi.getText();
        Colaborationevent c = new Colaborationevent();
        Crudcolaboration cc = new Crudcolaboration();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Event Deletion");
        alert.setHeaderText("Event Deletion");
        alert.setContentText("Are you sure you want to delete the event?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                cc.supprimer(search.getText());
                nomi.setText("");
                adressei.setText("");
                nbri.setText("");
                prixi.setText("");
                datei.setValue(null);
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Event Deletion");
                alert1.setHeaderText("Event Deletion");
                alert1.setContentText("Event Deleted!");
                alert1.showAndWait();
            }
        });
    }

}
