/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import com.twilio.http.Response;
import edu.esprit.entities.Reclamation;
import edu.esprit.entities.Reponse;
import edu.esprit.services.ServiceReclamation;
import edu.esprit.services.ServiceReponse;
import static java.awt.PageAttributes.MediaType.C;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import java.io.IOException;
import okhttp3.Credentials;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.IOException;
import okhttp3.*;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author abidi
 */
public class BackController implements Initializable {

    @FXML
    private Button repondre;
    @FXML
    private TextField cin1;

    @FXML
    private TextField res1;

    @FXML
    private TextField num1;

    @FXML
    private TextField reclamation1;

    @FXML
    private DatePicker date1;

    @FXML
    private TableView<Reponse> tableView;

    @FXML
    private TableColumn<Reponse, Integer> cin2;

    @FXML
    private TableColumn<Reponse, String> res2;

    @FXML
    private TableColumn<Reponse, Integer> num2;

    @FXML
    private TableColumn<Reponse, Date> date2;

    @FXML
    private Button btnSupp;
    @FXML
    private Button btnmodif;
    @FXML
    private TextField searchid;

    @FXML
    private Button btnRechercher;
//    private DatePicker dateR;
    @FXML
    private TableView<Reclamation> tableView2;

    @FXML
    private TableColumn<Reclamation, String> typeRe;

    @FXML
    private TableColumn<Reclamation, String> objetRe;

    @FXML
    private TableColumn<Reclamation, String> messageRe;

    @FXML
    private TableColumn<Reclamation, Date> dateRe;
    @FXML
    private TableColumn<Reclamation, String> cinRe;
    @FXML
    private Button afficherid;
    @FXML
    private TextField mssg;
    @FXML
    private Button btntr;
    @FXML
    private TextField traduction;
    @FXML
    private Button btnAfficher;
    @FXML
    private Button btnAppuyez;

    /**
     * Initializes the controller class.
     */
    @FXML
    void appuiyez(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("apiSms.fxml"));
        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) btnAppuyez.getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
    }

    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        afficherReponse();

    }

    @FXML
    private void ajouterReponse(ActionEvent event) {

        int cin = Integer.parseInt(cin1.getText());
        String resultat = res1.getText();
        int num = Integer.parseInt(num1.getText());
        Date dateR = Date.valueOf(date1.getValue());
        int reclamationId = Integer.parseInt(reclamation1.getText());

        Reponse r = new Reponse(cin, resultat, num, dateR, reclamationId);
        ServiceReponse sr = new ServiceReponse() {
        };
        sr.ajouter(r);

        Alert a = new Alert(Alert.AlertType.ERROR, "reponse ajouter", ButtonType.OK);
        a.showAndWait();
        afficherReponse();

    }

    @FXML
    private void afficherReponse() {

        ServiceReponse sr = new ServiceReponse() {
        };
        List<Reponse> reponse = sr.getAll();
        List<Reponse> tri_rep = sr.trier(reponse);
        ObservableList<Reponse> observableClients = FXCollections.observableList(reponse);

        tableView.setItems(observableClients);
        cin2.setCellValueFactory(new PropertyValueFactory<>("cin"));
        res2.setCellValueFactory(new PropertyValueFactory<>("resultat"));

        num2.setCellValueFactory(new PropertyValueFactory<>("num"));

        date2.setCellValueFactory(new PropertyValueFactory<>("dateR"));

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

        {
            int cin = Integer.parseInt(cin1.getText());
            String resultat = res1.getText();
            int num = Integer.parseInt(num1.getText());

            Date dateR = Date.valueOf(date1.getValue());

            Reponse r = new Reponse(cin, resultat, num, dateR);
            ServiceReponse sr = new ServiceReponse() {
            };
            sr.modifier(r);

            Alert a = new Alert(Alert.AlertType.ERROR, "Reponse modifier", ButtonType.OK);
            a.showAndWait();
            afficherReponse();
        }
    }

    @FXML
    void rechercheR(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (searchid.getText().isEmpty()) {
            alert.setContentText("remplir le champ a rechercher");
            alert.showAndWait();
            return;
        }
        ServiceReponse sr = new ServiceReponse() {
        };
        List<Reponse> reponse = sr.getAll();
        List<Reponse> rep_cherche = sr.rechercher(reponse, searchid.getText());
        ObservableList<Reponse> observableClients = FXCollections.observableList(rep_cherche);
        tableView.setItems(observableClients);
        cin2.setCellValueFactory(new PropertyValueFactory<>("cin"));
        res2.setCellValueFactory(new PropertyValueFactory<>("resultat"));

        num2.setCellValueFactory(new PropertyValueFactory<>("num"));

        date2.setCellValueFactory(new PropertyValueFactory<>("dateR"));

    }

    @FXML
    private void getData(MouseEvent event) {
        Reclamation r = tableView2.getSelectionModel().getSelectedItem();
        mssg.setText(r.getMessage());
    }

    @FXML
    private void showsReclamation2() {
        ServiceReclamation sr = new ServiceReclamation();
        List<Reclamation> r = sr.getAll();
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

        tableView2.setItems(observableClients);
        cinRe.setCellValueFactory(new PropertyValueFactory<>("cin"));
        typeRe.setCellValueFactory(new PropertyValueFactory<>("type"));
        objetRe.setCellValueFactory(new PropertyValueFactory<>("objet"));
        messageRe.setCellValueFactory(new PropertyValueFactory<>("message"));
        dateRe.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    @FXML
    private void afficher() {
        showsReclamation2();
    }

    @FXML
    private void traduire(ActionEvent event) throws IOException {
        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("q", "English is hard, but detectably so")
                .build();

        Request request = new Request.Builder()
                .url("https://google-translate1.p.rapidapi.com/language/translate/v2/detect")
                .post(body)
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .addHeader("Accept-Encoding", "application/gzip")
                .addHeader("X-RapidAPI-Key", "cf9df17959msh795e091327e4e77p17dc55jsn58fccd786a0d\"")
                .addHeader("X-RapidAPI-Host", "google-translate1.p.rapidapi.com")
                .build();

    }

    public TextField getMssg() {
        return mssg;
    }
}
