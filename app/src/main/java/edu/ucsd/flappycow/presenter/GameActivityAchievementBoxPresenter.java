package edu.ucsd.flappycow.presenter;

import edu.ucsd.flappycow.consts.ApplicationConstants;
import edu.ucsd.flappycow.view.AchievementBox;
import edu.ucsd.flappycow.view.AchievementBoxUpdate;
import edu.ucsd.flappycow.util.IObserver;
import edu.ucsd.flappycow.util.Subject;
import static edu.ucsd.flappycow.util.Contract.require;
import static edu.ucsd.flappycow.util.Contract.ensure;


public class GameActivityAchievementBoxPresenter implements IObserver<AchievementBoxUpdate> {
    // observer - view
    // subject - model
    private AchievementBox achievementBox;

    public GameActivityAchievementBoxPresenter(Subject gameActivity, AchievementBox achievementBox) {
        gameActivity.register(this);
        this.achievementBox = achievementBox;
    }
    @Override
    public void onUpdate(AchievementBoxUpdate data) {
        require(data.getUpdatedKey().getClass().getName() == "String", "key in data is of type string");
        require(data.getUpdatedValue().getClass().getName() == "String", "value in data is of type string");
        require(data.getUpdatedKey() != null, "key in data is not null");
        require(data.getUpdatedValue() != null, "value in data is not null");

        String key = data.getUpdatedKey();
        String value = data.getUpdatedValue();

        if (key == ApplicationConstants.POINTS) {
            achievementBox.setPoints(Integer.parseInt(value));
            ensure(achievementBox.getPoints() == Integer.parseInt(value), "points is set to value");
        } else if (key == ApplicationConstants.ACHIEVEMENT_50_COINS) {
            achievementBox.setAchievement_50_coins(Boolean.parseBoolean(value));
            ensure(getAchievementBox().isAchievement_50_coins() == Boolean.parseBoolean(value), "points is set to value");
        } else if (key == ApplicationConstants.ACHIEVEMENT_BRONZE) {
            achievementBox.setAchievement_bronze(Boolean.parseBoolean(value));
            ensure(getAchievementBox().isAchievement_bronze() == Boolean.parseBoolean(value), "points is set to value");
        } else if (key == ApplicationConstants.ACHIEVEMENT_SILVER) {
            achievementBox.setAchievement_silver(Boolean.parseBoolean(value));
            ensure(achievementBox.isAchievement_silver() == Boolean.parseBoolean(value), "points is set to value");
        } else if (key == ApplicationConstants.ACHIEVEMENT_GOLD) {
            achievementBox.setAchievement_gold(Boolean.parseBoolean(value));
            ensure(achievementBox.isAchievement_gold() == Boolean.parseBoolean(value), "points is set to value");
        }
    }
    public AchievementBox getAchievementBox() {
        return achievementBox;
    }
}
