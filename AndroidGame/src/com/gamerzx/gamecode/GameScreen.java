package com.gamerzx.gamecode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.gamerzx.framework.Game;
import com.gamerzx.framework.Graphics;
import com.gamerzx.framework.Image;
import com.gamerzx.framework.Input.TouchEvent;
import com.gamerzx.framework.Screen;

public class GameScreen extends Screen {
	enum GameState {
		Ready, Running, Paused, GameOver
	}

	GameState state = GameState.Ready;

	// Variable Setup

	private static Background bg1, bg2, bg3, bg4;
	private static Robot robot;
	public static Pusher pusher1;

	private Image currentSprite, character, character1, character2, character3,
			character4, characterB1, characterB2, characterB3, characterB4,
			robot1, robot2, robot3, robot4, robot5, robot6, robot7;
	private Animation anim, pusherAnimation;

	private ArrayList tilearray = new ArrayList();

	private static int livesLeft = 1;
	private static int score = 0;
	Paint paint, paintS, paint2, paint2S, strokePaint, textPaint;

	public GameScreen(Game game) {
		super(game);
		//AssetManager assetManager = getAssets();

		// Initialize game objects here

		bg1 = new Background(0, 0);
		bg2 = new Background(2420, 0);
		bg3 = new Background(0, 0);
		bg4 = new Background(2420, 0);

		robot = new Robot();
		pusher1 = new Pusher(1000, 431, 10);

		character = Assets.character;
		character1 = Assets.character1;
		character2 = Assets.character2;
		character3 = Assets.character3;
		character4 = Assets.character4;
		characterB1 = Assets.characterB1;
		characterB2 = Assets.characterB2;
		characterB3 = Assets.characterB3;
		characterB4 = Assets.characterB4;

		robot1 = Assets.robot1;
		robot2 = Assets.robot2;
		robot3 = Assets.robot3;
		robot4 = Assets.robot4;
		robot5 = Assets.robot5;
		robot6 = Assets.robot6;
		robot7 = Assets.robot7;

		anim = new Animation();
		anim.addFrame(character, 4000);
		anim.addFrame(character1, 700);
		anim.addFrame(character2, 700);
		anim.addFrame(character3, 700);
		anim.addFrame(character4, 700);
		anim.addFrame(character, 300);
		anim.addFrame(characterB1, 40);
		anim.addFrame(characterB2, 40);
		anim.addFrame(characterB3, 40);
		anim.addFrame(characterB4, 40);
		anim.addFrame(characterB3, 40);
		anim.addFrame(characterB2, 40);
		anim.addFrame(characterB1, 40);

		pusherAnimation = new Animation();
		pusherAnimation.addFrame(robot1, 40);
		pusherAnimation.addFrame(robot2, 40);
		pusherAnimation.addFrame(robot3, 40);
		pusherAnimation.addFrame(robot4, 40);
		pusherAnimation.addFrame(robot5, 40);
		pusherAnimation.addFrame(robot6, 40);
		pusherAnimation.addFrame(robot7, 40);
		pusherAnimation.addFrame(robot6, 35);
		pusherAnimation.addFrame(robot5, 35);
		pusherAnimation.addFrame(robot4, 35);
		pusherAnimation.addFrame(robot3, 35);
		pusherAnimation.addFrame(robot2, 35);

		currentSprite = anim.getImage();

		loadMap();

		// Defining a paint object
		paintS = new Paint();
		paintS.setColor(Color.BLACK);
		paintS.setTextAlign(Paint.Align.CENTER);
		paintS.setTextSize(35);
		paintS.setStyle(Paint.Style.STROKE);
		paintS.setAntiAlias(true);
		paintS.setStrokeWidth(5);
		paintS.setTypeface(Typeface.MONOSPACE);
		paintS.setShadowLayer(2, 0, 1,(Color.BLACK));
		
		paint = new Paint();
		paint.setTextSize(35);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setAntiAlias(true);
		paint.setTypeface(Typeface.MONOSPACE);
		paint.setColor(Color.WHITE);

		paint2S = new Paint();
		paint2S.setColor(Color.BLACK);
		paint2S.setTextAlign(Paint.Align.CENTER);
		paint2S.setTextSize(100);
		paint2S.setStyle(Paint.Style.STROKE);
		paint2S.setAntiAlias(true);
		paint2S.setStrokeWidth(12);
		paint2S.setTypeface(Typeface.MONOSPACE);
		
		paint2 = new Paint();
		paint2.setTextSize(100);
		paint2.setTextAlign(Paint.Align.CENTER);
		paint2.setAntiAlias(true);
		paint2.setTypeface(Typeface.MONOSPACE);
		paint2.setColor(Color.WHITE);
		
		// The strokePaint & textPaint objects for displaying score...
		strokePaint = new Paint();
		strokePaint.setColor(Color.BLACK);
		strokePaint.setTextAlign(Paint.Align.CENTER);
		strokePaint.setTextSize(47);
		strokePaint.setStyle(Paint.Style.STROKE);
		strokePaint.setAntiAlias(true);
		strokePaint.setStrokeWidth(5);
		//strokePaint.setTypeface(Typeface.createFromAsset(mgr, path));
		strokePaint.setTypeface(Typeface.MONOSPACE);
		strokePaint.setShadowLayer(2, 0, 1,(Color.BLACK));
		
		textPaint = new Paint();
		textPaint.setColor(Color.WHITE);
		textPaint.setTextAlign(Paint.Align.CENTER);
		textPaint.setTextSize(47);
		textPaint.setAntiAlias(true);
		textPaint.setTypeface(Typeface.MONOSPACE);
	}

	private void loadMap() {
		ArrayList lines = new ArrayList();
		int width = 0;
		int height = 0;

		Scanner scanner = new Scanner(SampleGame.map);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();

			// no more lines to read
			if (line == null) {
				break;
			}

			if (!line.startsWith("!")) {
				lines.add(line);
				width = Math.max(width, line.length());

			}
		}
		height = lines.size();

		for (int j = 0; j < 18; j++) {
			String line = (String) lines.get(j);
			for (int i = 0; i < width; i++) {

				if (i < line.length()) {
					char ch = line.charAt(i);
					Tile t = new Tile(i, j, Character.getNumericValue(ch));
					tilearray.add(t);
				}

			}
		}

	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

		// We have four separate update methods in this example.
		// Depending on the state of the game, we call different update methods.
		// Refer to Unit 3's code. We did a similar thing without separating the
		// update methods.

		if (state == GameState.Ready)
			updateReady(touchEvents);
		else if (state == GameState.Running)
			updateRunning(touchEvents, deltaTime);
		else if (state == GameState.Paused)
			updatePaused(touchEvents);
		else if (state == GameState.GameOver)
			updateGameOver(touchEvents);
	}

	private void updateReady(List<TouchEvent> touchEvents) {

		// This example starts with a "Ready" screen.
		// When the user touches the screen, the game begins.
		// state now becomes GameState.Running.
		// Now the updateRunning() method will be called!

		if (touchEvents.size() > 0)
			state = GameState.Running;
	}

	private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {

		// This is identical to the update() method from our Unit 2/3 game.

		// 1. All touch input is handled here:
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = (TouchEvent) touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_DOWN) {

				if (inBounds(event, 203, 0, 450, 415)) {
					robot.jump();
					currentSprite = anim.getImage();
					robot.setDucked(false);
				}

				else if ((inBounds(event, 12, 448, 97, 81) && !inBounds(event,
						850, 448, 97, 81))
						|| (!inBounds(event, 12, 448, 97, 81) && inBounds(
								event, 850, 448, 97, 81))) {

					if (robot.isDucked() == false && robot.isJumped() == false
							&& robot.isReadyToFire()) {
						robot.shoot();
					}
				}

				else if (inBounds(event, 144, 419, 679, 121)
						&& robot.isJumped() == false) {
					currentSprite = Assets.characterDown;
					robot.setDucked(true);
					robot.setSpeedX(0);

				}

				if (inBounds(event, 694, 80, 266, 338)) {
					// Move right.
					robot.moveRight();
					robot.setMovingRight(true);

				} else if (event.x <= 200 && event.y <= 417) {
					// Move left.
					robot.moveLeft();
					robot.setMovingLeft(true);
				}

			}

			else if (event.type == TouchEvent.TOUCH_UP) {

				if (inBounds(event, 144, 419, 679, 121)) {
					currentSprite = anim.getImage();
					robot.setDucked(false);

				}

				else if (inBounds(event, 889, 8, 62, 64)) {
					pause();

				}

				if (inBounds(event, 694, 80, 266, 338)) {
					// Stop moving right.
					robot.stopRight();

				} else if (event.x <= 200 && event.y <= 417) {
					// Stop moving left.
					robot.stopLeft();
				}
			}
		}

		// 2. Check miscellaneous events like death:

		if (livesLeft == 0) {
			state = GameState.GameOver;
		}

		// 3. Call individual update() methods here.
		// This is where all the game updates happen.
		// For example, robot.update();
		robot.update(deltaTime);
		if (robot.isJumped()) {
			currentSprite = Assets.characterJump;
		} else if (robot.isJumped() == false && robot.isDucked() == false) {
			currentSprite = anim.getImage();
		}

		ArrayList<Projectile> projectiles = robot.getProjectiles();
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = (Projectile) projectiles.get(i);
			if (p.isVisible() == true) {
				p.update();
			} else {
				projectiles.remove(i);
			}
		}

		updateTiles(deltaTime);
		pusher1.update(deltaTime);
		bg1.update();
		bg2.update();
		bg3.update();
		bg4.update();
		animate();

		if (robot.getCenterY() > 550) {
			state = GameState.GameOver;
		} else if (robot.getHealth() <= 0) {
			state = GameState.GameOver;
		}
	}

	private boolean inBounds(TouchEvent event, int x, int y, int width,
			int height) {
		if (event.x > x && event.x < x + width - 1 && event.y > y
				&& event.y < y + height - 1)
			return true;
		else
			return false;
	}

	private void updatePaused(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = (TouchEvent) touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (inBounds(event, 0, 0, 800, 240)) {

					if (!inBounds(event, 0, 0, 35, 35)) {
						resume();
					}
				}

				else if (inBounds(event, 0, 240, 800, 240)) {
					nullify();
					goToMenu();
				}
			}
		}
	}

	private void updateGameOver(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = (TouchEvent) touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (inBounds(event, 0, 0, 960, 540)) {
					nullify();
					game.setScreen(new MainMenuScreen(game));
					return;
				}
			}
		}

	}

	private void updateTiles(float deltaTime) {

		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = (Tile) tilearray.get(i);
			t.update(deltaTime);
		}

	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();

		g.drawImage(Assets.background2, (int) bg3.getBgX(), (int) bg3.getBgY());
		g.drawImage(Assets.background2, (int) bg4.getBgX(), (int) bg4.getBgY());
		g.drawImage(Assets.background, (int) bg1.getBgX(), (int) bg1.getBgY());
		g.drawImage(Assets.background, (int) bg2.getBgX(), (int) bg2.getBgY());
		paintTiles(g);

		ArrayList<Projectile> projectiles = robot.getProjectiles();
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = (Projectile) projectiles.get(i);
			g.drawRect(p.getX(), p.getY(), 8, 4, Color.YELLOW);
		}
		// First draw the game elements.

		/*
		 * Collision Rectangles
		 * 
		 * g.drawRect(Robot.bodyTop.left, Robot.bodyTop.top, Robot.bodyTop.right
		 * - Robot.bodyTop.left, Robot.bodyTop.bottom - Robot.bodyTop.top,
		 * Color.YELLOW); g.drawRect(Robot.bodyBot.left, Robot.bodyBot.top,
		 * Robot.bodyBot.right - Robot.bodyBot.left, Robot.bodyBot.bottom -
		 * Robot.bodyBot.top, Color.RED); g.drawRect(Robot.armLeft.left,
		 * Robot.armLeft.top, Robot.armLeft.right - Robot.armLeft.left,
		 * Robot.armLeft.bottom - Robot.armLeft.top, Color.MAGENTA);
		 * g.drawRect(Robot.armRight.left, Robot.armRight.top,
		 * Robot.armRight.right - Robot.armRight.left, Robot.armRight.bottom -
		 * Robot.armRight.top, Color.BLUE); g.drawRect(Robot.footLeft.left,
		 * Robot.footLeft.top, Robot.footLeft.right - Robot.footLeft.left,
		 * Robot.footLeft.bottom - Robot.footLeft.top, Color.CYAN);
		 * g.drawRect(Robot.footRight.left, Robot.footRight.top,
		 * Robot.footRight.right - Robot.footRight.left, Robot.footRight.bottom
		 * - Robot.footRight.top, Color.WHITE);
		 */

		g.drawImage(currentSprite, robot.getCenterX() - 90,
				robot.getCenterY() - 72);
		g.drawImage(pusherAnimation.getImage(), pusher1.getCenterX() - 66,
				pusher1.getCenterY() - 70);

		// Example:
		// g.drawImage(Assets.background, 0, 0);
		// g.drawImage(Assets.character, characterX, characterY);

		// Secondly, draw the UI above the game elements.
		if (state == GameState.Ready)
			drawReadyUI();
		else if (state == GameState.Running)
			drawRunningUI();
		else if (state == GameState.Paused)
			drawPausedUI();
		else if (state == GameState.GameOver)
			drawGameOverUI();

	}

	private void paintTiles(Graphics g) {
		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = (Tile) tilearray.get(i);
			if (t.type != 0) {
				g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY());
			}
		}
	}

	public void animate() {
		anim.update(10);
		pusherAnimation.update(35);

	}

	private void nullify() {

		// Set all variables to null. You will be recreating them in the
		// constructor.
		bg1 = null;
		bg2 = null;
		bg3 = null;
		bg4 = null;
		robot = null;
		pusher1 = null;
		currentSprite = null;
		character = null;
		character2 = null;
		character3 = null;
		character4 = null;
		characterB1 = null;
		characterB2 = null;
		characterB3 = null;
		characterB4 = null;
		robot = null;
		robot2 = null;
		robot3 = null;
		robot4 = null;
		robot5 = null;
		anim = null;
		pusherAnimation = null;
		score = 0;

		// Call garbage collector to clean up memory.
		System.gc();
	}

	private void drawReadyUI() {
		Graphics g = game.getGraphics();

		g.drawARGB(155, 0, 0, 0);
		g.drawString("Tap to Start", 480, 270, paintS);
		g.drawString("Tap to Start", 480, 270, paint);

	}

	private void drawRunningUI() {
		Graphics g = game.getGraphics();
		g.drawImage(Assets.button, 6, 6, 64, 71, 162, 64);
		g.drawImage(Assets.button, 623, 6, 0, 0, 254, 69);
		g.drawImage(Assets.button, 889, 8, 1, 71, 62, 64);
		g.drawImage(Assets.button, 12, 448, 1, 137, 97, 81);
		g.drawImage(Assets.button, 850, 448, 100, 136, 97, 81);
		g.drawString(Integer.toString(score), 750, 59, strokePaint);
		g.drawString(Integer.toString(score), 750, 59, textPaint);
	}

	private void drawPausedUI() {
		Graphics g = game.getGraphics();
		// Darken the entire screen so you can display the Paused screen.
		g.drawARGB(155, 0, 0, 0);
		g.drawString("Resume", 480, 180, paint2S);
		g.drawString("Menu", 480, 360, paint2S);
		g.drawString("Resume", 480, 180, paint2);
		g.drawString("Menu", 480, 360, paint2);

	}

	private void drawGameOverUI() {
		Graphics g = game.getGraphics();
		g.drawRect(0, 0, 1280, 801, Color.BLACK);
		g.drawString("YOU ARE DEAD!", 480, 270, paint2S);
		g.drawString("Tap to return", 480, 320, paintS);
		g.drawString("YOU ARE DEAD!", 480, 270, paint2);
		g.drawString("Tap to return", 480, 320, paint);

	}

	@Override
	public void pause() {
		if (state == GameState.Running)
			state = GameState.Paused;

	}

	@Override
	public void resume() {
		if (state == GameState.Paused)
			state = GameState.Running;
	}

	@Override
	public void dispose() {

	}

	@Override
	public void backButton() {
		pause();
	}

	private void goToMenu() {
		// TODO Auto-generated method stub
		game.setScreen(new MainMenuScreen(game));

	}

	public static Background getBg1() {
		// TODO Auto-generated method stub
		return bg1;
	}

	public static Background getBg2() {
		// TODO Auto-generated method stub
		return bg2;
	}

	public static Background getBg3() {
		// TODO Auto-generated method stub
		return bg3;
	}

	public static Background getBg4() {
		// TODO Auto-generated method stub
		return bg4;
	}

	public static Robot getRobot() {
		// TODO Auto-generated method stub
		return robot;
	}

	public static Pusher getPusher1() {
		return pusher1;
	}

	public int getLivesLeft() {
		return livesLeft;
	}

	public static void setLivesLeft(int livesLef) {
		livesLef = livesLeft;
	}
	
	public static int getScore() {
		return score;
	}

	public static void setScore(int score) {
		GameScreen.score = score;
	}

	public ArrayList getTile(){
		return tilearray;
	}
}
