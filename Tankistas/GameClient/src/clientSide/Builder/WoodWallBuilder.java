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
    public void addSize() {
        addPlanks();
    }
    
    @Override
    public void addMaterial() {
        addWood();
    }
            
    @Override
    public void addDestructability() {
        addFragility();
    }
}