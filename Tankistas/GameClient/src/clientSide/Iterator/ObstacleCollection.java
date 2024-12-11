// src/clientSide/Iterator/ObstacleCollection.java
package clientSide.Iterator;

import clientSide.Maps.Obstacle;
import java.util.ArrayList;
import java.util.List;

public class ObstacleCollection implements Aggregate<Obstacle> {
    private List<Obstacle> obstacles;

    public ObstacleCollection() {
        this.obstacles = new ArrayList<>();
    }

    public void addObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
    }

    public void removeObstacle(Obstacle obstacle) {
        obstacles.remove(obstacle);
    }

    @Override
    public Iterator<Obstacle> createIterator() {
        return new ObstacleIterator(obstacles);
    }
}