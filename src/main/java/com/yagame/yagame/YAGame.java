package com.yagame.yagame;

import javafx.application.Application;
import javafx.stage.Stage;

public class YAGame extends Application {

    @Override
    public void start(Stage primaryStage){
        SceneManager.startScreen(primaryStage);
    }
}