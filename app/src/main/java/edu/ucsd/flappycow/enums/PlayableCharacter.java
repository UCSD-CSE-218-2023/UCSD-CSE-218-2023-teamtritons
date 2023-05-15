package edu.ucsd.flappycow.enums;

public enum PlayableCharacter {
    COW("COW"),
    NYAN_CAT("NYAN_CAT");

    String playableCharacter;
    //Constructor to define name
    PlayableCharacter(String playableCharacter) {
        this.playableCharacter = playableCharacter;
    }
    //override the inherited method
    @Override
    public String toString() {
        return playableCharacter;
    }

}
