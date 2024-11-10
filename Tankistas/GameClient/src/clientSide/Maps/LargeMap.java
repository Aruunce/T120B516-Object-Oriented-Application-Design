package clientSide.Maps;


import java.util.ArrayList;
import clientSide.Builder.StoneWallBuilder;
import clientSide.Builder.Builder;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class LargeMap extends Map {
    public LargeMap() {
        MediumMap mediumMap = new MediumMap();
        Map clonedMap = mediumMap.makeCopy();
        
        clonedMap.obstacles.addAll(createObstacles(clonedMap.obstacles.size()));
        
        this.obstacles = clonedMap.getObstacles();
    }
    
    @Override
    public ArrayList<Obstacle> createObstacles(int size) {
        ArrayList<Obstacle> obstacles = new ArrayList<>();
        
        Builder builder = new StoneWallBuilder();
        
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
        
        return obstacles;
    }
}