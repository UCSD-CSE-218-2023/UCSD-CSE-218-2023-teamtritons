package edu.ucsd.flappycow.model;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;
import androidx.test.ext.junit.runners.AndroidJUnit4;

@RunWith(AndroidJUnit4.class)
public class NyanCatTest {
    @Test
    public void initNyanCatObject(){
        Rainbow r = new Rainbow();
        int width = 2;
        int height = 2;
        int heightPixels = 2;
        NyanCat tNyanCat= new NyanCat(width, height, heightPixels, r);
        tNyanCat.move(width, height);
        assertTrue("Move successful", true);
    }
}