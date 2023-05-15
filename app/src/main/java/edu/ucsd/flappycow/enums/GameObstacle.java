package edu.ucsd.flappycow.enums;

public enum GameObstacle {
    SPIDER("SPIDER"), WOODLOG("WOODLOG");
    String gameObstacle;
    //Constructor to define name
    GameObstacle(String powerUp) {
        this.gameObstacle = gameObstacle;
    }
    //override the inherited method
    @Override
    public String toString() {
        return gameObstacle;
    }
}
