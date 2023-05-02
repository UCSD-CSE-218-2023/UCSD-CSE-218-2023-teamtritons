package edu.ucsd.flappycow.sprites;

import edu.ucsd.flappycow.GameActivity;
import edu.ucsd.flappycow.GameView;

public abstract class IGameButton extends Sprite {

    @Override
    public abstract void move(int viewWidth, int viewHeight);
}
