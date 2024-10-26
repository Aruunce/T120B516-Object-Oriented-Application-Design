package clientSide.Commands;

import clientSide.Tank;

public class ShootCommand implements Command {
    private Tank tank;

    public ShootCommand(Tank tank) {
        this.tank = tank;
    }

    @Override
    public void execute() {
        System.out.println("ShootCommand");
        tank.shot();
    }
}