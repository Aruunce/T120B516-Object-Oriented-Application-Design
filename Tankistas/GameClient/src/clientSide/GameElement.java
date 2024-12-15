package clientSide;

public interface GameElement {
    void accept(GameElementVisitor visitor);
}
