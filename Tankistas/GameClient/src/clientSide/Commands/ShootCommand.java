package clientSide.Commands;

import clientSide.Client;
import clientSide.Protocol;
import clientSide.Tank;

public class ShootCommand implements Command {
    private Tank tank;
    private Client client;

    public ShootCommand(Tank tank) {
        this.tank = tank;
        this.client = Client.getGameClient();
    }

    @Override
    public void execute() {
        if (tank.getState() == "Healthy" || tank.getState() == "Damaged") {
            client.sendToServer(new Protocol().ShotPacket(tank.getTankID()));
        }

        tank.shot();
    }
    

    @Override
    public void undo() {
        // This is a placeholder 
        System.out.println("Undo shoot command");
    }
}