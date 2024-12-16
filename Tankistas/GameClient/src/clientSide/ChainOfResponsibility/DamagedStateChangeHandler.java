package clientSide.ChainOfResponsibility;

import clientSide.Tank;
import clientSide.State.*;

public class DamagedStateChangeHandler extends StateChangeHandler {
    @Override
    public void handleStateChange(Tank tank, String newState) {
        if ("Damaged".equals(newState)) {
            tank.setState(new DamagedState(tank));
            System.out.println("Tank state changed to Damaged.");
        } else if (nextHandler != null) {
            nextHandler.handleStateChange(tank, newState);
        }
    }
}
