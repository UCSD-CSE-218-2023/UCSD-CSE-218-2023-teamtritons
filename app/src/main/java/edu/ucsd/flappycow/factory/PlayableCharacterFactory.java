package edu.ucsd.flappycow.factory;

import edu.ucsd.flappycow.enums.Accessory;
import edu.ucsd.flappycow.enums.PlayableCharacter;
import edu.ucsd.flappycow.model.Cow;
import edu.ucsd.flappycow.model.IPlayableCharacter;
import edu.ucsd.flappycow.model.NyanCat;
import edu.ucsd.flappycow.model.Obstacle;
import edu.ucsd.flappycow.model.Rainbow;

public class PlayableCharacterFactory {
    public static IPlayableCharacter getInstance(PlayableCharacter type, int width, int height, int heightPixels) {
        IPlayableCharacter playableCharacter = null;
        if(type.equals(PlayableCharacter.NYAN_CAT)) {
            playableCharacter = new NyanCat.NyanCatBuilder().
                    setViewWidth(width)
                    .setViewHeight(height)
                    .setHeightPixels(heightPixels)
                    .setRainbow(new Rainbow())
                    .build();
        } else if (type.equals(PlayableCharacter.COW)) {
            playableCharacter = new Cow.CowBuilder()
                    .setAccessory(AccessoryFactory.getInstance(Accessory.ACCESSORY))
                    .setWidth(width)
                    .setHeightPixels(heightPixels)
                    .setHeight(height)
                    .build();
        }
        return playableCharacter;
    }
}
