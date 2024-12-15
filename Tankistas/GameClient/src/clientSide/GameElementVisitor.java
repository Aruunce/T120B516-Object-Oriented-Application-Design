package clientSide;

import clientSide.Maps.Obstacle;

public interface GameElementVisitor {
    void visitTank(Tank tank);
    void visitBullet(Bullet bullet);
    void visitObstacle(Obstacle obstacle);
    void visitPowerUp(PowerUp powerUp);
}