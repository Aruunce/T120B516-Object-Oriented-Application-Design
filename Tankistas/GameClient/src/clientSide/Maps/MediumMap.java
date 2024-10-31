package clientSide.Maps;


import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class MediumMap extends Map {
    private static final int MAP_WIDTH = 609;
    private static final int MAP_HEIGHT = 523;
    
    public MediumMap() {
        SmallMap smallMap = new SmallMap();
        SmallMap clonedSmallMap = smallMap.makeCopy();
        this.obstacles = clonedSmallMap.getObstacles();
        this.obstacles.addAll(createObstacles());
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
    
    @Override
    public int getWidth() {
        return MAP_WIDTH;
    }
    
    @Override
    public int getHeight() {
        return MAP_HEIGHT;
    }

    @Override
    public MediumMap makeCopy() {
        try {
            return (MediumMap) this.clone();
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
            return this;
        }
    }
}