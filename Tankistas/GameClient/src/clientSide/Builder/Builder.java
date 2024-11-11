// src/clientSide/Builder/Builder.java
package clientSide.Builder;

import clientSide.Maps.Obstacle;
import clientSide.Maps.ObstacleImpl;

public abstract class Builder {
    protected Obstacle obstacle;

    public Builder(ObstacleImpl implementation) {
        this.obstacle = new Obstacle(implementation);
    }

    public abstract Builder addSize();
    public abstract Builder addMaterial();
    public abstract Builder addDestructability();

    protected abstract ObstacleImpl createImplementation();

    public Obstacle getBuildable() {
        Obstacle finalObstacle = this.obstacle;
        this.obstacle = new Obstacle(createImplementation());
        return finalObstacle;
    }
}