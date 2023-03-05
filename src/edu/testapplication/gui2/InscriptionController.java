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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class InscriptionController implements Initializable { 

    
    @FXML
    private TextField cintf;
    @FXML
    private PasswordField pwdtf;
    @FXML
    private Button signinbtn;
    @FXML
    private Button btn;
    @FXML
    private Hyperlink forgotpwd;
    @FXML
    private Button admin;
   

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    } 
    
    @FXML
    private void goTopage(ActionEvent event) throws IOException{
        Parent signup = FXMLLoader.load(getClass().getResource("Signup.fxml")) ; 
        Scene signupscene = new Scene (signup) ; 
        Stage appStage= (Stage)((Node)event.getSource()).getScene().getWindow() ; 
    appStage.setScene(signupscene) ; 
    appStage.show() ; 
    
    }
    @FXML
    public void signinButtonClicked(ActionEvent event) throws IOException, NoSuchAlgorithmException {
        Alert alert = new Alert(AlertType.ERROR);

    String cin = cintf.getText();
    String password = pwdtf.getText();
    
     if (cin.isEmpty() || password.isEmpty()){
         alert.setContentText("remplir les champs");
alert.showAndWait();
            return;
     }
         
  if (password.length() < 8) {
             alert.setContentText("longueur du pwd doit etre supperieur a 8");
alert.showAndWait();
            return;
        }
        if (cin.length() != 8) {
alert.setContentText("longueur du cin doit etre supperieur  8");
alert.showAndWait();   
return;
        }
 
    // Vérifier les informations de connexion et récupérer l'utilisateur correspondant
    Client c = authenticateUser(cin, password);
 
    if (c != null && c.getRole().equals("admin")) {
        Parent signup = FXMLLoader.load(getClass().getResource("admin.fxml")) ; 
        Scene signupscene = new Scene (signup) ; 
        Stage appStage= (Stage)((Node)event.getSource()).getScene().getWindow() ; 
    appStage.setScene(signupscene) ; 
    appStage.show() ; 
    
        Notifications.create()
                .title("Notification")
                .text("Bonjour ! authentification effectué en tant que admin")
                .showWarning();
    }
     if (c != null && c.getRole().equals("Client")){
         Parent signup = FXMLLoader.load(getClass().getResource("choixutilisateur.fxml")) ; 
        Scene signupscene = new Scene (signup) ; 
        Stage appStage= (Stage)((Node)event.getSource()).getScene().getWindow() ; 
    appStage.setScene(signupscene) ; 
    appStage.show() ;
    Notifications.create()
                .title("Notification")
                .text("Bonjour ! authentification effectué en tant que client")
                .showWarning();
     }
     if (c != null && c.getRole().equals("Chauffeur")){
         Parent signup = FXMLLoader.load(getClass().getResource("choixchauffeur.fxml")) ; 
        Scene signupscene = new Scene (signup) ; 
        Stage appStage= (Stage)((Node)event.getSource()).getScene().getWindow() ; 
    appStage.setScene(signupscene) ; 
    appStage.show() ; 
    Notifications.create()
                .title("Notification")
                .text("Bonjour ! authentification effectué en tant que chauffeur")
                .showWarning();
     }
     
     UserSession userSession = UserSession.getInstance();
     userSession.setCin(cintf.getText());


   

}

// Cette méthode simule l'authentification de l'utilisateur en vérifiant
// les informations de connexion dans une liste prédéfinie d'utilisateurs.
public Client authenticateUser(String cin, String password) throws NoSuchAlgorithmException {
    Serviceclient sc= new Serviceclient() ;
    List<Client> users =sc.getall() ; 

    for (Client user : users) {
        if ((user.getCin().equals(cin)) && (user.getPwd().equals(hashPassword(password)))) {
//            System.out.println(user.getPwd()); 
//                        System.out.println(hashPassword(password)); 

            return user;
        }
    }
        return null;

   
}
    
//    public Client Login(String cin, String pwd) throws Exception {
//        Serviceclient sc= new Serviceclient() ;
//    List<Client> users =sc.getall() ; 
//
//    for (Client user : users) {
//        if (user.getCin().equals(cin) && user.getPwd().equals(password)) {
//            return user;
////            labelStatus.setText("Login is Successful");
////            Stage primaryStage = new Stage();
//            Parent root = FXMLLoader.load(getClass().getResource("/sample/Main.fxml"));
//            Scene scene = new Scene(root,400,400);
//            primaryStage.setScene(scene);
//            primaryStage.show();
//        } else {
//            labelStatus.setText("Login failed!!");
//        }
//    }



    @FXML
    private void gotocheckpwd(ActionEvent event) throws IOException {
        Parent signup = FXMLLoader.load(getClass().getResource("forgetpwd.fxml")) ; 
        Scene signupscene = new Scene (signup) ; 
        Stage appStage= (Stage)((Node)event.getSource()).getScene().getWindow() ; 
    appStage.setScene(signupscene) ; 
    appStage.show() ; 
    }
     private String hashPassword(String password) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    byte[] hash = md.digest(password.getBytes());
    StringBuilder sb = new StringBuilder();
    for (byte b : hash) {
        sb.append(String.format("%02x", b));
    }
    return sb.toString();
}

    @FXML
    private void gotoadmin(ActionEvent event) throws IOException {
        Parent signup = FXMLLoader.load(getClass().getResource("admin.fxml")) ; 
        Scene signupscene = new Scene (signup) ; 
        Stage appStage= (Stage)((Node)event.getSource()).getScene().getWindow() ; 
    appStage.setScene(signupscene) ; 
    appStage.show() ;
    }

  
}
