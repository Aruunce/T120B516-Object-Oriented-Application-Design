package clientSide.Maps;


import java.util.ArrayList;
import clientSide.Builder.WoodWallBuilder;
import clientSide.Builder.StoneWallBuilder;
import clientSide.Builder.Builder;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class SmallMap extends Map {
    public SmallMap() {
        super();
        this.obstacles.addAll(createObstacles(0));
    }
    
    @Override
    public ArrayList<Obstacle> createObstacles(int size) {
        obstacles = new ArrayList<>();
        ObstacleFacade facade = new ObstacleFacade();

        Builder stoneBuilder = new StoneWallBuilder();
        Builder woodBuilder = new WoodWallBuilder();

        // corner top left
        facade.addObstacle(obstacles, stoneBuilder, 164, 100);
        // corner top right
        facade.addObstacle(obstacles, stoneBuilder, 444, 100);
        // corner bottom left
        facade.addObstacle(obstacles, stoneBuilder, 164, 433);
        // corner bottom right
        facade.addObstacle(obstacles, stoneBuilder, 444, 433);

        // other
        facade.addObstacle(obstacles, woodBuilder, 214, 205);
        facade.addObstacle(obstacles, woodBuilder, 394, 205);
        facade.addObstacle(obstacles, woodBuilder, 214, 338);
        facade.addObstacle(obstacles, woodBuilder, 394, 338);
        return obstacles;
    }
}