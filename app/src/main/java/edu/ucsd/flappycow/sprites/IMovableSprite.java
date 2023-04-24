package edu.ucsd.flappycow.sprites;

import android.graphics.Canvas;
import android.graphics.Rect;

import edu.ucsd.flappycow.GameActivity;
import edu.ucsd.flappycow.GameView;

public class IMovableSprite extends Sprite {
    /** The source frame of the bitmap that should be drawn */
    protected Rect src;

    /** The destination area that the frame should be drawn to */
    protected Rect dst;

    /** Coordinates of the frame in the spritesheet */
    protected byte col, row;

    /** Number of columns the sprite has */
    protected byte colNr = 1;

    /** How long a frame should be displayed */
    protected short frameTime;

    /**
     * Counter for the frames
     * Cycling through the columns
     */
    protected short frameTimeCounter;



    public IMovableSprite(GameView view, GameActivity gameActivity) {
        super(view, gameActivity);
        this.frameTime = 1;
        this.src = new Rect();
        this.dst = new Rect();
    }

    public void draw(Canvas canvas) {
        src.set(col * width, row * height, (col + 1) * width, (row + 1) * height);
        dst.set(x, y, x + width, y + height);
        canvas.drawBitmap(bitmap, src, dst, null);
    }

    /**
     * Modifies the x and y coordinates according to the speedX and speedY value
     */
    public void move() {
        // changeToNextFrame();
        // Its more efficient if only the classes that need this implement it in their move method.

        x += speedX;
        y += speedY;
    }

    /**
     * Changes the frame by cycling through the columns.
     */
    protected void changeToNextFrame() {
        this.frameTimeCounter++;
        if (this.frameTimeCounter >= this.frameTime) {
            this.col = (byte) ((this.col + 1) % this.colNr);
            this.frameTimeCounter = 0;
        }
    }

    /**
     * Checks whether this sprite is so far to the left, it's not visible anymore.
     * @return
     */
    public boolean isOutOfRange() {
        return this.x + width < 0;
    }
}
