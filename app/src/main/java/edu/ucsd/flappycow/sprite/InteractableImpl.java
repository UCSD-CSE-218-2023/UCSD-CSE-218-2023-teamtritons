package edu.ucsd.flappycow.sprite;

public class InteractableImpl implements IInteractable{
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
}
