package edu.ucsd.flappycow.factory;

import static edu.ucsd.flappycow.util.Contract.ensure;
import static edu.ucsd.flappycow.util.Contract.require;

import edu.ucsd.flappycow.model.Accessory;
import edu.ucsd.flappycow.model.IAccessory;


public class AccessoryFactory {
    public static IAccessory getInstance(edu.ucsd.flappycow.enums.Accessory type) {
        require(type != null, "Type is not null");
        IAccessory accessory = null;
        if(type.equals(edu.ucsd.flappycow.enums.Accessory.ACCESSORY)) {
            accessory = new Accessory();
        }
        ensure(accessory != null, "accessory should not be NULL");
        return accessory;
    }
}
