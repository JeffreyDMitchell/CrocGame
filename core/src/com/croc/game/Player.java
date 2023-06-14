package com.croc.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player
{
    Vector2 pos, vel;
    GraphicsWrapper gw;
    float movespeed = 5;
    int[] control = new int[4];
    int dir = 1;

    public Player(Vector2 pos, GraphicsWrapper gw)
    {
        this.pos = pos;
        this.gw = gw;
    }
}

class GraphicsWrapper
{
    int width, height;
    Animation<TextureRegion> idle;
    Animation<TextureRegion> walk;

    public GraphicsWrapper(int width, int height, Animation<TextureRegion> idle, Animation<TextureRegion> walk)
    {
        this.width = width;
        this.height = height;
        this.idle = idle;
        this.walk = walk;
    }
}
