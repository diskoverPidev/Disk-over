/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.Offre;
import edu.esprit.entities.Promotion;
import edu.esprit.services.IOffreService;
import edu.esprit.services.IPromotionService;
import edu.esprit.services.Serviceoffre;
import edu.esprit.services.Servicepromotion;
import edu.esprit.utils.Datasource;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class PromotionCrudController implements Initializable {

    @FXML
    private Button btnback;
    @FXML
    private Button deleteoffre;
    @FXML
    private Button modifierroffre;
    @FXML
    private Button ajouteroffre;
    @FXML
    private TextField search;
    @FXML
    private Button btnpdf;
    @FXML
    private TextField txtAvant;
    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtpour;
    @FXML
    private TextField txtApres;
    @FXML
    private TextField txtido;
    @FXML
    private TextField txtduree1;
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
    private ObservableList<Promotion> PromotionsList;
    private Connection con;
    @FXML
    private TableColumn<Promotion, Integer> idProcolumn;
    @FXML
    private TextArea idtxt;

    /**
     * Initializes the controller class.
     */
    @Override
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
  idProcolumn.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getid_promotion()));
   nomColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getnom_promotion()));
    dureeColumn1.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getduree_promotion()));
     prixAVColumn.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getprix_avant()));
      pourColumn.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getpourcentage()));
       prixAPColumn11.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getprix_apres()));
        

  TableView1.setItems(PromotionsList);
 
  search();
 
  }
 
  public void refresh(){
            PromotionsList.clear();
            con=Datasource.getInstance().getConnection();
            PromotionsList = FXCollections.observableArrayList();
     
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
  idProcolumn.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getid_promotion()));
   nomColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getnom_promotion()));
    dureeColumn1.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getduree_promotion()));
     prixAVColumn.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getprix_avant()));
      pourColumn.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getpourcentage()));
       prixAPColumn11.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getprix_apres()));
        

  TableView1.setItems(PromotionsList);
  
  search();
  }
    
    
    
    
    
    
    
    @FXML
    private void gomenu(ActionEvent event) {
    }

    @FXML
    private void deleteOffre(ActionEvent event) {
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Warning");
       alert.setContentText("Es-tu sûre de modifier!");

        String Value1 = idtxt.getText();
        String duree =txtduree1.getText();
        String pourcentage =txtpour.getText();
          String nom = txtnom.getText();
        String apres =txtApres.getText();
        String avant =txtAvant.getText();
         String ido =txtido.getText();
        
        
       
       
        Optional<ButtonType>result =  alert.showAndWait();
        if(result.get() == ButtonType.OK)
        {
     
         Promotion o= new Promotion(Integer.parseInt(Value1),nom,Integer.parseInt(duree),Integer.parseInt(avant),Integer.parseInt(pourcentage),Integer.parseInt(apres),Integer.parseInt(ido));
        IPromotionService es= new Servicepromotion();
        es.supprimerPromotion(o);
        refresh();
     
        idtxt.setText(null);
        txtduree1.setText(null);
        txtpour.setText(null);
        txtnom.setText(null);
        txtApres.setText(null);
        txtAvant.setText(null);
        txtido.setText(null);
        }
    }

    @FXML
    private void Update(ActionEvent event) {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Warning");
       alert.setContentText("Es-tu sûre de modifier!");

        String Value1 = idtxt.getText();
        String duree =txtduree1.getText();
        String pourcentage =txtpour.getText();
          String nom = txtnom.getText();
        String apres =txtApres.getText();
        String avant =txtAvant.getText();
         String ido =txtido.getText();
        
        
       
       
        Optional<ButtonType>result =  alert.showAndWait();
        if(result.get() == ButtonType.OK)
        {
     
         Promotion o= new Promotion(Integer.parseInt(Value1),nom,Integer.parseInt(duree),Integer.parseInt(avant),Integer.parseInt(pourcentage),Integer.parseInt(apres),Integer.parseInt(ido));
        IPromotionService es= new Servicepromotion();
        es.modifierPromotion(o);
        refresh();
     
        idtxt.setText(null);
        txtduree1.setText(null);
        txtpour.setText(null);
        txtnom.setText(null);
        txtApres.setText(null);
        txtAvant.setText(null);
        txtido.setText(null);
        
        
       
        
        
        } 
        
    }

    @FXML
    private void ajouterOffre(ActionEvent event) {
         
        String duree =txtduree1.getText();
        String pourcentage =txtpour.getText();
          String nom = txtnom.getText();
        String apres =txtApres.getText();
        String avant =txtAvant.getText();
         String ido =txtido.getText();
         
           if( duree.isEmpty() || pourcentage.isEmpty()  || nom.isEmpty()|| pourcentage.isEmpty()  || apres.isEmpty()|| avant.isEmpty()  || ido.isEmpty())
     {
     Alert alert = new Alert(Alert.AlertType.ERROR);
     alert.setContentText("champs vide !!");
     alert.showAndWait();
     
     }   else {
               
   
         Promotion o= new Promotion(nom,Integer.parseInt(duree),Integer.parseInt(avant),Integer.parseInt(pourcentage),Integer.parseInt(apres),Integer.parseInt(ido));
        IPromotionService es= new Servicepromotion();
        es.ajouterPromotion(o);
        refresh();
           
           
           }
         
         
         
         
         
         
         
        
        
    }

    @FXML
    private void rechercheEq(MouseEvent event) {
    }

   
    
    
    @FXML
    private void pdfP(ActionEvent event) {
    }

    @FXML
    private void selectO(MouseEvent event) {
         Promotion clicked = TableView1.getSelectionModel().getSelectedItem();
      idtxt.setText(String.valueOf(clicked.getid_promotion()));
        txtduree1.setText(String.valueOf(clicked.getduree_promotion()));
        txtpour.setText(String.valueOf(clicked.getpourcentage()));
        
         txtnom.setText(String.valueOf(clicked.getnom_promotion()));
        txtApres.setText(String.valueOf(clicked.getprix_apres()));
        txtAvant.setText(String.valueOf(clicked.getprix_avant()));
         txtido.setText(String.valueOf(clicked.getIdO()));
       
    }
    

  private void search() {      
        FilteredList<Promotion>filteredData = new FilteredList<>(PromotionsList, b->true);
        search.textProperty().addListener((observable, oldValue, newValue)->{
            filteredData.setPredicate(Promotion->{
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
               
                String lowerCaseFilter = newValue.toLowerCase();
                 if(String.valueOf(Promotion.getnom_promotion()).indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                  else if(String.valueOf(Promotion.getpourcentage()).indexOf(lowerCaseFilter) != -1){
                    return true;
                }else{
                return false;
                }
            });          
        });
        SortedList<Promotion>sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(TableView1.comparatorProperty());
        TableView1.setItems(sortedData);
    }










}
