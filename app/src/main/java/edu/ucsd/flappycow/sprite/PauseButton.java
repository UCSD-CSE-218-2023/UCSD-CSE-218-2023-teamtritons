package edu.ucsd.flappycow.sprite;

import edu.ucsd.flappycow.GameActivity;
import edu.ucsd.flappycow.GameView;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.Util;

public class PauseButton extends IGameButton{
    public PauseButton(GameView view, GameActivity gameActivity) {
        super(view, gameActivity);
        this.setBitmap(Util.getScaledBitmapAlpha8(gameActivity, R.drawable.pause_button));
        this.setWidth(this.getBitmap().getWidth());
        this.setHeight(this.getBitmap().getHeight());
    }

    /**
     * Sets the button in the right upper corner.
     */
    @Override
    public void move() {
        this.setX(this.getView().getWidth() - this.getWidth());
        this.setY(0);
    }
}
