// src/clientSide/Maps/SmallMap.java
package clientSide.Maps;

import java.util.ArrayList;

public class SmallMap extends Map {
    @Override
    public ArrayList<Obstacle> createObstacles() {
        ArrayList<Obstacle> obstacles = new ArrayList<>();
        MapBuilderFacade facade = new MapBuilderFacade();

        // Central position for the map
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // Place a stone wall in the center
        Obstacle stoneWall = facade.createStoneWall(20, 20); // Adjusting for obstacle size
        obstacles.add(stoneWall);

        // Place a wood wall slightly off-center
        Obstacle woodWall = facade.createWoodWall(60, 20); // Adjusting for obstacle size
        obstacles.add(woodWall);

        // Place a slowing obstacle slightly off-center
        Obstacle slowingObstacle = facade.createSlowingObstacle(centerX - 60, centerY - 20); // Adjusting for obstacle size
        obstacles.add(slowingObstacle);

         System.out.println("Obstacles created:");
        for (Obstacle obstacle : obstacles) {
            System.out.println(obstacle);
        }


        return obstacles;
    }
}