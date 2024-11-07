// src/clientSide/Factory/ObstacleFactory.java
package clientSide.Factory;

import clientSide.Maps.Obstacle;

public interface ObstacleFactory {
    Obstacle createStoneWall(int x, int y);
    Obstacle createWoodWall(int x, int y);
    Obstacle createSlowingObstacle(int x, int y);
}