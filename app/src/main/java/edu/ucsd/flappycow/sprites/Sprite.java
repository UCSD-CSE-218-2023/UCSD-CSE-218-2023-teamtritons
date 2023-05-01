package edu.ucsd.flappycow.sprites;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Sprite implements IInteractable, IMovable {

    /** The bitmaps that holds the frames that should be drawn */
    private Bitmap bitmap;

    /** Height and width of one frame of the bitmap */
    private int height, width;

    /** x and y coordinates on the canvas */
    private int x, y;

    /** Horizontal and vertical speed of the sprite */
    private float speedX, speedY;

    /** The source frame of the bitmap that should be drawn */
    private Rect src;

    /** The destination area that the frame should be drawn to */
    private Rect dst;

    /** Coordinates of the frame in the spritesheet */
    private byte col, row;

    /** Number of columns the sprite has */
    private byte colNr = 1;

    /** How long a frame should be displayed */
    private short frameTime;

    /**
     * Counter for the frames
     * Cycling through the columns
     */
    private short frameTimeCounter;

    public Sprite() {
        frameTime = 1;
        src = new Rect();
        dst = new Rect();
    }

    public void onInitBitmap() {

    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public Rect getSrc() {
        return src;
    }

    public void setSrc(Rect src) {
        this.src = src;
    }

    public Rect getDst() {
        return dst;
    }

    public void setDst(Rect dst) {
        this.dst = dst;
    }

    public byte getCol() {
        return col;
    }

    public void setCol(byte col) {
        this.col = col;
    }

    public byte getRow() {
        return row;
    }

    public void setRow(byte row) {
        this.row = row;
    }

    public byte getColNr() {
        return colNr;
    }

    public void setColNr(byte colNr) {
        this.colNr = colNr;
    }

    public short getFrameTime() {
        return frameTime;
    }

    public void setFrameTime(short frameTime) {
        this.frameTime = frameTime;
    }

    public short getFrameTimeCounter() {
        return frameTimeCounter;
    }

    public void setFrameTimeCounter(short frameTimeCounter) {
        this.frameTimeCounter = frameTimeCounter;
    }

    /**
     * Draws the frame of the bitmap specified by col and row
     * at the position given by x and y
     * @param canvas Canvas that should be drawn on
     */
    @Override
    public void draw(Canvas canvas) {
        src.set(col * width, row * height, (col + 1) * width, (row + 1) * height);
        dst.set(x, y, x + width, y + height);
        canvas.drawBitmap(bitmap, src, dst, null);
    }

    /**
     * Modifies the x and y coordinates according to the speedX and speedY value
     */
    @Override
    public void move(int viewHeight, int viewWidth) {
        // changeToNextFrame();
        // Its more efficient if only the classes that need this implement it in their move method.

        x += speedX;
        y += speedY;
    }

    /**
     * Changes the frame by cycling through the columns.
     */
    @Override
    public void changeToNextFrame() {
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
    @Override
    public boolean isOutOfRange() {
        return this.x + width < 0;
    }

    /**
     * Checks whether the sprite is touching this.
     * Seeing the sprites as rectangles.
     * @param sprite
     * @return
     */
    @Override
    public boolean isColliding(Sprite sprite, int heightPixels) {
        if (this.x + getCollisionTolerance(heightPixels) < sprite.x + sprite.width
            && this.x + this.width > sprite.x + getCollisionTolerance(heightPixels)
            && this.y + getCollisionTolerance(heightPixels) < sprite.y + sprite.height
            && this.y + this.height > sprite.y + getCollisionTolerance(heightPixels)) {
            return true;
        }
        return false;
    }

    /**
     * What should be done, when the player collide with this sprite?
     */
    @Override
    public void onCollision() {
        // Every subclass has to specify this itself
    }

    /**
     * Checks whether the sprite is touching the ground or the sky.
     * @return
     */
    @Override
    public boolean isTouchingEdge(int viewHeight) {
        return isTouchingGround(viewHeight) || isTouchingSky();
    }

    /**
     * Checks whether the sprite is touching the ground.
     * @return
     */
    @Override
    public boolean isTouchingGround(int viewHeight) {
        return this.y + this.height > viewHeight - viewHeight * Frontground.getGroundHeight();
    }

    /**
     * Checks whether the sprite is touching the sky.
     * @return
     */
    @Override
    public boolean isTouchingSky() {
        return this.y < 0;
    }

    /**
     * Checks whether the play has passed this sprite.
     * @return
     */
    @Override
    public boolean isPassed(int viewPlayerX) {
        return this.x + this.width < viewPlayerX;
    }

    /**
          * Checks whether the sprite is touching this.
          * With the distance of the 2 centers.
          * @param sprite
          * @return
     * */
    @Override
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
    @Override
    public boolean isTouching(int x, int y) {
        return (x > this.x && x < this.x + width
            && y > this.y && y < this.y + height);
    }



    /**
     * Gives a value that will be tolerated when touching a sprite.
     * Because my images have some whitespace to the edge.
     * @return
     */
    @Override
    public int getCollisionTolerance(int heightPixels) {
        return heightPixels/50;
    }

}
