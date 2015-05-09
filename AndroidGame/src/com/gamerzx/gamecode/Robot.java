package com.gamerzx.gamecode;

import java.util.ArrayList;

import android.graphics.Rect;

public class Robot {

	// Constants are Here
	final int JUMPSPEED = -16;
	final double MOVESPEED = 3; // If the constant is changed, the robot will
								// NOT move left after passing coordinate 360

	private int health = 10;
	private int centerX = 120;
	private int centerY = -100;
	private boolean jumped = false;
	private boolean movingLeft = false;
	private boolean movingRight = false;
	private boolean ducked = false;
	private boolean readyToFire = true;

	private double speedX = 0;
	private double speedY = 0;
	public static Rect bodyTop = new Rect(0, 0, 0, 0);
	public static Rect bodyBot = new Rect(0, 0, 0, 0);
	public static Rect armLeft = new Rect(0, 0, 0, 0);
	public static Rect armRight = new Rect(0, 0, 0, 0);
	public static Rect yellowRed = new Rect(0, 0, 0, 0);

	public static Rect footLeft = new Rect(0, 0, 0, 0);
	public static Rect footRight = new Rect(0, 0, 0, 0);

	private Background bg1 = GameScreen.getBg1();
	private Background bg2 = GameScreen.getBg2();
	private Background bg3 = GameScreen.getBg3();
	private Background bg4 = GameScreen.getBg4();

	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

	public void update(float deltaTime) {

		// Moves Character or Scrolls Background accordingly.
		bg1.setSpeedX(0);
		bg2.setSpeedX(0);
		bg3.setSpeedX(0);
		bg4.setSpeedX(0);

		if (centerX < 100) {
			if (speedX < 0) {
				bg1.setSpeedX(0.7);
				bg2.setSpeedX(0.7);
				bg3.setSpeedX(0.2);
				bg4.setSpeedX(0.2);
			} else if (speedX > 0) {
				centerX += (speedX * deltaTime);
			}

		} else if (centerX >= 100 && centerX <= 350) {
			if (speedX < 0 || speedX > 0) {
				centerX += (speedX * deltaTime);
			}

		} else {
			if (speedX > 0) {
				bg1.setSpeedX(-0.7);
				bg2.setSpeedX(-0.7);
				bg3.setSpeedX(-0.2);
				bg4.setSpeedX(-0.2);
			} else if (speedX < 0) {
				centerX += (speedX * deltaTime);
			}
		}

		// Updates Y Position
		centerY += speedY;

		// Handles Jumping

		speedY += 3.5;

		if (speedY > 4) {
			jumped = true;
		}

		// If robot is pushed past the left edge, the game ends
		if (centerX < -80) {
			health = 0;
		}

		// Important! it needs to be greater! bodyBot.bottom >
		// robot.setCenterY(tileY - 73);
		// @VerticalColission or it will lag, intersect Tile and Robot's
		// bodyTop!

		bodyTop.set(centerX - 29, centerY - 73, centerX + 29, centerY);
		bodyBot.set(centerX - 20, centerY, centerX + 20, centerY + 74);
		armLeft.set(bodyTop.left - 61, bodyTop.top + 20, bodyTop.left,
				bodyTop.top + 47);
		armRight.set(bodyTop.right, armLeft.top, bodyTop.right + 61,
				armLeft.bottom);

		yellowRed.set(armLeft.left - 45, bodyTop.top - 45, armRight.right + 45,
				bodyBot.bottom + 45);

		footLeft.set(centerX - 39, bodyBot.bottom - 12, centerX - 7,
				bodyBot.bottom - 1);
		footRight.set(centerX + 6, footLeft.top, centerX + 38,
				bodyBot.bottom - 1);

	}

	public void moveRight() {
		if (ducked == false) {
			speedX = MOVESPEED;
		}
	}

	public void moveLeft() {
		if (ducked == false) {
			speedX = -MOVESPEED;
		}
	}

	public void stopRight() {
		setMovingRight(false);
		stop();
	}

	public void stopLeft() {
		setMovingLeft(false);
		stop();
	}

	private void stop() {
		if (isMovingRight() == false && isMovingLeft() == false) {
			speedX = 0;
		}

		else if (isMovingRight() == false && isMovingLeft() == true) {
			moveLeft();
		}

		else if (isMovingRight() == true && isMovingLeft() == false) {
			moveRight();
		}

	}

	public void jump() {
		if (jumped == false) {
			speedY = JUMPSPEED;
			jumped = true;
		}

	}

	public void shoot() {
		if (readyToFire) {
			Projectile p = new Projectile(armRight.right - 30,
					armRight.bottom - 7);
			Projectile p2 = new Projectile(armRight.right - 30,
					armRight.bottom - 20);
			projectiles.add(p);
			projectiles.add(p2);
		}
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public boolean isJumped() {
		return jumped;
	}

	public double getSpeedX() {
		return speedX;
	}

	public double getSpeedY() {
		return speedY;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void setJumped(boolean jumped) {
		this.jumped = jumped;
	}

	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}

	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}

	public boolean isDucked() {
		return ducked;
	}

	public void setDucked(boolean ducked) {
		this.ducked = ducked;
	}

	public boolean isMovingRight() {
		return movingRight;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}

	public boolean isMovingLeft() {
		return movingLeft;
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}

	public boolean isReadyToFire() {
		return readyToFire;
	}

	public void setReadyToFire(boolean readyToFire) {
		this.readyToFire = readyToFire;
	}

	public double getMOVESPEED() {
		return MOVESPEED;
	}

}