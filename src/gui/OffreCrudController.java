/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class OffreCrudController implements Initializable {

    @FXML
    private Button ajouteroffre;
    @FXML
    private Button deleteoffre;
    @FXML
    private TableView<?> TableView;
    @FXML
    private TableColumn<?, ?> descColumn;
    @FXML
    private TableColumn<?, ?> dureeColumn;
    @FXML
    private Button modifierroffre;
    @FXML
    private TextField txtduree;
    @FXML
    private TextField txtdesc;
    @FXML
    private TextField txtid;
    @FXML
    private TextField search;
    @FXML
    private Button btnpdf;
    @FXML
    private Button btnback;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterOffre(ActionEvent event) {
    }

    @FXML
    private void deleteOffre(ActionEvent event) {
    }

    @FXML
    private void selectO(MouseEvent event) {
    }

    @FXML
    private void Update(ActionEvent event) {
    }

    @FXML
    private void rechercheEq(MouseEvent event) {
    }

    @FXML
    private void pdfP(ActionEvent event) {
    }

    @FXML
    private void gomenu(ActionEvent event) {
    }
    
}
