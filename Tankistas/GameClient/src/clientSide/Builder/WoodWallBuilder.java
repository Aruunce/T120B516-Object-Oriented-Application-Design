package clientSide.Builder;

public class WoodWallBuilder extends Builder {
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
    public Builder addSize (){
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
