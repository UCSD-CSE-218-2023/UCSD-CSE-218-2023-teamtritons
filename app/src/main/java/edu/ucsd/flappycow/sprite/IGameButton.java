package edu.ucsd.flappycow.sprite;

import edu.ucsd.flappycow.GameActivity;
import edu.ucsd.flappycow.GameView;

public abstract class IGameButton extends Sprite {
    public IGameButton(GameView view, GameActivity gameActivity) {
        super(view, gameActivity);
    }

    @Override
    public abstract void move();
}
