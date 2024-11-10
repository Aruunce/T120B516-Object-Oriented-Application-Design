package test;

import clientSide.Maps.*;
import clientSide.Position;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class MapTest {
    private Map smallMap;
    private Map mediumMap;
    private Map largeMap;

    @Before
    public void setUp() {
        smallMap = new SmallMap();
        mediumMap = new MediumMap();
        largeMap = new LargeMap();
    }

    @Test
    public void testCreateObstacles() {
        ArrayList<Obstacle> smallMapObstacles = smallMap.createObstacles();
        assertNotNull(smallMapObstacles);
        assertFalse(smallMapObstacles.isEmpty());

        ArrayList<Obstacle> mediumMapObstacles = mediumMap.createObstacles();
        assertNotNull(mediumMapObstacles);
        assertFalse(mediumMapObstacles.isEmpty());

        ArrayList<Obstacle> largeMapObstacles = largeMap.createObstacles();
        assertNotNull(largeMapObstacles);
        assertFalse(largeMapObstacles.isEmpty());
    }

    @Test
    public void testGetWidth() {
        assertEquals(609, smallMap.getWidth());
        assertEquals(609, mediumMap.getWidth());
        assertEquals(609, largeMap.getWidth());
    }

    @Test
    public void testGetHeight() {
        assertEquals(523, smallMap.getHeight());
        assertEquals(523, mediumMap.getHeight());
        assertEquals(523, largeMap.getHeight());
    }

    @Test
    public void testSetWidth() {
        smallMap.setWidth(700);
        assertEquals(700, smallMap.getWidth());

        mediumMap.setWidth(900);
        assertEquals(900, mediumMap.getWidth());

        largeMap.setWidth(1100);
        assertEquals(1100, largeMap.getWidth());
    }

    @Test
    public void testSetHeight() {
        smallMap.setHeight(600);
        assertEquals(600, smallMap.getHeight());

        mediumMap.setHeight(700);
        assertEquals(700, mediumMap.getHeight());

        largeMap.setHeight(900);
        assertEquals(900, largeMap.getHeight());
    }

    @Test
    public void testGetObstacles() {
        assertNotNull(smallMap.getObstacles());
        assertNotNull(mediumMap.getObstacles());
        assertNotNull(largeMap.getObstacles());
    }

    @Test
    public void testGetRandomValidPosition() {
        Position smallMapPosition = smallMap.getRandomValidPosition();
        assertTrue(smallMap.isValidPosition(smallMapPosition.x, smallMapPosition.y));

        Position mediumMapPosition = mediumMap.getRandomValidPosition();
        assertTrue(mediumMap.isValidPosition(mediumMapPosition.x, mediumMapPosition.y));

        Position largeMapPosition = largeMap.getRandomValidPosition();
        assertTrue(largeMap.isValidPosition(largeMapPosition.x, largeMapPosition.y));
    }

    @Test
    public void testIsValidPosition() {
        assertTrue(smallMap.isValidPosition(50, 50));
        assertFalse(smallMap.isValidPosition(-1, -1));

        assertTrue(mediumMap.isValidPosition(50, 50));
        assertFalse(mediumMap.isValidPosition(900, 900));

        assertTrue(largeMap.isValidPosition(50, 50));
        assertFalse(largeMap.isValidPosition(1100, 1100));
    }

    @Test
    public void testMakeCopy() {
        Map smallMapCopy = smallMap.makeCopy();
        assertNotNull(smallMapCopy);
        assertEquals(smallMap.getWidth(), smallMapCopy.getWidth());
        assertEquals(smallMap.getHeight(), smallMapCopy.getHeight());

        Map mediumMapCopy = mediumMap.makeCopy();
        assertNotNull(mediumMapCopy);
        assertEquals(mediumMap.getWidth(), mediumMapCopy.getWidth());
        assertEquals(mediumMap.getHeight(), mediumMapCopy.getHeight());

        Map largeMapCopy = largeMap.makeCopy();
        assertNotNull(largeMapCopy);
        assertEquals(largeMap.getWidth(), largeMapCopy.getWidth());
        assertEquals(largeMap.getHeight(), largeMapCopy.getHeight());
    }
    @Test
    public void testMapFactory() {
        MapFactory smallMapFactory = new SmallMapFactory();
        MapFactory mediumMapFactory = new MediumMapFactory();
        MapFactory largeMapFactory = new LargeMapFactory();

        Map smallMap = smallMapFactory.createMap();
        Map mediumMap = mediumMapFactory.createMap();
        Map largeMap = largeMapFactory.createMap();

        assertNotNull(smallMap);
        assertNotNull(mediumMap);
        assertNotNull(largeMap);

        assertTrue(smallMap instanceof SmallMap);
        assertTrue(mediumMap instanceof MediumMap);
        assertTrue(largeMap instanceof LargeMap);
    }

    @Test
    public void testMapAbstractFactory() {
        Map smallMap = MapAbstractFactory.createMap(0);
        Map mediumMap = MapAbstractFactory.createMap(1);
        Map largeMap = MapAbstractFactory.createMap(2);

        assertNotNull(smallMap);
        assertNotNull(mediumMap);
        assertNotNull(largeMap);

        assertTrue(smallMap instanceof SmallMap);
        assertTrue(mediumMap instanceof MediumMap);
        assertTrue(largeMap instanceof LargeMap);

        Map currentMap = MapAbstractFactory.getCurrentMap();
        assertNotNull(currentMap);
    }
}