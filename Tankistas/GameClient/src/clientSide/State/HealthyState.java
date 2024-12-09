package clientSide.State;

import clientSide.Tank;

public class HealthyState implements TankState {
    private Tank tank;

    public HealthyState(Tank tank) {
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
        tank.shot(true);
    }
}
