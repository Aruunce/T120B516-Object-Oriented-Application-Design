// src/clientSide/Builder/SlowingObstacleBuilder.java
package clientSide.Builder;

import clientSide.Maps.Obstacle;
import clientSide.Maps.SlowingObstacleImpl;

public class SlowingObstacleBuilder implements ObstacleBuilder {
    private Obstacle obstacle;

    public SlowingObstacleBuilder() {
        this.obstacle = new Obstacle(new SlowingObstacleImpl());
    }

    @Override
    public ObstacleBuilder addSize(int width, int height) {
        obstacle.setSize(width, height);
        return this;
    }

    @Override
    public ObstacleBuilder addMaterial(String material) {
        // Material is already set in SlowingObstacleImpl, so this method can be a no-op
        return this;
    }

    @Override
    public ObstacleBuilder addDestructability(boolean destructible) {
        // Destructibility is already set in SlowingObstacleImpl, so this method can be a no-op
        return this;
    }

    @Override
    public Obstacle build() {
        return obstacle;
    }
}