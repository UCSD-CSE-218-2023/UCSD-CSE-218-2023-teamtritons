package edu.ucsd.flappycow.sprites;

public interface IInteractable {
    boolean isCollidingRadius(Sprite sprite, float factor);

    boolean isTouching(int x, int y);

    boolean isColliding(Sprite sprite, int heightPixels);

    void onCollision();
    boolean isTouchingEdge(int viewHeight);

    boolean isTouchingGround(int viewHeight);

    boolean isTouchingSky();
    boolean isPassed();
    int getCollisionTolerance(int heightPixels);

}
