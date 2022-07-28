package controller;

import com.google.zxing.WriterException;
import db.InMemoryDB;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import util.Navigation;
import util.Routes;

import java.io.IOException;

public class LoginFormController {
    public TextField txtNIC;
    public Button btnLogin;

    public void initialize(){
        Platform.runLater(txtNIC::requestFocus);
    }

    public void lblRegisterOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.REGISTRATION);
    }

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException, WriterException {
        if (!RegisterFormController.isValidNIC(txtNIC.getText()) ||
                InMemoryDB.findUser(txtNIC.getText()) == null){
            new Alert(Alert.AlertType.ERROR, "Please enter a valid NIC to login").showAndWait();
            txtNIC.requestFocus();
        }else{
            UserDashboardFormController ctrl =
                    (UserDashboardFormController) Navigation.navigate(Routes.DASHBOARD);
            ctrl.setData(txtNIC.getText());
        }
    }
}
