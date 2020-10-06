package com.mygdx.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ChowFightMain extends Game {
    public SpriteBatch batch;
    public BitmapFont font;

    public static final int V_WIDTH = 400;
    public static final int V_HEIGHT = 208;

    public static final float PPM = 100;

    public void create() {
        batch = new SpriteBatch();
        //Use LibGDX's default Arial font.
        font = new BitmapFont();
        this.setScreen(new MainMenuScreen(this));
    }

    public void render() {
        super.render(); //important!
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
