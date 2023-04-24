package edu.ucsd.flappycow.sprite;

import android.graphics.Canvas;

public class NyanCat implements IPlayableCharacter{
    @Override
    public boolean isColliding() {
        return false;
    }

    @Override
    public boolean isCollidingRadius() {
        return false;
    }

    @Override
    public boolean isTouching() {
        return false;
    }

    @Override
    public void onCollision() {

    }

    @Override
    public boolean isTouchingEdge() {
        return false;
    }

    @Override
    public boolean isTouchingSky() {
        return false;
    }

    @Override
    public boolean isPassed() {
        return false;
    }

    @Override
    public int getCollisionTolerance() {
        return 0;
    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void changeToNextFrame() {

    }

    @Override
    public boolean isOutOfRange() {
        return false;
    }

    @Override
    public void move() {

    }
}
