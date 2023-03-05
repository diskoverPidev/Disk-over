/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.Reclamation;
import edu.esprit.services.ServiceReclamation;
import java.awt.event.MouseEvent;
import java.io.IOException;



import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author abidi
 */
public class ReclamationController implements Initializable {

    @FXML
    private TextField objetR;

    @FXML
    private TextField messageR;

    @FXML
    private TextField typeR;

    @FXML
    private DatePicker dateR;

    @FXML
    private Button btnmodifier;

    @FXML
    private TableView<Reclamation> tableView;

    @FXML
    private TableColumn<Reclamation, String> typeRe;

    @FXML
    private TableColumn<Reclamation, String> objetRe;

    @FXML
    private TableColumn<Reclamation, String> messageRe;

    @FXML
    private TableColumn<Reclamation, Date> dateRe;

    

    private Button menuRe;

    @FXML
    private Button btnValider;

    @FXML
    private Button btnSupprimer;

    @FXML
    private Button btnRechercher;
    @FXML
    private TextField searchid;
    @FXML
    private TextField cinrec;
    @FXML
    private TableColumn<?, ?> cinRe;
    @FXML
    private Button afficherrec;
    @FXML
    private WebView web;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        showsReclamation();

    }

    @FXML
    private void ajouterReclamation(ActionEvent event) {
//        InputStream imgStream = getClass().getResourceAsStream("C:/Users/abidi/OneDrive/Images");
        String cin = cinrec.getText();
        String type = typeR.getText();
        String objet = objetR.getText();
        String message = messageR.getText();
        Date date = Date.valueOf(dateR.getValue());

        Reclamation t = new Reclamation(cin,type, objet, message, date);
        ServiceReclamation sr = new ServiceReclamation();
        sr.ajouter(t);

        Alert a = new Alert(Alert.AlertType.ERROR, "reclamation ajouter", ButtonType.OK);
        a.showAndWait();

    }

    @FXML
    
    
    
    public void showsReclamation() {
    ServiceReclamation sr = new ServiceReclamation();
    List<Reclamation> r = sr.getOneById(cinrec.getText());
    List<Reclamation> trimmedReclamations = new ArrayList<>();

    for (Reclamation reclamation : r) {
        Reclamation trimmed = new Reclamation();
        trimmed.setCin(reclamation.getCin());
        trimmed.setType(reclamation.getType());
        trimmed.setObjet(reclamation.getObjet());
        trimmed.setMessage(reclamation.getMessage());
        trimmed.setDate(reclamation.getDate());
        trimmedReclamations.add(trimmed);
    }

    ObservableList<Reclamation> observableClients = FXCollections.observableList(trimmedReclamations);

    tableView.setItems(observableClients);
    cinRe.setCellValueFactory(new PropertyValueFactory<>("cin"));  
    typeRe.setCellValueFactory(new PropertyValueFactory<>("type"));
    objetRe.setCellValueFactory(new PropertyValueFactory<>("objet"));
    messageRe.setCellValueFactory(new PropertyValueFactory<>("message"));
    dateRe.setCellValueFactory(new PropertyValueFactory<>("date"));
}

//    public void showsReclamation() {
//    ServiceReclamation sr = new ServiceReclamation();
//    List<Reclamation> r =  sr.getOneById(cinRe.getText());
////    List<Reclamation> tri_rec = sr.trier(r);
//    ObservableList<Reclamation> observableClients = FXCollections.observableList(r);
//
//    tableView.setItems(observableClients);
////    idRe.setCellValueFactory(new PropertyValueFactory<>("id"));
//    cinRe.setCellValueFactory(new PropertyValueFactory<>("cin"));  
//    typeRe.setCellValueFactory(new PropertyValueFactory<>("type"));
//    objetRe.setCellValueFactory(new PropertyValueFactory<>("objet"));
//    messageRe.setCellValueFactory(new PropertyValueFactory<>("message"));
//    dateRe.setCellValueFactory(new PropertyValueFactory<>("date"));
//}

//    public void showsReclamation() {
//        ServiceReclamation sr = new ServiceReclamation();
//        List<Reclamation> r =  sr.getOneById(cinRe.getText());
//
//       List<Reclamation> tri_rec = sr.trier(r);
//        ObservableList<Reclamation> observableClients = FXCollections.observableList(r);
//
////      ObservableList<Reclamation> observableClients = FXCollections.observableList(reclamation);
//        tableView.setItems(observableClients);
////        idRe.setCellValueFactory(new PropertyValueFactory<>("id"));
//         cinRe.setCellValueFactory(new PropertyValueFactory<>("cin"));  
//        typeRe.setCellValueFactory(new PropertyValueFactory<>("type"));
//        objetRe.setCellValueFactory(new PropertyValueFactory<>("objet"));
//        messageRe.setCellValueFactory(new PropertyValueFactory<>("message"));
//        dateRe.setCellValueFactory(new PropertyValueFactory<>("date"));
//
//    }
    
    @FXML
    private void getData(javafx.scene.input.MouseEvent event) {
        Reclamation r = tableView.getSelectionModel().getSelectedItem() ; 
        cinrec.setText(r.getCin());
                typeR.setText(r.getType());
                        objetR.setText(r.getObjet());
                               messageR.setText(r.getMessage());
                               SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // Format de la date
    String dateStr = formatter.format(r.getDate()); // Conversion de la date en String
    LocalDate localDate = LocalDate.parse(dateStr); // Conversion du String en LocalDate
    dateR.setValue(localDate);
                             
                               


                          



    }



   

    @FXML
    void modifierReclamation(ActionEvent event) {
        {
            

            Reclamation t = new Reclamation(cinrec.getText(),typeR.getText(), objetR.getText(), messageR.getText(), Date.valueOf(dateR.getValue()));
            ServiceReclamation sr = new ServiceReclamation();
            sr.modifier(t);
                        showsReclamation();


            Alert a = new Alert(Alert.AlertType.ERROR, "reclamation modifier", ButtonType.OK);
            a.showAndWait();
            
        }

    }
    

    @FXML
    void supprimerReclamation(ActionEvent event) {
         

        Reclamation t = new Reclamation(cinrec.getText(),typeR.getText(), objetR.getText(), messageR.getText(), Date.valueOf(dateR.getValue()));
        ServiceReclamation sr = new ServiceReclamation();
        sr.supprimer(t);

        Alert a = new Alert(Alert.AlertType.ERROR, "reclamation supprimer", ButtonType.OK);
        a.showAndWait();
        showsReclamation();
    }

    private void menuRe(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Consulte.fxml"));
        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) menuRe.getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
    }

    @FXML
    void rechercheR(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (searchid.getText().isEmpty()) {
            alert.setContentText("remplir le champ a rechercher");
            alert.showAndWait();
            return;
        }
        ServiceReclamation sr = new ServiceReclamation();
        List<Reclamation> reclamation = sr.getAll();
        List<Reclamation> rec_cherches = sr.rechercher(reclamation, searchid.getText());
        ObservableList<Reclamation> observableClients = FXCollections.observableList(rec_cherches);
        tableView.setItems(observableClients);
        cinRe.setCellValueFactory(new PropertyValueFactory<>("cin")); 
        typeRe.setCellValueFactory(new PropertyValueFactory<>("type"));
        objetRe.setCellValueFactory(new PropertyValueFactory<>("objet"));
        messageRe.setCellValueFactory(new PropertyValueFactory<>("message"));
        dateRe.setCellValueFactory(new PropertyValueFactory<>("date"));

    }

    @FXML
    private void afficherreclamation(ActionEvent event) {
        showsReclamation();
        
    }

    

}

