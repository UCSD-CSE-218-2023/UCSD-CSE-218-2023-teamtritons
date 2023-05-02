package edu.ucsd.flappycow.model;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import androidx.test.ext.junit.runners.AndroidJUnit4;

@RunWith(AndroidJUnit4.class)
public class SpiderTest {

    @Test
    public void initSpiderObject(){
        Spider tSpider = new Spider();
        tSpider.init(3, 4);
        assertTrue(tSpider.getX() == 3 && tSpider.getY() == 4);
    }

}
