package edu.ucsd.flappycow.sprites;

import android.graphics.Bitmap;

import edu.ucsd.flappycow.GameActivity;
import edu.ucsd.flappycow.GameView;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.Util;

public class Rainbow extends Sprite{
    /**
     * Static bitmap to reduce memory usage.
     */
    private static Bitmap globalBitmap;
    public Rainbow() {
        super();
        //TODO: presenter
//        if (globalBitmap == null) {
//            globalBitmap = Util.getScaledBitmapAlpha8(gameActivity, R.drawable.rainbow);
//        }
        this.setBitmap(globalBitmap);
        this.setColNr(new Integer(8).byteValue());
        this.setWidth(this.getBitmap().getWidth() / getColNr());
        this.setHeight(this.getBitmap().getHeight() / 3);
    }

    @Override
    public void move(int viewHeight, int viewWidth) {
        changeToNextFrame();
        super.move(viewHeight, viewWidth);
    }
}
