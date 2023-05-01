package edu.ucsd.flappycow.sprites;

import android.graphics.Bitmap;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.Util;

public class Rainbow extends Sprite{
    public Rainbow() {
        super();
        this.setColNr(new Integer(8).byteValue());
    }

    @Override
    public void onInitBitmap() {
        this.setWidth(this.getBitmap().getWidth() / getColNr());
        this.setHeight(this.getBitmap().getHeight() / 3);
    }

    @Override
    public void move(int viewHeight, int viewWidth) {
        changeToNextFrame();
        super.move(viewHeight, viewWidth);
    }
}
