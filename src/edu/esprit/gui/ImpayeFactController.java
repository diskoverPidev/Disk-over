/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author faten
 */
public class ImpayeFactController implements Initializable {

    @FXML
    private DatePicker datef;
    @FXML
    private ChoiceBox<String> bstatut;
        ObservableList<String> choice = FXCollections.observableArrayList("Impayé");

    @FXML
    private VBox vbox;
    @FXML
    private AnchorPane an;
    @FXML
    private Button btncode;
    @FXML
    private Button btnenregistrer;
    
    String statut = "Impayé";

    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          bstatut.setItems(choice);
          
        // TODO
    }    

    @FXML
    private void directQRcode(ActionEvent event) throws IOException {
        String statut = bstatut.getValue();
        
         FXMLLoader loader = new FXMLLoader(getClass().getResource("ImpayeQRcode.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public String getStatut() {
        return statut;
    }

    public ChoiceBox<String> getBstatut() {
        return bstatut;
    }
    
    
    
  /*  public String getStatut() {
        return statut;
    }*/

    @FXML
    private void ajouter(ActionEvent event) {
    }
    
}
