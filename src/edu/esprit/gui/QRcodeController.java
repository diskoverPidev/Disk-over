/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import com.barcodelib.barcode.QRCode;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import java.awt.image.BufferedImage;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;


/**
 * FXML Controller class
 *
 * @author faten
 */
public class QRcodeController implements Initializable {

    @FXML
    private AnchorPane an;
    @FXML
    private Button btngenerer;
    @FXML
    private ImageView QRimage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void generer(ActionEvent event) throws WriterException {

        FactureController fc = new FactureController();
        

        /*     FXMLLoader loader = new FXMLLoader(getClass().getResource("votre_fichier.fxml"));
Parent root = loader.load();
MainController controller = loader.getController();
         */
// Récupération de l'ObservableList
/*List <String> listfact = fc.getListfact().getItems();
for (String item : listfact) {
    System.out.println(item);
}*/


      // Récupération des valeurs des champs à partir du contrôleur de vue
String statutValue = fc.getStatut();
//String notesValue = String.valueOf(fc.getTfnotes());

String textToEncode = statutValue;
int width = 300;
int height = 300;
String charset = "UTF-8";

BitMatrix bitMatrix = new QRCodeWriter().encode(textToEncode, BarcodeFormat.QR_CODE, width, height);

BufferedImage qrCodeImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
WritableImage image = SwingFXUtils.toFXImage(qrCodeImage, null); // convertir l'image BufferedImage en image JavaFX
QRimage.setImage(image);

// Vous pouvez maintenant sauvegarder l'image ou l'afficher à l'utilisateur, selon vos besoins



    }

}
