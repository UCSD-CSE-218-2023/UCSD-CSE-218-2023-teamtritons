package edu.ucsd.flappycow.factory;


import edu.ucsd.flappycow.model.Tutorial;

public class TutorialFactory {

    public static Tutorial getInstance(edu.ucsd.flappycow.enums.Tutorial type) {
        edu.ucsd.flappycow.model.Tutorial tutorial = null;
        if(type.equals(edu.ucsd.flappycow.enums.Tutorial.TUTORIAL)) {
            tutorial = new Tutorial();
        }
        return tutorial;
    }
}
