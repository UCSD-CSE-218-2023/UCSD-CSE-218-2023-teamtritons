package edu.ucsd.flappycow.factory;

import edu.ucsd.flappycow.model.Accessory;
import edu.ucsd.flappycow.model.IAccessory;


public class AccessoryFactory {

    public static IAccessory getInstance(edu.ucsd.flappycow.enums.Accessory type) {
        IAccessory accessory = null;
        if(type.equals(edu.ucsd.flappycow.enums.Accessory.ACCESSORY)) {
            accessory = new Accessory();
        }
        return accessory;
    }
}
