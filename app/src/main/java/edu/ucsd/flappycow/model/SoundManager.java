package edu.ucsd.flappycow.model;

import android.media.AudioManager;
import android.media.SoundPool;

import edu.ucsd.flappycow.GameFacade;
import edu.ucsd.flappycow.view.GameActivity;
import edu.ucsd.flappycow.view.MainActivity;

/**
 * The SoundManager is responsible for managing the sound pool and playing of sound
 */
public class SoundManager {

    int sound;
    public static SoundPool soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);


    public int getSound() {
        return sound;
    }

    public void setSound(int sound) {
        this.sound = sound;
    }

    public SoundManager() {

    }

    public void playSound() {
        int sound = this.sound;
        soundPool.play(sound, MainActivity.volume, MainActivity.volume, 0, 0, 1);
    }

    public static SoundPool getSoundPool() {
        return soundPool;
    }

    public static void setSoundPool(SoundPool soundPool) {
        soundPool = soundPool;
    }
}
