package edu.discover.gui;

import edu.discover.entities.Reservationevent;
import edu.discover.services.Crudreservation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class ListeReservationController implements Initializable {

    @FXML
    private TableColumn<Reservationevent, String> nomc;
    @FXML
    private TableColumn<Reservationevent, Integer> npersone;
    @FXML
    private TableColumn<Reservationevent, String> nomevent;
    @FXML
    private TextField search;
    @FXML
    private Button btnSupp;
    @FXML
    private TableView<Reservationevent> tableView;
    @FXML
    private Button btnAffiche;
    @FXML
    private Button btnMailing;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    btnAffiche.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //    showsEvent();
                //  Crudcolaboration sr = new Crudcolaboration();
                Crudreservation sr = new Crudreservation();
                ObservableList<Reservationevent> observableClients;
                observableClients = FXCollections.observableArrayList();

                nomc.setCellValueFactory(new PropertyValueFactory<Reservationevent, String>("NomClient"));
                npersone.setCellValueFactory(new PropertyValueFactory<Reservationevent, Integer>("NbrClient"));
                nomevent.setCellValueFactory(new PropertyValueFactory<Reservationevent, String>("NomEvent"));
                observableClients.addAll(sr.getAll());

                System.out.println(observableClients);
                tableView.setItems(observableClients);
            }
        });  
    
    
    }
     @FXML
    private void listeres(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Mailing.fxml"));
        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) btnMailing.getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
    }
    
    
    
    public void afficherReservations(ActionEvent event) {
        Crudreservation crudReservation = new Crudreservation();
        ObservableList<Reservationevent> reservations = FXCollections.observableArrayList();

        nomc.setCellValueFactory(new PropertyValueFactory<>("NomClient"));
        npersone.setCellValueFactory(new PropertyValueFactory<>("NbrClient"));
        nomevent.setCellValueFactory(new PropertyValueFactory<>("NomEvent"));

        reservations.addAll(crudReservation.getAll());
        tableView.setItems(reservations);
    }
    
    
    @FXML
    private void supprimerListe(ActionEvent event) {
        Crudreservation sr = new Crudreservation();
        Reservationevent t = tableView.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);// alert de confirmation suppression
        alert.setTitle("ALERT suppression");
        alert.setHeaderText(null);
        alert.setContentText(" VOULEZ VOUS SUPPRIMER la reclamation ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            sr.supprimer(t);

            JOptionPane.showMessageDialog(null, " rec supprimer ");
        } else {
            JOptionPane.showMessageDialog(null, "rec non supprimer ");
        }
        sr.getAll();
    }
    
    
    
}
