package clientSide.Builder;

public class StoneWallBuilder extends Builder {
    public void addStone() {
        obstacle.setMaterial("/Images/StoneWall.png");
    }
    
    public void addBricks() {
        obstacle.setSize(40, 40);
    }
    
    public void addHardness() {
        obstacle.setDestructability(false);
    }
    
    @Override
    public Builder addSize (){
        addBricks();
        return this;
    }
    
    @Override
    public Builder addMaterial() {
        addStone();
        return this;
    }
            
    @Override
    public Builder addDestructability() {
        addHardness();
        return this;
    }
}
