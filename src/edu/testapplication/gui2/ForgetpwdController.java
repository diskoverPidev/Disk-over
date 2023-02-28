/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.testapplication.gui2;

import edu.esprit.entities.Client;
import edu.esprit.services.Serviceclient;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.util.Properties;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class ForgetpwdController implements Initializable {

    @FXML
    private Button valider;
    @FXML
    private TextField emailid;
    @FXML
    private Button valider1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void validermail(ActionEvent event) throws NoSuchAlgorithmException {
         // Recipient's email ID needs to be mentioned.
        String to = emailid.getText() ; 

        // Sender's email ID needs to be mentioned
        String from = "jouinimohamed512@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        //properties.put("mail.smtp.port", "465");
        //properties.put("mail.smtp.ssl.enable", "true");
        //properties.put("mail.smtp.auth", "true");
        
    properties.put("mail.smtp.port", "587");
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.starttls.required", "true");
    properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("jouinimohamed512@gmail.com", "iuurgveclizmbjyi");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            // Now set the actual message
                Serviceclient sc= new Serviceclient() ;
                Client c = new Client() ; 
                c.setEmail(emailid.getText());
                String ch =generateRandomPassword() ; 
                c.setPwd(ch) ; 
                
                sc.modifierpwd(c) ; 
                
                
            
            message.setText(ch);

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }
    public static String generateRandomPassword() {
    SecureRandom random = new SecureRandom();
    byte[] passwordBytes = new byte[12];
    random.nextBytes(passwordBytes);
    String password = Base64.getEncoder().encodeToString(passwordBytes);
    return password;
}

    @FXML
    private void gotosignin(ActionEvent event) throws IOException {
        Parent signup = FXMLLoader.load(getClass().getResource("inscription.fxml")) ; 
        Scene signupscene = new Scene (signup) ; 
        Stage appStage= (Stage)((Node)event.getSource()).getScene().getWindow() ; 
    appStage.setScene(signupscene) ; 
    appStage.show() ; 
    }
    
        
    }
    

