package edu.ucsd.flappycow.sprites;


import android.graphics.Bitmap;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.Util;

public class Tutorial extends Sprite {
    public static Bitmap globalBitmap;

    public Tutorial() {
        super();
        //TODO: presenter
//        if (globalBitmap == null) {
//            globalBitmap = Util.getScaledBitmapAlpha8(gameActivity, R.drawable.tutorial);
//        }
        this.setBitmap(globalBitmap);
        this.setWidth(this.getBitmap().getWidth());
        this.setHeight(this.getBitmap().getHeight());
    }

    /**
     * Sets the position to the center of the view.
     */
    @Override
    public void move(int viewWidth, int viewHeight) {
        this.setX(viewWidth/ 2 - this.getWidth() / 2);
        this.setY(viewHeight/ 2 - this.getHeight() / 2);
    }

}

