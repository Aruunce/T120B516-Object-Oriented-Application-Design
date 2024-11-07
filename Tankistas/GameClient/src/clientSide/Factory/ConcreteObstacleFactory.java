// src/clientSide/Factory/ConcreteObstacleFactory.java
package clientSide.Factory;

import clientSide.Maps.Obstacle;
import clientSide.Maps.StoneWall;
import clientSide.Maps.WoodWall;
import clientSide.Maps.SlowingObstacleImpl;

public class ConcreteObstacleFactory implements ObstacleFactory {
    @Override
    public Obstacle createStoneWall(int x, int y) {
        Obstacle obstacle = new Obstacle(new StoneWall());
        obstacle.setPosition(x, y);
        return obstacle;
    }

    @Override
    public Obstacle createWoodWall(int x, int y) {
        Obstacle obstacle = new Obstacle(new WoodWall());
        obstacle.setPosition(x, y);
        return obstacle;
    }

    @Override
    public Obstacle createSlowingObstacle(int x, int y) {
        Obstacle obstacle = new Obstacle(new SlowingObstacleImpl());
        obstacle.setPosition(x, y);
        return obstacle;
    }
}