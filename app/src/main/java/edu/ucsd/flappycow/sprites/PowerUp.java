package edu.ucsd.flappycow.sprites;

public abstract class PowerUp extends Sprite {
    public PowerUp(int viewWidth, int viewHeight, int viewSpeedX) {
        super();
        this.setX(viewWidth * 4 / 5);
        this.setY( 0 - viewHeight);
        this.setSpeedX(this.getSpeedX()-viewSpeedX);
        this.setSpeedY ( (int) (viewSpeedX * (Math.random() + 0.5)));
    }

    public void onCollision() {
        // Every subclass has to specify this itself
    }
}

