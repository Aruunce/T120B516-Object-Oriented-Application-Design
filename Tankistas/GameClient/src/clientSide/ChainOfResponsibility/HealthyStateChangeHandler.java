package clientSide.ChainOfResponsibility;

import clientSide.Tank;
import clientSide.State.*;

public class HealthyStateChangeHandler extends StateChangeHandler {
    @Override
    public void handleStateChange(Tank tank, String newState) {
        if ("Healthy".equals(newState)) {
            tank.setState(new HealthyState(tank));
            System.out.println("Tank state changed to Healthy.");
        } else if (nextHandler != null) {
            nextHandler.handleStateChange(tank, newState);
        }
    }
}
