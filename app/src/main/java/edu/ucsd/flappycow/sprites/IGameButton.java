package edu.ucsd.flappycow.sprites;

import edu.ucsd.flappycow.GameActivity;
import edu.ucsd.flappycow.GameView;

public abstract class IGameButton extends Sprite {
    public IGameButton() {
        super();
    }

    @Override
    public abstract void move(int viewHeight, int viewWidth);
}
