// src/clientSide/Builder/StoneWallBuilder.java
package clientSide.Builder;

import clientSide.Maps.Obstacle;
import clientSide.Maps.StoneWall;

public class StoneWallBuilder implements ObstacleBuilder {
    private Obstacle obstacle;

    public StoneWallBuilder() {
        this.obstacle = new Obstacle(new StoneWall());
    }

    @Override
    public ObstacleBuilder addSize(int width, int height) {
        obstacle.setSize(width, height);
        return this;
    }

    @Override
    public ObstacleBuilder addMaterial(String material) {
        // Material is already set in StoneWall, so this method can be a no-op
        return this;
    }

    @Override
    public ObstacleBuilder addDestructability(boolean destructible) {
        // Destructibility is already set in StoneWall, so this method can be a no-op
        return this;
    }

    @Override
    public Obstacle build() {
        Obstacle builtObstacle = this.obstacle;
        this.obstacle = new Obstacle(new StoneWall()); // Reset the builder state
        return builtObstacle;
    }
}