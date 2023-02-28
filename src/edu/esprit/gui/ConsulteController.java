package edu.esprit.gui;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import edu.esprit.entities.Reclamation;
import edu.esprit.services.ServiceReclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class ConsulteController implements Initializable {

    @FXML
    private TextField typeRe1;

    @FXML
    private TextField objetRe1;

    @FXML
    private TextField messageRe1;

    @FXML
    private DatePicker dateRe1;

    @FXML
    private TableView<Reclamation> tableView;

    @FXML
    private TableColumn<Reclamation, String> typeRe;

    @FXML
    private TableColumn<Reclamation, String> objetRe;

    @FXML
    private TableColumn<Reclamation, String> messageRe;

    @FXML
    private TableColumn<Reclamation, Date> dateRe;

    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnMenu;
    @FXML
    private TextField searchid;
    @FXML
    private Button btnRechercher;
    private ObjectProperty<LocalDate> dateselectionne;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    public void initialize(URL url, ResourceBundle rb) {
        showsReclamation();
        dateselectionne = dateRe1.valueProperty();
    }
      @FXML
    private void getData(MouseEvent event) {
        Reclamation reclamation = tableView.getSelectionModel().getSelectedItem();
        if (reclamation != null) {
            typeRe1.setText(reclamation.getType());
            objetRe1.setText(reclamation.getObjet());
            messageRe1.setText(reclamation.getMessage());
//             dateRe1.setValue(reclamation.getDate());
            LocalDate localDate = dateselectionne.get();
            java.sql.Date date = java.sql.Date.valueOf(localDate);
        }
    }

    public void showsReclamation() {
        ServiceReclamation sr = new ServiceReclamation();
        List<Reclamation> reclamation = sr.getAll();

        List<Reclamation> tri_rec = sr.trier(reclamation);
        ObservableList<Reclamation> observableClients = FXCollections.observableList(tri_rec);

//        ObservableList<Reclamation> observableClients = FXCollections.observableList(reclamation);
        tableView.setItems(observableClients);
//        idRe.setCellValueFactory(new PropertyValueFactory<>("id"));
        typeRe.setCellValueFactory(new PropertyValueFactory<>("type"));
        objetRe.setCellValueFactory(new PropertyValueFactory<>("objet"));
        messageRe.setCellValueFactory(new PropertyValueFactory<>("message"));
        dateRe.setCellValueFactory(new PropertyValueFactory<>("date"));

    }

    @FXML
    private void supprimerListe(ActionEvent event) {
        ServiceReclamation sr = new ServiceReclamation();
        Reclamation t = tableView.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);// alert de confirmation suppression
        alert.setTitle("ALERT suppression");
        alert.setHeaderText(null);
        alert.setContentText(" VOULEZ VOUS SUPPRIMER la reclamation ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            sr.supprimer(t);

            JOptionPane.showMessageDialog(null, " rec supprimer ");
        } else {
            JOptionPane.showMessageDialog(null, "rec non supprimer ");
        }
        sr.getAll();
    }

  

    @FXML
    private void modifierReclamation(ActionEvent event) {
        ServiceReclamation sr = new ServiceReclamation();

        String lieu = typeRe1.getText();
        if (typeRe1.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText(" vérifier vos informations ");

            alert.showAndWait();
        } else {
            String e = typeRe1.getText();
            Reclamation t = tableView.getSelectionModel().getSelectedItem();
            t.setType(e);

            t.setObjet(objetRe1.getText());

            t.setMessage(messageRe1.getText());
            LocalDate localDate = dateselectionne.get();
            java.sql.Date date = java.sql.Date.valueOf(localDate);

            JOptionPane.showMessageDialog(null, "Reclamation modifiee !");
            sr.modifier(t);
            showsReclamation();
        }

    }

    @FXML
    private void retouMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Reclamation.fxml"));
        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) btnMenu.getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
    }

    @FXML
    private void rechercheR(ActionEvent event) {
        Alert alert = new Alert(AlertType.ERROR);
        if (searchid.getText().isEmpty()) {
            alert.setContentText("remplir le champ a rechercher");
            alert.showAndWait();
            return;
        }
        ServiceReclamation sr = new ServiceReclamation();
        List<Reclamation> reclamation = sr.getAll();
        List<Reclamation> rec_cherches = sr.rechercher(reclamation, searchid.getText());
        ObservableList<Reclamation> observableClients = FXCollections.observableList(rec_cherches);
        tableView.setItems(observableClients);

        typeRe.setCellValueFactory(new PropertyValueFactory<>("type"));
        objetRe.setCellValueFactory(new PropertyValueFactory<>("objet"));
        messageRe.setCellValueFactory(new PropertyValueFactory<>("message"));
        dateRe.setCellValueFactory(new PropertyValueFactory<>("date"));

    }

}
