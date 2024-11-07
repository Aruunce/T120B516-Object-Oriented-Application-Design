// src/clientSide/Maps/LargeMap.java
package clientSide.Maps;

import java.util.ArrayList;
import clientSide.Builder.StoneWallBuilder;

public class LargeMap extends Map {
    public LargeMap() {
        MediumMap mediumMap = new MediumMap();
        Map clonedMap = mediumMap.makeCopy();
        
        clonedMap.obstacles.addAll(createObstacles());
        
        this.obstacles = clonedMap.getObstacles();
    }
    
    @Override
    public ArrayList<Obstacle> createObstacles() {
        ArrayList<Obstacle> obstacles = new ArrayList<>();
        
        StoneWallBuilder stoneBuilder = new StoneWallBuilder();
        
        // Use builders to create obstacles
        // edges
        Obstacle stoneWallEdge1 = stoneBuilder.addSize(40, 40).addMaterial("").addDestructability(false).build();
        stoneWallEdge1.setPosition(304, 478);
        obstacles.add(stoneWallEdge1);

        Obstacle stoneWallEdge2 = stoneBuilder.addSize(40, 40).addMaterial("").addDestructability(false).build();
        stoneWallEdge2.setPosition(304, 54);
        obstacles.add(stoneWallEdge2);

        Obstacle stoneWallEdge3 = stoneBuilder.addSize(40, 40).addMaterial("").addDestructability(false).build();
        stoneWallEdge3.setPosition(73, 273);
        obstacles.add(stoneWallEdge3);

        Obstacle stoneWallEdge4 = stoneBuilder.addSize(40, 40).addMaterial("").addDestructability(false).build();
        stoneWallEdge4.setPosition(536, 273);
        obstacles.add(stoneWallEdge4);
        
        return obstacles;
    }
}