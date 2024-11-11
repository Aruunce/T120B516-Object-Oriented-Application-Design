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
        initializeObstacles();
    }

    @Override
    protected void initializeObstacles() {
        // Create builders
        Builder stoneBuilder = new StoneWallBuilder();
        Builder woodBuilder = new WoodWallBuilder();
        
        // Add corner stone walls using facade
        obstacleFacade.addObstacleWithBuilder(stoneBuilder, 0, 164, 100); // top left
        obstacleFacade.addObstacleWithBuilder(stoneBuilder, 1, 444, 100); // top right
        obstacleFacade.addObstacleWithBuilder(stoneBuilder, 2, 164, 433); // bottom left
        obstacleFacade.addObstacleWithBuilder(stoneBuilder, 3, 444, 433); // bottom right
        
        // Add wood walls using facade
        obstacleFacade.addObstacleWithBuilder(woodBuilder, 4, 214, 205);
        obstacleFacade.addObstacleWithBuilder(woodBuilder, 5, 394, 205);
        obstacleFacade.addObstacleWithBuilder(woodBuilder, 6, 214, 338);
        obstacleFacade.addObstacleWithBuilder(woodBuilder, 7, 394, 338);
    }
    
}