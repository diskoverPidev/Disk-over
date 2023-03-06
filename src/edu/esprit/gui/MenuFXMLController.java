/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class MenuFXMLController implements Initializable {

    @FXML
    private Button offrefront;
    @FXML
    private Button promotionfront;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void versOffre(MouseEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("OffreFXML.fxml"));
            Parent root = loader.load();
            promotionfront.getScene().setRoot(root);
            JOptionPane.showMessageDialog(null, "Welcome HOME !!");
        } catch (IOException ex) {
            Logger.getLogger(MenuFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
        
    }

    @FXML
    private void versPromotion(MouseEvent event) {
           try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("frontpromotion.fxml"));
            Parent root = loader.load();
            promotionfront.getScene().setRoot(root);
            JOptionPane.showMessageDialog(null, "Welcome HOME !!");
        } catch (IOException ex) {
            Logger.getLogger(MenuFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
}
