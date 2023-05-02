package edu.ucsd.flappycow.sprites;

import android.graphics.Bitmap;

import edu.ucsd.flappycow.GameActivity;
import edu.ucsd.flappycow.GameView;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.Util;

public class Virus extends PowerUp{

    public Virus(GameView view, GameActivity gameActivity, int speedX) {
        super(view, gameActivity, speedX);
    }

    @Override
    public void onInitBitmap(Bitmap bitmap) {
        super.onInitBitmap(bitmap);
        this.setWidth(this.getBitmap().getWidth());
        this.setHeight(this.getBitmap().getHeight());
    }

    /**
     * When eaten the player will become infected.
     */
    @Override
    public void onCollision() {
        super.onCollision();
//        // TODO: presenter
//        this.getGameActivity().decreasePoints();
//        this.getView().changeToSick();
    }
}
