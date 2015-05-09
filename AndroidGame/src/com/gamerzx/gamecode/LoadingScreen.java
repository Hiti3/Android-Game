package com.gamerzx.gamecode;

import com.gamerzx.framework.Game;
import com.gamerzx.framework.Graphics;
import com.gamerzx.framework.Graphics.ImageFormat;
import com.gamerzx.framework.Screen;

public class LoadingScreen extends Screen {
    public LoadingScreen(Game game) {
        
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        
        Assets.menu = g.newImage("menu.png", ImageFormat.RGB565);
        
        Assets.background = g.newImage("background.png", ImageFormat.RGB565);
        Assets.background2 = g.newImage("background2.png", ImageFormat.RGB565);
       // Assets.background3 = g.newImage("background3.png", ImageFormat.RGB565);
        
        Assets.character = g.newImage("character.png", ImageFormat.ARGB4444);
        Assets.character1 = g.newImage("character1.png", ImageFormat.ARGB4444);
        Assets.character2 = g.newImage("character2.png", ImageFormat.ARGB4444);
        Assets.character3  = g.newImage("character3.png", ImageFormat.ARGB4444);
        Assets.character4 = g.newImage("character4.png", ImageFormat.ARGB4444);
        Assets.characterB1 = g.newImage("characterB1.png", ImageFormat.ARGB4444);
        Assets.characterB2 = g.newImage("characterB2.png", ImageFormat.ARGB4444);
        Assets.characterB3 = g.newImage("characterB3.png", ImageFormat.ARGB4444);
        Assets.characterB4 = g.newImage("characterB4.png", ImageFormat.ARGB4444);
        Assets.characterJump = g.newImage("jumped.png", ImageFormat.ARGB4444);
        Assets.characterDown = g.newImage("down.png", ImageFormat.ARGB4444);

        
        Assets.robot1 = g.newImage("robot1.png", ImageFormat.ARGB4444);
        Assets.robot2 = g.newImage("robot2.png", ImageFormat.ARGB4444);
        Assets.robot3  = g.newImage("robot3.png", ImageFormat.ARGB4444);
        Assets.robot4  = g.newImage("robot4.png", ImageFormat.ARGB4444);
        Assets.robot5  = g.newImage("robot5.png", ImageFormat.ARGB4444);
        Assets.robot6  = g.newImage("robot6.png", ImageFormat.ARGB4444);
        Assets.robot7  = g.newImage("robot7.png", ImageFormat.ARGB4444);
        
        Assets.tileMid = g.newImage("TileMid.png", ImageFormat.RGB565);
        Assets.tileBot = g.newImage("TileBot.png", ImageFormat.RGB565);
        Assets.tileTop = g.newImage("TileTop.png", ImageFormat.RGB565);
        Assets.tileLeft = g.newImage("TileLeft.png", ImageFormat.RGB565);
        Assets.tileRight = g.newImage("TileRight.png", ImageFormat.RGB565);
        Assets.tileLCorner = g.newImage("TileLCorner.png", ImageFormat.RGB565);
        Assets.tileRCorner = g.newImage("TileRCorner.png", ImageFormat.RGB565);
        Assets.tileLCornerBot = g.newImage("TileLCornerBot.png", ImageFormat.RGB565);
        Assets.tileRCornerBot = g.newImage("TileRCornerBot.png", ImageFormat.RGB565);
        
        Assets.button = g.newImage("button.png", ImageFormat.RGB565);

        //This is how you would load a sound if you had one.
        //Assets.click = game.getAudio().createSound("explode.ogg");

        
        game.setScreen(new MainMenuScreen(game));

    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawImage(Assets.splash, 0, 0);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void backButton() {

    }
}
