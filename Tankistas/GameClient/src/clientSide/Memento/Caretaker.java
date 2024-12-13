package clientSide.Memento;

import java.util.ArrayList;

public class Caretaker {
    private final ArrayList<GameStateMemento> mementoList;
    
    public Caretaker() {
        mementoList = new ArrayList<GameStateMemento>();
    }
    
    public void add(GameStateMemento memento) {
        mementoList.add(memento);
    }
    
    public GameStateMemento get(int index) {
        GameStateMemento restoreMemento = mementoList.get(index);
        mementoList.remove(index);
        return restoreMemento;
    }
    
    public int size() {
        return mementoList.size();
    }
}
