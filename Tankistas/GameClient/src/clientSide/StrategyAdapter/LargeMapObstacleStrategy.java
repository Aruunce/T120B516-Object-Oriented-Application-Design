package clientSide.StrategyAdapter;

import java.util.ArrayList;
import clientSide.Builder.Builder;
import clientSide.Builder.StoneWallBuilder;
import clientSide.Builder.WoodWallBuilder;
import clientSide.Maps.Obstacle;

public class LargeMapObstacleStrategy implements ObstacleCreationStrategy {
    @Override
    public ArrayList<Obstacle> createObstacles(int size) {
        ArrayList<Obstacle> obstacles = new ArrayList<>();
        
        Builder builder = new StoneWallBuilder();
        
        // corner top left
        obstacles.add(builder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(164, 100);
        obstacles.get(obstacles.size() - 1).setId(size++);
        // corner top right
        obstacles.add(builder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(444, 100);
        obstacles.get(obstacles.size() - 1).setId(size++);
        // corner bottom left
        obstacles.add(builder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(164, 433);
        obstacles.get(obstacles.size() - 1).setId(size++);
        // corner bottom right
        obstacles.add(builder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(444, 433);
        obstacles.get(obstacles.size() - 1).setId(size++);
        
        builder = new WoodWallBuilder();
        
        // other
        obstacles.add(builder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(214, 205);
        obstacles.get(obstacles.size() - 1).setId(obstacles.size() - 1);
        obstacles.add(builder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(394, 205);
        obstacles.get(obstacles.size() - 1).setId(obstacles.size() - 1);
        obstacles.add(builder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(214, 338);
        obstacles.get(obstacles.size() - 1).setId(obstacles.size() - 1);
        obstacles.add(builder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(394, 338);
        obstacles.get(obstacles.size() - 1).setId(obstacles.size() - 1);
        
        //center
        obstacles.add(builder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(304, 273);
        obstacles.get(obstacles.size() - 1).setId(size++);
        // corner top left
        obstacles.add(builder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(124, 100);
        obstacles.get(obstacles.size() - 1).setId(size++);
        // corner top right
        obstacles.add(builder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(484, 100);
        obstacles.get(obstacles.size() - 1).setId(size++);      
        // corner bottom left
        obstacles.add(builder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(124, 433);      
        obstacles.get(obstacles.size() - 1).setId(size++);
        // corner bottom right
        obstacles.add(builder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(484, 433);
        obstacles.get(obstacles.size() - 1).setId(size++);

        builder = new StoneWallBuilder();
        
        // edges
        obstacles.add(builder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(304, 478);
        obstacles.get(obstacles.size() - 1).setId(size++);
        obstacles.add(builder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(304, 54);
        obstacles.get(obstacles.size() - 1).setId(size++);
        obstacles.add(builder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(73, 273);
        obstacles.get(obstacles.size() - 1).setId(size++);
        obstacles.add(builder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(536, 273);
        obstacles.get(obstacles.size() - 1).setId(size++);
        
        builder = new WoodWallBuilder();
        
        // center
        obstacles.add(builder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(304, 273);
        obstacles.get(obstacles.size() - 1).setId(size++);
        
        return obstacles;
    }
}