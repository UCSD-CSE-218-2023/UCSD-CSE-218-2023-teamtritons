package edu.ucsd.flappycow.enums;

public enum Accessory {
    ACCESSORY("ACCESSORY");


    String accessory;
    //Constructor to define name
    Accessory(String accessory) {
        this.accessory = accessory;
    }
    //override the inherited method
    @Override
    public String toString() {
        return accessory;
    }
}
