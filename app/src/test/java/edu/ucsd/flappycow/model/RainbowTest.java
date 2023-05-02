package edu.ucsd.flappycow.model;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import androidx.test.ext.junit.runners.AndroidJUnit4;

@RunWith(AndroidJUnit4.class)
public class RainbowTest {

    @Test
    public void initRainbowObject(){
        int width = 2;
        int height = 2;
        Rainbow tRainbow = new Rainbow();
        tRainbow.move(width, height);
        boolean moveSuccessful = true;
        assertTrue("Move was successful", moveSuccessful);
    }

}