// src/clientSide/Builder/ObstacleBuilder.java
package clientSide.Builder;

import clientSide.Maps.Obstacle;

public interface ObstacleBuilder {
    ObstacleBuilder addSize(int width, int height);
    ObstacleBuilder addMaterial(String material);
    ObstacleBuilder addDestructability(boolean destructible);
    Obstacle build();
}