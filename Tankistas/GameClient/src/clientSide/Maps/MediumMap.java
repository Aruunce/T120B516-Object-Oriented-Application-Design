package clientSide.Maps;

import clientSide.StrategyAdapter.MediumMapObstacleStrategy;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class MediumMap extends Map {
    public MediumMap() {
       super();
        setObstacleCreationStrategy(new MediumMapObstacleStrategy());
        this.obstacles.addAll(createObstacles(0));
    }
}