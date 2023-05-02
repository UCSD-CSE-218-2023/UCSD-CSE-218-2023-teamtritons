package edu.ucsd.flappycow.model;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

@RunWith(AndroidJUnit4.class)
public class PauseButtonTest {

    @Test
    public void move() {
        int width = 2;
        int height = 2;

        PauseButton pauseButton = new PauseButton();
        pauseButton.move(width, height);
        //if it reaches here, move executed with no errors
        boolean move = true;
        assertTrue("move succeessful", move);

    }
}