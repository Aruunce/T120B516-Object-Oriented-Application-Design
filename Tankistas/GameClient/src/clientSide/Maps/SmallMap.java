package clientSide.Maps;


import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class SmallMap extends Map {
    public SmallMap() {
        super();
        this.obstacles.addAll(createObstacles(0));
    }
    
    @Override
    public ArrayList<Obstacle> createObstacles(int size) {
        obstacles = new ArrayList<>();
        ObstacleFacade facade = new ObstacleFacade();

        // corner top left
        facade.addObstacle(obstacles, ObstacleType.STONE, 164, 100);
        // corner top right
        facade.addObstacle(obstacles, ObstacleType.STONE, 444, 100);
        // corner bottom left
        facade.addObstacle(obstacles, ObstacleType.STONE, 164, 433);
        // corner bottom right
        facade.addObstacle(obstacles, ObstacleType.STONE, 444, 433);

        // other
        facade.addObstacle(obstacles, ObstacleType.WOOD, 214, 205);
        facade.addObstacle(obstacles, ObstacleType.WOOD, 394, 205);
        facade.addObstacle(obstacles, ObstacleType.WOOD, 214, 338);
        facade.addObstacle(obstacles, ObstacleType.WOOD, 394, 338);
        return obstacles;
    }
}