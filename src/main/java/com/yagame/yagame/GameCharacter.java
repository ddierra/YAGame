package com.yagame.yagame;

//Come back to this
public class GameCharacter {
    private int centerX, centerY;

    public GameCharacter(int centerX, int centerY) {
        this.centerX = centerX;
        this.centerY = centerY;
    }

    public void setCenterX(int i) {
        this.centerX = i;
    }
    public void setCenterY(int i) {
        this.centerY = i;
    }
    public int getCenterX() {
        return this.centerX;
    }
    public int getCenterY() {
        return this.centerY;
    }

    //Adding basic movement methods
    public void moveLeft(int speed){
        centerX -= speed;
    }
    public void moveRight(int speed){
        centerX += speed;
    }
    public void moveUp(int speed){
        centerY -= speed;
    }
    public void moveDown(int speed){
        centerY += speed;
    }
}



