package clientSide.ChainOfResponsibility;

import clientSide.Tank;
import clientSide.State.*;

public class DestroyedStateChangeHandler extends StateChangeHandler {
    @Override
    public void handleStateChange(Tank tank, String newState) {
        if ("Destroyed".equals(newState)) {
            tank.setState(new DestroyedState(tank));
            System.out.println("Tank state changed to Destroyed.");
        } else if (nextHandler != null) {
            nextHandler.handleStateChange(tank, newState);
        }
    }
}
