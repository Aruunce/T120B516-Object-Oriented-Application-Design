package clientSide.Interpreter;

import clientSide.Tank;

public class HealTankCommand implements Expression {
    private int tankId;

    public HealTankCommand(int tankId) {
        this.tankId = tankId;
    }

    @Override
    public void interpret(Context context) {
        Tank tank = context.getTank(tankId);
        if (tank != null) {
            tank.resetLives();
            System.out.println("Tank " + tankId + " healed.");
        } else {
            System.out.println("Tank " + tankId + " not found for healing.");
        }
    }
}