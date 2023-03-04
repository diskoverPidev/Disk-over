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
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import edu.discover.entities.Colaborationevent;
import edu.discover.services.Crudcolaboration;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author ramzi
 */
public class AffichereservationController implements Initializable {

    @FXML
    private TextField nomi;
    @FXML
    private TextField adressei;
    @FXML
    private TextField nbri;
    @FXML
    private TextField prixi;
    @FXML
    private DatePicker datei;

    @FXML
    private Button btnadd;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;

    @FXML
    private Button btnaffiche;
    @FXML
    private Button lister;

    @FXML
    private TextField search;
    @FXML
    private AnchorPane ev;
    private ListView<?> listviewevent;
    @FXML
    private VBox vbox;
    @FXML
    private Button pdf;

    @FXML
    private void listeres(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeReservation.fxml"));
        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) lister.getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
    }

    /**
     *
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //actualiser chaque 5sec 
        Timeline timeline = new Timeline(
    new KeyFrame(Duration.millis(5000), e -> showsEvent(null))
);
timeline.setCycleCount(Animation.INDEFINITE);
timeline.play();

    }

    private void GoResrvationListe(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) lister.getScene().getWindow();
        currentStage.hide();
        Parent root = FXMLLoader.load(getClass().getResource("ListeReservation.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
   
public void showsEvent(ActionEvent event) {

    List<Colaborationevent> even = new ArrayList();
    Crudcolaboration se = new Crudcolaboration();

    even = se.getAll();
    System.out.println(ev);

    // clear the vbox before displaying the new elements
    vbox.getChildren().clear();

    int x = 0, y = 0;
    for (Colaborationevent e : even) {
        AnchorPane an = new AnchorPane();
        an.setLayoutX(x);
        an.setLayoutY(y);

        Label name = new Label(e.getNomevent());
        name.setLayoutX(x + 14);
        name.setLayoutY(y + 22);
        String d = String.valueOf(e.getDateevent());
        Label date = new Label(d);
        date.setLayoutX(x + 135);
        date.setLayoutY(y + 22);
        Label location = new Label(e.getAdresseevent());
        location.setLayoutX(x + 214);
        location.setLayoutY(y + 22);
        String u = String.valueOf(e.getNbrplacevehicule());
        Label nbr = new Label(u);
        nbr.setLayoutX(x + 312);
        nbr.setLayoutY(y + 22);

        String nb = String.valueOf(e.getPrixvehiculeevent());
        Label prix = new Label(nb);
        prix.setLayoutX(x + 446);
        prix.setLayoutY(y + 22);

        InputStream imgStream = getClass().getResourceAsStream("/img/update.png");
        Image img = new Image(imgStream, 25, 25, false, false);
        ImageView imv = new ImageView(img);
        imv.setOnMouseClicked(MouseEvent ->se.modifier(e, u) );
        imv.setLayoutX(x + 518);
        imv.setLayoutY(y + 22);

        Button btnaffiche = new Button("Affichage");

        an.getChildren().addAll(name, date, location, nbr, prix,imv);
        ev.getChildren().addAll(an);

        vbox.getChildren().add(an);
    }
}


    @FXML
    private void add(ActionEvent event) {

        String nomevent = nomi.getText();
        Date dateevent = Date.valueOf(datei.getValue());
        String adresseevent = adressei.getText();
        int nbrplacevehicule = Integer.parseInt(nbri.getText());//Integer.parseInt pour forcer la conversion en int
        int prixvehiculeevent = Integer.parseInt(prixi.getText());

        Colaborationevent c = new Colaborationevent(nomevent, dateevent, adresseevent, nbrplacevehicule, prixvehiculeevent);
        Crudcolaboration cc = new Crudcolaboration();
        cc.ajout(c);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Event Registation");

        alert.setHeaderText("Event Registation");
        alert.setContentText("Event Added!");

        alert.showAndWait();

    }

    @FXML
    private void update(ActionEvent event) {
        String nomevent = nomi.getText();
        Date dateevent = Date.valueOf(datei.getValue());
        String adresseevent = adressei.getText();
        int nbrplacevehicule = Integer.parseInt(nbri.getText());
        int prixvehiculeevent = Integer.parseInt(prixi.getText());

        Colaborationevent c = new Colaborationevent(nomevent, dateevent, adresseevent, nbrplacevehicule, prixvehiculeevent);
        Crudcolaboration cc = new Crudcolaboration();
        cc.modifier(c, search.getText());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Event Update");
        alert.setHeaderText("Event Update");
        alert.setContentText("Event Updated!");
        alert.showAndWait();

    }

    @FXML
    private void delete(ActionEvent event) {
        String nomevent = nomi.getText();
        Colaborationevent c = new Colaborationevent();
        Crudcolaboration cc = new Crudcolaboration();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Event Deletion");
        alert.setHeaderText("Event Deletion");
        alert.setContentText("Are you sure you want to delete the event?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                cc.supprimer(search.getText());
                nomi.setText("");
                adressei.setText("");
                nbri.setText("");
                prixi.setText("");
                datei.setValue(null);
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Event Deletion");
                alert1.setHeaderText("Event Deletion");
                alert1.setContentText("Event Deleted!");
                alert1.showAndWait();
            }
        });
    }

    
    
    
    
    
    @FXML
    private void listepdf(ActionEvent event) throws FileNotFoundException, DocumentException, SQLException, IOException {
     
         Document document = new Document(); //cration de l'instance document
        try {
            PdfWriter.getInstance(document,
                    new FileOutputStream("C:\\Users\\ramzi\\OneDrive\\Documents\\NetBeansProjects\\discover6\\evenement.pdf"));
            // creation de outputstream instance et pdfwriter instance
            document.open();
            document.add(new Paragraph(" PLATEFORME DISCOVER "));
            document.add(new Paragraph(" ------------------------- "));

//            Image img = Image.getInstance("C:\\Users\\ASUS\\OneDrive\\Documents\\NetBeansProjects\\PIDev\\logoartisty.PNG");
//            img.scaleAbsoluteWidth(90);
//            img.scaleAbsoluteHeight(90);
//            img.setAlignment(Image.ALIGN_RIGHT);
//            
//            document.add(img);

            Font font = new Font(Font.FontFamily.TIMES_ROMAN, 28, Font.UNDERLINE, BaseColor.BLACK);
            Paragraph p = new Paragraph(" ticket de réservation  ", font);
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);

            Crudcolaboration sr = new Crudcolaboration();
            Crudcolaboration se = new Crudcolaboration();
            Colaborationevent r = sr.AffichagePDF();
//            Events e = new Events();
            document.add(new Paragraph("cher client  "));
            document.add(new Paragraph("vous avez reservé dans cette events " + sr.AffichagePDF() + " "));
            //document.add(pdfPTable);

            // Ajouter un nouveau paragraphe au document
            document.add(new Paragraph(" "));

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("L'opération a été effectuée avec succès !");
            alert.showAndWait();
            document.close();

            Desktop.getDesktop().open(new File("C:\\Users\\ramzi\\OneDrive\\Documents\\NetBeansProjects\\discover6\\evenement.pdf"));

        } catch (DocumentException | IOException e) {

            System.out.println("ERROR PDF");

            System.out.println(e.getMessage());

        }
    
    }

}
