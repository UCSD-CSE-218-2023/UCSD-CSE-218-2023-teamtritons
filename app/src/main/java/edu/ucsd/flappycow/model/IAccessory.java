package edu.ucsd.flappycow.model;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public interface IAccessory {
    void moveTo(int x, int y);
    void setBitmap(Bitmap bitmap);
    void draw(Canvas canvas);
}
