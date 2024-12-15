package clientSide;

import clientSide.Maps.Obstacle;

public class DamageVisitor implements GameElementVisitor {
    @Override
    public void visitTank(Tank tank) {
        // Implement tank damage logic
    }

    @Override 
    public void visitBullet(Bullet bullet) {
        bullet.setDestroyed(true);
    }

    @Override
    public void visitObstacle(Obstacle obstacle) {
        obstacle.decreaseHealth(25);
        if (obstacle.getHealth() <= 0) {
            obstacle.setDestroyed(true);
        }
    }

    @Override
    public void visitPowerUp(PowerUp powerUp) {
        powerUp.setCollected(true);
    }
}