package edu.ucsd.flappycow;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import edu.ucsd.flappycow.consts.ApplicationConstants;
import edu.ucsd.flappycow.view.AchievementBox;
import edu.ucsd.flappycow.view.AchievementBoxUpdate;
import edu.ucsd.flappycow.view.GameActivity;

@RunWith(AndroidJUnit4.class)
public class GameActivityTest {
    @Test
    public void decreasePointsTest(){
        AchievementBoxUpdate mockData = new AchievementBoxUpdate(ApplicationConstants.POINTS, "1");
        AchievementBox achievementBox = new AchievementBox();
        achievementBox.setPoints(2);

        GameActivity gameActivityTest = new GameActivity();
        gameActivityTest.getGameActivitySub().register(achievementBox);

        gameActivityTest.notifyObserver(mockData);

        assertTrue(achievementBox.getPoints() == 1);
        assertThat(achievementBox.getPoints(), is(instanceOf(Integer.class)));
    }
}
