package edu.ucsd.flappycow.builder;

import edu.ucsd.flappycow.enums.PlayableCharacter;
import edu.ucsd.flappycow.factory.AccessoryFactory;
import edu.ucsd.flappycow.factory.PlayableCharacterFactory;
import edu.ucsd.flappycow.model.Accessory;
import edu.ucsd.flappycow.model.Cow;

public class CowBuilder {
    public static Cow getInstance(int width, int height, int heightPixels) {

        Accessory accessory =
                (Accessory) AccessoryFactory.getInstance(edu.ucsd.flappycow.enums.Accessory.ACCESSORY);
        return new Cow(width, height, heightPixels, accessory);

    }
}
