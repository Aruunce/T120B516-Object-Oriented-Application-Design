package clientSide.Maps;


import clientSide.Position;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public abstract class Map implements Cloneable {
    protected int MAP_WIDTH;
    protected int MAP_HEIGHT;
    protected final ObstacleFacade obstacleFacade;

    private static final int TANK_SIZE = 32; // Standard tank size
    public static final Random random = new Random();
    
    protected abstract void initializeObstacles();
    
    public Map() {
        this.MAP_WIDTH = 609;
        this.MAP_HEIGHT = 523;
        this.obstacleFacade = new ObstacleFacade();
    }

    public ArrayList<Obstacle> getObstacles() {
        return new ArrayList<>(obstacleFacade.getObstacles());
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
        if (x < 0 || x > getWidth() - TANK_SIZE || 
            y < 0 || y > getHeight() - TANK_SIZE) {
            return false;
        }
        return !obstacleFacade.checkCollision(x, y, TANK_SIZE, TANK_SIZE);
    }

    public Map makeCopy() {
        try {
            return (Map) super.clone();
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
            return this;
        }
    }
}