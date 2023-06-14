package com.croc.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen
{
    Game game;
    OrthographicCamera cam;
    SpriteBatch batch;
    TextureAtlas atlas;

    Player active_player;
    Array<Player> players;


    // TODO testing remove
    Animation<TextureRegion> animation;
    float elapsed = 0;
    ShapeRenderer sr;

    public GameScreen(Game game)
    {
        this.game = game;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, CrocGame.RES_X, CrocGame.RES_Y);

        this.batch = CrocGame.batch;
        this.atlas = CrocGame.atlas;

        this.players = new Array<Player>();

        this.active_player = new Player(
                new Vector2(CrocGame.RES_X / 2.0f, CrocGame.RES_Y / 2.0f),
                new GraphicsWrapper(
                        150,
                        150,
                        new Animation<TextureRegion>(0.05f, atlas.findRegions("lizardidle"), Animation.PlayMode.LOOP),
                        new Animation<TextureRegion>(0.05f, atlas.findRegions("lizardwalking"), Animation.PlayMode.LOOP)
        ));

        players.add(active_player);
        Gdx.input.setInputProcessor(new EZInputProcessor(active_player));

        sr = new ShapeRenderer();
    }

    @Override
    public void show()
    {
    }

    @Override
    public void render(float delta)
    {
        update(delta);

        ScreenUtils.clear(Color.WHITE);
        batch.setProjectionMatrix(cam.combined);

        batch.begin();

        elapsed += delta;

        for(Player p : players)
        {
            Animation<TextureRegion> anim = Math.abs(p.control[0] + p.control[1]) + Math.abs(p.control[2] + p.control[3]) != 0 ? p.gw.walk : p.gw.idle;
            batch.draw(
                    anim.getKeyFrame(elapsed),
                    p.pos.x - (p.gw.width / 2.0f),
                    p.pos.y,
                    (p.gw.width / 2.0f), // check this
                    0,
                    p.gw.width,
                    p.gw.height,
                    p.dir,
                    1,
                    0
            );
        }

        batch.end();
    }

    public void update(float delta)
    {
        active_player.pos.x += (active_player.control[0] + active_player.control[1]) * active_player.movespeed;
        active_player.pos.y += (active_player.control[2] + active_player.control[3]) * active_player.movespeed;
    }

    @Override
    public void resize(int width, int height)
    {

    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void hide()
    {

    }

    @Override
    public void dispose()
    {

    }
}

class EZInputProcessor implements InputProcessor
{
    Player p;

    public EZInputProcessor(Player p)
    {
        this.p = p;
    }

    @Override
    public boolean keyDown(int keycode)
    {
        switch (keycode)
        {
            case Input.Keys.A: p.dir = p.control[0] = -1; break;
            case Input.Keys.D: p.dir = p.control[1] = 1; break;
            case Input.Keys.W: p.control[2] = 1; break;
            case Input.Keys.S: p.control[3] = -1; break;

            default: return false;
        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        switch (keycode)
        {
            case Input.Keys.A: p.control[0] = 0; break;
            case Input.Keys.D: p.control[1] = 0; break;
            case Input.Keys.W: p.control[2] = 0; break;
            case Input.Keys.S: p.control[3] = 0; break;

            default: return false;
        }

        return true;
    }

    @Override
    public boolean keyTyped(char character)
    {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY)
    {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY)
    {
        return false;
    }
}