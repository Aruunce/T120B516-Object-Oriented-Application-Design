package clientSide.Composite;

public interface ObstacleComponent {
    void add(ObstacleComponent obstacle);
    void remove(ObstacleComponent obstacle);
    ObstacleComponent getChild(int index);
    void display();
}