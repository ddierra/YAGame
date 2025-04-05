package com.yagame.yagame;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class SceneManager extends Parent {

    //Start screen
    public static void startScreen(Stage primaryStage){
        Pane startScreen = new Pane();
        Button startButton = new Button("Start Game");
        Button exitButton = new Button("Quit Game");


        VBox vBox = new VBox(startButton, exitButton);
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane(startScreen);
        borderPane.setCenter(vBox);

        startButton.setOnAction(e -> {
            startGame(primaryStage);
        });

        exitButton.setOnAction(e -> {
            System.exit(0);
        });

        Scene scene = new Scene(borderPane, 800, 600);
        primaryStage.setTitle("You're Annoying!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //start game with player movements and visual circle
    public static void startGame(Stage gameStage) {
        Pane gameScreen = new Pane();
        Player player = new Player(100, 100);
        Circle playerCircle = new Circle(player.getCenterX(), player.getCenterY(),20);
        playerCircle.setFill(Color.color(Math.random(), Math.random(), Math.random()));
        gameScreen.getChildren().add(playerCircle);

        Jenny jenny = new Jenny(200, 200);
        Circle jennyCircle = new Circle(jenny.getCenterX(), jenny.getCenterY(),20);
        jennyCircle.setFill(Color.DARKSLATEBLUE);
        gameScreen.getChildren().add(jennyCircle);

        gameScreen.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case UP -> player.moveUp(20);
                case DOWN -> player.moveDown(20);
                case LEFT -> player.moveLeft(20);
                case RIGHT -> player.moveRight(20);
            }
            playerCircle.setCenterX(player.getCenterX());
            playerCircle.setCenterY(player.getCenterY());
        });

        gameScreen.setStyle("-fx-background-color: ALICEBLUE");
        Scene scene = new Scene(gameScreen, 800, 600);
        gameStage.setScene(scene);
        gameStage.show();
        gameScreen.requestFocus();
    }
}
