package clientSide.ChainOfResponsibility;

import clientSide.Tank;

public abstract class StateChangeHandler {
    protected StateChangeHandler nextHandler;

    public void setNextHandler(StateChangeHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleStateChange(Tank tank, String newState);
}
