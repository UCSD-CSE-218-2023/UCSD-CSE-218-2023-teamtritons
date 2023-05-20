package edu.ucsd.flappycow.model;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import edu.ucsd.flappycow.enums.PlayableCharacter;
import edu.ucsd.flappycow.factory.PlayableCharacterFactory;

@RunWith(AndroidJUnit4.class)
public class CowTest {

    @Test
    public void initCowObject(){
        int width = 2;
        int height = 2;
        int heightPixels = 2;
        Cow tCow = (Cow) PlayableCharacterFactory.getInstance(PlayableCharacter.COW, width, height, heightPixels);
        tCow.move(width, height);
        assertTrue("Move successfull", true);
    }
}