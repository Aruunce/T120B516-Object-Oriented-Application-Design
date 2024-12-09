// src/clientSide/Builder/WoodWallBuilder.java
package clientSide.Builder;

import clientSide.Maps.ObstacleImpl;
import clientSide.Maps.WoodObstacle;

public class WoodWallBuilder extends Builder {
    public WoodWallBuilder() {
        super(new WoodObstacle());
    }
    
    @Override
    protected ObstacleImpl createImplementation() {
        return new WoodObstacle();
    }

    public void addWood() {
        obstacle.setMaterial("/Images/WoodWall.png");
    }
    
    public void addPlanks() {
        obstacle.setSize(35, 35);
    }
    
    public void addFragility() {
        obstacle.setDestructability(true);
    }
    
    @Override
    public Builder addSize() {
        addPlanks();
        return this;
    }
    
    @Override
    public Builder addMaterial() {
        addWood();
        return this;
    }
            
    @Override
    public Builder addDestructability() {
        addFragility();
        return this;
    }
}