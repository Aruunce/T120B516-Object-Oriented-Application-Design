package clientSide.Maps;

import clientSide.StrategyAdapter.SmallMapObstacleStrategy;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class SmallMap extends Map {
    public SmallMap() {
        super();
        setObstacleCreationStrategy(new SmallMapObstacleStrategy());
        this.obstacles.addAll(createObstacles(0));
    }
}