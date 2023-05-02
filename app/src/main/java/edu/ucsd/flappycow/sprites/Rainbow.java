package edu.ucsd.flappycow.sprites;

import android.graphics.Bitmap;

import edu.ucsd.flappycow.GameActivity;
import edu.ucsd.flappycow.GameView;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.Util;

public class Rainbow extends Sprite{
    public Rainbow(GameView view, GameActivity gameActivity) {
        super(view, gameActivity);
        this.setColNr(new Integer(8).byteValue());
    }

    @Override
    public void move(int viewWidth, int viewHeight) {
        changeToNextFrame();
        super.move(viewWidth, viewHeight);
    }

    @Override
    public void onInitBitmap(Bitmap bitmap) {
        super.onInitBitmap(bitmap);
        this.setWidth(this.getBitmap().getWidth() / getColNr());
        this.setHeight(this.getBitmap().getHeight() / 3);
    }
}
