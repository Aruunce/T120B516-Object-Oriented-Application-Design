package clientSide.Proxy;

import clientSide.*;

public class TankProxySecured extends Tank {
    private Tank realTank;

    public TankProxySecured(Tank realTank) {
        this.realTank = realTank;
    }

    @Override
    public void shot() {
        System.out.println("Shooting is restricted in this proxy.");
    }
}