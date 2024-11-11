package clientSide.StrategyAdapter;

import java.util.ArrayList;

import clientSide.Maps.Obstacle;

public interface ObstacleCreationStrategy {
    ArrayList<Obstacle> createObstacles(int size);
}