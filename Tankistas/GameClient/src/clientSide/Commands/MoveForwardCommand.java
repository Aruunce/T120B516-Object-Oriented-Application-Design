package clientSide.Commands;

import clientSide.Tank;

public class MoveForwardCommand implements Command {
    private Tank tank;

    public MoveForwardCommand(Tank tank) {
        this.tank = tank;
    }

    @Override
    public void execute() {
        System.out.println("MoveForwardCommand");
        tank.moveForward();
    }
}