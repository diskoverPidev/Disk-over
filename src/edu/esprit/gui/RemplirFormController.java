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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author faten
 */
public class RemplirFormController implements Initializable {

    @FXML
    private Button btnvalider;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfnum;
    @FXML
    private TextField tfmail;
    private TextField tftype;
    private TextField tfcateg;
    @FXML
    private TextField tfdep;
    @FXML
    private TextField tfdest;
    @FXML
    private TextField tfnbr;
    private TextField tfopt;
    @FXML
    private ChoiceBox<String> btye;
    ObservableList<String> choicetype = FXCollections.observableArrayList("trajet_Intra_Urbain", "trajet_Inter_Urbain");
    @FXML
    private ChoiceBox<String> bcateg;
    ObservableList<String> choicecateg = FXCollections.observableArrayList("moyenne_gamme", "haute_gamme");
    @FXML
    private ChoiceBox<String> bopt;
    ObservableList<String> choiceopt = FXCollections.observableArrayList("siège_enfant", "wifi", "garniture");

    public TextField getTfnom() {
        return tfnom;
    }

    public TextField getTfnum() {
        return tfnum;
    }

    public TextField getTfmail() {
        return tfmail;
    }

    public TextField getTftype() {
        return tftype;
    }

    public TextField getTfcateg() {
        return tfcateg;
    }

    public TextField getTfdep() {
        return tfdep;
    }

    public TextField getTfdest() {
        return tfdest;
    }

    public TextField getTfnbr() {
        return tfnbr;
    }

    public TextField getTfopt() {
        return tfopt;
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        btye.setItems(choicetype);
        bcateg.setItems(choicecateg);
        bopt.setItems(choiceopt);
        
        
        // TODO
    } 
    
    

    
    
    
    

    @FXML
    private void ajouterReservation(ActionEvent event) throws IOException {
        String nom = tfnom.getText();
        int tlp = Integer.parseInt(tfnum.getText());
        String mail = tfmail.getText();
        String type = btye.getValue();
        //String type = tftype.getText();
        //String categ = tfcateg.getText();
        String categ = bcateg.getValue();
        String depart = tfdep.getText();
        String destination = tfdest.getText();
        int nbr = Integer.parseInt(tfnbr.getText());
        //String opt = tfopt.getText();
        String opt = bopt.getValue();
        
        FormulaireR fr = new FormulaireR(nom, tlp, mail, nbr, type, categ, depart, destination, opt);
        ServiceFormulaire sform = new ServiceFormulaire();
        sform.ajouter(fr);
        
    FXMLLoader loader = new FXMLLoader(getClass().getResource("InfoReservation.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
        //JOptionPane.showConfirmDialog(null, "Reservation enregistrée !");
        //sform.notifier("Nouvelle notification ", "Reservation enregistrée ! ");

}
    
    
    
    
    
    
}
