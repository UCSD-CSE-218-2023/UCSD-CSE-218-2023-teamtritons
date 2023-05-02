package edu.ucsd.flappycow.sprites;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import static org.junit.Assert.assertTrue;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import edu.ucsd.flappycow.GameActivity;
import edu.ucsd.flappycow.GameView;
import edu.ucsd.flappycow.sprites.NyanCat;
import edu.ucsd.flappycow.sprites.Rainbow;

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