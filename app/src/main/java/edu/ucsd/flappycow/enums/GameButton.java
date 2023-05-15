package edu.ucsd.flappycow.enums;

public enum GameButton {

    PAUSEBUTTON("PAUSEBUTTON");


    String gameButton;
    //Constructor to define name
    GameButton(String gameButton) {
        this.gameButton = gameButton;
    }
    //override the inherited method
    @Override
    public String toString() {
        return gameButton;
    }
}
