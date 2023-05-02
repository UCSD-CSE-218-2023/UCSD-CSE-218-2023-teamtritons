package edu.ucsd.flappycow.presenter;

import edu.ucsd.flappycow.view.AchievementBoxUpdate;
import edu.ucsd.flappycow.view.IObserver;
//import edu.ucsd.flappycow.Subject;


public class GameActivityAchievementBoxPresenter implements IObserver<AchievementBoxUpdate> {
    // observer - view
    // subject - model
//    private AchievementBox achievementBox;
//
//    public GameActivityAchievementBoxPresenter(Subject subject, AchievementBox achievementBox) {
//        subject.register(this);
//        this.achievementBox = achievementBox;
//    }
//
    @Override
    public void onUpdate(AchievementBoxUpdate data) {
//        String key = data.getUpdatedKey();
//        String value = data.getUpdatedValue();
//
//        if (key == ApplicationConstants.POINTS) {
//            achievementBox.setPoints(Integer.parseInt(value));
//        } else if (key == ApplicationConstants.ACHIEVEMENT_50_COINS) {
//            achievementBox.setAchievement_50_coins(Boolean.parseBoolean(value));
//        } else if (key == ApplicationConstants.ACHIEVEMENT_BRONZE) {
//            achievementBox.setAchievement_bronze(Boolean.parseBoolean(value));
//        } else if (key == ApplicationConstants.ACHIEVEMENT_SILVER) {
//            achievementBox.setAchievement_silver(Boolean.parseBoolean(value));
//        } else if (key == ApplicationConstants.ACHIEVEMENT_GOLD) {
//            achievementBox.setAchievement_gold(Boolean.parseBoolean(value));
//        }
    }
//
//    public AchievementBox getAchievementBox() {
//        return achievementBox;
//    }
}
