// src/clientSide/Maps/MediumMap.java
package clientSide.Maps;

import java.util.ArrayList;
import clientSide.Builder.StoneWallBuilder;
import clientSide.Builder.WoodWallBuilder;

public class MediumMap extends Map {
    public MediumMap() {
        SmallMap smallMap = new SmallMap();
        Map clonedMap = smallMap.makeCopy();

        clonedMap.obstacles.addAll(createObstacles());
        
        this.obstacles = clonedMap.getObstacles();
    }
    
    @Override
    public ArrayList<Obstacle> createObstacles() {
        ArrayList<Obstacle> obstacles = new ArrayList<>();
        
        WoodWallBuilder woodBuilder = new WoodWallBuilder();
        StoneWallBuilder stoneBuilder = new StoneWallBuilder();
        
        MapBuilderFacade facade = new MapBuilderFacade();
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // Place a stone wall in the center
        Obstacle stoneWall = facade.createStoneWall(centerX - 20, centerY - 20); // Adjusting for obstacle size
        obstacles.add(stoneWall);

        // // Use builders to create obstacles
        // // corner top left
        Obstacle woodWallTopLeft = woodBuilder.addSize(40, 40).addMaterial("").addDestructability(true).build();
        woodWallTopLeft.setPosition(124, 140);
        obstacles.add(woodWallTopLeft);

        // // corner top right
        // Obstacle woodWallTopRight = woodBuilder.addSize(40, 40).addMaterial("").addDestructability(true).build();
        // woodWallTopRight.setPosition(489, 140);
        // obstacles.add(woodWallTopRight);

        // // corner bottom left
        // Obstacle woodWallBottomLeft = woodBuilder.addSize(40, 40).addMaterial("").addDestructability(true).build();
        // woodWallBottomLeft.setPosition(124, 398);
        // obstacles.add(woodWallBottomLeft);

        // // corner bottom right
        // Obstacle woodWallBottomRight = woodBuilder.addSize(40, 40).addMaterial("").addDestructability(true).build();
        // woodWallBottomRight.setPosition(489, 398);
        // obstacles.add(woodWallBottomRight);

        // // center
        // Obstacle stoneWallCenter = stoneBuilder.addSize(40, 40).addMaterial("").addDestructability(false).build();
        // stoneWallCenter.setPosition(304, 273);
        // obstacles.add(stoneWallCenter);

        // Print out the list of obstacles
        System.out.println("Obstacles created:");
        for (Obstacle obstacle : obstacles) {
            System.out.println(obstacle);
        }

        return obstacles;
    }
}