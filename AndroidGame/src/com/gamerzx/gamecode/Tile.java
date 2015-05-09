package com.gamerzx.gamecode;

import android.graphics.Rect;

import com.gamerzx.framework.Image;

public class Tile {

	private int tileX, tileY;
	private double speedX;
	public int type;
	public Image tileImage;

	private Robot robot = GameScreen.getRobot();
	private Background bg = GameScreen.getBg1();
	private Pusher pusher1 = GameScreen.getPusher1();
	private double robotMoveSpeed = robot.getMOVESPEED();

	private Rect tile;

	public Tile(int x, int y, int typeInt) {
		tileX = x * 30;
		tileY = y * 30;

		type = typeInt;

		tile = new Rect();

		if (type == 5) {
			tileImage = Assets.tileMid;
		} else if (type == 8) {
			tileImage = Assets.tileTop;
		} else if (type == 4) {
			tileImage = Assets.tileLeft;
		} else if (type == 6) {
			tileImage = Assets.tileRight;
		} else if (type == 2) {
			tileImage = Assets.tileBot;
		} else if (type == 7) {
			tileImage = Assets.tileLCorner;
		} else if (type == 9) {
			tileImage = Assets.tileRCorner;
		} else if (type == 1) {
			tileImage = Assets.tileLCornerBot;
		} else if (type == 3) {
			tileImage = Assets.tileRCornerBot;
		} else {
			type = 0;
		}
	}

	public void update(float deltaTime) {
		speedX = Math.round(bg.getSpeedX() * robotMoveSpeed * deltaTime);
		tileX += speedX;
		tile.set(tileX, tileY, tileX + 30, tileY + 30);

		// Robot Collision Detection
		if (Rect.intersects(tile, Robot.yellowRed) && type != 0) {

			checkVerticalCollisionRobot(Robot.bodyTop, Robot.bodyBot);
			checkSideCollisionRobot(Robot.armLeft, Robot.armRight,
					Robot.footLeft, Robot.footRight);
		}
		if (Rect.intersects(tile, Pusher.yellowRed) && type != 0) {

			checkVerticalCollisionPusher(Pusher.bot);
			checkSideCollisionPusher(Pusher.side, Pusher.body, Pusher.arms);
		}
	}

	public void checkVerticalCollisionRobot(Rect bodyTop, Rect bodyBot) {

		if (Rect.intersects(bodyBot, tile)
				&& (type == 8 || type == 7 || type == 9)) {
			robot.setJumped(false);
			robot.setSpeedY(0);
			robot.setCenterY(tileY - 73);

		} else if (Rect.intersects(bodyTop, tile)) {
		}
	}

	public void checkSideCollisionRobot(Rect armLeft, Rect armRight,
			Rect footLeft, Rect footRight) {
		
		if (type != 5 && type != 2 && type != 0 && type != 8) {
			
			if (Rect.intersects(armLeft, tile)) {
				robot.setCenterX(tileX + 90);
				robot.setSpeedX(0);

			} else if (Rect.intersects(footLeft, tile)) {

				robot.setCenterX(tileX + 39);
				robot.setSpeedX(0);
			}

			if (Rect.intersects(armRight, tile)) {
				robot.setCenterX(tileX - 90);
				robot.setSpeedX(0);
			}

			else if (Rect.intersects(footRight, tile)) {
				robot.setCenterX(tileX - 38);
				robot.setSpeedX(0);
			}
		}
	}

	// Pusher Collision Detection
	public void checkVerticalCollisionPusher(Rect bot) {
		if (Rect.intersects(bot, tile) && (type == 8 || type == 7 || type == 9)) {
			pusher1.setSpeedY(0);
			pusher1.setCenterY(tileY - 89);
		} else {
			pusher1.setSpeedY(20);
		}
	}

	public void checkSideCollisionPusher(Rect side, Rect body, Rect arms) {

		if (type != 5 && type != 2 && type != 0 && type != 8) {

			if (Rect.intersects(tile, side)) {
				pusher1.setCenterX(tileX + 4);
				pusher1.setSpeedX(0);
			}

			else if (Rect.intersects(tile, body)) {
				pusher1.setCenterX(tileX + 16);
				pusher1.setSpeedX(0);
			}

			else if (Rect.intersects(tile, arms)) {
				pusher1.setCenterX(tileX + 56);
				pusher1.setSpeedX(0);
			}
		}
	}

	public int getTileX() {
		return tileX;
	}

	public void setTileX(int tileX) {
		this.tileX = tileX;
	}

	public int getTileY() {
		return tileY;
	}

	public void setTileY(int tileY) {
		this.tileY = tileY;
	}

	public Image getTileImage() {
		return tileImage;
	}

	public void setTileImage(Image tileImage) {
		this.tileImage = tileImage;
	}

	public double getSpeedX() {
		return speedX;
	}

	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}
	

}