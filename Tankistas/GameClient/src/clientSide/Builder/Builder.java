// src/clientSide/Builder/Builder.java
package clientSide.Builder;

import clientSide.Maps.Obstacle;
import clientSide.Maps.ObstacleImpl;

public abstract class Builder {
    protected Obstacle obstacle;

    public Builder(ObstacleImpl implementation) {
        this.obstacle = new Obstacle(implementation);
    }

    // Template method
    public final Obstacle build() {
        addSize();
        addMaterial();
        addDestructability();
        return getBuildable();
    }

    protected abstract void addSize();
    protected abstract void addMaterial();
    protected abstract void addDestructability();
    protected abstract ObstacleImpl createImplementation();

    public Obstacle getBuildable() {
        System.out.println("Building obstacle");
        Obstacle finalObstacle = this.obstacle;
        this.obstacle = new Obstacle(createImplementation());
        return finalObstacle;
    }
}