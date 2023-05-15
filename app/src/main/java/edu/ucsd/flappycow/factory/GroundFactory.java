package edu.ucsd.flappycow.factory;

import edu.ucsd.flappycow.enums.Ground;
import edu.ucsd.flappycow.enums.PowerUp;
import edu.ucsd.flappycow.model.Background;
import edu.ucsd.flappycow.model.Coin;
import edu.ucsd.flappycow.model.Frontground;
import edu.ucsd.flappycow.model.IGround;
import edu.ucsd.flappycow.model.IPowerUp;
import edu.ucsd.flappycow.model.Toast;
import edu.ucsd.flappycow.model.Virus;

public class GroundFactory {
    public static IGround getInstance(Ground type) {
        IGround ground = null;
        if(type.equals(Ground.BACKGROUND)) {
            ground = new Background();
        } else if (type.equals(Ground.FRONTGROUND)) {
            ground = new Frontground();
        }
        return ground;
    }
}
