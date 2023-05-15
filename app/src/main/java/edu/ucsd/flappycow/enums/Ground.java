package edu.ucsd.flappycow.enums;

public enum Ground {
    FRONTGROUND("FRONTGROUND"),
    BACKGROUND("BACKGROUND");

    String ground;
    //Constructor to define name
    Ground(String ground) {
        this.ground = ground;
    }
    //override the inherited method
    @Override
    public String toString() {
        return ground;
    }

}
