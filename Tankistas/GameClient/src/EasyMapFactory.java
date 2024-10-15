/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.ArrayList;

public class EasyMapFactory implements MapFactory {
    @Override
    public ArrayList<Obstacle> createObstacles() {
        ArrayList<Obstacle> obstacles = new ArrayList<>();
        // Create simple obstacle layout for easy map
        obstacles.add(new Obstacle(100, 100));
        obstacles.add(new Obstacle(200, 200));
        // Add more obstacles if necessary
        return obstacles;
    }
}