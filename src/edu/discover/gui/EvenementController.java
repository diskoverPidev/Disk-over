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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
public class EvenementController implements Initializable {

    @FXML
    private TableColumn<Colaborationevent, String> nomcol;
    @FXML
    private TableColumn<Colaborationevent, Date> datecol;
    @FXML
    private TableColumn<Colaborationevent, String> adresscol;
    @FXML
    private TableColumn<Colaborationevent, Integer> nbrcol;
    @FXML
    private TableColumn<Colaborationevent, Integer> prixcol;
    @FXML
    private Button reserver;
    @FXML
    private Button retour;
    @FXML
    private Button afficher;
    @FXML
    private TableView<?> tableView;
    @FXML
    private TextField search;

    
    @FXML
    private void listeres(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterReservation.fxml"));
        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) reserver.getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
    }
    
  
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    public void showsEvents(ActionEvent event) {
        //   Crudcolaboration sr = new Crudcolaboration();
        Crudcolaboration sr = new Crudcolaboration();
        ObservableList<Colaborationevent> observableClients;
        observableClients = FXCollections.observableArrayList();

        nomcol.setCellValueFactory(new PropertyValueFactory<Colaborationevent, String>("NomEvent"));
        datecol.setCellValueFactory(new PropertyValueFactory<Colaborationevent, Date>("DateEvent"));
        adresscol.setCellValueFactory(new PropertyValueFactory<Colaborationevent, String>("AdresseEvent"));
        nbrcol.setCellValueFactory(new PropertyValueFactory<Colaborationevent, Integer>("NbrPlaceVehicule"));
        prixcol.setCellValueFactory(new PropertyValueFactory<Colaborationevent, Integer>("PrixVehiculeEvent"));
        observableClients.addAll(sr.getAll());
        System.out.println(sr.getAll());
        //tableView.setItems(observableClients);

    }   
    
}
