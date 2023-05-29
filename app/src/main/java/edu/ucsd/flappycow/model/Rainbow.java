package edu.ucsd.flappycow.model;

import android.graphics.Bitmap;

public class Rainbow extends Sprite{
    public Rainbow() {
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
        this.setWidth(this.getSpriteBitmap().getBitmap().getWidth() / getColNr());
        this.setHeight(this.getSpriteBitmap().getBitmap().getHeight() / 3);
    }
}
