package clientSide.Maps;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class SmallMapFactory implements MapFactory {
    @Override
    public Map createMap() {
        return new SmallMap();
    }
}