package clientSide.Interpreter;

import clientSide.Tank;
import clientSide.GameBoardPanel;


public class Context {

    private GameBoardPanel gameBoardPanel;
    private int tankId;



    public Context(GameBoardPanel gameBoardPanel) {
        this.gameBoardPanel = gameBoardPanel;
    }

    public Context(GameBoardPanel gameBoardPanel, int tankId) {

        this.gameBoardPanel = gameBoardPanel;

        this.tankId = tankId;

    }



    public GameBoardPanel getGameBoardPanel() {

        return gameBoardPanel;

    }



    public Tank getTank(int tankId) {
        return gameBoardPanel.getTank(tankId);
    }

}

