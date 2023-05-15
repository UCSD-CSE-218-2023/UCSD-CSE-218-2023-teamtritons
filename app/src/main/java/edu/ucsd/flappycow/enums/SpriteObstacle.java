package edu.ucsd.flappycow.enums;

public enum SpriteObstacle {
    OBSTACLE("OBSTACLE");
    String gameObstacle;
    //Constructor to define name
    SpriteObstacle(String gameObstacle) {
        this.gameObstacle = gameObstacle;
    }
    //override the inherited method
    @Override
    public String toString() {
        return gameObstacle;
    }
}
