// src/test/java/clientSide/SlowingObstacleTest.java
package test;

import clientSide.Maps.SlowingObstacle;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SlowingObstacleTest {
    private SlowingObstacle slowingObstacle;

    @Before
    public void setUp() {
        slowingObstacle = new SlowingObstacle(100, 100);
    }

    @Test
    public void testConstructor() {
        assertFalse(slowingObstacle.isDestructible()); // Assuming default destructibility is false
    }

    @Test
    public void testGetSlowFactor() {
        assertEquals(0.5f, slowingObstacle.getSlowFactor(), 0.0f);
    }
}