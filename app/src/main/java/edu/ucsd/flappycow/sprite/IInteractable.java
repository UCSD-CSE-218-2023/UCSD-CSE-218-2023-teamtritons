package edu.ucsd.flappycow.sprite;

public interface IInteractable {
    boolean isCollidingRadius(Sprite sprite, float factor);

    boolean isTouching(int x, int y);

    boolean isColliding(Sprite sprite);

    void onCollision();
    boolean isTouchingEdge();

    boolean isTouchingGround();

    boolean isTouchingSky();
    boolean isPassed();
    int getCollisionTolerance();

}
