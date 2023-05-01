package edu.ucsd.flappycow.sprites;

import android.graphics.Canvas;

public interface IMovable {
    void draw(Canvas canvas);
    void move(int viewHeight, int viewWidth);
    void changeToNextFrame();
    boolean isOutOfRange();
}
