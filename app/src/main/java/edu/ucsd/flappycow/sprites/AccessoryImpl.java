package edu.ucsd.flappycow.sprites;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public interface AccessoryImpl {
    void moveTo(int x, int y);
    void setBitmap(Bitmap bitmap);
    void draw(Canvas canvas);
}











