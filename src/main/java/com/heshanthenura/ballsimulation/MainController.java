package com.heshanthenura.ballsimulation;

import com.heshanthenura.ballsimulation.Components.Ball;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private AnchorPane background;

    @FXML
    private Circle ball;

    @FXML
    private AnchorPane bottomBorder;

    @FXML
    private Label xCoord;

    @FXML
    private Label yCoord;

    @FXML
    private Label kinEnergy;

    @FXML
    private Label potEnergy;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(()->{
            new Ball(background,ball,xCoord,yCoord,kinEnergy,potEnergy);
        });
    }
}