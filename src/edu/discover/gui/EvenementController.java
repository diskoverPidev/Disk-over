package edu.discover.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import edu.discover.entities.Colaborationevent;
import edu.discover.services.Crudcolaboration;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    private Button affichage;
    @FXML
    private ScrollPane scrol;

    @FXML
    private void listeres(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterReservation.fxml"));
        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) reserver.getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
    }

    // méthodes pour récupérer les données et créer les VBox
    private List<Colaborationevent> getData() throws SQLException {
        Crudcolaboration sc = new Crudcolaboration();
        List<Colaborationevent> even = sc.getAll();
        return even;
    }

    private VBox createVBox(Colaborationevent event, String i) {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(15);
        vBox.setPrefSize(150, 150);
        
        // Chargement de l'image
        String imagePath = "C:\\Users\\ramzi\\OneDrive\\Documents\\NetBeansProjects\\discover6\\src\\img\\"+i+".png"; // chemin d'accès relatif à l'image
        Image image = new Image(new File(imagePath).toURI().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(200); // ajuste la largeur à 100 pixels
        imageView.setFitHeight(150);
        

        Label nomLabel = new Label(event.getNomevent());
        nomLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        nomLabel.setPrefWidth(150);
        nomLabel.setMinWidth(150);
        nomLabel.setPrefHeight(50);
        nomLabel.setMinHeight(50);
        nomLabel.setAlignment(Pos.CENTER);

        String d = String.valueOf(event.getDateevent());
        Label dateLabel = new Label(d);
        dateLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        dateLabel.setPrefWidth(150);
        dateLabel.setMinWidth(150);
        dateLabel.setPrefHeight(40);
        dateLabel.setMinHeight(40);
        dateLabel.setAlignment(Pos.CENTER);
        
        String nbr = String.valueOf(event.getNbrplacevehicule());
        Label nbrLabel = new Label(nbr);
        nbrLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        nbrLabel.setPrefWidth(150);
        nbrLabel.setMinWidth(150);
        nbrLabel.setPrefHeight(40);
        nbrLabel.setMinHeight(40);
        nbrLabel.setAlignment(Pos.CENTER);
        
        String prix = String.valueOf(event.getPrixvehiculeevent());
        Label prixLabel = new Label(prix);
        prixLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        prixLabel.setPrefWidth(150);
        prixLabel.setMinWidth(150);
        prixLabel.setPrefHeight(40);
        prixLabel.setMinHeight(40);
        prixLabel.setAlignment(Pos.CENTER);


        vBox.getChildren().addAll(imageView,nomLabel, dateLabel,nbrLabel,prixLabel);

        return vBox;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<Colaborationevent> even = getData();
            vbox.setSpacing(300);

            HBox hbox = new HBox();
            hbox.setSpacing(150);
            hbox.setPadding(new Insets(0, 0, 0, 0));
            int i=0;
            for (Colaborationevent categorie : even) {
                VBox vBox = createVBox(categorie,categorie.getNomevent());
                i++;
                hbox.getChildren().add(vBox);
            }

            vbox.getChildren().add(hbox);
            scrol.setContent(vbox);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void showsEvents(ActionEvent event) {
    }
}
