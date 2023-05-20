package edu.ucsd.flappycow.factory;

import edu.ucsd.flappycow.enums.GameLevel;

public class GameLevelFactoryProvider {
    public static AbstractFactory getFactory(GameLevel level) {
        switch (level) {
            case LEVEL_1:
                return new Level1();
            case LEVEL_2:
                return new Level2();
            case LEVEL_3:
                return new Level3();
            default:
                throw new IllegalArgumentException("Game Level not supported yet");
        }
    }
}
