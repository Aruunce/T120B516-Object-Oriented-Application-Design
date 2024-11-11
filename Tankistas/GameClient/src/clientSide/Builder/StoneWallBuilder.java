// src/clientSide/Builder/StoneWallBuilder.java
package clientSide.Builder;

import clientSide.Maps.ObstacleImpl;
import clientSide.Maps.StoneObstacle;

public class StoneWallBuilder extends Builder {
    public StoneWallBuilder() {
        super(new StoneObstacle());
    }
    
    @Override
    protected ObstacleImpl createImplementation() {
        return new StoneObstacle();
    }
    
    @Override
    public Builder addSize() {
        obstacle.setSize(40, 40);
        return this;
    }
    
    @Override
    public Builder addMaterial() {
        obstacle.setMaterial("/Images/StoneWall.png");
        return this;
    }
    
    @Override
    public Builder addDestructability() {
        obstacle.setDestructability(false);
        return this;
    }
}