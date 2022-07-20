package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

public class SplashScreenFormController {
    public Label lblStatus;
    public Rectangle pgbContainer;
    public Rectangle pgbLoad;

    public void initialize(){
        Timeline tl = new Timeline();
        var frame1 = new KeyFrame(Duration.millis(250), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lblStatus.setText("Connecting with the database...!");
                pgbLoad.setWidth(pgbLoad.getWidth() + 25);
            }
        });
        var frame2 = new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lblStatus.setText("Loading data...!");
                pgbLoad.setWidth(pgbLoad.getWidth() + 30);
            }
        });
        var frame3 = new KeyFrame(Duration.millis(750), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lblStatus.setText("Setting up the UI");
                pgbLoad.setWidth(pgbLoad.getWidth() + 25);
            }
        });
        var frame4 = new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    pgbLoad.setWidth(pgbContainer.getWidth());
                    URL res = this.getClass().getResource("/view/HomeForm.fxml");
                    Parent container = FXMLLoader.load(res);
                    Scene scene = new Scene(container);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("National Fuel Pass App");
                    stage.show();
                    stage.centerOnScreen();
                    lblStatus.getScene().getWindow().hide();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        tl.getKeyFrames().addAll(frame1, frame2, frame3, frame4);
        tl.playFromStart();
    }
}
