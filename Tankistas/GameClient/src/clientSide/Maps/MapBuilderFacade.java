// src/clientSide/Maps/MapBuilderFacade.java
package clientSide.Maps;

import clientSide.Factory.ObstacleFactory;
import clientSide.Factory.ConcreteObstacleFactory;

public class MapBuilderFacade {
    private ObstacleFactory obstacleFactory;

    public MapBuilderFacade() {
        this.obstacleFactory = new ConcreteObstacleFactory();
    }

    public Map createSmallMap() {
        Map smallMap = new SmallMap();
        smallMap.createObstacles();
        return smallMap;
    }

    public Map createLargeMap() {
        Map largeMap = new LargeMap();
        largeMap.createObstacles();
        return largeMap;
    }

    public Obstacle createStoneWall(int x, int y) {
        return obstacleFactory.createStoneWall(x, y);
    }

    public Obstacle createWoodWall(int x, int y) {
        return obstacleFactory.createWoodWall(x, y);
    }

    public Obstacle createSlowingObstacle(int x, int y) {
        return obstacleFactory.createSlowingObstacle(x, y);
    }
}