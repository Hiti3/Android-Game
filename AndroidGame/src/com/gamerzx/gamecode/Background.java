package com.gamerzx.gamecode;

public class Background {
    
    private double bgX, bgY, speedX;
    
    public Background(int x, int y){
        bgX = x;
        bgY = y;
        speedX = 0;
    }
    
    public void update() {
        bgX += speedX;

        if (bgX <= -2420){
            bgX += 4840;
        }
    }

    public double getBgX() {
        return bgX;
    }

    public double getBgY() {
        return bgY;
    }

    public double getSpeedX() {
        return speedX;
    }

    public void setBgX(double bgX) {
        this.bgX = bgX;
    }

    public void setBgY(double bgY) {
        this.bgY = bgY;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }   
}