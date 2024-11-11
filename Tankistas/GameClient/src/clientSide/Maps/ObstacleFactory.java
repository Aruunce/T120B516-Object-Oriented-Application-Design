// src/clientSide/Maps/ObstacleFactory.java
package clientSide.Maps;

import java.util.Map;

public class ObstacleFactory {
    private static final Map<ObstacleType, String> MATERIAL_PATHS = Map.of(
        ObstacleType.STONE, "/Images/StoneWall.png",
        ObstacleType.WOOD, "/Images/WoodWall.png",
        ObstacleType.SLOWING, "/Images/SlowingTrap.png"
    );

    public static Obstacle createObstacle(ObstacleType type, int id, int x, int y, 
                                        int width, int height, boolean destructible) {
        ObstacleImpl impl;
        switch (type) {
            case STONE:
                impl = new StoneObstacle();
                break;
            case WOOD:
                impl = new WoodObstacle();
                break;
            case SLOWING:
                impl = new SlowingObstacle();
                break;
            default:
                throw new IllegalArgumentException("Unknown obstacle type: " + type);
        }

        Obstacle obstacle = new Obstacle(impl);
        obstacle.setId(id);
        obstacle.setPosition(x, y);
        obstacle.setSize(width, height);
        obstacle.setDestructability(destructible);
        obstacle.setMaterial(MATERIAL_PATHS.get(type));
        
        return obstacle;
    }
}