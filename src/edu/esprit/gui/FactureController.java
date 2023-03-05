/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import edu.esprit.entities.FactureR;
import edu.esprit.entities.FormulaireR;
import edu.esprit.services.ServiceFacture;
import edu.esprit.services.ServiceFormulaire;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author faten
 */
public class FactureController implements Initializable {

    @FXML
    private Label infoFact;
    @FXML
    private VBox vbox;
    @FXML
    private AnchorPane an;
    @FXML
    private Button btnafficher;
    @FXML
    private ListView listfact;
    
    @FXML
    private Button btncode;
    @FXML
    private TextField tfnotes;
    @FXML
    private DatePicker datef;
    @FXML
    private ChoiceBox<String> bstatut;
    ObservableList<String> choice = FXCollections.observableArrayList("Payé");
    
    String statut = "payé";

    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bstatut.setItems(choice);
        
    }

    /*    ModalitePaiementController paiementController = new ModalitePaiementController(); // obtenir une référence au contrôleur de l'interface paiement

        Button btnPayer = paiementController.getBtnligne();
        Button btnFacture = paiementController.getBtnesp();

        btnPayer.setOnAction(event -> payerClicked());
        btnFacture.setOnAction(event -> factureClicked());
    }

    private void payerClicked() {
        tfstatut.setText("payé");
    }

    private void factureClicked() {
        tfstatut.setText("impayé");
    }
    
        */


/*  private void payerClicked() {
        ModalitePaiementController paiementController = new ModalitePaiementController(); // obtenir une référence au contrôleur de l'interface paiement
        Button btnligne = paiementController.getBtnligne();
        tfstatut.setText("payé");
    }

    private void factureClicked() {
        ModalitePaiementController paiementController = new ModalitePaiementController(); // obtenir une référence au contrôleur de l'interface paiement
        Button btnesp = paiementController.getBtnesp();
        tfstatut.setText("impayé");
    }
 */
public ListView getListfact() {
        return listfact;
    }

    public TextField getTfnotes() {
        return tfnotes;
    }
    
    

    @FXML
        private void Afficher(ActionEvent event) {
         
        //String statut = tfstatut.getText();
        String notes = tfnotes.getText();

        List<String> fact = new ArrayList();
        ServiceFacture sf = new ServiceFacture();
        FactureR fr = new FactureR();
        /* listfact.getItems();
        
        fact = (List) sf.getOnebyId(1);
        System.out.println(an);
        int x = 0, y = 0;
        // listevent.getItems().addAll(even);
        
            AnchorPane an1 = new AnchorPane();
            an1.setLayoutX(x);
            an1.setLayoutY(y);

            Label reservation = new Label(String.valueOf(fr.getFormulaire()));
            reservation.setLayoutX(x + 4);
            reservation.setLayoutY(y + 7);
            
            
              Button btnafficher = new Button("Affichage");

            an.getChildren().addAll(reservation);
            an.getChildren().addAll(an1);

            vbox.getChildren().add(an1);
         */

        listfact.getItems();
        //sf.getOnebyId(fr.getId_facture());

        fact.add(String.valueOf(sf.getOnebyId(fr.getId_facture())));
        String affichage = String.valueOf(sf.getOnebyId(fr.getId_facture()));
        System.out.println(an);
        int x = 0, y = 0;
        //info.setText(chargeString);
        //listPaiement.getItems();

        for (String s : fact) {

            AnchorPane an1 = new AnchorPane();
            an1.setLayoutX(x);
            an1.setLayoutY(y);

            Label infoFact = new Label(affichage);
            infoFact.setLayoutX(x + 7);
            infoFact.setLayoutY(y + 6);

            Button btnafficher = new Button("reservation");

            an.getChildren().addAll(infoFact);
            //an.getChildren().
            an.getChildren().addAll(an1);

            vbox.getChildren().add(an1);

        }

        // listPaiement.getItems();
        //Res = sf.getOnebyId(1);
        //Charge charge = Charge.create(chargeParams)
        //String chargeString = charge.toJson();
        //liste.add(chargeString);
        // System.out.println(an);
        //int x = 0, y = 0;
        // listevent.getItems().addAll(even);
    }
     
      /*  
        public String getStatut() {
        return statut;
        }
*/
   /* public ChoiceBox<String> getBstatut() {
        return bstatut;
    }
        
       */ 
  
    
    public String getStatut(){
        return "payé";
    } 
     
     
    @FXML
        private void directQRcode(ActionEvent event) throws IOException {
        String statut = bstatut.getValue();
        System.out.println(statut);
/*
            
        String statut = tfstatut.getText();
        LocalDate date_facture = datef.getValue();
        String notes = tfnotes.getText();
        
        
        FormulaireR selectedFeature = (FormulaireR) listfact.getSelectionModel().getSelectedItem();
        int selectedId = selectedFeature.getId();
        FactureR fact = new FactureR(date_facture, statut, notes, selectedId);
        
        //FormulaireR fr = new FormulaireR();
        //FormulaireR selectedFeature = (FormulaireR) listfact.getSelectionModel().getSelectedItem();
        //int selectedId = selectedFeature.getId();
        
        //FactureR fact = new FactureR(date_facture, statut, notes, fr);
        ServiceFacture sfact = new ServiceFacture();
        sfact.ajouter(fact);
*/
        FXMLLoader loader = new FXMLLoader(getClass().getResource("QRcode.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    

   
  
}
