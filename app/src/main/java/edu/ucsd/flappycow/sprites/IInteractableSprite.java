package edu.ucsd.flappycow.sprites;

import edu.ucsd.flappycow.GameActivity;
import edu.ucsd.flappycow.GameView;

public interface IInteractableSprite extends Sprite {

    public IInteractableSprite(GameView view, GameActivity gameActivity) {
        super(view, gameActivity);
    }

    /**
     * Checks whether the sprite is touching this.
     * Seeing the sprites as rectangles.
     * @param sprite
     * @return
     */
    public boolean isColliding(Sprite sprite) {
        if (this.x + getCollisionTolerance() < sprite.x + sprite.width
                && this.x + this.width > sprite.x + getCollisionTolerance()
                && this.y + getCollisionTolerance() < sprite.y + sprite.height
                && this.y + this.height > sprite.y + getCollisionTolerance()) {
            return true;
        }
        return false;
    }

    /**
     * Checks whether the sprite is touching this.
     * With the distance of the 2 centers.
     * @param sprite
     * @return
     */
    public boolean isCollidingRadius(Sprite sprite, float factor) {
        int m1x = this.x + (this.width >> 1);
        int m1y = this.y + (this.height >> 1);
        int m2x = sprite.x + (sprite.width >> 1);
        int m2y = sprite.y + (sprite.height >> 1);
        int dx = m1x - m2x;
        int dy = m1y - m2y;
        int d = (int) Math.sqrt(dy * dy + dx * dx);

        if (d < (this.width + sprite.width) * factor
                || d < (this.height + sprite.height) * factor) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks whether the point specified by the x and y coordinates is touching the sprite.
     * @param x
     * @param y
     * @return
     */
    public boolean isTouching(int x, int y) {
        return (x > this.x && x < this.x + width
                && y > this.y && y < this.y + height);
    }

    /**
     * What should be done, when the player collide with this sprite?
     */
    public void onCollision() {
        // Every subclass has to specify this itself
    }

    /**
     * Checks whether the sprite is touching the ground or the sky.
     * @return
     */
    public boolean isTouchingEdge() {
        return isTouchingGround() || isTouchingSky();
    }

    /**
     * Checks whether the sprite is touching the ground.
     * @return
     */
    public boolean isTouchingGround() {
        return this.y + this.height > this.view.getHeight() - this.view.getHeight() * Frontground.GROUND_HEIGHT;
    }

    /**
     * Checks whether the sprite is touching the sky.
     * @return
     */
    public boolean isTouchingSky() {
        return this.y < 0;
    }

    private int getCollisionTolerance() {
        // 25 @ 720x1280 px
        return gameActivity.getResources().getDisplayMetrics().heightPixels / 50;
    }

    public boolean isPassed() {
        return this.x + this.width < view.getPlayer().getX();
    }

}
