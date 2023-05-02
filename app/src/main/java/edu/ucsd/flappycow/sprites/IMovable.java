package edu.ucsd.flappycow.sprites;

import android.graphics.Canvas;

public interface IMovable {
    void draw(Canvas canvas);
    void move(int viewWidth, int viewHeight);
    void changeToNextFrame();
    boolean isOutOfRange();
}
