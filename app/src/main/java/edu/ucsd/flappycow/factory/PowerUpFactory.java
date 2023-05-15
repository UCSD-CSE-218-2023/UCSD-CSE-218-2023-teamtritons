package edu.ucsd.flappycow.factory;

import edu.ucsd.flappycow.enums.PowerUp;
import edu.ucsd.flappycow.model.Coin;
import edu.ucsd.flappycow.model.Toast;
import edu.ucsd.flappycow.model.Virus;
public class PowerUpFactory {
    public static edu.ucsd.flappycow.model.PowerUp getInstance(PowerUp type, int speedX, int viewWidth) {
        edu.ucsd.flappycow.model.PowerUp powerUp = null;
        if(type.equals(PowerUp.COIN)) {
            powerUp = new Coin(speedX, viewWidth);
        } else if (type.equals(PowerUp.TOAST)) {
            powerUp = new Toast(speedX, viewWidth);
        } else if (type.equals(PowerUp.VIRUS)) {
            powerUp = new Virus(speedX, viewWidth);
        }
        return powerUp;
    }
}
