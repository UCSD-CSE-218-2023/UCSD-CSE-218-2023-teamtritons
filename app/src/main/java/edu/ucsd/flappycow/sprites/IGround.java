package edu.ucsd.flappycow.sprites;

import android.graphics.Canvas;

import edu.ucsd.flappycow.GameActivity;
import edu.ucsd.flappycow.GameView;

public abstract class IGround extends Sprite {

    public IGround() {
        super();
    }

    /**
     * Draws the bitmap to the Canvas.
     * The height of the bitmap will be scaled to the height of the canvas.
     * When the bitmap is scrolled to far to the left, so it won't cover the whole screen,
     * the bitmap will be drawn another time behind the first one.
     */
    @Override
    public void draw(Canvas canvas) {
        double factor = (1.0 * canvas.getHeight()) / this.getBitmap().getHeight();

        if (-this.getX() > this.getBitmap().getWidth()) {
            // The first bitmap is completely out of the screen
            this.setX(this.getX() + this.getBitmap().getWidth());

        }

        int endBitmap = Math.min(-this.getX() + (int) (canvas.getWidth() / factor), this.getBitmap().getWidth());
        int endCanvas = (int) ((endBitmap + this.getX()) * factor) + 1;
        this.getSrc().set(-this.getX(), 0, endBitmap, this.getBitmap().getHeight());
        this.getDst().set(0, 0, endCanvas, canvas.getHeight());
        canvas.drawBitmap(this.getBitmap(), this.getSrc(), this.getDst(), null);

        if (endBitmap == this.getBitmap().getWidth()) {
            // draw second bitmap
            this.getSrc().set(0, 0, (int) (canvas.getWidth() / factor), this.getBitmap().getHeight());
            this.getDst().set(endCanvas, 0, endCanvas + canvas.getWidth(), canvas.getHeight());
            canvas.drawBitmap(this.getBitmap(), this.getSrc(), this.getDst(), null);
        }
    }
}
