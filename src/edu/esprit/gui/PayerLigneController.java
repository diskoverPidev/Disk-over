/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import com.stripe.Stripe;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import edu.esprit.entities.FormulaireR;
import edu.esprit.services.ServiceFormulaire;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import edu.esprit.gui.FactureController;

/**
 * FXML Controller class
 *
 * @author faten
 */
public class PayerLigneController implements Initializable {

    @FXML
    private TextField tfmontant;
    @FXML
    private TextField tfsource;
    @FXML
    private TextField tfdevice;
    @FXML
    private Button btnpayer;
    @FXML
    private Label info;
    @FXML
    private ListView<String> listPaiement;
    @FXML
    private VBox vbox;
    @FXML
    private AnchorPane an;
    @FXML
    private Button btnfacture;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
     //  FactureController fc = new FactureController();
     
    
       
    
 

        // TODO
    }

    @FXML
    private void payer(ActionEvent event) {

        int montant = Integer.valueOf( tfmontant.getText());
        String devise = tfdevice.getText();
        String source = tfsource.getText();
         List<String> liste = new ArrayList();

        // Set your API key
        Stripe.apiKey = "sk_test_51Mdev4BkXviPD7IqckPNDsrV8KvxnSi8o6VEu8H8VOZBIxTnvVlCn9peUUOAIFo94JTfZHUhuSnh2LPnx1PEOqvj00Bj9fl98o";

        // Create a map with the parameters for the charge
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", montant); // amount in cents
        chargeParams.put("currency", devise);
        chargeParams.put("source", source); // replace with your test token

        try {
            //Charge charge = Charge.create(chargeParams);
            listPaiement.getItems();
            Charge charge = Charge.create(chargeParams);
            String chargeString = charge.toJson();
            liste.add(chargeString);
            System.out.println(an);
            int x = 0, y = 0;
            //info.setText(chargeString);
            //listPaiement.getItems();
            
            for (String s : liste) {

            AnchorPane an1 = new AnchorPane();
            an1.setLayoutX(x);
            an1.setLayoutY(y);

            Label info = new Label(chargeString);
            info.setLayoutX(x + 7);
            info.setLayoutY(y + 6); 
            
            
            Button btnpayer = new Button("paiement");

            an.getChildren().addAll(info);
            //an.getChildren().
            an.getChildren().addAll(an1);

            vbox.getChildren().add(an1);

    }
            
            Platform.runLater(() -> info.setText(chargeString));

           // System.out.println(charge);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } /*catch (APIConnectionException e) {
            e.printStackTrace();
        }*/ catch (CardException e) {
            e.printStackTrace();
        }/* catch (APIException e) {
            e.printStackTrace();
        } */ catch (StripeException ex) {
            System.err.println(ex.getMessage());
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

    @FXML
    private void diretfacture(ActionEvent event) throws IOException {
    Button btnfacture = new Button("facture");    
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Facture.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
}