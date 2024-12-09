package clientSide.State;

import clientSide.Tank;

public class DestroyedState implements TankState {
    private Tank tank;

    public DestroyedState(Tank tank) {
        this.tank = tank;
    }

    @Override
    public void moveLeft() {
        System.out.println("Tank is destroyed - unable to move");
    }

    @Override
    public void moveRight() {
        System.out.println("Tank is destroyed - unable to move");
    }

    @Override
    public void moveForward() {
        System.out.println("Tank is destroyed - unable to move");
    }

    @Override
    public void moveBackward() {
        System.out.println("Tank is destroyed - unable to move");
    }

    @Override
    public void shoot() {
        System.out.println("Tank is destroyed - unable to shoot");
    }
}
