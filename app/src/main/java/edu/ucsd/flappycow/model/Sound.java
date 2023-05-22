package edu.ucsd.flappycow.model;

import edu.ucsd.flappycow.GameFacade;
import edu.ucsd.flappycow.view.MainActivity;

public class Sound {

    int sound;

    public int getSound() {
        return sound;
    }

    public void setSound(int sound) {
        this.sound = sound;
    }

    public Sound() {

    }

    public void playSound(GameFacade gameFacade) {
        int sound = this.sound;
        gameFacade.getGameActivity().getSoundPool().play(sound, MainActivity.volume, MainActivity.volume, 0, 0, 1);
    }
}
