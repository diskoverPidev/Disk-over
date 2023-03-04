package edu.discover.gui;

import edu.discover.entities.Reservationevent;
import edu.discover.services.Crudreservation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class ListeReservationController implements Initializable {

    @FXML
    private Label nomc;
    @FXML
    private Label npersone;
    @FXML
    private Label nomevent;
    @FXML
    private TextField search;
    @FXML
    private Button btnSupp;
    @FXML
    private Button btnAffiche;
    @FXML
    private Button btnMailing;
    @FXML
    private VBox vbox;
    @FXML
    private AnchorPane ev;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
    }
    
    
     @FXML
    private void listeres(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Mailing.fxml"));
        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) btnMailing.getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
    }

    
    public void showsEvent(ActionEvent event) {

    List<Reservationevent> res = new ArrayList();
    Crudreservation se = new Crudreservation();

    res = se.getAll();
    System.out.println(ev);

    // clear the vbox before displaying the new elements
    vbox.getChildren().clear();

    int x = 0, y = 0;
    for (Reservationevent e : res) {
        AnchorPane an = new AnchorPane();
        an.setLayoutX(x);
        an.setLayoutY(y);

        Label name = new Label(e.getNomclient());
        name.setLayoutX(x + 14);
        name.setLayoutY(y + 17);
        String u = String.valueOf(e.getNbrclient());
        Label nbr = new Label(u);
        nbr.setLayoutX(x + 208);
        nbr.setLayoutY(y + 17);
        Label nomev = new Label(e.getNomevnet());
        nomev.setLayoutX(x + 372);
        nomev.setLayoutY(y + 17);


        Button btnAffiche = new Button("Affichage");

        an.getChildren().addAll(name, nbr, nomev);
        ev.getChildren().addAll(an);

        vbox.getChildren().add(an);
    }
    }
    
    
    
    
    
    @FXML
    private void supprimerListe(ActionEvent event) {
    }
    
    
    
}
