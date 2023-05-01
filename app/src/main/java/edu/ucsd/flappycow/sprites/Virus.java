package edu.ucsd.flappycow.sprites;

import android.graphics.Bitmap;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.Util;

public class Virus extends PowerUp{

    public Virus(int viewWidth, int viewHeight, int viewSpeedX) {
        super(viewWidth, viewHeight, viewSpeedX);
    }

    @Override
    public void onInitBitmap() {
        this.setWidth(this.getBitmap().getWidth());
        this.setHeight(this.getBitmap().getHeight());
    }

    /**
     * When eaten the player will become infected.
     */
    //TODO: fix this
    @Override
    public void onCollision() {
        super.onCollision();
//        this.getGameActivity().decreasePoints();
//        this.getView().changeToSick();
    }
}
