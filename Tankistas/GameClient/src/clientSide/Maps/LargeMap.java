package clientSide.Maps;

import clientSide.StrategyAdapter.LargeMapObstacleStrategy;
import clientSide.StrategyAdapter.LargeMapObstacleStrategyAdapter;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class LargeMap extends Map {
    public LargeMap() {
        super();
        LargeMapObstacleStrategy largeMapObstacleStrategy = new LargeMapObstacleStrategy();
        setObstacleCreationStrategy(new LargeMapObstacleStrategyAdapter(largeMapObstacleStrategy));
        this.obstacles.addAll(createObstacles(0));
    }

}