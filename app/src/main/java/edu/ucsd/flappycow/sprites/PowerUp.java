package edu.ucsd.flappycow.sprites;

public abstract class PowerUp extends Sprite {
    public PowerUp(int viewWidth, int viewHeight, int viewSpeedX) {
        super();
        init(viewWidth, viewHeight, viewSpeedX);
    }

    public void onCollision() {
        // Every subclass has to specify this itself
    }

    /**
     * Sets this sprite above the visible screen.
     * At x = 4/5 of the screen.
     * Uses the speed of the GameView to let the power-up fall slowly down.
     */
    private void init(int viewWidth, int viewHeight, int viewSpeedX) {
        this.setX(viewWidth * 4 / 5);
        this.setY( 0 - viewHeight);
        this.setSpeedX(this.getSpeedX()-viewSpeedX);
        this.setSpeedY ( (int) (viewSpeedX * (Math.random() + 0.5)));
    }
}

