package clientSide.State;

import clientSide.Tank;

public class DamagedState implements TankState {
    private Tank tank;

    public DamagedState(Tank tank) {
        this.tank = tank;
    }

    @Override
    public void moveLeft() {
        System.out.println("Tank is damaged - unable to move");
    }

    @Override
    public void moveRight() {
        System.out.println("Tank is damaged - unable to move");
    }

    @Override
    public void moveForward() {
        System.out.println("Tank is damaged - unable to move");
    }

    @Override
    public void moveBackward() {
        System.out.println("Tank is damaged - unable to move");
    }

    @Override
    public void shoot() {
        tank.shot(true);
    }
}
