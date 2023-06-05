package edu.ucsd.flappycow.model;

import android.graphics.Bitmap;

public interface ISpriteBitmap {
    public Bitmap getBitmap();

    public void onInitBitmap(Bitmap bitmap);
}
