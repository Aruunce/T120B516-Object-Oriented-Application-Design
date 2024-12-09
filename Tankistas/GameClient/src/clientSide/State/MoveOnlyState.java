package clientSide.State;

import clientSide.Tank;

public class MoveOnlyState implements TankState {
    private Tank tank;

    public MoveOnlyState(Tank tank) {
        this.tank = tank;
    }

    @Override
    public void moveLeft() {
        tank.tankMoveLeft();
    }

    @Override
    public void moveRight() {
        tank.tankMoveRight();
    }

    @Override
    public void moveForward() {
        tank.tankMoveForward();
    }

    @Override
    public void moveBackward() {
        tank.tankMoveBackward();
    }

    @Override
    public void shoot() {
        System.out.println("Tank is in move only state - unable to shoot");
    }
}
