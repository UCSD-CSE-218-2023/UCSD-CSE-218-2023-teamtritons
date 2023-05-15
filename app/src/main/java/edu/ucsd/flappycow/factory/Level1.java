package edu.ucsd.flappycow.factory;

import edu.ucsd.flappycow.enums.Accessory;
import edu.ucsd.flappycow.enums.GameButton;
import edu.ucsd.flappycow.enums.GameObstacle;
import edu.ucsd.flappycow.enums.Ground;
import edu.ucsd.flappycow.enums.PlayableCharacter;
import edu.ucsd.flappycow.enums.PowerUp;
import edu.ucsd.flappycow.enums.SpriteObstacle;
import edu.ucsd.flappycow.model.IAccessory;
import edu.ucsd.flappycow.model.IGameButton;
import edu.ucsd.flappycow.model.IGameObstacle;
import edu.ucsd.flappycow.model.IGround;
import edu.ucsd.flappycow.model.IPlayableCharacter;
import edu.ucsd.flappycow.model.IPowerUp;
import edu.ucsd.flappycow.model.Obstacle;
import edu.ucsd.flappycow.model.Tutorial;

public class Level1 implements AbstractFactory{
    @Override
    public IPlayableCharacter createPlayableCharacter(PlayableCharacter type, int width, int height, int heightPixels) {
        return PlayableCharacterFactory.getInstance(type, width, height, heightPixels);
    }

    @Override
    public IAccessory createAccessory(Accessory type) {
        return AccessoryFactory.getInstance(type);
    }

    @Override
    public IGround createGround(Ground type) {
        return GroundFactory.getInstance(type);
    }

    @Override
    public IPowerUp createPowerUp(PowerUp type, int speedX, int viewWidth) {
        return PowerUpFactory.getInstance(type, speedX, viewWidth);
    }

    @Override
    public Tutorial createTutorial(edu.ucsd.flappycow.enums.Tutorial type) {
        return TutorialFactory.getInstance(type);
    }

    @Override
    public IGameButton createGameButton(GameButton type) {
        return ButtonFactory.getInstance(type);
    }

    @Override
    public Obstacle createObstacle(SpriteObstacle type, IGameObstacle spider, IGameObstacle woodLog,  int widthPixels, int heightPixels, int speedX) {
        return ObstacleFactory.getInstance(type, spider, woodLog, widthPixels, heightPixels, speedX);
    }

    @Override
    public IGameObstacle createGameObstacle(GameObstacle type) {
        return GameObstacleFactory.getInstance(type);
    }
}
