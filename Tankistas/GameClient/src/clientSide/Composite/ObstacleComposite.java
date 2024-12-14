// src/clientSide/Composite/ObstacleComposite.java
package clientSide.Composite;

import java.util.ArrayList;
import java.util.List;

import clientSide.Maps.Obstacle;

public class ObstacleComposite implements ObstacleComponent {
    private List<ObstacleComponent> obstacles = new ArrayList<>();

    @Override
    public void add(ObstacleComponent obstacle) {
        obstacles.add(obstacle);
    }

    @Override
    public void remove(ObstacleComponent obstacle) {
        obstacles.remove(obstacle);
    }

    @Override
    public ObstacleComponent getChild(int index) {
        return obstacles.get(index);
    }

    @Override
    public void display() {
        for (ObstacleComponent obstacle : obstacles) {
            obstacle.display();
        }
    }

    public List<Obstacle> getObstacles() {
        List<Obstacle> obstacleList = new ArrayList<>();
        for (ObstacleComponent component : obstacles) {
            if (component instanceof ObstacleLeaf) {
                obstacleList.add(((ObstacleLeaf) component).getObstacle());
            } else if (component instanceof ObstacleComposite) {
                obstacleList.addAll(((ObstacleComposite) component).getObstacles());
            }
        }
        return obstacleList;
    }
}