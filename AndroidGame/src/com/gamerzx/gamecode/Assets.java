package com.gamerzx.gamecode;

import com.gamerzx.framework.Image;
import com.gamerzx.framework.Music;
import com.gamerzx.framework.Sound;

public class Assets {
    
    public static Image menu, splash, background, background2, character, character1, character2, character3, character4, characterB1, characterB2, characterB3, characterB4, robot1, robot2, robot3, robot4, robot5, robot6, robot7;
    public static Image tileBot, tileTop, tileLeft, tileRight, tileLCorner, tileRCorner, tileLCornerBot, tileRCornerBot, tileMid, characterJump, characterDown;
    public static Image button;
    public static Sound click;
    public static Music theme;
    
    public static void load(SampleGame sampleGame) {
        // TODO Auto-generated method stub
        theme = sampleGame.getAudio().createMusic("menutheme.mp3");
        theme.setLooping(true);
        theme.setVolume(0.85f);
        theme.play();
    }
    
}
