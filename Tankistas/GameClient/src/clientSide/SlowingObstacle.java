package clientSide;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

// New slowing obstacle class
public class SlowingObstacle extends Obstacle {
    private float slowFactor = 0.5f;
    
    public SlowingObstacle(int x, int y) {
        super(x, y);
    }
    
    public float getSlowFactor() {
        return slowFactor;
    }
}