package client;


import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class LargeMap extends Map {
    private static final int MAP_WIDTH = 800;
    private static final int MAP_HEIGHT = 600;
    
    public LargeMap() {
        obstacles = createObstacles();
    }
    
    @Override
    public ArrayList<Obstacle> createObstacles() {
        ArrayList<Obstacle> obstacles = new ArrayList<>();
        // Add large map specific obstacles
        // Using a more complex pattern
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                obstacles.add(new Obstacle(200 + i * 300, 200 + j * 200));
            }
        }
        // Add more obstacles as needed
        return obstacles;
    }
    
    @Override
    public int getWidth() {
        return MAP_WIDTH;
    }
    
    @Override
    public int getHeight() {
        return MAP_HEIGHT;
    }
}