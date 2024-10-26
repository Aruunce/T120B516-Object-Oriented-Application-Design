package clientSide.Commands;

import clientSide.Tank;

public class MoveRightCommand implements Command {
    private Tank tank;

    public MoveRightCommand(Tank tank) {
        this.tank = tank;
    }

    @Override
    public void execute() {
        System.out.println("MoveRightCommand");
        tank.moveRight();
    }
}