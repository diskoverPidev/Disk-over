/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.FormulaireR;
import edu.esprit.services.ServiceFormulaire;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author faten
 */
public class InfoReservationController implements Initializable {

    @FXML
    private ListView<String> listRes;
    @FXML
    private ListView<?> listchauff;
    @FXML
    private ScrollPane scres;
    @FXML
    private VBox vbox;
    @FXML
    private AnchorPane an;
    @FXML
    private Button btnafficher;
    @FXML
    private Button btnannuler;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnconfirmer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
    }

    @FXML
    private void Afficher(ActionEvent event) {

        List<FormulaireR> Res = new ArrayList();
        ServiceFormulaire sf = new ServiceFormulaire();
        FormulaireR fr = new FormulaireR();
        listRes.getItems();
        //Res = sf.getOnebyId(fr.getId());
        System.out.println(an);
        int x = 0, y = 0;
        // listevent.getItems().addAll(even);
        for (FormulaireR e : Res) {

            AnchorPane an1 = new AnchorPane();
            an1.setLayoutX(x);
            an1.setLayoutY(y);

            Label nom = new Label(e.getNom());
            nom.setLayoutX(x + 7);
            nom.setLayoutY(y + 7);
            String num = String.valueOf(e.getTlp());
            Label numero = new Label(num);
            numero.setLayoutX(x + 142);
            numero.setLayoutY(y + 7);
            Label mail = new Label(e.getMail());
            mail.setLayoutX(x + 213);
            mail.setLayoutY(y + 7);
            Label type = new Label(e.getType());
            type.setLayoutX(x + 279);
            type.setLayoutY(y + 7);
            Label categorie = new Label(e.getCateg());
            categorie.setLayoutX(x + 353);
            categorie.setLayoutY(y + 7);
            Label depart = new Label(e.getDepart());
            depart.setLayoutX(x + 422);
            depart.setLayoutY(y + 7);
            Label destination = new Label(e.getDestination());
            mail.setLayoutX(x + 503);
            mail.setLayoutY(y + 7);
            String nbr = String.valueOf(e.getNbr());
            Label nombre = new Label(nbr);
            nombre.setLayoutX(x + 557);
            nombre.setLayoutY(y + 7);
            Label option = new Label(e.getOpt());
            option.setLayoutX(x + 553);
            option.setLayoutY(y + 7);

            Button btnafficher = new Button("Affichage");

            an.getChildren().addAll(nom, numero, mail, type, categorie, depart, destination, nombre, option);
            an.getChildren().addAll(an1);

            vbox.getChildren().add(an1);

        }
    }

    @FXML
    private void modification(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierResrvation.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void AnnulerRes(ActionEvent event) {

        FormulaireR fr = new FormulaireR();
        ServiceFormulaire sform = new ServiceFormulaire();
        sform.ajouter(fr);

    }

    @FXML
    private void confirmer(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModalitePaiement.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

}
