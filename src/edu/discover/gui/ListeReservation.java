/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.discover.gui;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ramzi
 */
public class ListeReservation extends Application{
    public void start(Stage primaryStage) {
        try {
            // appel de fichier qui regroupe les ellement graphique
            Parent root =FXMLLoader.load(getClass().getResource("ListeReservation.fxml"));
            Scene scene = new Scene(root);
            
            primaryStage.setTitle("Liste Reservation !");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
                System.out.println(ex.getMessage());
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}