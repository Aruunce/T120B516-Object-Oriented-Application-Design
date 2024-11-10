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
        ArrayList<Obstacle> obstacles = new ArrayList<>();
        
        Builder builder = new StoneWallBuilder();
        
        // corner top left
        obstacles.add(builder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(164, 100);
        obstacles.get(obstacles.size() - 1).setId(obstacles.size() - 1);
        // corner top right
        obstacles.add(builder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(444, 100);
        obstacles.get(obstacles.size() - 1).setId(obstacles.size() - 1);
        // corner bottom left
        obstacles.add(builder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(164, 433);
        obstacles.get(obstacles.size() - 1).setId(obstacles.size() - 1);
        // corner bottom right
        obstacles.add(builder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(444, 433);
        obstacles.get(obstacles.size() - 1).setId(obstacles.size() - 1);
        
        builder = new WoodWallBuilder();
        
        // other
        obstacles.add(builder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(214, 205);
        obstacles.get(obstacles.size() - 1).setId(obstacles.size() - 1);
        obstacles.add(builder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(394, 205);
        obstacles.get(obstacles.size() - 1).setId(obstacles.size() - 1);
        obstacles.add(builder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(214, 338);
        obstacles.get(obstacles.size() - 1).setId(obstacles.size() - 1);
        obstacles.add(builder.addSize().addMaterial().addDestructability().getBuildable());
        obstacles.get(obstacles.size() - 1).setPosition(394, 338);
        obstacles.get(obstacles.size() - 1).setId(obstacles.size() - 1);
        
        return obstacles;
    }
}