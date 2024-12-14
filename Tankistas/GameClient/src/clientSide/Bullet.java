package clientSide;

public class Bullet implements GameElement {
    private int x, y;
    private int direction;
    private boolean destroyed = false;
    private int damage;

    public Bullet(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void move() {
        switch (direction) {
            case 1: y -= 5; break; // Up
            case 2: x += 5; break; // Right
            case 3: y += 5; break; // Down
            case 4: x -= 5; break; // Left
        }
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public void accept(GameElementVisitor visitor) {
        visitor.visitBullet(this);
    }

    public boolean collidesWith(GameElement element) {
        // Implement collision detection logic
        return false;
    }
}