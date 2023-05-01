package edu.ucsd.flappycow.presenter;

import android.app.Activity;
import android.media.MediaPlayer;

import edu.ucsd.flappycow.GameActivity;
import edu.ucsd.flappycow.MainActivity;
import edu.ucsd.flappycow.R;

public class MediaPlayerPresenter extends Activity {

    MediaPlayer musicPlayer;
    public boolean musicShouldPlay = false;

    GameActivity gameActivity;
    //TODO : check how to remove view dependency in this

    public MediaPlayerPresenter(GameActivity gameActivity) {
        this.gameActivity = gameActivity;

        if (musicPlayer == null) {
            // to avoid unnecessary reinitialisation
            musicPlayer = MediaPlayer.create(gameActivity, R.raw.nyan_cat_theme);
            if (musicPlayer == null) {
                return;
            }
            musicPlayer.setLooping(true);
            musicPlayer.setVolume(MainActivity.volume, MainActivity.volume);
        }
        musicPlayer.seekTo(0);    // Reset song to position 0
    }

    @Override
    protected void onPause() {
        gameActivity.getView().pause();
        if (musicPlayer != null && musicPlayer.isPlaying()) {
            musicPlayer.pause();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        gameActivity.getView().drawOnce();
        if (musicPlayer != null && musicShouldPlay) {
            musicPlayer.start();
        }
        super.onResume();
    }

    public MediaPlayer getMusicPlayer() {
        return musicPlayer;
    }

    public void setMusicPlayer(MediaPlayer musicPlayer) {
        this.musicPlayer = musicPlayer;
    }
}
