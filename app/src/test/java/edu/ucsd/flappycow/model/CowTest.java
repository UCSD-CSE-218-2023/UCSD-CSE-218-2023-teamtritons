package edu.ucsd.flappycow.model;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;
import androidx.test.ext.junit.runners.AndroidJUnit4;

@RunWith(AndroidJUnit4.class)
public class CowTest {

    @Test
    public void initCowObject(){
        Accessory accessory = new Accessory();
        int width = 2;
        int height = 2;
        int heightPixels = 2;
        Cow tCow = new Cow(width, height, heightPixels, accessory);
        tCow.move(width, height);
        assertTrue("Move successfull", true);
    }
}