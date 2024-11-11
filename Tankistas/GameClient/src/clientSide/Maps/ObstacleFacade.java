package clientSide.Maps;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import clientSide.Builder.Builder;

public class ObstacleFacade {
    private final List<Obstacle> obstacles;

    public ObstacleFacade() {
        this.obstacles = new ArrayList<>();
    }

    public void initializeMapObstacles(Map map) {
        obstacles.clear();
        obstacles.addAll(map.getObstacles());
    }

    public void initializeMapObstacles(ArrayList<Obstacle> obstacles) {
        this.obstacles.clear();
        this.obstacles.addAll(obstacles);
    }

    public void drawObstacles(Graphics2D g) {
        obstacles.stream()
            .filter(o -> !o.isDestroyed())
            .forEach(o -> o.draw(g));
    }

    public List<Obstacle> getObstacles() {
        return new ArrayList<>(obstacles);
    }

    public boolean checkCollision(int x, int y, int width, int height) {
        return obstacles.stream()
            .filter(o -> !o.isDestroyed())
            .anyMatch(o -> o.collidesWith(x, y, width, height));
    }

    public void addObstacleWithBuilder(Builder builder, int id, int x, int y) {
        Obstacle obstacle = builder.addSize()
                                 .addMaterial()
                                 .addDestructability()
                                 .getBuildable();
        obstacle.setPosition(x, y);
        obstacle.setId(id);
        obstacles.add(obstacle);
    }

    public void removeObstacle(int id) {
        obstacles.stream()
            .filter(o -> o.getId() == id)
            .findFirst()
            .ifPresent(o -> {
                if (o.isDestructible()) {
                    o.setDestroyed(true);
                }
            });
    }

    public Obstacle getObstacleById(int id) {
        return obstacles.stream()
            .filter(o -> o.getId() == id)
            .findFirst()
            .orElse(null);
    }
}