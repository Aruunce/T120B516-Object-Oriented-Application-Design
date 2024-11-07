package clientSide.Commands;

import clientSide.Tank;

public class MoveLeftCommand implements Command {
    private Tank tank;

    public MoveLeftCommand(Tank tank) {
        this.tank = tank;
    }

    @Override
    public void execute() {
        tank.moveLeft();
    }
}