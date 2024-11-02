package clientSide.Maps;


import java.util.ArrayList;

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
        // corner top left
        obstacles.add(new Obstacle(164, 100));
        // corner top right
        obstacles.add(new Obstacle(444, 100));
        // corner bottom left
        obstacles.add(new Obstacle(164, 433));
        // corner bottom right
        obstacles.add(new Obstacle(444, 433));
        // other
        obstacles.add(new Obstacle(214, 205));
        obstacles.add(new Obstacle(394, 205));
        obstacles.add(new Obstacle(214, 338));
        obstacles.add(new Obstacle(394, 338));
        return obstacles;
    }
}