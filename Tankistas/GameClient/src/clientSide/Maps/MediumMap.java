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

        clonedMap.obstacles.addAll(createObstacles(clonedMap.obstacles.size()));
        
        this.obstacles = clonedMap.getObstacles();
    }
    
    @Override
    public ArrayList<Obstacle> createObstacles(int size) {
        ArrayList<Obstacle> obstacles = new ArrayList<>();
        
        Builder builder = new WoodWallBuilder();
        
        // corner top left
        obstacles.add(builder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(124, 140);
        obstacles.get(obstacles.size() - 1).setId(size++);
        // corner top right
        obstacles.add(builder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(489, 140);
        obstacles.get(obstacles.size() - 1).setId(size++);
        // corner bottom left
        obstacles.add(builder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(124, 398);
        obstacles.get(obstacles.size() - 1).setId(size++);
        // corner bottom right
        obstacles.add(builder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(489, 398);
        obstacles.get(obstacles.size() - 1).setId(size++);
        
        builder = new StoneWallBuilder();
        
        //center
        obstacles.add(builder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(304, 273);
        obstacles.get(obstacles.size() - 1).setId(size++);
        // corner top left
        obstacles.add(builder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(124, 100);
        obstacles.get(obstacles.size() - 1).setId(size++);
        // corner top right
        obstacles.add(builder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(484, 100);
        obstacles.get(obstacles.size() - 1).setId(size++);      
        // corner bottom left
        obstacles.add(builder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(124, 433);      
        obstacles.get(obstacles.size() - 1).setId(size++);
        // corner bottom right
        obstacles.add(builder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(484, 433);
        obstacles.get(obstacles.size() - 1).setId(size++);
        return obstacles;
    }
}