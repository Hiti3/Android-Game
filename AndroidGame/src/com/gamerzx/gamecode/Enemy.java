package com.gamerzx.gamecode;

public abstract class Enemy {

	protected int centerX;
	protected int centerY;
	protected int health;
    
    public Enemy(int centerX, int centerY, int health){
    	
    	this.centerX = centerX;
    	this.centerY = centerY;
    	this.health = health;
    	
    }
    // Behavioral Methods
    public abstract void update(float deltaTime);

    public abstract void checkCollision();

    public abstract void follow();

    public abstract void die();

    public abstract void attack();

}
