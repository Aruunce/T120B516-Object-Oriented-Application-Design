package clientSide.ChainOfResponsibility;

import clientSide.Tank;
import clientSide.State.*;

public class MoveOnlyStateChangeHandler extends StateChangeHandler {
    @Override
    public void handleStateChange(Tank tank, String newState) {
        if ("MoveOnly".equals(newState)) {
            tank.setState(new MoveOnlyState(tank));
            System.out.println("Tank state changed to MoveOnly.");
        } else if (nextHandler != null) {
            nextHandler.handleStateChange(tank, newState);
        }
    }
}
