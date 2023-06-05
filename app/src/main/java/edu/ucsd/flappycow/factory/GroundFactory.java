package edu.ucsd.flappycow.factory;

import static edu.ucsd.flappycow.util.Contract.ensure;
import static edu.ucsd.flappycow.util.Contract.require;

import edu.ucsd.flappycow.enums.Ground;
import edu.ucsd.flappycow.model.Background;
import edu.ucsd.flappycow.model.Frontground;
import edu.ucsd.flappycow.model.IGround;

public class GroundFactory {
    public static IGround getInstance(Ground type) {
        require(type != null, "Type is not null");
        require(type instanceof edu.ucsd.flappycow.enums.Ground, "Type should be of type Ground");
        IGround ground = null;
        if(type.equals(Ground.BACKGROUND)) {
            ground = new Background();
        } else if (type.equals(Ground.FRONTGROUND)) {
            ground = new Frontground();
        }
        ensure(ground != null, "ground should not be NULL");
        return ground;
    }
}
