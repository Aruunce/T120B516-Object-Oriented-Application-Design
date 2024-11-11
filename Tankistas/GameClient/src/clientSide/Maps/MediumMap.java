package clientSide.Maps;


import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class MediumMap extends Map {
    public MediumMap() {
        SmallMap smallMap = new SmallMap();
        Map clonedMap = smallMap.makeCopy();

        clonedMap.obstacles.addAll(createObstacles(clonedMap.obstacles.size()));
        
        this.obstacles = clonedMap.getObstacles();
    }
    
    @Override
    public ArrayList<Obstacle> createObstacles(int size) {
        ArrayList<Obstacle> obstacles = new ArrayList<>();
        ObstacleFacade facade = new ObstacleFacade();

        // corner top left
        facade.addObstacle(obstacles, ObstacleType.WOOD, 124, 140);
        // corner top right
        facade.addObstacle(obstacles, ObstacleType.WOOD, 489, 140);
        // corner bottom left
        facade.addObstacle(obstacles, ObstacleType.WOOD, 124, 398);
        // corner bottom right
        facade.addObstacle(obstacles, ObstacleType.WOOD, 489, 398);
        
        // center
        facade.addObstacle(obstacles, ObstacleType.STONE, 304, 273);
        // corner top left
        facade.addObstacle(obstacles, ObstacleType.STONE, 124, 100);
        // corner top right
        facade.addObstacle(obstacles, ObstacleType.STONE, 484, 100);
        // corner bottom left
        facade.addObstacle(obstacles, ObstacleType.STONE, 124, 433);
        // corner bottom right
        facade.addObstacle(obstacles, ObstacleType.STONE, 484, 433);
        
        return obstacles;
    }
}