package edu.ucsd.flappycow;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class TimerHandler {
    private Timer timer;
    private TimerTask timerTask;
    private long updateInterval;

    public TimerHandler(long updateInterval) {
        this.timer = new Timer();
        this.updateInterval = updateInterval;
    }

    public void startTimer(GameView gameView) {
        setUpTimerTask(gameView);
        timer = new Timer();
        timer.schedule(timerTask, updateInterval, updateInterval);
    }

    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
        if (timerTask != null) {
            timerTask.cancel();
        }
    }

    public void setUpTimerTask(GameView gameView) {
        stopTimer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                gameView.run();
            }
        };
    }
}
