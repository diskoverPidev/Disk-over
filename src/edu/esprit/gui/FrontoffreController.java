/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.Offre;
import edu.esprit.utils.Datasource;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Observable;
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
public class FrontoffreController implements Initializable {

    @FXML
    private Button btnback;
    @FXML
    private TableView<Offre> TableViewFrontoffre;
    @FXML
    private TableColumn<Offre, String> descColumn;
    @FXML
    private TableColumn<Offre, String> dureeColumn;
    @FXML
    private TextField search;

    ObservableList<Offre>offrelist;
    Connection con;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherOffres();
    }    

    
    void afficherOffres(){
            con=Datasource.getInstance().getConnection();
            offrelist = FXCollections.observableArrayList();
     
      try {
            String requete = "SELECT * FROM offre o ";
            Statement st = Datasource.getInstance().getConnection()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                Offre o = new Offre();
                o.setid_offre(rs.getInt("idO"));
                o.setdescription_offre(rs.getString("DescO"));
                o.setduree_offre(rs.getString("DureeO"));
                
                
                offrelist.add(o);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
  
  descColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getdescription_offre()));
  dureeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getduree_offre()));
  
 
  TableViewFrontoffre.setItems(offrelist);
 
 
 
  }
    
    
    
    
    
    
    
    
    
    
    @FXML
    private void gomenu(ActionEvent event) {
    }

    @FXML
    private void selectO(MouseEvent event) {
    }

    @FXML
    private void rechercheEq(MouseEvent event) {
    }
    
}
