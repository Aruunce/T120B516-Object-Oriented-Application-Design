package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.awt.image.BufferedImage;
import clientSide.*;

public class TankTest {
    private Tank tank;

    @Before
    public void setUp() {
        tank = new Tank();
    }

    @Test
    public void testGetXposition() {
        int expectedX = tank.getXposition();
        assertEquals(expectedX, tank.getXposition());
    }

    @Test
    public void testGetYposition() {
        int expectedY = tank.getYposition();
        assertEquals(expectedY, tank.getYposition());
    }

    @Test
    public void testMoveLeft() {
        tank.moveLeft();//Point the tank to the left
        int initialX = tank.getXposition();
        tank.moveLeft();
        assertTrue(tank.getXposition() < initialX);
    }

    @Test
    public void testMoveRight() {
        tank.moveRight();//Point the tank to the right
        int initialX = tank.getXposition();
        tank.moveRight();
        assertTrue(tank.getXposition() > initialX);
    }

    @Test
    public void testMoveForward() {
        int initialY = tank.getYposition();
        tank.moveForward();
        assertTrue(tank.getYposition() < initialY);
    }

    @Test
    public void testMoveBackward() {
        int initialY = tank.getYposition();
        tank.moveBackward();
        assertTrue(tank.getYposition() > initialY);
    }

    @Test
    public void testShot() {
        int initialBombCount = tank.getCurrentBombCount();
        tank.shot();
        assertEquals(initialBombCount + 1, tank.getCurrentBombCount());
    }

    @Test
    public void testSetAndGetDirection() {
        int newDirection = 2;
        tank.setDirection(newDirection);
        assertEquals(newDirection, tank.getDirection());
    }

    @Test
    public void testSetAndGetTankID() {
        int newID = 5;
        tank.setTankID(newID);
        assertEquals(newID, tank.getTankID());
    }

    @Test
    public void testReduceLives() {
        int initialLives = tank.getLives();
        tank.reduceLives();
        assertEquals(initialLives - 1, tank.getLives());
    }

    @Test
    public void testResetLives() {
        tank.reduceLives();
        tank.resetLives();
        assertEquals(3, tank.getLives());
    }

    @Test
    public void testIsDead() {
        tank.resetLives();
        assertFalse(tank.isDead());
        tank.reduceLives();
        tank.reduceLives();
        tank.reduceLives();
        assertTrue(tank.isDead());
    }

    @Test
    public void testCheckCollision() {
        int x = tank.getXposition();
        int y = tank.getYposition();
        assertFalse(tank.checkCollision(x, y));
    }

    @Test
    public void testLoadImage() {
        tank.loadImage(4);
        assertNotNull(tank.getBuffImage());
    }

    @Test
    public void testUpdateTankImage() {
        tank.setDirection(1);
        tank.updateTankImage();
        assertNotNull(tank.getBuffImage());
    }

    @Test
    public void testGetCurrentBombCount() {
        int initialBombCount = tank.getCurrentBombCount();
        assertEquals(initialBombCount, tank.getCurrentBombCount());
    }

    @Test
    public void testSetXposition() {
        int newX = 100;
        tank.setXpoistion(newX);
        assertEquals(newX, tank.getXposition());
    }

    @Test
    public void testSetYposition() {
        int newY = 100;
        tank.setYposition(newY);
        assertEquals(newY, tank.getYposition());
    }

    @Test
    public void testGetBuffImage() {
        BufferedImage image = tank.getBuffImage();
        assertNotNull(image);
    }

    @Test
    public void testGetTankID() {
        int id = tank.getTankID();
        assertEquals(0, id);
    }

    @Test
    public void testGetDirection() {
        int direction = tank.getDirection();
        assertEquals(1, direction);
    }
}