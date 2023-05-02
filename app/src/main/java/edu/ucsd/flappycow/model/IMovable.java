package edu.ucsd.flappycow.model;

import android.graphics.Canvas;

public interface IMovable {
    void draw(Canvas canvas);
    void move(int viewWidth, int viewHeight);
    void changeToNextFrame();
    boolean isOutOfRange();
}
