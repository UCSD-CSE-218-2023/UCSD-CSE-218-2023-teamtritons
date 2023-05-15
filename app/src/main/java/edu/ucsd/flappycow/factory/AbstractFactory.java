package edu.ucsd.flappycow.factory;

import edu.ucsd.flappycow.enums.Accessory;
import edu.ucsd.flappycow.enums.GameButton;
import edu.ucsd.flappycow.enums.GameObstacle;
import edu.ucsd.flappycow.enums.Ground;
import edu.ucsd.flappycow.enums.PlayableCharacter;
import edu.ucsd.flappycow.enums.PowerUp;
import edu.ucsd.flappycow.enums.SpriteObstacle;
import edu.ucsd.flappycow.enums.Tutorial;
import edu.ucsd.flappycow.model.IAccessory;
import edu.ucsd.flappycow.model.IGameButton;
import edu.ucsd.flappycow.model.IGameObstacle;
import edu.ucsd.flappycow.model.IGround;
import edu.ucsd.flappycow.model.IPlayableCharacter;
import edu.ucsd.flappycow.model.IPowerUp;
import edu.ucsd.flappycow.model.Obstacle;

public interface AbstractFactory {
    public IPlayableCharacter createPlayableCharacter(PlayableCharacter type, int width, int height, int heightPixels);
    public IAccessory createAccessory(Accessory type);
    public IGround createGround(Ground type);
    public IPowerUp createPowerUp(PowerUp type, int speedX, int viewWidth);
    public edu.ucsd.flappycow.model.Tutorial createTutorial(Tutorial type);

    public IGameButton createGameButton(GameButton type);

    public Obstacle createObstacle(SpriteObstacle type, IGameObstacle spider, IGameObstacle woodLog,  int widthPixels, int heightPixels, int speedX);

    public IGameObstacle createGameObstacle(GameObstacle type);

}
