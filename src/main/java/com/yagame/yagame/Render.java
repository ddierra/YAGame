package com.yagame.yagame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class Render {

    //Road Renderer
    public static void renderRoad(Pane world){
        Image roadTile = new Image(Render.class.getResource("/images/Road.png").toExternalForm());
        int tileSize = 64;
        int roadWidth = 10;
        int roadLength = 100;

        for (int y = 0; y < roadLength; y++) {
            for (int x = 0; x < roadWidth; x++) {
                ImageView tile  = new ImageView(roadTile);
                tile.setFitWidth(tileSize);
                tile.setFitHeight(tileSize);
                tile.setX(x * tileSize);
                tile.setY(y * tileSize);
                world.getChildren().add(tile);
            }
        }
    }


    public static ImageView renderJP(GameCharacter player){
        Image playerJP = new Image(Render.class.getResource("/images/Idle-JP.png").toExternalForm());

        ImageView playerView = new ImageView(playerJP);
        playerView.setFitWidth(128);
        playerView.setFitHeight(128);
        playerView.setX(player.getCenterX());
        playerView.setY(player.getCenterY());

        return playerView;
    }
}

