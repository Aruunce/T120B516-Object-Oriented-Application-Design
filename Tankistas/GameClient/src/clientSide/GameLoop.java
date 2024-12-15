package clientSide;

public class GameLoop {
    private GameMediator mediator;

    public GameLoop(GameMediator mediator) {
        this.mediator = mediator;
    }

    public void update() {
        // Update game logic through the mediator
        mediator.updateGame();
    }
}