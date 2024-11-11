package clientSide.StrategyAdapter;

import java.util.ArrayList;
import clientSide.Maps.Obstacle;
import clientSide.Maps.ObstacleFacade;
import clientSide.Maps.ObstacleType;

public class MediumMapObstacleStrategy implements ObstacleCreationStrategy {
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
        // corner top left
        facade.addObstacle(obstacles, ObstacleType.WOOD, 124, 140);
        // corner top right
        facade.addObstacle(obstacles, ObstacleType.WOOD, 489, 140);
        // corner bottom left
        facade.addObstacle(obstacles, ObstacleType.WOOD, 124, 398);
        // corner bottom right
        facade.addObstacle(obstacles, ObstacleType.WOOD, 489, 398);
        
        // center
        facade.addObstacle(obstacles, ObstacleType.STONE, 304, 273);
        // corner top left
        facade.addObstacle(obstacles, ObstacleType.STONE, 124, 100);
        // corner top right
        facade.addObstacle(obstacles, ObstacleType.STONE, 484, 100);
        // corner bottom left
        facade.addObstacle(obstacles, ObstacleType.STONE, 124, 433);
        // corner bottom right
        facade.addObstacle(obstacles, ObstacleType.STONE, 484, 433);
        return obstacles;
    }
}