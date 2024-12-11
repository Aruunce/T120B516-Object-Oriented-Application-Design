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
        System.out.println("Creating stone wall implementation");
        return new StoneObstacle();
    }
    
    @Override
    protected void addSize() {
        System.out.println("Adding size to stone wall");
        obstacle.setSize(40, 40);
    }
    
    @Override
    protected void addMaterial() {
        System.out.println("Adding material to stone wall");
        obstacle.setMaterial("/Images/StoneWall.png");
    }
    
    @Override
    protected void addDestructability() {
        System.out.println("Adding destructability to stone wall");
        obstacle.setDestructability(false);
    }
}