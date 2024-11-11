package clientSide.Maps;

import java.util.ArrayList;

public class ObstacleFacade {

    public void addObstacle(ArrayList<Obstacle> obstacles, ObstacleType type, int x, int y) {
        Obstacle obstacle = ObstacleFactory.createObstacle(type, obstacles.size(), x, y, -1, -1, type != ObstacleType.STONE);
        obstacles.add(obstacle);
    }

    public ArrayList<Obstacle> createObstacles(String data) {
        ArrayList<Obstacle> obstacles = new ArrayList<>();
        String[] obstacleData = data.split(";");

        for (String obs : obstacleData) {
            try {
                String[] parts = obs.split(",");
                if (parts.length < 6) continue;

                int id = Integer.parseInt(parts[0]);
                int x = Integer.parseInt(parts[1]);
                int y = Integer.parseInt(parts[2]);
                int width = Integer.parseInt(parts[3]);
                int height = Integer.parseInt(parts[4]);
                boolean destructible = Boolean.parseBoolean(parts[5]);

                ObstacleType type = destructible ? ObstacleType.WOOD : ObstacleType.STONE;
                if (parts.length > 6 && parts[6].equals("slowing")) {
                    type = ObstacleType.SLOWING;
                }

                obstacles.add(ObstacleFactory.createObstacle(
                        type, id, x, y, width, height, destructible));

            } catch (NumberFormatException e) {
                System.err.println("Invalid number format: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Error creating obstacle: " + e.getMessage());
            }
        }
        return obstacles;
    }
}