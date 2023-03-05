/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.testapplication.gui2;

import edu.esprit.entities.Client;
import edu.esprit.services.Serviceclient;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class EditprofilController implements Initializable {

    @FXML
    private TextField editid;
    @FXML
    private Button consulterbtn;
    @FXML
    private TextField editcin;
    @FXML
    private TextField editnom;
    @FXML
    private TextField editprenom;
    @FXML
    private ChoiceBox<String> choicebox3;
    @FXML
    private TextField editemail;
    @FXML
    private TextField editpassword;
    @FXML
    private Button modifierbtn;
    @FXML
    private Button supprimerbtn;
    @FXML
    private Button signinbtn;

    /**
     * Initializes the controller class.
     */
//          UserSession userSession = UserSession.getInstance();
//      userSession.setCin(cintf.getText());
//
//
//        System.out.print(userSession.getCin());
//    UserSession userSession = UserSession.getInstance();
//String cin = userSession.getCin();
//System.out.print(userSession.getCin());
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         choicebox3.getItems().addAll("Client","Chauffeur");
             UserSession userSession = UserSession.getInstance();
String cin = userSession.getCin();
editid.setText(cin) ; 

    }    

    @FXML
    private void consulterProfil(ActionEvent event) {
        Serviceclient sc= new Serviceclient() ; 
        Client client ; 
        client = sc.getOneById(editid.getText());
         editcin.setText(client.getCin());
                editnom.setText(client.getNom());
                        editprenom.setText(client.getPrenom());
                               choicebox3.setValue(client.getRole());
                               
                        editemail.setText(client.getEmail());
                          editpassword.setText(client.getPwd());
        
    }

    @FXML
    private void modifierprofil(ActionEvent event) {
          Alert alert = new Alert(Alert.AlertType.ERROR);
        Client c = new Client(editcin.getText(),editnom.getText(),editprenom.getText(),choicebox3.getValue(),editemail.getText(),editpassword.getText()) ;  
               Serviceclient sc= new Serviceclient() ; 
sc.modifier(c) ;
alert.setContentText("le profil est modifier");
alert.showAndWait(); 
    }

    @FXML
    private void supprimerProfil(ActionEvent event) {
                  Alert alert = new Alert(Alert.AlertType.ERROR);

        Client c = new Client(editcin.getText(),editnom.getText(),editprenom.getText(),choicebox3.getValue(),editemail.getText(),editpassword.getText()) ; 
               Serviceclient sc= new Serviceclient() ; 
sc.supprimer(c.getCin()) ;
alert.setContentText("le profil est supprimer");
alert.showAndWait(); 
    }

    @FXML
    private void reglagenavigation(ActionEvent event) throws IOException {
          Parent signup = FXMLLoader.load(getClass().getResource("inscription.fxml")) ; 
        Scene signupscene = new Scene (signup) ; 
        Stage appStage= (Stage)((Node)event.getSource()).getScene().getWindow() ; 
    appStage.setScene(signupscene) ; 
    appStage.show() ;
    }
    
}
