/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.ArrayList;

public class MediumMapFactory implements MapFactory {
    @Override
    public ArrayList<Obstacle> createObstacles() {
        ArrayList<Obstacle> obstacles = new ArrayList<>();
        // Create medium difficulty obstacle layout
        obstacles.add(new Obstacle(100, 100));
        obstacles.add(new Obstacle(150, 150));
        obstacles.add(new Obstacle(200, 200));
        // Add more obstacles
        return obstacles;
    }
}