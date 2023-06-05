package edu.ucsd.flappycow.factory;


import static edu.ucsd.flappycow.util.Contract.ensure;
import static edu.ucsd.flappycow.util.Contract.require;

import edu.ucsd.flappycow.model.Tutorial;

public class TutorialFactory {

    public static Tutorial getInstance(edu.ucsd.flappycow.enums.Tutorial type) {
        require(type != null, "Type is not null");
        edu.ucsd.flappycow.model.Tutorial tutorial = null;
        if(type.equals(edu.ucsd.flappycow.enums.Tutorial.TUTORIAL)) {
            tutorial = new Tutorial();
        }
        ensure(tutorial != null, "tutorial should not be NULL");
        return tutorial;
    }
}
