// src/clientSide/Maps/Obstacle.java
package clientSide.Maps;

import java.awt.Graphics2D;

public class Obstacle {
    private int x, y, width, height;
    protected ObstacleImpl impl;

    public Obstacle(ObstacleImpl impl) {
        this.impl = impl;
        this.width = 40;
        this.height = 40;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics2D g) {
        impl.draw(g, x, y, width, height);
    }

    public boolean isDestructible() {
        return impl.isDestructible();
    }

    public boolean collidesWith(int x, int y, int width, int height) {
        // Simple collision detection logic
        return this.x < x + width && this.x + this.width > x &&
               this.y < y + height && this.y + this.height > y;
    }

    public boolean isSlowingObstacle() {
        return impl instanceof SlowingObstacleImpl;
    }

    public float getSlowFactor() {
        return impl.getSlowFactor();
    }

    @Override
    public String toString() {
        return "Obstacle{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                ", type=" + impl.getClass().getSimpleName() +
                '}';
    }
}