/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.Reclamation;
import edu.esprit.entities.Reponse;
import edu.esprit.services.ServiceReclamation;
import edu.esprit.services.ServiceReponse;
import static java.awt.PageAttributes.MediaType.C;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author abidi
 */
public class BackController implements Initializable {

    @FXML
    private Button repondre;
    @FXML
    private TextField idC;
    @FXML
    private TextField idCh;
    @FXML
    private TextField numR;
    @FXML
    private TextField resR;
     @FXML
    private DatePicker datei;
    @FXML
    private TableView<Reponse> tableView;
    @FXML
    private TableColumn<Reponse, Integer> idi;
    @FXML
    private TableColumn<Reponse, Integer> idic;
    @FXML
    private TableColumn<Reponse, Integer> numir;
    @FXML
    private TableColumn<Reponse, String> resir;
    @FXML
    private TableColumn<?, ?> dateir;
    @FXML
    private Button btnSupp;
    @FXML
    private Button btnmodif;
    @FXML
    private DatePicker dateR;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficherReponse();
    }

    @FXML
    private void ajouterReponse(ActionEvent event) {

        int idclient = Integer.parseInt(idC.getText());
        int idchauffeur = Integer.parseInt(idCh.getText());
        int num = Integer.parseInt(numR.getText());
        String resultat = resR.getText();
        Date dateR = Date.valueOf(datei.getValue());

        Reponse r = new Reponse(idclient, idchauffeur, num, resultat, dateR);
        ServiceReponse sr = new ServiceReponse() {
        };
        sr.ajouter(r);

        Alert a = new Alert(Alert.AlertType.ERROR, "reponse ajouter", ButtonType.OK);
        a.showAndWait();

    }

    @FXML
    private void afficherReponse() {

        ServiceReponse sr = new ServiceReponse() {
        };
        List<Reponse> reponse = sr.getAll();
          List<Reponse> tri_rep = sr.trier(reponse);
        ObservableList<Reponse> observableClients = FXCollections.observableList(reponse);
      

        
      


        tableView.setItems(observableClients);
        idi.setCellValueFactory(new PropertyValueFactory<>("idclient"));
        idic.setCellValueFactory(new PropertyValueFactory<>("idchauffeur"));

        numir.setCellValueFactory(new PropertyValueFactory<>("num"));

        resir.setCellValueFactory(new PropertyValueFactory<>("resultat"));
        dateir.setCellValueFactory(new PropertyValueFactory<>("dateR"));
    }

    @FXML
    private void supprimerReponse(ActionEvent event) {
        ServiceReponse sr = new ServiceReponse() {
        };
        Reponse i = tableView.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);// alert de confirmation suppression
        alert.setTitle("ALERT suppression");
        alert.setHeaderText(null);
        alert.setContentText(" VOULEZ VOUS SUPPRIMER la reponse ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            sr.supprimer(i);

            JOptionPane.showMessageDialog(null, " rec supprimer ");
        } else {
            JOptionPane.showMessageDialog(null, "rec non supprimer ");
        }
        afficherReponse();
        sr.getAll();
    }

    @FXML
    private void modifierReponse(ActionEvent event) {
//        ServiceReponse sr = new ServiceReponse() {
//        };
//
//        String lieu = resir.getText();
//        if (resir.getText().isEmpty()) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Erreur");
//            alert.setHeaderText(null);
//            alert.setContentText(" vérifier vos informations ");
//
//            alert.showAndWait();
//        } else {
//            String e = resR.getText();
//            Reponse i = tableView.getSelectionModel().getSelectedItem();
//      
//            i.setIdClient(idC.getId());
//            i.setIdChauffeur(idCh.getId());
//            i.setNum(numR.getId());
//            i.setResultat(resR.getText());
////            i.setDate(datei.getValue());
//            JOptionPane.showMessageDialog(null, "Reclamation modifiee !");
//            sr.modifier(i);
//            afficherReponse();
//        }
//
//    }

    }
}
