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

public class Level3 implements AbstractFactory{
    // Creates required sprite instance depending on what is required for given game level
    @Override
    public IPlayableCharacter createPlayableCharacter(PlayableCharacter type, int width, int height, int heightPixels) {
        return null;
    }
    @Override
    public IAccessory createAccessory(Accessory type) {
        return null;
    }
    @Override
    public IGround createGround(Ground type) {
        return null;
    }
    @Override
    public IPowerUp createPowerUp(PowerUp type, int speedX, int viewWidth) {
        return null;
    }
    @Override
    public Tutorial createTutorial(edu.ucsd.flappycow.enums.Tutorial type) {
        return null;
    }
    @Override
    public IGameButton createGameButton(GameButton type) {
        return null;
    }
    @Override
    public Obstacle createObstacle(SpriteObstacle type, IGameObstacle spider, IGameObstacle woodLog,  int widthPixels, int heightPixels, int speedX) { return null; }
    @Override
    public IGameObstacle createGameObstacle(GameObstacle type) { return null; }
}
