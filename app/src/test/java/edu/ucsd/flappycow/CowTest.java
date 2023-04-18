package edu.ucsd.flappycow;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import android.graphics.Canvas;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.internal.AndroidTestEnvironment;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import android.app.Application;
import android.content.Context;
import android.graphics.Canvas;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import edu.ucsd.flappycow.GameActivity;
import edu.ucsd.flappycow.GameView;
import edu.ucsd.flappycow.MainActivity;
import edu.ucsd.flappycow.sprites.Accessory;
import edu.ucsd.flappycow.sprites.Cow;

@RunWith(AndroidJUnit4.class)
public class CowTest {

    @Test
    public void initCowObject(){
        GameActivity gameActivity = Robolectric.setupActivity(GameActivity.class);
        GameView gameView = new GameView(gameActivity);
        Cow tCow = new Cow(gameView, gameActivity, new Accessory(gameView, gameActivity));
//        assertFalse(tAccessory.getX() == 0 && tAccessory.getY() == 0);
//        assertTrue(tAccessory.getX() == 3 && tAccessory.getY() == 4);
    }

    @Test
    public void checkTouchingSpider(){
        GameActivity gameActivity = Robolectric.setupActivity(GameActivity.class);
        GameView gameView = new GameView(gameActivity);
        Accessory tAccessory = new Accessory(gameView, gameActivity);
        assertTrue(tAccessory.isTouching(1, 1));
    }

}