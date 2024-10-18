package client;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class MapAbstractFactory {
    private static Map currentMap;
    private static final Map[] availableMaps = new Map[3];
    
    public static Map createMap(int index) {
        if (availableMaps[index] == null) {
            switch (index) {
                case 0:
                    availableMaps[index] = new SmallMapFactory().createMap();
                    break;
                case 1:
                    availableMaps[index] = new MediumMapFactory().createMap();
                    break;
                case 2:
                    availableMaps[index] = new LargeMapFactory().createMap();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid map index");
            }
        }
        currentMap = availableMaps[index];
        return currentMap;
    }
    
    public static Map getCurrentMap() {
        if (currentMap == null) {
            currentMap = createMap(0); // Default to small map
        }
        return currentMap;
    }
}