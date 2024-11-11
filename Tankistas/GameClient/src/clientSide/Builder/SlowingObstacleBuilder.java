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

    public void addSlowEffect() {
        obstacle.setMaterial("/Images/SlowingTrap.png");
    }
    
    public void addTrapSize() {
        obstacle.setSize(35, 35);
    }
    
    public void addFragility() {
        obstacle.setDestructability(true);
    }
    
    @Override
    public Builder addSize() {
        addTrapSize();
        return this;
    }
    
    @Override
    public Builder addMaterial() {
        addSlowEffect();
        return this;
    }
            
    @Override
    public Builder addDestructability() {
        addFragility();
        return this;
    }
}