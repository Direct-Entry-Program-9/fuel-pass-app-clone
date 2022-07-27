package controller;

import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;

public class UserDashboardFormController {
    public AnchorPane pneDashboard;

    public void initialize(){
        Platform.runLater(pneDashboard::requestFocus);
    }
}
