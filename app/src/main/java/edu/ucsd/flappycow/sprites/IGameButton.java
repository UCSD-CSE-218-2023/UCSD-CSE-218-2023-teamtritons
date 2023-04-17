package edu.ucsd.flappycow.sprites;

import edu.ucsd.flappycow.GameActivity;
import edu.ucsd.flappycow.GameView;

public abstract class IGameButton extends Sprite{
    protected IGameButton(GameView view, GameActivity gameActivity) {
        super(view, gameActivity);
    }

    @Override
    public void move() {

    }
}
