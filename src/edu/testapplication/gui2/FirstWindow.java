/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.testapplication.gui2;

import java.awt.Image;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author lenovo
 */
public class FirstWindow extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
try{
    FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
  
 BorderPane borderPane = new BorderPane();
Parent root = loader.load();
//borderPane.setCenter(root);
 Scene scene = new Scene(root);
  primaryStage.setScene(scene);
 primaryStage.show();
         /*FXMLLoader loader= FXMLLoader.load(getClass().getResource("inscription.fxml")) ;
      
        Parent root =loader.load();
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("gestion d'utilisateur");
        primaryStage.setScene(scene);
        primaryStage.show();*/
    }catch (Exception e){
        System.out.println(e.getMessage());
    }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
