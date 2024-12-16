package clientSide.Interpreter;

import clientSide.Tank;

public class KillTankCommand implements Expression {
    private int tankId;

    public KillTankCommand(int tankId) {
        this.tankId = tankId;
    }

    @Override
    public void interpret(Context context) {
        Tank tank = context.getTank(tankId);
        if (tank != null) {
            tank.reduceLives();
            tank.reduceLives();
            tank.reduceLives();
            tank.reduceLives();
            System.out.println("Tank " + tankId + " killed.");
        } else {
            System.out.println("Tank " + tankId + " not found for killing.");
        }
    }
}