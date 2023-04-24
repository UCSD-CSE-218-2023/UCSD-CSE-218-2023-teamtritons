package edu.ucsd.flappycow.sprites;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.internal.AndroidTestEnvironment;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import android.app.Application;
import android.content.Context;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import edu.ucsd.flappycow.GameActivity;
import edu.ucsd.flappycow.GameView;
import edu.ucsd.flappycow.MainActivity;
import edu.ucsd.flappycow.sprites.Spider;

@RunWith(AndroidJUnit4.class)
public class SpiderTest {

    @Test
    public void initSpiderObject(){
        GameActivity gameActivity = Robolectric.setupActivity(GameActivity.class);
        GameView gameView = new GameView(gameActivity);

        Spider tSpider = new Spider(gameView, gameActivity);

        tSpider.init(3, 4);

//        assertFalse(tSpider.getX() == 0 && tSpider.getY() == 0);
        assertTrue(tSpider.getX() == 3 && tSpider.getY() == 4);
    }

    @Test
    public void checkTouchingSpider(){

        GameActivity gameActivity = Robolectric.setupActivity(GameActivity.class);
        GameView gameView = new GameView(gameActivity);

        Spider tSpider = new Spider(gameView, gameActivity);

        assertTrue(tSpider.isTouching(1, 1));
    }

}
