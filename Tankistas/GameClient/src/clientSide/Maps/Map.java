package clientSide.Maps;


import clientSide.Position;
import clientSide.Iterator.Iterator;
import clientSide.Iterator.ObstacleCollection;
import clientSide.StrategyAdapter.ObstacleCreationStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public abstract class Map implements Cloneable {
    protected ArrayList<Obstacle> obstacles;
    protected int MAP_WIDTH;
    protected int MAP_HEIGHT;
    private ObstacleCreationStrategy obstacleCreationStrategy;
    private ObstacleCollection obstacleCollection;

    
    private static final int TANK_SIZE = 32; // Standard tank size
    public static final Random random = new Random();
        
    public Map() {
        this.MAP_WIDTH = 609;
        this.MAP_HEIGHT = 523;
        this.obstacles = new ArrayList<>();
        this.obstacleCollection = new ObstacleCollection();
    }
    
    public int getWidth() {
        return MAP_WIDTH;
    }
    
    public int getHeight() {
        return MAP_HEIGHT;
    }
    
    public void setWidth(int width) {
        this.MAP_WIDTH = width;
    }

    public void setHeight(int height) {
        this.MAP_HEIGHT = height;
    }
    
    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }
    
    public void setObstacles(ArrayList<Obstacle> obstacles) {
        this.obstacles.clear();
        this.obstacles.addAll(obstacles);
    }
    
    public Position getRandomValidPosition() {
        // Define spawn zones - areas known to be safe for tanks
        Position[] spawnZones = {
            new Position(50, 50),      // Top-left spawn
            new Position(509, 50),     // Top-right spawn
            new Position(50, 473),     // Bottom-left spawn
            new Position(509, 473)     // Bottom-right spawn
        };
        
        // Try each spawn zone in random order
        ArrayList<Integer> indices = new ArrayList<>();
        for (int i = 0; i < spawnZones.length; i++) {
            indices.add(i);
        }
        Collections.shuffle(indices);
        
        for (int index : indices) {
            Position basePos = spawnZones[index];
            // Add some randomness within the safe zone
            int x = basePos.x;
            int y = basePos.y;
            
            if (isValidPosition(x, y)) {
                return new Position(x, y);
            }
        }
        
        // If all spawn zones are occupied, return the first one
        // (this should rarely happen in practice)
        return spawnZones[0];
    }
    
    protected boolean isValidPosition(int x, int y) {
        // Check map bounds
        if (x < 0 || x > getWidth() - TANK_SIZE || 
            y < 0 || y > getHeight() - TANK_SIZE) {
            return false;
        }
        
        // Use the existing collision detection from Obstacle class
        for (Obstacle obstacle : obstacles) {
            if (obstacle.collidesWith(x, y, TANK_SIZE, TANK_SIZE)) {
                return false;
            }
        }
        
        return true;
    }

    public Map makeCopy() {
        try {
            return (Map) super.clone();
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
            return this;
        }
    }

    public void setObstacleCreationStrategy(ObstacleCreationStrategy strategy) {
        this.obstacleCreationStrategy = strategy;
    }

    public ArrayList<Obstacle> createObstacles(int size) {
        if (obstacleCreationStrategy != null) {
            return obstacleCreationStrategy.createObstacles(size, obstacleCollection);
        }
        return new ArrayList<>();
    }
    
    public void printObstacles() {
        Iterator<Obstacle> iterator = obstacleCollection.createIterator();
        while (iterator.hasNext()) {
            Obstacle obstacle = iterator.next();
            System.out.println("Obstacle ID: " + obstacle.getId() + ", Type: " + obstacle.getImplementation().getClass().getSimpleName() + ", Position: (" + obstacle.getX() + ", " + obstacle.getY() + ")");
        }
    }
}