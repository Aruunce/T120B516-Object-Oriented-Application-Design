package clientSide.Builder;

import clientSide.Maps.Obstacle;

public abstract class Builder {
    protected Obstacle obstacle = new Obstacle();
    
    public abstract Builder addSize();

    public abstract Builder addMaterial();
    
    public abstract Builder addDestructability();
    
    public Obstacle getBuildable() {
        Obstacle finalObstacle = this.obstacle;
        this.obstacle = new Obstacle();
        return finalObstacle;
    }
}
