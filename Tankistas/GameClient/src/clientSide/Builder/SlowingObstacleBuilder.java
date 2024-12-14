// src/clientSide/Builder/SlowingObstacleBuilder.java
package clientSide.Builder;

import clientSide.Maps.ObstacleImpl;
import clientSide.Maps.SlowingObstacle;

public class SlowingObstacleBuilder extends Builder {
    public SlowingObstacleBuilder() {
        super(new SlowingObstacle());
    }
    
    @Override
    protected ObstacleImpl createImplementation() {
        return new SlowingObstacle();
    }
    
    @Override
    public void addSize() {
        addTrapSize();
    }
    
    @Override
    public void addMaterial() {
        addSlowEffect();
    }
            
    @Override
    public void addDestructability() {
        addFragility();
    }

    public void addTrapSize() {
        obstacle.setSize(35, 35);
    }
    
    public void addFragility() {
        obstacle.setDestructability(true);
    }

    public void addSlowEffect() {
        obstacle.setMaterial("/Images/SlowingTrap.png");
    }
}