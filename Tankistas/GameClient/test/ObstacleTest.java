// src/test/java/clientSide/ObstacleTest.java
package test;

import clientSide.Maps.Obstacle;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import java.io.File;
import java.awt.Color;

public class ObstacleTest {
    private Obstacle obstacle;

    @Before
    public void setUp() {
        obstacle = new Obstacle(100, 100);
    }


    @Test
    public void testSetAndGetDestructibility() {
        obstacle.setDestructability(true);
        assertTrue(obstacle.isDestructible());
    }


    @Test
    public void testCollidesWith() {
        assertTrue(obstacle.collidesWith(100, 100, 40, 40));
        assertFalse(obstacle.collidesWith(200, 200, 40, 40));
    }
}