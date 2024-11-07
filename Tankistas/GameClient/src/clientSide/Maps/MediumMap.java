package clientSide.Maps;


import java.util.ArrayList;
import clientSide.Builder.WoodWallBuilder;
import clientSide.Builder.StoneWallBuilder;
import clientSide.Builder.Builder;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class MediumMap extends Map {
    public MediumMap() {
        SmallMap smallMap = new SmallMap();
        Map clonedMap = smallMap.makeCopy();
        
        clonedMap.obstacles.addAll(createObstacles());
        
        this.obstacles = clonedMap.getObstacles();
    }
    
    @Override
    public ArrayList<Obstacle> createObstacles() {
        ArrayList<Obstacle> obstacles = new ArrayList<>();
        
        Builder builder = new WoodWallBuilder();
        
        // corner top left
        obstacles.add(builder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(124, 140);
        // corner top right
        obstacles.add(builder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(489, 140);
        // corner bottom left
        obstacles.add(builder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(124, 398);
        // corner bottom right
        obstacles.add(builder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(489, 398);
        
        builder = new StoneWallBuilder();
        
        //center
        obstacles.add(builder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(304, 273);
        // corner top left
        obstacles.add(builder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(124, 100);
        // corner top right
        obstacles.add(builder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(484, 100);        
        // corner bottom left
        obstacles.add(builder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(124, 433);        
        // corner bottom right
        obstacles.add(builder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(484, 433);
        return obstacles;
    }
}