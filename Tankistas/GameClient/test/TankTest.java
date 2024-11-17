package test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import org.junit.runners.Parameterized;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Collection;
import java.awt.Graphics2D;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.*;

import clientSide.*;

@RunWith(Parameterized.class)
public class TankTest {
    private Tank tank;
    private int xPosition;
    private int yPosition;

    public TankTest(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            { 0, 0 },
            { 100, 100 },
            { 200, 200 },
            { 300, 300 },
            { 400, 400 }
        });
    }

    //parameterized test
    @Test
    public void testSetXposition() {
        tank.setXpoistion(xPosition);
        assertEquals(xPosition, tank.getXposition());
    }

    //parameterized test
    @Test
    public void testSetYposition() {
        tank.setYposition(yPosition);
        assertEquals(yPosition, tank.getYposition());
    }

    // Mock test for draw method using Mockito
    @Test
    public void testDraw() {
        Graphics2D g = mock(Graphics2D.class);
        
        tank.updateTankImage();
        tank.draw(g);
        
        verify(g).drawImage(any(), eq(tank.getXposition()), eq(tank.getYposition()), isNull());
    }


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

    //@Test
    //public void testSetXposition() {
    //    int newX = 100;
    //    tank.setXpoistion(newX);
    //    assertEquals(newX, tank.getXposition());
    // }

    //@Test
    //public void testSetYposition() {
    //    int newY = 100;
    //    tank.setYposition(newY);
    //    assertEquals(newY, tank.getYposition());
    //}

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