package edu.ucsd.flappycow.factory;

import edu.ucsd.flappycow.consts.ApplicationConstants;
import edu.ucsd.flappycow.enums.PowerUp;
import edu.ucsd.flappycow.model.Accessory;
import edu.ucsd.flappycow.model.Coin;
import edu.ucsd.flappycow.model.Cow;
import edu.ucsd.flappycow.model.IPlayableCharacter;
import edu.ucsd.flappycow.model.IPowerUp;
import edu.ucsd.flappycow.model.NyanCat;
import edu.ucsd.flappycow.model.Rainbow;
import edu.ucsd.flappycow.model.Toast;
import edu.ucsd.flappycow.model.Virus;

public class PowerUpFactory {
    public static IPowerUp getInstance(PowerUp type, int speedX, int viewWidth) {
        IPowerUp powerUp = null;
        if(type.equals(PowerUp.COIN)) {
            powerUp = (IPowerUp) new Coin(speedX, viewWidth);
        } else if (type.equals(PowerUp.TOAST)) {
            powerUp = (IPowerUp) new Toast(speedX, viewWidth);
        } else if (type.equals(PowerUp.VIRUS)) {
            powerUp = (IPowerUp) new Virus(speedX, viewWidth);
        }
        return powerUp;
    }
}
