package clientSide.StrategyAdapter;

import java.util.ArrayList;

import clientSide.Composite.ObstacleComponent;
import clientSide.Composite.ObstacleComposite;
import clientSide.Composite.ObstacleLeaf;
import clientSide.Iterator.ObstacleCollection;
import clientSide.Maps.Obstacle;
import clientSide.Maps.ObstacleFacade;
import clientSide.Maps.ObstacleFactory;
import clientSide.Maps.ObstacleType;
import clientSide.Maps.StoneObstacle;
import clientSide.Maps.WoodObstacle;

public class SmallMapObstacleStrategy implements ObstacleCreationStrategy {
    @Override
    public ArrayList<Obstacle> createObstacles(int size, ObstacleCollection obstacleCollection) {
        ArrayList<Obstacle> obstacles = new ArrayList<>();
        ObstacleFacade facade = new ObstacleFacade();

        // corner top left
        facade.addObstacle(obstacles, ObstacleType.STONE, 164, 100);
        // corner top right
        facade.addObstacle(obstacles, ObstacleType.STONE, 444, 100);
        // corner bottom left
        facade.addObstacle(obstacles, ObstacleType.STONE, 164, 433);
        // corner bottom right
        facade.addObstacle(obstacles, ObstacleType.STONE, 444, 433);

        // other
        facade.addObstacle(obstacles, ObstacleType.WOOD, 214, 205);
        facade.addObstacle(obstacles, ObstacleType.WOOD, 394, 205);
        facade.addObstacle(obstacles, ObstacleType.WOOD, 214, 338);
        facade.addObstacle(obstacles, ObstacleType.WOOD, 394, 338);

        for (Obstacle obstacle : obstacles) {
            obstacleCollection.addObstacle(obstacle);
        }


        
        // Create individual obstacles using ObstacleFactory
        Obstacle stoneObstacle1 = ObstacleFactory.createObstacle(ObstacleType.STONE, 1, 100, 100, 40, 40, false);
        Obstacle stoneObstacle2 = ObstacleFactory.createObstacle(ObstacleType.STONE, 2, 200, 200, 40, 40, false);
        Obstacle woodObstacle1 = ObstacleFactory.createObstacle(ObstacleType.WOOD, 3, 300, 300, 35, 35, true);

        // Create leaf nodes
        ObstacleComponent stoneLeaf1 = new ObstacleLeaf(stoneObstacle1);
        ObstacleComponent stoneLeaf2 = new ObstacleLeaf(stoneObstacle2);
        ObstacleComponent woodLeaf1 = new ObstacleLeaf(woodObstacle1);

        // Create a composite node
        ObstacleComposite composite = new ObstacleComposite();
        composite.add(stoneLeaf1);
        composite.add(stoneLeaf2);
        composite.add(woodLeaf1);

        // Display all obstacles
        composite.display();


        
        return obstacles;
        
    }
}