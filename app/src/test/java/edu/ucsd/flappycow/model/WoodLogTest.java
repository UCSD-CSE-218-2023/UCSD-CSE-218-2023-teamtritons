package edu.ucsd.flappycow.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import static org.junit.Assert.assertTrue;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import edu.ucsd.flappycow.view.GameActivity;
import edu.ucsd.flappycow.view.GameView;

@RunWith(AndroidJUnit4.class)
public class WoodLogTest {
    @Test
    public void init() {
        GameActivity gameActivity = Robolectric.setupActivity(GameActivity.class);
        GameView gameView = new GameView(gameActivity);

        WoodLog woodLogT = new WoodLog();
        woodLogT.init(3,4);
        assertTrue(woodLogT.getX() == 3 && woodLogT.getY() == 4);
    }
}
