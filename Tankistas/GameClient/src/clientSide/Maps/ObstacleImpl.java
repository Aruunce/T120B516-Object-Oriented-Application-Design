// src/clientSide/Maps/ObstacleImpl.java
package clientSide.Maps;

import java.awt.Graphics2D;

public interface ObstacleImpl {
    void draw(Graphics2D g, int x, int y);
    void setMaterial(String material);
    void setSize(int width, int height);
    void setDestructible(boolean destructible);
    boolean isDestructible();
    int getWidth();
    int getHeight();
}