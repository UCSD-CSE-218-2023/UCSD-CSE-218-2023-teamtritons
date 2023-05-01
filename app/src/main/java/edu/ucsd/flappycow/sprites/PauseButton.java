package edu.ucsd.flappycow.sprites;

import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.Util;

public class PauseButton extends IGameButton{
    public PauseButton() {
        super();
    }

    @Override
    public void onInitBitmap() {
        this.setWidth(this.getBitmap().getWidth());
        this.setHeight(this.getBitmap().getHeight());
    }

    /**
     * Sets the button in the right upper corner.
     */
    @Override
    public void move(int viewHeight, int viewWidth) {
        this.setX(viewWidth - this.getWidth());
        this.setY(0);
    }
}
