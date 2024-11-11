package clientSide.StrategyAdapter;

import java.util.ArrayList;
import clientSide.Maps.Obstacle;
import clientSide.Maps.ObstacleFacade;
import clientSide.Maps.ObstacleType;

public class SmallMapObstacleStrategy implements ObstacleCreationStrategy {
    @Override
    public ArrayList<Obstacle> createObstacles(int size) {
        ArrayList<Obstacle> obstacles = new ArrayList<>();
        ObstacleFacade facade = new ObstacleFacade();

        // corner top left
        facade.addObstacle(obstacles, ObstacleType.STONE, 164, 100);
        // corner top right
        facade.addObstacle(obstacles, ObstacleType.STONE, 444, 100);
        // corner bottom left
        facade.addObstacle(obstacles, ObstacleType.STONE, 164, 433);
        // corner bottom right
        facade.addObstacle(obstacles, ObstacleType.STONE, 444, 433);

        // other
        facade.addObstacle(obstacles, ObstacleType.WOOD, 214, 205);
        facade.addObstacle(obstacles, ObstacleType.WOOD, 394, 205);
        facade.addObstacle(obstacles, ObstacleType.WOOD, 214, 338);
        facade.addObstacle(obstacles, ObstacleType.WOOD, 394, 338);
        return obstacles;
    }
}