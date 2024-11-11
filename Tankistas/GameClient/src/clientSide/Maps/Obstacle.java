// src/clientSide/Maps/Obstacle.java
package clientSide.Maps;

import java.awt.Graphics2D;

public class Obstacle {
    private int x, y;
    private int width, height;
    private int id;
    private final ObstacleImpl implementation;
    private boolean destroyed = false;

    public Obstacle(ObstacleImpl implementation) {
        this.implementation = implementation;
        this.width = implementation.getWidth();
        this.height = implementation.getHeight();
    }

    public void draw(Graphics2D g) {
        implementation.draw(g, x, y);
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
        implementation.setSize(width, height);
    }

    public void setMaterial(String material) {
        implementation.setMaterial(material);
    }

    public void setDestructability(boolean destructible) {
        implementation.setDestructible(destructible);
    }

    public boolean isDestructible() {
        return implementation.isDestructible();
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ObstacleImpl getImplementation() {
        return implementation;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public boolean collidesWith(int objX, int objY, int objWidth, int objHeight) {
        return (objX < x + width &&
                objX + objWidth > x &&
                objY < y + height &&
                objY + objHeight > y);
    }
}