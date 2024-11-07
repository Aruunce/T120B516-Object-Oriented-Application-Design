// src/clientSide/Maps/SmallMap.java
package clientSide.Maps;

import java.util.ArrayList;
import clientSide.Builder.StoneWallBuilder;
import clientSide.Builder.WoodWallBuilder;
import clientSide.Builder.SlowingObstacleBuilder;

public class SmallMap extends Map {
    @Override
    public ArrayList<Obstacle> createObstacles() {
        ArrayList<Obstacle> obstacles = new ArrayList<>();
        
        // Central position for the map
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // Use builders to create obstacles
        StoneWallBuilder stoneBuilder = new StoneWallBuilder();
        WoodWallBuilder woodBuilder = new WoodWallBuilder();
        SlowingObstacleBuilder slowingBuilder = new SlowingObstacleBuilder();

        // Place a stone wall in the center
        Obstacle stoneWall = stoneBuilder.addSize(40, 40).addMaterial("").addDestructability(false).build();
        stoneWall.setPosition(centerX - 20, centerY - 20); // Adjusting for obstacle size
        obstacles.add(stoneWall);

        // Place a wood wall slightly off-center
        Obstacle woodWall = woodBuilder.addSize(40, 40).addMaterial("").addDestructability(true).build();
        woodWall.setPosition(centerX + 60, centerY - 20); // Adjusting for obstacle size
        obstacles.add(woodWall);

        // Place a slowing obstacle slightly off-center
        Obstacle slowingObstacle = slowingBuilder.addSize(40, 40).addMaterial("").addDestructability(true).build();
        slowingObstacle.setPosition(centerX - 60, centerY - 20); // Adjusting for obstacle size
        obstacles.add(slowingObstacle);

        return obstacles;
    }
}