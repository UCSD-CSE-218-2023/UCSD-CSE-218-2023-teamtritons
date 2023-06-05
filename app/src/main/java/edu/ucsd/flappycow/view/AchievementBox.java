/**
 * Saves achievements and score in shared preferences.
 * You should use a SQLite DB instead, but I'm too lazy to chance it now.
 *
 * @author Lars Harmsen
 * Copyright (c) <2014> <Lars Harmsen - Quchen>
 */

package edu.ucsd.flappycow.view;

import android.app.Activity;
import android.content.SharedPreferences;
import static edu.ucsd.flappycow.util.Contract.require;
import static edu.ucsd.flappycow.util.Contract.ensure;

import edu.ucsd.flappycow.consts.ApplicationConstants;

public class AchievementBox {
    /**
     * Points needed for a gold medal
     */
    private static final int GOLD_POINTS = 100;

    /**
     * Points needed for a silver medal
     */
    private static final int SILVER_POINTS = 50;

    /**
     * Points needed for a bronze medal
     */
    private static final int BRONZE_POINTS = 10;

    private static final String SAVE_NAME = "achivements";

    private static final String KEY_POINTS = "points";
    private static final String ACHIEVEMENT_KEY_50_COINS = "achievement_survive_5_minutes";
    private static final String ACHIEVEMENT_KEY_TOASTIFICATION = "achievement_toastification";
    private static final String ACHIEVEMENT_KEY_BRONZE = "achievement_bronze";
    private static final String ACHIEVEMENT_KEY_SILVER = "achievement_silver";
    private static final String ACHIEVEMENT_KEY_GOLD = "achievement_gold";

    private int points;
    private boolean achievement_50_coins;
    private boolean achievement_toastification;
    private boolean achievement_bronze;
    private boolean achievement_silver;
    private boolean achievement_gold;

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
    public void save(Activity activity) throws RuntimeException{
        try{
            require(activity != null, "Activity is not null");
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
            ensure(editor.commit(), "Saved to editor");
        } catch (RuntimeException e){
            e.printStackTrace();
        }



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

    public static int getGoldPoints() {
        return GOLD_POINTS;
    }

    public static int getSilverPoints() {
        return SILVER_POINTS;
    }

    public static int getBronzePoints() {
        return BRONZE_POINTS;
    }

    public static String getSaveName() {
        return SAVE_NAME;
    }

    public static String getKeyPoints() {
        return KEY_POINTS;
    }

    public static String getAchievementKey50Coins() {
        return ACHIEVEMENT_KEY_50_COINS;
    }

    public static String getAchievementKeyToastification() {
        return ACHIEVEMENT_KEY_TOASTIFICATION;
    }

    public static String getAchievementKeyBronze() {
        return ACHIEVEMENT_KEY_BRONZE;
    }

    public static String getAchievementKeySilver() {
        return ACHIEVEMENT_KEY_SILVER;
    }

    public static String getAchievementKeyGold() {
        return ACHIEVEMENT_KEY_GOLD;
    }

//    @Override
//    public void onUpdate(AchievementBoxUpdate data) {
//        String key = data.getUpdatedKey();
//        String value = data.getUpdatedValue();
//
//        if (key == ApplicationConstants.POINTS) {
//            setPoints(Integer.parseInt(value));
//        } else if (key == ApplicationConstants.ACHIEVEMENT_50_COINS) {
//            setAchievement_50_coins(Boolean.parseBoolean(value));
//        } else if (key == ApplicationConstants.ACHIEVEMENT_BRONZE) {
//            setAchievement_bronze(Boolean.parseBoolean(value));
//        } else if (key == ApplicationConstants.ACHIEVEMENT_SILVER) {
//            setAchievement_silver(Boolean.parseBoolean(value));
//        } else if (key == ApplicationConstants.ACHIEVEMENT_GOLD) {
//            setAchievement_gold(Boolean.parseBoolean(value));
//        }
//    }
}