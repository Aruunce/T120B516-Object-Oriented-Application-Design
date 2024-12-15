package clientSide.Memento;

import clientSide.Tank;
import clientSide.GameElementVisitor;
import java.util.ArrayList;

public class GameStateMemento {
    private ArrayList<Tank> tanks;
    
    public GameStateMemento(ArrayList<Tank> tanks) {
        this.tanks = new ArrayList<>();
        for (Tank tank : tanks) {
            if (tank != null) {
                try {
                    this.tanks.add(tank.clone());
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public ArrayList<Tank> restore() {
        return tanks;
    }

    public void accept(GameElementVisitor visitor) {
        for (Tank tank : tanks) {
            tank.accept(visitor);
        }
    }
}