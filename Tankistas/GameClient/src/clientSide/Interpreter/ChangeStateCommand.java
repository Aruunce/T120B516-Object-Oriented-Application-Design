package clientSide.Interpreter;

import clientSide.Tank;

public class ChangeStateCommand implements Expression {
    private int tankId;
    private String newState;

    public ChangeStateCommand(int tankId, String newState) {
        this.tankId = tankId;
        this.newState = newState;
    }

    @Override
    public void interpret(Context context) {
        Tank tank = context.getTank(tankId);
        if (tank != null) {
            tank.updateState(newState);
            System.out.println("Tank " + tankId + " state changed to " + newState + ".");
        } else {
            System.out.println("Tank " + tankId + " not found for state change.");
        }
    }
}