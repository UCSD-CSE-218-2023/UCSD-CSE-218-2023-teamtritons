package edu.ucsd.flappycow.factory;

import static edu.ucsd.flappycow.util.Contract.ensure;
import static edu.ucsd.flappycow.util.Contract.require;

import edu.ucsd.flappycow.enums.GameObstacle;
import edu.ucsd.flappycow.model.IGameObstacle;
import edu.ucsd.flappycow.model.Spider;
import edu.ucsd.flappycow.model.WoodLog;

public class GameObstacleFactory {
    public static IGameObstacle getInstance(GameObstacle type) {
        require(type != null, "Type is not null");
        IGameObstacle gameObstacle = null;
        if(type.equals(GameObstacle.SPIDER)) {
            gameObstacle = new Spider();
        } else if (type.equals(GameObstacle.WOODLOG)) {
            gameObstacle = new WoodLog();
        }
        ensure(gameObstacle != null, "gameObstacle should not be NULL");
        return gameObstacle;
    }
}
