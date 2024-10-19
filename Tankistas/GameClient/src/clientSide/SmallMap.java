package clientSide;


import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class SmallMap extends Map {
    private static final int MAP_WIDTH = 609;
    private static final int MAP_HEIGHT = 523;
    
    public SmallMap() {
        obstacles = createObstacles();
    }
    
    @Override
    public ArrayList<Obstacle> createObstacles() {
        
        ArrayList<Obstacle> obstacles = new ArrayList<>();
        // corner top left
        obstacles.add(new Obstacle(124, 140));
        // corner top right
        obstacles.add(new Obstacle(484, 140));
        // corner bottom left
        obstacles.add(new Obstacle(164, 433));
        // corner bottom right
        obstacles.add(new Obstacle(444, 433));
        // center
        obstacles.add(new Obstacle(304, 273));
        // other
        obstacles.add(new Obstacle(214, 205));
        obstacles.add(new Obstacle(214, 338));
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