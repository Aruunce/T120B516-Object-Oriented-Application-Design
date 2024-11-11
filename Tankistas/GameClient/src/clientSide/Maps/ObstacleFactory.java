package clientSide.Maps;

import java.util.Map;
import java.util.HashMap;

public class ObstacleFactory {
    private static final Map<ObstacleType, String> MATERIAL_PATHS = new HashMap<>();

    static {
        MATERIAL_PATHS.put(ObstacleType.STONE, "/Images/StoneWall.png");
        MATERIAL_PATHS.put(ObstacleType.WOOD, "/Images/WoodWall.png");
        MATERIAL_PATHS.put(ObstacleType.SLOWING, "/Images/SlowingTrap.png");
    }

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
        if (width != -1 && height != -1) {
            obstacle.setSize(width, height);
        }
        obstacle.setDestructability(destructible);
        obstacle.setMaterial(MATERIAL_PATHS.get(type));
        
        return obstacle;
    }
}