package clientSide;

import java.util.Iterator;
import java.util.List;
import clientSide.Maps.Obstacle;

public class GameLoop {
    private CollisionHandler collisionHandler = new CollisionHandler();
    private Tank tank;
    private List<Obstacle> obstacles;
    private List<PowerUp> powerUps;

    public GameLoop(Tank tank, List<Obstacle> obstacles, List<PowerUp> powerUps) {
        this.tank = tank;
        this.obstacles = obstacles;
        this.powerUps = powerUps;
    }

    public void update() {
        // Move bullets
        Iterator<Bullet> bulletIterator = tank.getBullets().iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            bullet.move();
            if (bullet.isDestroyed()) {
                bulletIterator.remove();
            }
        }

        // Check for collisions with obstacles
        for (Bullet bullet : tank.getBullets()) {
            for (Obstacle obstacle : obstacles) {
                if (bullet.collidesWith(obstacle)) {
                    collisionHandler.handleCollision(bullet, obstacle);
                }
            }
        }

        // Check for collisions with power-ups
        for (PowerUp powerUp : powerUps) {
            if (tank.collidesWith(powerUp)) {
                tank.collectPowerUp(powerUp);
            }
        }

        // Remove collected power-ups
        powerUps.removeIf(PowerUp::isCollected);
    }

    public List<PowerUp> getPowerUps() {
        return powerUps;
    }
}