package clientSide.Maps;


import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class LargeMap extends Map {
    private static final int MAP_WIDTH = 609;
    private static final int MAP_HEIGHT = 523;
    
    public LargeMap() {
        MediumMap mediumMap = new MediumMap();
        MediumMap clonedMediumMap = (MediumMap) mediumMap.makeCopy();
        this.obstacles = clonedMediumMap.getObstacles();
        this.obstacles.addAll(createObstacles());
    }
    
    @Override
    public ArrayList<Obstacle> createObstacles() {
        ArrayList<Obstacle> obstacles = new ArrayList<>();
        // edges
        obstacles.add(new Obstacle(304, 478));
        obstacles.add(new Obstacle(304, 54));
        obstacles.add(new Obstacle(73, 273));
        obstacles.add(new Obstacle(536, 273));
        return obstacles;
    }
    
    @Override
    public int getWidth() {
        return MAP_WIDTH;
    }
    
    @Override
    public int getHeight() {
        return MAP_HEIGHT;
    }
}