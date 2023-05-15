package edu.ucsd.flappycow.enums;

public enum GameLevel {
    LEVEL_1("LEVEL_1"), LEVEL_2("LEVEL_2"), LEVEL_3("LEVEL_3");
    String gameLevel;
    //Constructor to define name
    GameLevel(String gameLevel) {
        this.gameLevel = gameLevel;
    }
    //override the inherited method
    @Override
    public String toString() {
        return gameLevel;
    }
}
