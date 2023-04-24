package edu.ucsd.flappycow.sprites;

import edu.ucsd.flappycow.GameActivity;
import edu.ucsd.flappycow.GameView;

public abstract class IPlayableCharacter extends Sprite{
    private boolean isDead = false;

    public IPlayableCharacter(GameView view, GameActivity gameActivity) {
        super(view, gameActivity);
        move();
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    /**
     * Calls super.move
     * Moves the character to 1/6 of the horizontal screen
     * Manages the speed changes -> Falling
     */
    @Override
    public void move() {
        this.setX(this.getView().getWidth() / 6);

        if (this.getSpeedY() < 0) {
            // The character is moving up
            this.setSpeedY(this.getSpeedY()  * 2 / 3 + getSpeedTimeDecrease() / 2);
        } else {
            // the character is moving down
            this.setSpeedY(this.getSpeedY()  + getSpeedTimeDecrease());
        }

        if (this.getSpeedY() > getMaxSpeed()) {
            // speed limit
            this.setSpeedY(getMaxSpeed());
        }

        super.move();
    }
    /**
     * A dead character falls slowly to the ground.
     */
    public void dead() {
        this.isDead = true;
        this.setSpeedY(getMaxSpeed()/2);
    }

    /**
     * Let the character flap up.
     */
    public void onTap() {
        this.setSpeedY(getTabSpeed());
        this.setY(this.getY() + getPosTabIncrease());
    }

    /**
     * Falling speed limit
     *
     * @return
     */
    protected float getMaxSpeed() {
        // 25 @ 720x1280 px
        return this.getView().getHeight() / 51.2f;
    }

    /**
     * Every run cycle the speed towards the ground will increase.
     *
     * @return
     */
    protected float getSpeedTimeDecrease() {
        // 4 @ 720x1280 px
        return this.getView().getHeight() / 320;
    }

    /**
     * The character gets this speed when taped.
     *
     * @return
     */
    protected float getTabSpeed() {
        // -80 @ 720x1280 px
        return -this.getView().getHeight() / 16f;
    }

    /**
     * The character jumps up the pixel height of this value.
     *
     * @return
     */
    protected int getPosTabIncrease() {
        // -12 @ 720x1280 px
        return -this.getView().getHeight() / 100;
    }

    public void revive() {
        this.isDead = false;
        this.setRow(new Integer(0).byteValue());
    }

    public void upgradeBitmap(int points) {
        // Change bitmap, maybe when a certain amount of point is reached.
    }

    public void wearMask() {
        // Change bitmap to have a mask.
    }

}
