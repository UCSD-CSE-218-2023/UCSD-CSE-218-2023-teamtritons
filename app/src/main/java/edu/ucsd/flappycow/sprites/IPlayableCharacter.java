package edu.ucsd.flappycow.sprites;

public abstract class IPlayableCharacter extends Sprite{
    private boolean isDead = false;

    public IPlayableCharacter(int viewHeight, int viewWidth) {
        super();
        move(viewHeight, viewWidth);
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
    public void move(int viewHeight, int viewWidth) {
        this.setX(viewWidth/ 6);

        if (this.getSpeedY() < 0) {
            // The character is moving up
            this.setSpeedY(this.getSpeedY()  * 2 / 3 + getSpeedTimeDecrease(viewHeight) / 2);
        } else {
            // the character is moving down
            this.setSpeedY(this.getSpeedY()  + getSpeedTimeDecrease(viewHeight));
        }

        if (this.getSpeedY() > getMaxSpeed(viewHeight)) {
            // speed limit
            this.setSpeedY(getMaxSpeed(viewHeight));
        }

        super.move(viewHeight, viewWidth);
    }

    /**
     * A dead character falls slowly to the ground.
     */
    public void dead(int viewHeight) {
        this.isDead = true;
        this.setSpeedY(getMaxSpeed(viewHeight)/2);
    }

    /**
     * Let the character flap up.
     */
    public void onTap(int viewHeight) {
        this.setSpeedY(getTabSpeed(viewHeight));
        this.setY(this.getY() + getPosTabIncrease(viewHeight));
    }

    /**
     * Falling speed limit
     *
     * @return
     */
    protected float getMaxSpeed(int viewHeight) {
        // 25 @ 720x1280 px
        return viewHeight/ 51.2f;
    }

    /**
     * Every run cycle the speed towards the ground will increase.
     *
     * @return
     */
    protected float getSpeedTimeDecrease(int viewHeight) {
        // 4 @ 720x1280 px
        return viewHeight/ 320;
    }

    /**
     * The character gets this speed when taped.
     *
     * @return
     */
    protected float getTabSpeed(int viewHeight) {
        // -80 @ 720x1280 px
        return -viewHeight/ 16f;
    }

    /**
     * The character jumps up the pixel height of this value.
     *
     * @return
     */
    protected int getPosTabIncrease(int viewHeight) {
        // -12 @ 720x1280 px
        return -viewHeight/ 100;
    }

    public void revive(int viewHeight, int viewWidth) {
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
