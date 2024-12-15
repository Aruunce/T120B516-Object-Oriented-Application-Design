package clientSide;

import java.io.IOException;
import java.util.List;
import clientSide.Maps.MapAbstractFactory;
import clientSide.Maps.Obstacle;

public class Main {
    
    /** Creates a new instance of Main */
    public Main() {
    }
    
    public static void main(String args[]) throws IOException {
        Tank playerTank = new Tank();
        List<Obstacle> obstacles = MapAbstractFactory.getCurrentMap().getObstacles();
        List<PowerUp> powerUps = PowerUpGenerator.generateRandomPowerUps(5); // Generate 5 random power-ups

        ClientGUI client = new ClientGUI();
        client.setVisible(true);  // Ensure the GUI is visible

        // Create the mediator
        GameMediator mediator = new GameMediator(playerTank, obstacles, powerUps, client);

        // Initialize the game loop and start the game
        GameLoop gameLoop = new GameLoop(mediator);
        
        // Main game loop
        while (true) {
            gameLoop.update();
            mediator.updateGame();  // Update the game through the mediator
            try {
                Thread.sleep(16);  // Sleep for ~16ms to achieve ~60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}