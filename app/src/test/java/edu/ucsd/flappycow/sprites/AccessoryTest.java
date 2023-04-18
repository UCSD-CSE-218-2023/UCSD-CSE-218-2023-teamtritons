package edu.ucsd.flappycow;

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
import edu.ucsd.flappycow.sprites.Accessory;

@RunWith(AndroidJUnit4.class)
public class AccessoryTest {

    @Test
    public void initAccessoryObject(){
        GameActivity gameActivity = Robolectric.setupActivity(GameActivity.class);
        GameView gameView = new GameView(gameActivity);
        Accessory tAccessory = new Accessory(gameView, gameActivity);
        tAccessory.moveTo(3,4);
        assertFalse(tAccessory.getX() == 0 && tAccessory.getY() == 0);
        assertTrue(tAccessory.getX() == 3 && tAccessory.getY() == 4);
    }

    @Test
    public void checkMoveAccessory(){
        GameActivity gameActivity = Robolectric.setupActivity(GameActivity.class);
        GameView gameView = new GameView(gameActivity);
        Accessory tAccessory = new Accessory(gameView, gameActivity);
        tAccessory.moveTo(10, 11);
        assertFalse(tAccessory.getX() == 3 && tAccessory.getY() == 4);
        assertTrue(tAccessory.getX() == 10 && tAccessory.getY() == 11);
    }

}