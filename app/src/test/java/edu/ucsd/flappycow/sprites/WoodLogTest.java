package edu.ucsd.flappycow.sprites;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;

import edu.ucsd.flappycow.GameActivity;
import edu.ucsd.flappycow.GameView;

@RunWith(AndroidJUnit4.class)
public class WoodLogTest {
    @Test
    public void init() {
        GameActivity gameActivity = Robolectric.setupActivity(GameActivity.class);
        GameView gameView = new GameView(gameActivity);

        WoodLog woodLogT = new WoodLog(gameView, gameActivity);
        woodLogT.init(3,4);
        assertTrue(woodLogT.getX() == 3 && woodLogT.getY() == 4);
    }
}
