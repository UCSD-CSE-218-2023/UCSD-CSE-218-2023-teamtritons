package edu.ucsd.flappycow.model;

import android.graphics.Canvas;

public class Obstacle extends Sprite {
    private IGameObstacle spider;
    private IGameObstacle log;

    private static int collideSound = -1;
    private static int passSound = -1;

    /** Necessary so the onPass method is just called once */
    public boolean isAlreadyPassed = false;

    private Obstacle(ObstacleBuilder obstacleBuilder) {
        this.spider = obstacleBuilder.spider;
        this.log = obstacleBuilder.log;

        // TODO: presenter
//        if (collideSound == -1) {
//            collideSound = GameActivity.soundPool.load(gameActivity, R.raw.crash, 1);
//        }
//        // TODO: presenter
//        if (passSound == -1) {
//            passSound = GameActivity.soundPool.load(gameActivity, R.raw.pass, 1);
//        }

        initPos(obstacleBuilder.widthPixels, obstacleBuilder.heightPixels, obstacleBuilder.speedX);
    }

    /**
     * Creates a spider and a wooden log at the right of the screen.
     * With a certain gap between them.
     * The vertical position is in a certain area random.
     */
    private void initPos(int widthPixels, int heightPixels, int speedX) {
        int height = heightPixels;
        int gab = height / 4 - speedX;
        if (gab < height / 5) {
            gab = height / 5;
        }
        int random = (int) (Math.random() * height * 2 / 5);
        int y1 = (height / 10) + random - spider.getHeight();
        int y2 = (height / 10) + random + gab;

        spider.init(widthPixels, y1);
        log.init(widthPixels, y2);
    }

    /**
     * Draws spider and log.
     */
    @Override
    public void draw(Canvas canvas) {
        spider.draw(canvas);
        log.draw(canvas);
    }

    /**
     * Checks whether both, spider and log, are out of range.
     */
    @Override
    public boolean isOutOfRange() {
        return spider.isOutOfRange() && log.isOutOfRange();
    }

    /**
     * Checks whether the spider or the log is colliding with the sprite.
     */
    @Override
    public boolean isColliding(Sprite sprite, int heightPixels) {
        return spider.isColliding(sprite, heightPixels) || log.isColliding(sprite, heightPixels);
    }

    /**
     * Moves both, spider and log.
     */
    @Override
    public void move(int viewWidth, int viewHeight) {
        spider.move(viewWidth, viewHeight);
        log.move(viewWidth, viewHeight);
    }

    /**
     * Sets the speed of the spider and the log.
     */
    @Override
    public void setSpeedX(float speedX) {
        spider.setSpeedX(speedX);
        log.setSpeedX(speedX);
    }

    /**
     * Checks whether the spider and the log are passed.
     */
    @Override
    public boolean isPassed(int speedX) {
        return spider.isPassed(speedX) && log.isPassed(speedX);
    }

    private static final int SOUND_VOLUME_DIVIDER = 3;

    public static int getSoundVolumeDivider() {
        return SOUND_VOLUME_DIVIDER;
    }

    public static int getPassSound() {
        return passSound;
    }

    public static void setPassSound(int ps) {
        passSound = ps;
    }


    /**
     * Will call obstaclePassed of the game, if this is the first pass of this obstacle.
     */
    // TODO: presenter
    public void onPass() {
        if (!isAlreadyPassed) {
            isAlreadyPassed = true;
            // TODO: presenter
//            this.getView().getGameActivity().increasePoints();
//            GameActivity.soundPool.play(passSound, MainActivity.volume / SOUND_VOLUME_DIVIDER, MainActivity.volume / SOUND_VOLUME_DIVIDER, 0, 0, 1);
        }
    }

    // TODO: presenter
    @Override
    public void onCollision() {
        super.onCollision();
//        GameActivity.soundPool.play(collideSound, MainActivity.volume / SOUND_VOLUME_DIVIDER, MainActivity.volume / SOUND_VOLUME_DIVIDER, 0, 0, 1);
    }

    public boolean isAlreadyPassed() {
        return isAlreadyPassed;
    }

    public void setAlreadyPassed(boolean alreadyPassed) {
        isAlreadyPassed = alreadyPassed;
    }

    public static int getCollideSound() {
        return collideSound;
    }

    public static void setCollideSound(int cs) {
        collideSound = cs;
    }

    public static class ObstacleBuilder {
        private IGameObstacle spider;
        private IGameObstacle log;
        private int widthPixels;
        private int heightPixels;
        private int speedX;
        public ObstacleBuilder setSpider(IGameObstacle spider) {
            this.spider = spider;
            return this;
        }
        public ObstacleBuilder setWoodLog(IGameObstacle log) {
            this.log = log;
            return this;
        }
        public ObstacleBuilder setWidthPixels(int widthPixels) {
            this.widthPixels = widthPixels;
            return this;
        }
        public ObstacleBuilder setHeightPixels(int heightPixels) {
            this.heightPixels = heightPixels;
            return this;
        }
        public ObstacleBuilder setSpeedX(int speedX) {
            this.speedX = speedX;
            return this;
        }

        public Obstacle build() {
            Obstacle obstacle = new Obstacle(this);
            return obstacle;
        }
    }
}
