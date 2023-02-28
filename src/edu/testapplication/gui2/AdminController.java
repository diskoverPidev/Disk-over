/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.testapplication.gui2;

import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import edu.esprit.entities.Client;
import edu.esprit.services.Serviceclient;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class AdminController implements Initializable {

    @FXML
    private TextField idcin;
    @FXML
    private TextField idnom;
    @FXML
    private TextField idprenom;
    @FXML
    private TextField idemail;
    @FXML
    private PasswordField idpassword;
    @FXML
    private ChoiceBox<String> choicebox2;
    @FXML
    private Button savebtn;
    @FXML
    private Button updatebtn;
    @FXML
    private Button deletebtn;
    private TableColumn<Client, Integer> collid;
    @FXML
    private TableColumn<Client, String> collnom;
    @FXML
    private TableColumn<Client, String> collprenom;
    @FXML
    private TableColumn<Client, String > collrole;
    @FXML
    private TableColumn<Client, String> collemail;
    @FXML
    private TableView<Client> table;
    @FXML
    private TableColumn<Client, String> collpwd;
    @FXML
    private TableColumn<Client, String> collcin;
    @FXML
    private Button tribtn;
    @FXML
    private TextField idcin1;
    @FXML
    private Button tribtn1;
    @FXML
    private Button consultbtn;
    @FXML
    private Button homebtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                choicebox2.getItems().addAll("admin","Client","Chauffeur");

        showsUsers() ; 
    }   
    public void showsUsers() {
                Serviceclient sc= new Serviceclient() ; 
List<Client> clients = sc.getall();
ObservableList<Client> observableClients = FXCollections.observableList(clients);
        


table.setItems(observableClients) ; 
collcin.setCellValueFactory(new PropertyValueFactory<>("cin")) ; 
collnom.setCellValueFactory(new PropertyValueFactory<>("nom")) ;
collprenom.setCellValueFactory(new PropertyValueFactory<>("prenom")) ; 
collrole.setCellValueFactory(new PropertyValueFactory<>("role")) ;
collemail.setCellValueFactory(new PropertyValueFactory<>("email")) ; 
collpwd.setCellValueFactory(new PropertyValueFactory<>("pwd")) ; 
 






        
    }
    
    @FXML
     public void saveUser(ActionEvent event)
    {
        String cin=idcin.getText() ; 
        String nom=idnom.getText() ; 
        String prenom=idprenom.getText() ; 
        String role=(String) choicebox2.getValue() ; 
        String email=idemail.getText() ; 
        String pwd=idpassword.getText() ; 
        Client c = new Client(cin,nom,prenom,role,email,pwd) ; 
        Serviceclient sc= new Serviceclient() ; 
        sc.ajouter(c) ; 
                showsUsers() ; 

                }
      @FXML
    private void getData(MouseEvent event) {
        Client client = table.getSelectionModel().getSelectedItem() ; 
        idcin.setText(client.getCin());
                idnom.setText(client.getNom());
                        idprenom.setText(client.getPrenom());
                               choicebox2.setValue(client.getRole());
                               
                        idemail.setText(client.getEmail());
                          idpassword.setText(client.getPwd());
                          savebtn.setDisable(true) ; 




        
    }
    @FXML
    public void updateUser(ActionEvent event)
    {
       Client c = new Client(idcin.getText(),idnom.getText(),idprenom.getText(),choicebox2.getValue(),idemail.getText(),idpassword.getText()) ;  
               Serviceclient sc= new Serviceclient() ; 
sc.modifier(c) ;
showsUsers() ;


       
    }

    @FXML
    private void deleteUser(ActionEvent event) {
        Client c = new Client(idcin.getText(),idnom.getText(),idprenom.getText(),choicebox2.getValue(),idemail.getText(),idpassword.getText()) ;  
               Serviceclient sc= new Serviceclient() ; 
sc.supprimer(c.getCin()) ;
showsUsers() ;
    }

    @FXML
    private void triusers(ActionEvent event) {
          Serviceclient sc= new Serviceclient() ; 
          List<Client> clients = sc.getall();   
List<Client>  tri_client = sc.trier(clients) ;
ObservableList<Client> observableClients = FXCollections.observableList(tri_client);
        


table.setItems(observableClients) ; 
collcin.setCellValueFactory(new PropertyValueFactory<>("cin")) ; 
collnom.setCellValueFactory(new PropertyValueFactory<>("nom")) ;
collprenom.setCellValueFactory(new PropertyValueFactory<>("prenom")) ; 
collrole.setCellValueFactory(new PropertyValueFactory<>("role")) ;
collemail.setCellValueFactory(new PropertyValueFactory<>("email")) ; 
collpwd.setCellValueFactory(new PropertyValueFactory<>("pwd")) ; 
 






        
    }

    @FXML
    private void rechercheuser(ActionEvent event) {
        Alert alert = new Alert(AlertType.ERROR);
        if (idcin1.getText().isEmpty()) {
             alert.setContentText("remplir le champ a rechercher");
alert.showAndWait();
return ; 
        }
Serviceclient sc= new Serviceclient() ; 
          List<Client> clients = sc.getall(); 
          List<Client> clients_recherché=sc.rechercher(clients, idcin1.getText()) ; 
          ObservableList<Client> observableClients = FXCollections.observableList(clients_recherché);
          table.setItems(observableClients) ; 
collcin.setCellValueFactory(new PropertyValueFactory<>("cin")) ; 
collnom.setCellValueFactory(new PropertyValueFactory<>("nom")) ;
collprenom.setCellValueFactory(new PropertyValueFactory<>("prenom")) ; 
collrole.setCellValueFactory(new PropertyValueFactory<>("role")) ;
collemail.setCellValueFactory(new PropertyValueFactory<>("email")) ; 
collpwd.setCellValueFactory(new PropertyValueFactory<>("pwd")) ; 
          
          

           
            
    }
    @FXML
    private void afficheruser(ActionEvent event) {
        
        showsUsers() ; 
    }

    @FXML
    private void adminnavigation(ActionEvent event) throws IOException {
          Parent signup = FXMLLoader.load(getClass().getResource("home.fxml")) ; 
        Scene signupscene = new Scene (signup) ; 
        Stage appStage= (Stage)((Node)event.getSource()).getScene().getWindow() ; 
    appStage.setScene(signupscene) ; 
    appStage.show() ;
    }
    }
        
    
   
    

