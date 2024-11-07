// src/clientSide/Builder/WoodWallBuilder.java
package clientSide.Builder;

import clientSide.Maps.Obstacle;
import clientSide.Maps.WoodWall;

public class WoodWallBuilder implements ObstacleBuilder {
    private Obstacle obstacle;

    public WoodWallBuilder() {
        this.obstacle = new Obstacle(new WoodWall());
    }

    @Override
    public ObstacleBuilder addSize(int width, int height) {
        obstacle.setSize(width, height);
        return this;
    }

    @Override
    public ObstacleBuilder addMaterial(String material) {
        // Material is already set in WoodWall, so this method can be a no-op
        return this;
    }

    @Override
    public ObstacleBuilder addDestructability(boolean destructible) {
        // Destructibility is already set in WoodWall, so this method can be a no-op
        return this;
    }

    @Override
    public Obstacle build() {
        Obstacle builtObstacle = this.obstacle;
        this.obstacle = new Obstacle(new WoodWall()); // Reset the builder state
        return builtObstacle;
    }
}