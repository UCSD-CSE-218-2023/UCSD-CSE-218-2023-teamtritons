package edu.ucsd.flappycow.factory;

import edu.ucsd.flappycow.enums.GameObstacle;
import edu.ucsd.flappycow.model.IGameObstacle;
import edu.ucsd.flappycow.model.Spider;
import edu.ucsd.flappycow.model.WoodLog;

public class GameObstacleFactory {
    public static IGameObstacle getInstance(GameObstacle type) {
        IGameObstacle gameObstacle = null;
        if(type.equals(GameObstacle.SPIDER)) {
            gameObstacle = new Spider();
        } else if (type.equals(GameObstacle.WOODLOG)) {
            gameObstacle = new WoodLog();
        }
        return gameObstacle;
    }
}
