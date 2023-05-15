package edu.ucsd.flappycow.enums;

public enum Tutorial {
    TUTORIAL("TUTORIAL");
    String tutorial;
    //Constructor to define name
    Tutorial(String tutorial) {
        this.tutorial = tutorial;
    }
    //override the inherited method
    @Override
    public String toString() {
        return tutorial;
    }
}
