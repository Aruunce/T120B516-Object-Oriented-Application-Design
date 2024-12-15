package clientSide;

import java.util.List;
import clientSide.Maps.Obstacle;

public class GameMediator {
    private Tank playerTank;
    private List<Obstacle> obstacles;
    private List<PowerUp> powerUps;
    private ClientGUI clientGUI;

    public GameMediator(Tank playerTank, List<Obstacle> obstacles, List<PowerUp> powerUps, ClientGUI clientGUI) {
        this.playerTank = playerTank;
        this.obstacles = obstacles;
        this.powerUps = powerUps;
        this.clientGUI = clientGUI;
    }

    public void updateGame() {
        // Update game logic
        playerTank.update();
        for (Obstacle obstacle : obstacles) {
            obstacle.update();
        }
        for (PowerUp powerUp : powerUps) {
            powerUp.update();
        }
        clientGUI.repaint();
    }

    public Tank getPlayerTank() {
        return playerTank;
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public List<PowerUp> getPowerUps() {
        return powerUps;
    }

    public ClientGUI getClientGUI() {
        return clientGUI;
    }
}