// src/clientSide/Composite/ObstacleLeaf.java
package clientSide.Composite;

import clientSide.Maps.Obstacle;

public class ObstacleLeaf implements ObstacleComponent {
    private Obstacle obstacle;

    public ObstacleLeaf(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    @Override
    public void add(ObstacleComponent obstacle) {
        // Leaf nodes do not support add operation
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(ObstacleComponent obstacle) {
        // Leaf nodes do not support remove operation
        throw new UnsupportedOperationException();
    }

    @Override
    public ObstacleComponent getChild(int index) {
        // Leaf nodes do not support getChild operation
        throw new UnsupportedOperationException();
    }

    @Override
    public void display() {
        System.out.println("Leaf Obstacle ID: " + obstacle.getId() + ", Type: " + obstacle.getImplementation().getClass().getSimpleName() + ", Position: (" + obstacle.getX() + ", " + obstacle.getY() + ")");
    }

    public Obstacle getObstacle() {
        return obstacle;
    }
}