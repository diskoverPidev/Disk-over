/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.testapplication.gui2;

import edu.esprit.entities.Client;
import edu.esprit.services.Serviceclient;
import java.awt.TextField;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class SignupController implements Initializable {

    @FXML
    private javafx.scene.control.TextField tfcin;
    @FXML
    private javafx.scene.control.TextField tfnom;
    @FXML
    private javafx.scene.control.TextField tfprenom;
    @FXML
    private javafx.scene.control.TextField tfemail;
    @FXML
    private Button btnsignup;
    @FXML
    private javafx.scene.control.TextField tfpassword;
    @FXML
    private ChoiceBox<String> mychoicebox1;
    @FXML
    private Button signinid;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //mychoicebox.getItems().addAll(user) ;

       
        // if()
        mychoicebox1.getItems().addAll("Client","Chauffeur");
        

    }
   /*   public void goTosignin(ActionEvent event) throws IOException{
        Parent signup = FXMLLoader.load(getClass().getResource("inscription.fxml")) ; 
        Scene signupscene = new Scene (signup) ; 
        Stage appStage= (Stage)((Node)event.getSource()).getScene().getWindow() ; 
    appStage.setScene(signupscene) ; 
    appStage.show() ; 
    
    }*/
    @FXML
    public void savePerson(ActionEvent event) throws IOException
    {
                Alert alert = new Alert(AlertType.ERROR);

        String cin=tfcin.getText() ; 
        String nom=tfnom.getText() ; 
        String prenom=tfprenom.getText() ; 
        String role=mychoicebox1.getValue() ; 
        String email=tfemail.getText() ; 
        String pwd=tfpassword.getText() ; 
        if(cin.isEmpty() || nom.isEmpty() || prenom.isEmpty()|| role.isEmpty() || email.isEmpty() || pwd.isEmpty())
        {
            alert.setContentText("veuillez remplir tout les champs");
alert.showAndWait();  
        }
        Client c = new Client(cin,nom,prenom,role,email,pwd) ; 
        Serviceclient sc= new Serviceclient() ; 
        sc.ajouter(c) ; 
        Parent signup = FXMLLoader.load(getClass().getResource("inscription.fxml")) ; 
        Scene signupscene = new Scene (signup) ; 
        Stage appStage= (Stage)((Node)event.getSource()).getScene().getWindow() ; 
    appStage.setScene(signupscene) ; 
    appStage.show() ; 
                }

    @FXML
    private void signinidnavigate(ActionEvent event) throws IOException {
          Parent signup = FXMLLoader.load(getClass().getResource("inscription.fxml")) ; 
        Scene signupscene = new Scene (signup) ; 
        Stage appStage= (Stage)((Node)event.getSource()).getScene().getWindow() ; 
    appStage.setScene(signupscene) ; 
    appStage.show() ;
    }
   
}
