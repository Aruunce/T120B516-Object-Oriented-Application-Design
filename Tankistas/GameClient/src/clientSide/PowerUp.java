package clientSide;

import java.awt.Image;

public abstract class PowerUp implements GameElement {
    protected int x, y;
    protected boolean collected = false;

    public PowerUp(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public void accept(GameElementVisitor visitor) {
        visitor.visitPowerUp(this);
    }

    public boolean collidesWith(GameElement element) {
        if (element instanceof Tank) {
            Tank tank = (Tank) element;
            return (tank.getXposition() < x + 32 &&
                    tank.getXposition() + 32 > x &&
                    tank.getYposition() < y + 32 &&
                    tank.getYposition() + 32 > y);
        }
        return false;
    }

    public abstract void applyEffect(Tank tank);

    public abstract Image getImage();
}