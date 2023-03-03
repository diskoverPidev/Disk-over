/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.discover.gui;

import edu.discover.entities.Colaborationevent;
import edu.discover.services.Crudcolaboration;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ramzi
 */
public class EvenementController implements Initializable {

 
    @FXML
    private Button reserver;
    @FXML
    private Button retour;
    @FXML
    private TextField search;
    @FXML
    private VBox vbox;
    @FXML
    private AnchorPane ev;
    @FXML
    private Button affichage;

    
    @FXML
    private void listeres(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterReservation.fxml"));
        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) reserver.getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
    }
    
  
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    public void showsEvents(ActionEvent event) {
        List<Colaborationevent> even = new ArrayList();
        Crudcolaboration se = new Crudcolaboration();

        even = se.getAll();
        System.out.println(ev);
        int x = 0, y = 0;
        // listevent.getItems().addAll(even);
        for (Colaborationevent e : even) {

            AnchorPane an = new AnchorPane();
            an.setLayoutX(x);
            an.setLayoutY(y);

            Label name = new Label(e.getNomevent());
            name.setLayoutX(x + 14);
            name.setLayoutY(y + 22);
            String d = String.valueOf(e.getDateevent());
            Label date = new Label(d);
            date.setLayoutX(x + 135);
            date.setLayoutY(y + 22);
            Label location = new Label(e.getAdresseevent());
            location.setLayoutX(x + 214);
            location.setLayoutY(y + 22);
            String u = String.valueOf(e.getNbrplacevehicule());
            Label nbr = new Label(u);
            nbr.setLayoutX(x + 312);
            nbr.setLayoutY(y + 22);

            String nb = String.valueOf(e.getPrixvehiculeevent());
            Label prix = new Label(nb);
            prix.setLayoutX(x + 446);
            prix.setLayoutY(y + 22);

            
            
            Button btnaffiche = new Button("Affichage");

            an.getChildren().addAll(name, date, location, nbr, prix);
            ev.getChildren().addAll(an);

            vbox.getChildren().add(an);
        }

    } 
    
}
