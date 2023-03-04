/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;

/**
 * FXML Controller class
 *
 * @author faten
 */
public class ImpayeQRcodeController implements Initializable {

    @FXML
    private ImageView QRimage;
    @FXML
    private Button btngenerer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

   /* private void generer(ActionEvent event) throws WriterException {

        //FactureController fc = new FactureController();
        ImpayeFactController ifc = new ImpayeFactController();

// String statutValue = ifc.getBstatut().getValue();
        int selectedIndex = ifc.getBstatut().getSelectionModel().getSelectedIndex();
        String statutValeur = ifc.getBstatut().getItems().get(selectedIndex);

        // Récupération des valeurs des champs à partir du contrôleur de vue
//String statutValue = ifc.getStatut();
//String notesValue = String.valueOf(fc.getTfnotes());
        String textToEncode = statutValeur;
        int width = 300;
        int height = 300;
        String charset = "UTF-8";

        BitMatrix bitMatrix = new QRCodeWriter().encode(textToEncode, BarcodeFormat.QR_CODE, width, height);

        BufferedImage qrCodeImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
        WritableImage image = SwingFXUtils.toFXImage(qrCodeImage, null); // convertir l'image BufferedImage en image JavaFX
        QRimage.setImage(image);

// Vous pouvez maintenant sauvegarder l'image ou l'afficher à l'utilisateur, selon vos besoins
    }
*/
    @FXML
    private void generate(ActionEvent event) throws WriterException {

        ImpayeFactController ifc = new ImpayeFactController();
        int selectedIndex = ifc.getBstatut().getSelectionModel().getSelectedIndex();
        String statutValeur = ifc.getBstatut().getItems().get(selectedIndex);
        String textToEncode = statutValeur;
        int width = 300;
        int height = 300;
        String charset = "UTF-8";

        BitMatrix bitMatrix = new QRCodeWriter().encode(textToEncode, BarcodeFormat.QR_CODE, width, height);

        BufferedImage qrCodeImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
        WritableImage image = SwingFXUtils.toFXImage(qrCodeImage, null); // convertir l'image BufferedImage en image JavaFX
        QRimage.setImage(image);

    }

}
