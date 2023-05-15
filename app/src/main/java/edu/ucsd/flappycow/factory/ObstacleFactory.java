package edu.ucsd.flappycow.factory;

import edu.ucsd.flappycow.enums.GameObstacle;
import edu.ucsd.flappycow.enums.SpriteObstacle;
import edu.ucsd.flappycow.model.IGameObstacle;
import edu.ucsd.flappycow.model.Obstacle;
import edu.ucsd.flappycow.model.Spider;
import edu.ucsd.flappycow.model.WoodLog;

public class ObstacleFactory {
    public static Obstacle getInstance(SpriteObstacle type, Spider spider, WoodLog woodLog, int widthPixels, int heightPixels, int speedX) {
        Obstacle obstacle = null;
        if(type.equals(SpriteObstacle.OBSTACLE)) {
            obstacle = new Obstacle(spider, woodLog, widthPixels, heightPixels, speedX);
        }
        return obstacle;
    }
}
