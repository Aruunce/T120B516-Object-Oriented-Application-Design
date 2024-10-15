/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class ConcreteMapFactory extends AbstractMapFactory {
    @Override
    public MapFactory getMapFactory(int level) {
        switch (level) {
            case 1:
                return new EasyMapFactory();
            case 2:
                return new MediumMapFactory();
            case 3:
                return new HardMapFactory();
            default:
                throw new IllegalArgumentException("Invalid level");
        }
    }
}