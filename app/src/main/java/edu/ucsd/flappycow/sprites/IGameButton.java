package edu.ucsd.flappycow.sprites;

public abstract class IGameButton extends Sprite {
    public IGameButton() {
        super();
    }

    @Override
    public abstract void move(int viewHeight, int viewWidth);
}
