// src/clientSide/TankDecorator.java
package clientSide.Decorator;

import java.awt.image.BufferedImage;

import clientSide.Tank;

public abstract class TankDecorator extends Tank {
    protected Tank decoratedTank;

    public TankDecorator(Tank decoratedTank) {
        this.decoratedTank = decoratedTank;
    }

    @Override
    public void loadImage(int a) {
        decoratedTank.loadImage(a);
    }

    @Override
    public BufferedImage getBuffImage() {
        return decoratedTank.getBuffImage();
    }

    @Override
    public void updateTankImage() {
        decoratedTank.updateTankImage();
    }

}