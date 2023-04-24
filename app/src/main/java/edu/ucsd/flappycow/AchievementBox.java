/**
 * Saves achievements and score in shared preferences.
 * You should use a SQLite DB instead, but I'm too lazy to chance it now.
 *
 * @author Lars Harmsen
 * Copyright (c) <2014> <Lars Harmsen - Quchen>
 */

package edu.ucsd.flappycow;

import android.app.Activity;
import android.content.SharedPreferences;

public class AchievementBox<T> implements IObserver<T> {
    /**
     * Points needed for a gold medal
     */
    public static final int GOLD_POINTS = 100;

    /**
     * Points needed for a silver medal
     */
    public static final int SILVER_POINTS = 50;

    /**
     * Points needed for a bronze medal
     */
    public static final int BRONZE_POINTS = 10;

    public static final String SAVE_NAME = "achivements";

    public static final String KEY_POINTS = "points";
    public static final String ACHIEVEMENT_KEY_50_COINS = "achievement_survive_5_minutes";
    public static final String ACHIEVEMENT_KEY_TOASTIFICATION = "achievement_toastification";
    public static final String ACHIEVEMENT_KEY_BRONZE = "achievement_bronze";
    public static final String ACHIEVEMENT_KEY_SILVER = "achievement_silver";
    public static final String ACHIEVEMENT_KEY_GOLD = "achievement_gold";

    int points;
    boolean achievement_50_coins;
    boolean achievement_toastification;
    boolean achievement_bronze;
    boolean achievement_silver;
    boolean achievement_gold;

    /**
     * Stores the score and achievements locally.
     * <p>
     * The accomplishments will be saved local via SharedPreferences.
     * This makes it very easy to cheat.
     * <p>
     * todo: is activity the right thing to pass in here?
     *
     * @param activity activity that is needed for shared preferences
     */
    public void save(Activity activity) {
        SharedPreferences saves = activity.getSharedPreferences(SAVE_NAME, 0);
        SharedPreferences.Editor editor = saves.edit();

        if (points > saves.getInt(KEY_POINTS, 0)) {
            editor.putInt(KEY_POINTS, points);
        }
        if (achievement_50_coins) {
            editor.putBoolean(ACHIEVEMENT_KEY_50_COINS, true);
        }
        if (achievement_toastification) {
            editor.putBoolean(ACHIEVEMENT_KEY_TOASTIFICATION, true);
        }
        if (achievement_bronze) {
            editor.putBoolean(ACHIEVEMENT_KEY_BRONZE, true);
        }
        if (achievement_silver) {
            editor.putBoolean(ACHIEVEMENT_KEY_SILVER, true);
        }
        if (achievement_gold) {
            editor.putBoolean(ACHIEVEMENT_KEY_GOLD, true);
        }

        editor.apply();
    }

    /**
     * reads the local stored data
     *
     * @param activity activity that is needed for shared preferences
     * @return local stored score and achievements
     */
    public static AchievementBox load(Activity activity) {
        AchievementBox box = new AchievementBox();
        SharedPreferences saves = activity.getSharedPreferences(SAVE_NAME, 0);

        box.points = saves.getInt(KEY_POINTS, 0);
        box.achievement_50_coins = saves.getBoolean(ACHIEVEMENT_KEY_50_COINS, false);
        box.achievement_toastification = saves.getBoolean(ACHIEVEMENT_KEY_TOASTIFICATION, false);
        box.achievement_bronze = saves.getBoolean(ACHIEVEMENT_KEY_BRONZE, false);
        box.achievement_silver = saves.getBoolean(ACHIEVEMENT_KEY_SILVER, false);
        box.achievement_gold = saves.getBoolean(ACHIEVEMENT_KEY_GOLD, false);

        return box;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isAchievement_50_coins() {
        return achievement_50_coins;
    }

    public void setAchievement_50_coins(boolean achievement_50_coins) {
        this.achievement_50_coins = achievement_50_coins;
    }

    public boolean isAchievement_toastification() {
        return achievement_toastification;
    }

    public void setAchievement_toastification(boolean achievement_toastification) {
        this.achievement_toastification = achievement_toastification;
    }

    public boolean isAchievement_bronze() {
        return achievement_bronze;
    }

    public void setAchievement_bronze(boolean achievement_bronze) {
        this.achievement_bronze = achievement_bronze;
    }

    public boolean isAchievement_silver() {
        return achievement_silver;
    }

    public void setAchievement_silver(boolean achievement_silver) {
        this.achievement_silver = achievement_silver;
    }

    public boolean isAchievement_gold() {
        return achievement_gold;
    }

    public void setAchievement_gold(boolean achievement_gold) {
        this.achievement_gold = achievement_gold;
    }

    @Override
    public void onUpdate(T data) {

    }
}