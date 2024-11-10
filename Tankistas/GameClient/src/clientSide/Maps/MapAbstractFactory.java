package clientSide.Maps;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class MapAbstractFactory {
    private static Map currentMap;
    private static int currentMapIndex = 0;

    private static final MapFactory[] mapFactories = {
        new SmallMapFactory(),
        new MediumMapFactory(),
        new LargeMapFactory()
    };
    
    public static Map createMap(int index) {
        if (index < 0 || index >= mapFactories.length) {
            throw new IllegalArgumentException("Invalid map index");
        }

        currentMapIndex = index;
        // Create a new map each time to ensure obstacles reset
        currentMap = mapFactories[index].createMap();
        return currentMap;
    }
    
    public static Map getCurrentMap() {
        if (currentMap == null) {
            currentMap = createMap(0); // Default to small map
        }
        return currentMap;
    }
    
    public static int getCurrentMapIndex() {
        return currentMapIndex;
    }
}
