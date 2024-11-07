// src/clientSide/Maps/ObstacleImpl.java
package clientSide.Maps;

import java.awt.Graphics2D;

public interface ObstacleImpl {
    void draw(Graphics2D g, int x, int y, int width, int height);
    boolean isDestructible();
    float getSlowFactor();
}