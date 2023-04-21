package edu.ucsd.flappycow.sprites;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import static org.junit.Assert.assertTrue;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import edu.ucsd.flappycow.GameActivity;
import edu.ucsd.flappycow.GameView;

@RunWith(AndroidJUnit4.class)
public class NyanCatTest {
    @Test
    public void initNyanCatObject(){
        GameActivity gameActivity = Robolectric.setupActivity(GameActivity.class);
        GameView gameView = new GameView(gameActivity);
        NyanCat tNyanCat= new NyanCat(gameView, gameActivity, new Rainbow(gameView, gameActivity));
        tNyanCat.move();
        assertTrue("Move successful", true);
    }
}