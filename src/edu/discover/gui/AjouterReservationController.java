/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.discover.gui;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import edu.discover.entities.Colaborationevent;
import edu.discover.entities.Reservationevent;
import edu.discover.services.Crudcolaboration;
import edu.discover.services.Crudreservation;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ramzi
 */
public class AjouterReservationController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField nbrp;
    @FXML
    private Button reserver;

    @FXML
    private Button retourr;
    @FXML
    private Label mylabel;
    @FXML
    private ChoiceBox<String> mychoixbox;
    //private final String[] event = {"RAMZI", "jouini", "ahlem", "bochra", "kamilia", "housem"};
    @FXML
    private Button pdf;

    @FXML
    private void listeres(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Evenement.fxml"));
        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) retourr.getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        Crudcolaboration cr=new Crudcolaboration();
        List<String> even =cr.getNom();
        ObservableList<String> observableColaborationevents = FXCollections.observableList(even);
        mychoixbox.setItems(observableColaborationevents);
        mychoixbox.getSelectionModel().select(0);
        
//        mychoixbox.getItems().addAll(event);
//        mychoixbox.setOnAction(this::getEvent);

    }

    public void getEvent(ActionEvent event) {
     
//        String myEvent = mychoixbox.getValue();
//        mylabel.setText(myEvent);

    }

    @FXML
    private void add(ActionEvent event) {
        
        String nomclient = nom.getText();
        int nbrclient = Integer.parseInt(nbrp.getText());//Integer.parseInt pour forcer la conversion en int
        //String Colaborationevent = mychoixbox.getValue().getNomevent();
        String Colaborationevent = mychoixbox.getValue();

        //mena
        if(Colaborationevent.isEmpty()){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("Nom event doit etre remplir !!");
        alert.showAndWait();
        }else{
                Reservationevent c = new Reservationevent();
                c.setNomclient(nomclient);
                c.setNbrclient(nbrclient);
                c.setNomevnet(Colaborationevent);
                Crudreservation cc = new Crudreservation();
                cc.ajout(c);

        }
        
        
        
        
        
//        Reservationevent c = new Reservationevent(nomclient, nbrclient, nomev);
//        Crudreservation cc = new Crudreservation();
//        cc.ajout(c);
//
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Event Registration");
//        alert.setHeaderText("Event Registration");
//        alert.setContentText("Event Added!");
//        alert.showAndWait();

    }

    
    
    
    
    
    
    
    
    
    private void addEmptyLine(Document document, int number) throws DocumentException {
        for (int i = 0; i < number; i++) {
            Paragraph p = new Paragraph(" ");
            document.add(p);
        }
    }
    
    
    
    @FXML
    private void listepdf(ActionEvent event) {
        
        Document document = new Document(); //cration de l'instance document
        try {
            PdfWriter.getInstance(document,
                    new FileOutputStream("C:\\Users\\ramzi\\OneDrive\\Documents\\NetBeansProjects\\discover6\\Reservation.pdf"));
            // creation de outputstream instance et pdfwriter instance
            document.open();
            document.add(new Paragraph(" PLATEFORME DISCOVER "));
            document.add(new Paragraph(" -------------------------------------- "));
            
            //IMAGES
            Image img1 = Image.getInstance("C:\\Users\\ramzi\\OneDrive\\Documents\\NetBeansProjects\\discover6\\src\\img\\logo.png");

            img1.setAbsolutePosition(450, 790);
            img1.scaleToFit(130, 150);
            addEmptyLine(document, 1); // utiliser la méthode addEmptyLine pour les paragraphes
            document.add(img1);
            
            Image img2 = Image.getInstance("C:\\Users\\ramzi\\OneDrive\\Documents\\NetBeansProjects\\discover6\\src\\img\\sign.png");
            img2.setAbsolutePosition(450, 190);
            img2.scaleToFit(130, 150);
            addEmptyLine(document, 1); // utiliser la méthode addEmptyLine pour les paragraphes
            document.add(img2);
            
//            Image img3 = Image.getInstance("C:\\Users\\ramzi\\OneDrive\\Documents\\NetBeansProjects\\discover6\\src\\img\\titre.png");
//            img3.setAbsolutePosition(150, 590);
//            img3.scaleToFit(230, 350);
//            addEmptyLine(document, 1); // utiliser la méthode addEmptyLine pour les paragraphes
//            document.add(img3);
            
            

//            Image img = Image.getInstance("C:\\Users\\ASUS\\OneDrive\\Documents\\NetBeansProjects\\PIDev\\logoartisty.PNG");
//            img.scaleAbsoluteWidth(90);
//            img.scaleAbsoluteHeight(90);
//            img.setAlignment(Image.ALIGN_RIGHT);
//            
//            document.add(img);

            Font font = new Font(Font.FontFamily.TIMES_ROMAN, 28, Font.UNDERLINE, BaseColor.BLACK);
            Paragraph p = new Paragraph(" Votre Ticket De Réservation  ", font);
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);

            Crudcolaboration sr = new Crudcolaboration();
            Crudcolaboration se = new Crudcolaboration();
            Colaborationevent r = sr.AffichagePDF();
//            Events e = new Events();
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Cher Client, "+nom.getText()+"" ));
            document.add(new Paragraph("Vous avez reservé " +nbrp.getText() +" Place(s) dans l'evenement : " + mychoixbox.getValue() + " "));
            document.add(new Paragraph("Cordialement ."));
            

            // Ajouter un nouveau paragraphe au document
            document.add(new Paragraph(" "));

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("L'opération a été effectuée avec succès !");
            alert.showAndWait();
            document.close();

            Desktop.getDesktop().open(new File("C:\\Users\\ramzi\\OneDrive\\Documents\\NetBeansProjects\\discover6\\Reservation.pdf"));
            
            

        } catch (DocumentException | IOException e) {

            System.out.println("ERROR PDF");

            System.out.println(e.getMessage());

        }
    }

}
