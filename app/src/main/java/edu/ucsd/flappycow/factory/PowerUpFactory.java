package edu.ucsd.flappycow.factory;

import edu.ucsd.flappycow.enums.PowerUp;
import edu.ucsd.flappycow.model.Coin;
import edu.ucsd.flappycow.model.IPowerUp;
import edu.ucsd.flappycow.model.Toast;
import edu.ucsd.flappycow.model.Virus;
import static edu.ucsd.flappycow.util.Contract.require;
import static edu.ucsd.flappycow.util.Contract.ensure;

public class PowerUpFactory {
    public static IPowerUp getInstance(PowerUp type, int speedX, int viewWidth) {
        require(type != null, "Type is not null");
        require(speedX >= 0, "speedX is non negative");
        require(viewWidth >= 0, "viewWidth is non negative");
        IPowerUp powerUp = null;
        if(type.equals(PowerUp.COIN)) {
            powerUp = new Coin(speedX, viewWidth);
        } else if (type.equals(PowerUp.TOAST)) {
            powerUp = new Toast(speedX, viewWidth);
        } else if (type.equals(PowerUp.VIRUS)) {
            powerUp = new Virus(speedX, viewWidth);
        }
        ensure(powerUp != null, "PowerUp should not be NULL");
        return powerUp;
    }
}
