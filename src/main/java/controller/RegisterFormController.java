package controller;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;

public class RegisterFormController {
    public AnchorPane pneRegisterForm;
    public TextField txtNIC;
    public Label lblNicStatus;
    public TextField txtFirstName;
    public TextField txtLastName;
    public TextField txtAddress;
    public Button btnRegister;

    private void setDisableCmp(boolean disable){
        txtFirstName.setDisable(disable);
        txtLastName.setDisable(disable);
        txtAddress.setDisable(disable);
        btnRegister.setDisable(disable);
    }

    public void initialize(){
        Platform.runLater(txtNIC::requestFocus);
        txtNIC.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue,
                                String oldText, String currentText) {

                setDisableCmp(true);
                if (currentText.isBlank()){
                    lblNicStatus.setText("Please enter valid NIC Number to proceed");
                    lblNicStatus.setTextFill(Color.BLACK);
                }else{
                    if (isValidNIC(currentText)){
                        lblNicStatus.setText("Valid NIC ✅");
                        lblNicStatus.setTextFill(Color.GREEN);
                        setDisableCmp(false);
                    }else{
                        lblNicStatus.setText("Invalid NIC ❌");
                        lblNicStatus.setTextFill(Color.RED);
                    }
                }
            }
        });
    }

    private boolean isValidNIC(String input){
        if (input.length() != 10) return false;
        if (!(input.endsWith("v") || input.endsWith("V"))) return false;
        if (!input.substring(0, 9).matches("\\d+")) return false;
        return true;
    }

    public void lblLoginOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/LoginForm.fxml");
        AnchorPane loginForm = FXMLLoader.load(resource);
        AnchorPane pneContainer = (AnchorPane) pneRegisterForm.getParent();
        pneContainer.getChildren().clear();
        pneContainer.getChildren().add(loginForm);
        AnchorPane.setTopAnchor(loginForm, 0.0);
        AnchorPane.setRightAnchor(loginForm, 0.0);
        AnchorPane.setBottomAnchor(loginForm, 0.0);
        AnchorPane.setTopAnchor(loginForm, 0.0);
    }
}
