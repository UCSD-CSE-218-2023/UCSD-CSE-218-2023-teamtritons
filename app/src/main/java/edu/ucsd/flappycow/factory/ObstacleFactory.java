package edu.ucsd.flappycow.factory;

import static edu.ucsd.flappycow.util.Contract.ensure;
import static edu.ucsd.flappycow.util.Contract.require;

import edu.ucsd.flappycow.enums.SpriteObstacle;
import edu.ucsd.flappycow.model.IGameObstacle;
import edu.ucsd.flappycow.model.Obstacle;

public class ObstacleFactory {
    public static Obstacle getInstance(SpriteObstacle type, IGameObstacle spider, IGameObstacle woodLog, int widthPixels, int heightPixels, int speedX) {
        require(type != null, "Type is not null");
        require(type instanceof edu.ucsd.flappycow.enums.SpriteObstacle, "Type should be of type SpriteObstacle");
        Obstacle obstacle = null;
        if(type.equals(SpriteObstacle.OBSTACLE)) {
            obstacle = new Obstacle.ObstacleBuilder()
                    .setSpider(spider)
                    .setWoodLog(woodLog)
                    .setWidthPixels(widthPixels)
                    .setHeightPixels(heightPixels)
                    .setSpeedX(speedX)
                    .build();
        }
        ensure(obstacle != null, "obstacle should not be NULL");
        return obstacle;
    }
}
