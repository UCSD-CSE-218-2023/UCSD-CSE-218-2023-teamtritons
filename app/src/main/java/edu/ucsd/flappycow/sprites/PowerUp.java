/**
 * The abstract spriteclass for power-ups
 *
 * @author Lars Harmsen
 * Copyright (c) <2014> <Lars Harmsen - Quchen>
 */

package edu.ucsd.flappycow.sprites;

import edu.ucsd.flappycow.GameActivity;
import edu.ucsd.flappycow.GameView;

public abstract class PowerUp extends Sprite {
    public PowerUp(GameView view, GameActivity gameActivity) {
        super(view, gameActivity);
        init();
    }

    /**
     * Sets this sprite above the visible screen.
     * At x = 4/5 of the screen.
     * Uses the speed of the GameView to let the power-up fall slowly down.
     */
    private void init() {
        this.x = view.getWidth() * 4 / 5;
        this.y = 0 - this.height;
        this.speedX = -view.getSpeedX();
        this.speedY = (int) (view.getSpeedX() * (Math.random() + 0.5));
    }
}
