/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.Categorie;
import edu.esprit.entities.Vehicule;
import edu.esprit.services.ServiceCategorie;
import edu.esprit.services.ServiceVehicule;
import java.io.File;
import javafx.geometry.Insets;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ClientController implements Initializable {

    private HBox hbox;
    @FXML
    private ScrollPane scrol;
    @FXML
    private VBox vbox;

    /**
     * Initializes the controller class.
     */
    // méthodes pour récupérer les données et créer les VBox
    
    private List<Categorie> getData() throws SQLException {
        ServiceCategorie sc = new ServiceCategorie();
        List<Categorie> categories = sc.getALL();
        return categories;
    }

    private VBox createVBox(Categorie categorie) {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(15);
        vBox.setPrefSize(150, 150);
        // Chargement de l'image
        String imagePath = "C:\\Users\\asus\\Documents\\NetBeansProjects\\PiDiskover\\src\\edu\\esprit\\img\\toyota.png"; // chemin d'accès relatif à l'image
        Image image = new Image(new File(imagePath).toURI().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(200); // ajuste la largeur à 100 pixels
        imageView.setFitHeight(150);

        Label marqueLabel = new Label(categorie.getMarque());
        marqueLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        marqueLabel.setPrefWidth(150);
        marqueLabel.setMinWidth(150);
        marqueLabel.setPrefHeight(50);
        marqueLabel.setMinHeight(50);
        marqueLabel.setAlignment(Pos.CENTER);

        Button reserverButton = new Button("Réserver");
        reserverButton.setOnAction(event -> {
            // code pour réserver la catégorie sélectionnée
        });
        reserverButton.setPrefSize(150, 40);
        reserverButton.setMinSize(150, 40);
        reserverButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        reserverButton.setStyle("-fx-background-color:  #3cba64;");

        // largeur et hauteur préférées de 100 pixels
        vBox.getChildren().addAll(imageView, marqueLabel, reserverButton);

        return vBox;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<Categorie> categories = getData();
            vbox.setSpacing(300);

            HBox hbox = new HBox(); // nouvelle HBox pour contenir les VBoxes
            hbox.setSpacing(150);//ESPCAE ENTRE LES VBOC
            hbox.setPadding(new Insets(0, 0, 0, 0)); // marge inférieure de 20 pixels

            for (Categorie categorie : categories) {
                VBox vBox = createVBox(categorie);
                hbox.getChildren().add(vBox);
            }

            vbox.getChildren().add(hbox); // ajouter la HBox à la VBox principale

            scrol.setContent(vbox);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

}
