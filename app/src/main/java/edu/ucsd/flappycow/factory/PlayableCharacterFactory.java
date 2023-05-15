package edu.ucsd.flappycow.factory;

import edu.ucsd.flappycow.consts.ApplicationConstants;
import edu.ucsd.flappycow.enums.Accessory;
import edu.ucsd.flappycow.enums.PlayableCharacter;
import edu.ucsd.flappycow.model.Cow;
import edu.ucsd.flappycow.model.IPlayableCharacter;
import edu.ucsd.flappycow.model.NyanCat;
import edu.ucsd.flappycow.model.Rainbow;

public class PlayableCharacterFactory {
    public static IPlayableCharacter getInstance(PlayableCharacter type, int width, int height, int heightPixels) {
        IPlayableCharacter playableCharacter = null;
        if(type.equals(PlayableCharacter.NYAN_CAT)) {
            playableCharacter = new NyanCat(width, height, heightPixels, new Rainbow());
        } else if (type.equals(PlayableCharacter.COW)) {
            playableCharacter = new Cow(width, height, heightPixels, AccessoryFactory.getInstance(Accessory.ACCESSORY));
        }
        return playableCharacter;
    }
}
