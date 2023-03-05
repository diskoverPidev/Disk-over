/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.testapplication.gui2;

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
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class ChoixchauffeurController implements Initializable {

    @FXML
    private Button homebtnch;
   
          


    
    @FXML
    private Hyperlink profilch ;
    private TextField  cintf ; 
    @FXML
    private TextField idtf;
    @FXML
    private Label labelcin;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//      UserSession userSession = UserSession.getInstance();
//   String cin = userSession.getCin();
//    System.out.print(cin);
//labelcin.setText(cin) ; 
        }    
  
    @FXML
    private void homech(ActionEvent event) throws IOException {
         Parent signup = FXMLLoader.load(getClass().getResource("home.fxml")) ; 
        Scene signupscene = new Scene (signup) ; 
        Stage appStage= (Stage)((Node)event.getSource()).getScene().getWindow() ; 
    appStage.setScene(signupscene) ; 
    appStage.show() ;
    
    }

    @FXML
    private void navigaitioneditch(ActionEvent event) throws IOException {
         Parent signup = FXMLLoader.load(getClass().getResource("editprofil.fxml")) ; 
        Scene signupscene = new Scene (signup) ; 
        Stage appStage= (Stage)((Node)event.getSource()).getScene().getWindow() ; 
    appStage.setScene(signupscene) ; 
    appStage.show() ;
        
    }
  

    
}
