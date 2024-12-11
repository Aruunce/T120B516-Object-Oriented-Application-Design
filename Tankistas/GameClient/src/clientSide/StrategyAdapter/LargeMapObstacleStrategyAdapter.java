package clientSide.StrategyAdapter;

import clientSide.Iterator.ObstacleCollection;
import clientSide.Maps.Obstacle;
import java.util.ArrayList;
import java.util.List;

public class LargeMapObstacleStrategyAdapter implements ObstacleCreationStrategy {
    private LargeMapObstacleStrategy largeMapObstacleStrategy;

    public LargeMapObstacleStrategyAdapter(LargeMapObstacleStrategy largeMapObstacleStrategy) {
        this.largeMapObstacleStrategy = largeMapObstacleStrategy;
    }

    @Override
    public ArrayList<Obstacle> createObstacles(int size, ObstacleCollection obstacleCollection) {
        // Adapt the method call to the new interface
        List<Obstacle> obstacles = largeMapObstacleStrategy.generateLargeMapObstacles(size, obstacleCollection);
        
        for (Obstacle obstacle : obstacles) {
            obstacleCollection.addObstacle(obstacle);
        }

        return new ArrayList<>(obstacles);
    }
}