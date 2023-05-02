package edu.ucsd.flappycow.model;

public abstract class IGameButton extends Sprite {

    @Override
    public abstract void move(int viewWidth, int viewHeight);
}
