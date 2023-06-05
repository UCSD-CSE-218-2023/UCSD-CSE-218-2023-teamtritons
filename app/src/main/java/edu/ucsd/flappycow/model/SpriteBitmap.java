package edu.ucsd.flappycow.model;

import android.graphics.Bitmap;

public class SpriteBitmap implements ISpriteBitmap{
    private Bitmap bitmap;
    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }

    @Override
    public void onInitBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
