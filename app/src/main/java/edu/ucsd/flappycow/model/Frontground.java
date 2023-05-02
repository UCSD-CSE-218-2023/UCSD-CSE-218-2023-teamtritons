package edu.ucsd.flappycow.model;

public class Frontground extends IGround {
    /**
     * Height of the ground relative to the height of the bitmap
     */
    private static final float GROUND_HEIGHT = (1f * /*45*/ 35) / 720;

    public static float getGroundHeight() {
        return GROUND_HEIGHT;
    }



}
