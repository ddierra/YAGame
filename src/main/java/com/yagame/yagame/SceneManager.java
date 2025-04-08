package com.yagame.yagame;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Set;

public class SceneManager extends Parent {

    // Start screen
    public static void startScreen(Stage primaryStage) {
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

    // Start game with camera-follow logic
    public static void startGame(Stage gameStage) {
        Group gameRoot = new Group();
        Pane gameWorld = new Pane();
        Render.renderRoad(gameWorld);
        gameWorld.setPrefSize(16000, 16000);
        gameRoot.getChildren().add(gameWorld);

        Player player = new Player(100, 100);
        Circle playerCircle = new Circle(player.getCenterX(), player.getCenterY(), 20);
        playerCircle.setFill(Color.color(Math.random(), Math.random(), Math.random()));
        gameWorld.getChildren().add(playerCircle);

        Jenny jenny = new Jenny(200, 200);
        Circle jennyCircle = new Circle(jenny.getCenterX(), jenny.getCenterY(), 20);
        jennyCircle.setFill(Color.DARKSLATEBLUE);
        gameWorld.getChildren().add(jennyCircle);

        Set<KeyCode> activeKeys = new HashSet<>();

        Scene scene = new Scene(gameRoot, 800, 600);

        scene.setOnKeyPressed(event -> activeKeys.add(event.getCode()));
        scene.setOnKeyReleased(event -> activeKeys.remove(event.getCode()));

        // Animation timer for smooth movement
        javafx.animation.AnimationTimer timer = new javafx.animation.AnimationTimer() {
            @Override
            public void handle(long now) {
                int step = 5;
                double nextX = player.getCenterX();
                double nextY = player.getCenterY();

                if (activeKeys.contains(KeyCode.UP)) nextY -= step;
                if (activeKeys.contains(KeyCode.DOWN)) nextY += step;
                if (activeKeys.contains(KeyCode.LEFT)) nextX -= step;
                if (activeKeys.contains(KeyCode.RIGHT)) nextX += step;

                // Boundary check
                if (nextX >= 0 && nextX <= 16000 && nextY >= 0 && nextY <= 16000) {
                    player.setCenterX((int) nextX);
                    player.setCenterY((int) nextY);
                    playerCircle.setCenterX(nextX);
                    playerCircle.setCenterY(nextY);

                    // Smooth camera movement
                    gameWorld.setTranslateX(-nextX + 400);
                    gameWorld.setTranslateY(-nextY + 300);
                }
            }
        };

        timer.start();

        gameWorld.setStyle("-fx-background-color: ALICEBLUE");
        gameStage.setScene(scene);
        gameStage.show();
        scene.setOnMouseClicked(e -> gameWorld.requestFocus());

    }
}
