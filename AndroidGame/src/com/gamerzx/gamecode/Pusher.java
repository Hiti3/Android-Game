package com.gamerzx.gamecode;

import java.util.Random;

import android.graphics.Rect;

public class Pusher extends Enemy {

	private double speedX, movementSpeed;
	private Background bg = GameScreen.getBg1();
	private Robot robot = GameScreen.getRobot();
	private double robotMoveSpeed = robot.getMOVESPEED();
	private double speedY = 0;

	public static Rect bot = new Rect(0, 0, 0, 0);
	public static Rect body = new Rect(0, 0, 0, 0);
	public static Rect side = new Rect(0, 0, 0, 0);
	public static Rect arms = new Rect(0, 0, 0, 0);
	public static Rect yellowRed = new Rect(0, 0, 0, 0);

	public Pusher(int centerX, int centerY, int health) {
		super(centerX, centerY, health);
	}

	@Override
	public void update(float deltaTime) {

		bot.set(centerX, centerY + 54, centerX + 7, centerY + 90);
		side.set(centerX - 4, centerY + 29, centerX + 11, centerY + 62);
		body.set(centerX - 16, centerY - 60, centerX + 27, side.top);
		arms.set(centerX - 56, centerY - 23, centerX + 58, centerY + 20);
		yellowRed.set(arms.left - 45, body.top - 45, arms.right + 45,
				bot.bottom + 45);

		follow();

		centerX += (speedX * deltaTime);
		centerY += speedY;
		speedX = bg.getSpeedX() * robotMoveSpeed + movementSpeed;

		if (Rect.intersects(arms, Robot.yellowRed)) {
			checkCollision();
		}
		
		if (centerY > 700){
			die();
			centerY = 431;
		}
		
	}

	@Override
	public void follow() {
		if (centerX < -95 || centerX > 950) {
			movementSpeed = 0;
		}

		else if (Math.abs(robot.getCenterX() - centerX) < 5) {
			movementSpeed = 0;
		}

		else {

			if (robot.getCenterX() >= centerX) {
				movementSpeed = 1;
			} else {
				movementSpeed = -3;
			}
		}
	}

	@Override
	public void die() {
    	centerX += setAtRandomPosition(300, 80);
        health = 10;
        System.out.println(setAtRandomPosition(300, 700));
	}
	
	// sets the pusher to a new random position when it dies
	public int setAtRandomPosition(int interval, int distance){
		Random randomGenerator = new Random();
		return randomGenerator.nextInt(interval) * 3 + distance;
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub

	}

	@Override
	public void checkCollision() {
		if (Rect.intersects(body, Robot.bodyTop)
				|| Rect.intersects(body, Robot.bodyBot)) {
			robot.setCenterX(centerX - 45);
			robot.setSpeedX(-movementSpeed);
		}
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public static Rect getBot() {
		return bot;
	}

	public static void setBot(Rect bot) {
		Pusher.bot = bot;
	}

	public static Rect getBody() {
		return body;
	}

	public static void setBody(Rect body) {
		Pusher.body = body;
	}

	public static Rect getSide() {
		return side;
	}

	public static void setSide(Rect side) {
		Pusher.side = side;
	}

	public static Rect getArms() {
		return arms;
	}

	public static void setArms(Rect arms) {
		Pusher.arms = arms;
	}

	public static Rect getYellowRed() {
		return yellowRed;
	}

	public static void setYellowRed(Rect yellowRed) {
		Pusher.yellowRed = yellowRed;
	}

	public double getSpeedX() {
		return speedX;
	}
	
	public double getSpeedY() {
		return speedY;
	}

	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}
	
	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

}
