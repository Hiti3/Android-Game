package com.gamerzx.gamecode;

import android.graphics.Rect;


public class Projectile {

    private int x, y, speedX;
    private boolean visible;
    
    Pusher pusher1 = GameScreen.getPusher1();
    private Rect r;
    
    public Projectile(int startX, int startY){
        x = startX;
        y = startY;
        speedX = 15;
        visible = true;
        
        r = new Rect(0, 0, 0, 0);
    }
    
    public void update(){
        x += speedX;
        r.set(x, y, x+8, y+4);
        if (x > 960){
            visible = false;
            r = null;
        }
        else {
            checkCollision();
        }
    }

    private void checkCollision() {
        if(Rect.intersects(r, GameScreen.pusher1.body)){
            visible = false;
        
            if (pusher1.health > 0) {
                pusher1.health -= 1;
            }
            else if (pusher1.health == 0) {
            	pusher1.die();
            	GameScreen.setScore(GameScreen.getScore() + 5);
            }
        }
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeedX() {
        return speedX;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
    
}