/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import edu.esprit.gui.TwilioSMS;
import javafx.event.ActionEvent;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
/**
 * FXML Controller class
 *
 * @author abidi
 */
public class ApiSmsController implements Initializable {

    @FXML
    private TextField smsText;

    @FXML
    private TextField numText;

    @FXML
    private Button btnEnvoyer;
    
 public  final String ACCOUNT_SID = "";
  public  final String AUTH_TOKEN = "";
    
    public void sendMessage(String assoName,String titlePost){
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    Message message = Message.creator(
      new com.twilio.type.PhoneNumber(""),
      "MG5c01c879f4f2b9148bd6a3efe7fdd3c9",
      "Bonjour, l'association "+assoName +" vient de publier un nouvel Post de don intitulé "+titlePost+".")
    .create();

    System.out.println(message.getSid());
    }
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }


    @FXML
    void envoyerSMS(ActionEvent event) {
        String message = smsText.getText();
        String numero = numText.getText();
        TwilioSMS.envoyerSMS(message, numero);

    }
}
