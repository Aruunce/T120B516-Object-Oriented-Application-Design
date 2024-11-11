package clientSide.Maps;

import java.util.ArrayList;
import clientSide.Builder.Builder;

public class ObstacleFacade {

    public void addObstacle(ArrayList<Obstacle> obstacles, Builder builder, int x, int y) {
        Obstacle obstacle = builder.addSize().addMaterial().addDestructability().getBuildable();
        obstacle.setPosition(x, y);
        obstacle.setId(obstacles.size());
        obstacles.add(obstacle);
    }
}