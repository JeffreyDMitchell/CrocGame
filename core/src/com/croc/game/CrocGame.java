package com.croc.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.ScreenUtils;

public class CrocGame extends Game
{
	public static final int RES_X = 1600;
	public static final int RES_Y = 900;

	public static SpriteBatch batch;
	public static TextureAtlas atlas;
	
	@Override
	public void create ()
	{
		batch = new SpriteBatch();
		atlas = new TextureAtlas(Gdx.files.internal("swamp.atlas"));

		setScreen(new GameScreen(this));
	}

	@Override
	public void render ()
	{
		super.render();
	}
	
	@Override
	public void dispose ()
	{
		batch.dispose();
	}
}
