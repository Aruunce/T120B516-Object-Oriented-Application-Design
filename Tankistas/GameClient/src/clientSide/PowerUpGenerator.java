package clientSide;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import clientSide.Maps.Map;
import clientSide.Maps.Obstacle;
import clientSide.Maps.MapAbstractFactory;

public class PowerUpGenerator {
    private static final int POWERUP_SIZE = 32;
    private static final int DAMAGE_INCREASE = 1;
    private static final int MIN_DISTANCE = 50; // Minimum distance from obstacles
    
    public static List<PowerUp> generateRandomPowerUps(int count) {
        List<PowerUp> powerUps = new ArrayList<>();
        Map currentMap = MapAbstractFactory.getCurrentMap();
        
        if (currentMap == null) {
            return powerUps;
        }
        
        int mapWidth = currentMap.getWidth();
        int mapHeight = currentMap.getHeight();
        List<Obstacle> obstacles = currentMap.getObstacles();
        
        for (int i = 0; i < count; i++) {
            PowerUp powerUp = generateDamagePowerUp(obstacles, mapWidth, mapHeight);
            if (powerUp != null) {
                powerUps.add(powerUp);
            }
        }
        
        return powerUps;
    }
    
    public static PowerUp generateDamagePowerUp(List<Obstacle> obstacles, int mapWidth, int mapHeight) {
        Random random = new Random();
        int maxAttempts = 100; // Prevent infinite loop
        int attempts = 0;
        
        while (attempts < maxAttempts) {
            // Generate position within map bounds with padding
            int x = random.nextInt(mapWidth - POWERUP_SIZE - MIN_DISTANCE) + MIN_DISTANCE;
            int y = random.nextInt(mapHeight - POWERUP_SIZE - MIN_DISTANCE) + MIN_DISTANCE;
            
            Rectangle powerUpBounds = new Rectangle(x, y, POWERUP_SIZE, POWERUP_SIZE);
            boolean validPosition = true;
            
            // Check collision with obstacles
            for (Obstacle obstacle : obstacles) {
                Rectangle obstacleBounds = new Rectangle(
                    obstacle.getX(), 
                    obstacle.getY(), 
                    obstacle.getWidth(), 
                    obstacle.getHeight()
                );
                
                // Add padding around obstacle
                obstacleBounds.grow(MIN_DISTANCE, MIN_DISTANCE);
                
                if (powerUpBounds.intersects(obstacleBounds)) {
                    validPosition = false;
                    break;
                }
            }
            
            if (validPosition) {
                return new DamagePowerUp(x, y, DAMAGE_INCREASE);
            }
            
            attempts++;
        }
        
        System.out.println("Warning: Could not find valid position for power-up after " + maxAttempts + " attempts");
        return null;
    }
}