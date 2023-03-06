/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.Promotion;
import edu.esprit.utils.Datasource;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class FrontpromotionController implements Initializable {

    @FXML
    private Button btnback;
    @FXML
    private TextField search;
    @FXML
    private TableView<Promotion> TableView1;
    @FXML
    private TableColumn<Promotion, String> nomColumn;
    @FXML
    private TableColumn<Promotion, Integer> dureeColumn1;
    @FXML
    private TableColumn<Promotion, Integer> prixAVColumn;
    @FXML
    private TableColumn<Promotion, Integer> pourColumn;
    @FXML
    private TableColumn<Promotion, Integer> prixAPColumn11;

   Connection con;
   ObservableList<Promotion> PromotionsList;
    
    
    /**
     * Initializes the controller class.
     */
   
    public void initialize(URL url, ResourceBundle rb) {
       afficherPromotions();
    }    

    
    
    
    void afficherPromotions(){
            con=Datasource.getInstance().getConnection();
            PromotionsList = FXCollections.observableArrayList();
     
      try {
            String requete = "SELECT * FROM promotion o ";
            Statement st = Datasource.getInstance().getConnection()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                Promotion o = new Promotion();
                o.setid_promotion(rs.getInt("IdPro"));
                o.setnom_promotion(rs.getString("nomP"));
               o.setduree_promotion(rs.getInt("dureeP"));
               o.setprix_avant(rs.getInt("PrixAvant"));
               o.setpourcentage(rs.getInt("Pourcentage"));
               o.setprix_apres(rs.getInt("PrixApres"));
               o.setIdO(rs.getInt("idO"));
                
                
                PromotionsList.add(o);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
 
   nomColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getnom_promotion()));
    dureeColumn1.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getduree_promotion()));
     prixAVColumn.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getprix_avant()));
      pourColumn.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getpourcentage()));
       prixAPColumn11.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getprix_apres()));
        

  TableView1.setItems(PromotionsList);
 
   
  }
    
    
    
    
    
    
    
    
    
    
    @FXML
    private void gomenu(ActionEvent event) {
    }

    @FXML
    private void rechercheEq(MouseEvent event) {
    }

    @FXML
    private void selectO(MouseEvent event) {
    }
    
}
