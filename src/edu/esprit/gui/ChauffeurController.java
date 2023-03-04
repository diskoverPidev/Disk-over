/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.Mission;
import edu.esprit.services.ServiceMission;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ChauffeurController implements Initializable {

    //  private TableView<?> tablechauffeur;
    @FXML
    private Button ajoutchauff;
    @FXML
    private Button Afficher;
    @FXML
    private ListView<Mission> liste;

    private String matricule; // Variable pour stocker la valeur de matricule
    @FXML
    private AnchorPane an;
    @FXML
    private VBox vbox;
   

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
    private final ServiceMission sm = new ServiceMission();

   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    // ajouter les tranches de données au Pie Chart

    @FXML
    private void afficher(ActionEvent event) {

        List<Mission> mission = new ArrayList();
        ServiceMission sm = new ServiceMission();
        liste.getItems();
        mission = sm.recherche(matricule);
        System.out.println(mission);
        int x = 0, y = 0;
        // listevent.getItems().addAll(even);
        for (Mission m : mission) {

            AnchorPane an = new AnchorPane();
            an.setLayoutX(x);
            an.setLayoutY(y);

            Label name = new Label(m.getMatricule());
            name.setLayoutX(x + 2);
            name.setLayoutY(y + 22);
            String d = String.valueOf(m.getDescription());
            Label date = new Label(d);
            date.setLayoutX(x + 70);
            date.setLayoutY(y + 22);
            String u1 = String.valueOf(m.getHeure_debut());
            Label location = new Label(u1);
            location.setLayoutX(x + 150);
            location.setLayoutY(y + 22);
            String u = String.valueOf(m.getHeure_fin());
            Label user = new Label(u);
            user.setLayoutX(x + 300);
            user.setLayoutY(y + 22);

            an.getChildren().addAll(name, date, location, user);
            vbox.getChildren().add(an);

        }
       

    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutChauffeur.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

   
}
