package client;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public abstract class Map {
    protected ArrayList<Obstacle> obstacles;
    private static final int TANK_SIZE = 32; // Standard tank size
    public static final Random random = new Random();
    
    public abstract ArrayList<Obstacle> createObstacles();
    public abstract int getWidth();
    public abstract int getHeight();
    
    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
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
            int x = basePos.x + random.nextInt(21) - 10; // ±10 pixels
            int y = basePos.y + random.nextInt(21) - 10;
            
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
}