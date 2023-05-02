package edu.ucsd.flappycow.sprites;

import edu.ucsd.flappycow.GameActivity;
import edu.ucsd.flappycow.GameView;

public abstract class PowerUp extends Sprite {
    public PowerUp(GameView view, GameActivity gameActivity, int speedX) {
        super(view, gameActivity);
        init(speedX);
    }

    public void onCollision() {
        // Every subclass has to specify this itself
    }

    /**
     * Sets this sprite above the visible screen.
     * At x = 4/5 of the screen.
     * Uses the speed of the GameView to let the power-up fall slowly down.
     */
    private void init(int speedX) {
        this.setX(this.getView().getWidth() * 4 / 5);
        this.setY( 0 - this.getHeight());
        this.setSpeedX(this.getSpeedX()-speedX);
        this.setSpeedY ( (int) (speedX * (Math.random() + 0.5)));
    }
}

