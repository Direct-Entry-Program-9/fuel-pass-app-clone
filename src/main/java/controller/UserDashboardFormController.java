package controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import db.InMemoryDB;
import db.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import util.Navigation;
import util.Routes;

import java.io.IOException;

public class UserDashboardFormController {
    public AnchorPane pneDashboard;
    public ImageView imgQR;
    public Label lblQuota;
    public Label lblName;
    public Label lblNIC;
    public Label lblAddress;
    public Button btnDownload;
    public Button btnPrint;
    public Button btnLogOut;

    public void initialize() {
        Platform.runLater(pneDashboard::requestFocus);
    }

    public void setData(String nic) throws WriterException {
        User user = InMemoryDB.findUser(nic);
        lblName.setText(user.getFirstName() + " " + user.getLastName());
        lblAddress.setText(user.getAddress());
        lblNIC.setText(user.getNic());
        lblQuota.setText(user.getQuota() + " L (Weekly)");

        String plainSecret = user.getNic() + "-" + user.getFirstName();

        BitMatrix qrCode = new QRCodeWriter().encode(plainSecret, BarcodeFormat.QR_CODE, 150, 150);
        WritableImage image = new WritableImage(150, 150);
        PixelWriter pixelWriter = image.getPixelWriter();

        for (int y = 0; y < qrCode.getHeight(); y++) {
            for (int x = 0; x < qrCode.getWidth(); x++) {
                pixelWriter.setColor(x, y, qrCode.get(x, y) ? Color.BLACK : Color.WHITE);
            }
        }

        imgQR.setImage(image);
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.WELCOME);
    }

    public void btnPrintOnAction(ActionEvent actionEvent) {
    }

    public void btnDownloadOnAction(ActionEvent actionEvent) {
    }
}
