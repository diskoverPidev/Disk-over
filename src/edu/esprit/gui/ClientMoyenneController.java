/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.services.ServiceCategorie;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ClientMoyenneController implements Initializable {
  private HBox hbox;
    @FXML
    private ScrollPane scrol;
    @FXML
    private VBox vbox;
    private String type;

    /**
     * Initializes the controller class.
     */
    // méthodes pour récupérer les données et créer les VBox
     private List<edu.esprit.entities.Categorie> getData() throws SQLException {
        ServiceCategorie sc = new ServiceCategorie();
        List<edu.esprit.entities.Categorie> categories = sc.getALL(type);
        return categories;
    }

    private VBox createVBox(edu.esprit.entities.Categorie categorie, String i) {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(15);
        vBox.setPrefSize(150, 150);
        // Chargement de l'image
        String imagePath = "C:\\Users\\asus\\Documents\\NetBeansProjects\\PiDiskover final\\src\\edu\\esprit\\img\\"+i+".png"; // chemin d'accès relatif à l'image
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
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Categorie.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
            }
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
            String selectedType = "moyenne"; // Remplacez "haute" par le type sélectionné par l'utilisateur
            this.type = selectedType;

            List<edu.esprit.entities.Categorie> categories = getData();
            vbox.setSpacing(300);

            HBox hbox = new HBox(); // nouvelle HBox pour contenir les VBoxes
            hbox.setSpacing(150);//ESPCAE ENTRE LES VBOC
            hbox.setPadding(new Insets(0, 0, 0, 0)); // marge inférieure de 20 pixels
            int i=0;
            
            for (edu.esprit.entities.Categorie categorie : categories) {
                if (categorie.getType().equals(selectedType)) { // Remplacez "getType()" par la méthode qui retourne le type de la catégorie
                    VBox vBox = createVBox(categorie,categorie.getMarque());
                    i++;
                    hbox.getChildren().add(vBox);
                }
            }

            vbox.getChildren().add(hbox); // ajouter la HBox à la VBox principale

            scrol.setContent(vbox);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}

 
