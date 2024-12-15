package clientSide;

public class CollisionHandler {
    private GameElementVisitor damageVisitor = new DamageVisitor();

    public void handleCollision(GameElement element1, GameElement element2) {
        element1.accept(damageVisitor);
        element2.accept(damageVisitor);
    }
}