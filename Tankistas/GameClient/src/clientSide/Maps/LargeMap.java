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
        ObstacleFacade facade = new ObstacleFacade();
        
        Builder builder = new StoneWallBuilder();
        
        // edges
        facade.addObstacle(obstacles, builder, 304, 478);
        facade.addObstacle(obstacles, builder, 304, 54);
        facade.addObstacle(obstacles, builder, 73, 273);
        facade.addObstacle(obstacles, builder, 536, 273);
        
        return obstacles;
    }
}