// src/clientSide/Iterator/ObstacleIterator.java
package clientSide.Iterator;

import clientSide.Maps.Obstacle;
import java.util.List;

public class ObstacleIterator implements Iterator<Obstacle> {
    private List<Obstacle> obstacles;
    private int position = 0;

    public ObstacleIterator(List<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

    @Override
    public boolean hasNext() {
        return position < obstacles.size();
    }

    @Override
    public Obstacle next() {
        if (this.hasNext()) {
            return obstacles.get(position++);
        }
        return null;
    }

    @Override
    public void remove() {
        if (position > 0) {
            obstacles.remove(--position);
        }
    }
}