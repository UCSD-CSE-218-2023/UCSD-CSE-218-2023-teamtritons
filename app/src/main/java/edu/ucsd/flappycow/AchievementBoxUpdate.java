package edu.ucsd.flappycow;

public class AchievementBoxUpdate {
    private String updatedKey;
    private String updatedValue;

    public AchievementBoxUpdate(String updatedKey, String updatedValue) {
        this.updatedKey = updatedKey;
        this.updatedValue = updatedValue;
    }
    public String getUpdatedKey() {
        return updatedKey;
    }

    public void setUpdatedKey(String updatedKey) {
        this.updatedKey = updatedKey;
    }

    public String getUpdatedValue() {
        return updatedValue;
    }

    public void setUpdatedValue(String updatedValue) {
        this.updatedValue = updatedValue;
    }
}
