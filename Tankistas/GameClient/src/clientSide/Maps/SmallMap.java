package clientSide.Maps;


import java.util.ArrayList;
import clientSide.Builder.WoodWallBuilder;
import clientSide.Builder.StoneWallBuilder;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class SmallMap extends Map {
    public SmallMap() {
        super();
        this.obstacles.addAll(createObstacles());
    }
    
    @Override
    public ArrayList<Obstacle> createObstacles() {
        ArrayList<Obstacle> obstacles = new ArrayList<>();
        
        StoneWallBuilder stoneBuilder = new StoneWallBuilder();
        
        // corner top left
        obstacles.add(stoneBuilder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(164, 100);
        // corner top right
        obstacles.add(stoneBuilder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(444, 100);
        // corner bottom left
        obstacles.add(stoneBuilder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(164, 433);
        // corner bottom right
        obstacles.add(stoneBuilder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(444, 433);
        
        WoodWallBuilder woodBuilder = new WoodWallBuilder();
        
        // other
        obstacles.add(woodBuilder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(214, 205);
        obstacles.add(woodBuilder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(394, 205);
        obstacles.add(woodBuilder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(214, 338);
        obstacles.add(woodBuilder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(394, 338);
        
        return obstacles;
    }
}