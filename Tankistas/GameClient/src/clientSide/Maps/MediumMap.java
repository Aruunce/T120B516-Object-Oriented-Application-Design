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
        
        clonedMap.obstacles.addAll(createObstacles());
        
        this.obstacles = clonedMap.getObstacles();
    }
    
    @Override
    public ArrayList<Obstacle> createObstacles() {
        ArrayList<Obstacle> obstacles = new ArrayList<>();
        // corner top left
        obstacles.add(new Obstacle(124, 100));
        obstacles.add(new Obstacle(124, 140));
        // corner top right
        obstacles.add(new Obstacle(484, 100));
        obstacles.add(new Obstacle(484, 140));
        // corner bottom left
        obstacles.add(new Obstacle(124, 433));
        obstacles.add(new Obstacle(124, 393));
        // corner bottom right
        obstacles.add(new Obstacle(484, 433));
        obstacles.add(new Obstacle(484, 393));
        // center
        obstacles.add(new Obstacle(304, 273));
        return obstacles;
    }
}