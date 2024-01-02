package com.heshanthenura.ballsimulation.Components;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import java.util.logging.Logger;


public class Ball {

    private Circle ball;
    private AnchorPane background ;
    private Label xCoord;
    private Label yCoord;

    private Label kinEnergy;

    private Label potEnergy;
    Logger logger = Logger.getLogger("info");
    AnimationTimer dragAnimation ;
    double GRAVITY = 1;

    DoubleProperty VELOCITY = new SimpleDoubleProperty(0);
    double MASS =1;

    public Ball(AnchorPane background, Circle ball,Label xCoord,Label yCoord,Label kinEnergy,Label potEnergy) {

        this.background=background;
        this.ball = ball;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.kinEnergy = kinEnergy;
        this.potEnergy = potEnergy;

        coordinateLbl();
        energyLbl();

        ball.setOnMouseDragged(event -> {
            Platform.runLater(()->{
                ball.setLayoutY(event.getSceneY());
                ball.setLayoutX(event.getSceneX());
            });
        });

        ball.setOnMouseReleased(dragEvent -> {
            logger.info("Drag Stopped");
            gravity();
        });

        background.widthProperty().addListener(e->{
            gravity();
        });

        background.heightProperty().addListener(e->{
            gravity();
        });

    }
    public void gravity(){
        new AnimationTimer(){
            @Override
            public void handle(long l) {
                ball.setLayoutY(ball.getLayoutY()+(VELOCITY.getValue()+GRAVITY));
                VELOCITY.set(VELOCITY.doubleValue()+GRAVITY);
                if (((ball.getLayoutY()+ball.getRadius())>=background.getHeight()-25)){
                    ball.setLayoutY(background.getHeight()-25- ball.getRadius());
                    stop();
                    VELOCITY.set(0);
                }
            }
        }.start();
    }

    public void coordinateLbl(){
        DoubleProperty ballLayoutX = new SimpleDoubleProperty();
        DoubleProperty ballLayoutY = new SimpleDoubleProperty();
        ballLayoutX.bind(ball.layoutXProperty());
        ballLayoutY.bind(ball.layoutYProperty());
        xCoord.textProperty().bind( Bindings.format("%.2f",ballLayoutX));
        yCoord.textProperty().bind(Bindings.format("%.2f", ballLayoutY));

    }
    public void energyLbl(){
        DoubleProperty potentialEnergy = new SimpleDoubleProperty();
        DoubleProperty kineticEnergy = new SimpleDoubleProperty();

        // Binding for potential energy
        potentialEnergy.bind(
                Bindings.createDoubleBinding(
                        () -> ((background.getHeight() - (ball.getLayoutY() + ball.getRadius()) - 25)) * MASS * GRAVITY,
                        background.heightProperty(),
                        ball.layoutYProperty()
                )
        );


        potEnergy.textProperty().bind(Bindings.format("%.2f", potentialEnergy));
        kinEnergy.textProperty().bind(Bindings.format("%.2f", VELOCITY.multiply(VELOCITY).multiply(0.5).multiply(MASS)));
    }



}
