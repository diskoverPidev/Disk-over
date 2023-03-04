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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author faten
 */
public class ModifReservationController implements Initializable {

    @FXML
    private Button btnvalider;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfnum;
    @FXML
    private TextField tfmail;
    @FXML
    private TextField tftype;
    @FXML
    private TextField tfcateg;
    @FXML
    private TextField tfdep;
    @FXML
    private TextField tfnbr;
    @FXML
    private TextField tfdest;
    @FXML
    private TextField tfopt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  

    @FXML
    private void modifierRes(ActionEvent event) throws IOException {
        
        String nom = tfnom.getText();
        int tlp = Integer.parseInt(tfnum.getText());
        String mail = tfmail.getText();
        String type = tftype.getText();
        String categ = tfcateg.getText();
        String depart = tfdep.getText();
        String destination = tfdest.getText();
        int nbr = Integer.parseInt(tfnbr.getText());
        String opt = tfopt.getText();
        
        FormulaireR fr = new FormulaireR(nom, tlp, mail, nbr, type, categ, depart, destination, opt);
        ServiceFormulaire sform = new ServiceFormulaire();
        sform.modifier(fr);
        
    FXMLLoader loader = new FXMLLoader(getClass().getResource("InfoReservation.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
    
    
}
