package edu.ucsd.flappycow.enums;

public enum PowerUp {
    COIN("COIN"), TOAST("TOAST"), VIRUS("VIRUS");
    String powerUp;
    //Constructor to define name
    PowerUp(String powerUp) {
        this.powerUp = powerUp;
    }
    //override the inherited method
    @Override
    public String toString() {
        return powerUp;
    }
}
