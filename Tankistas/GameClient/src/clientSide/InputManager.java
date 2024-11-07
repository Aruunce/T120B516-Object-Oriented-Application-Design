package clientSide;

import clientSide.Commands.Command;
import clientSide.Commands.MoveBackwardCommand;
import clientSide.Commands.MoveForwardCommand;
import clientSide.Commands.MoveLeftCommand;
import clientSide.Commands.MoveRightCommand;
import clientSide.Commands.ShootCommand;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class InputManager implements KeyListener  
{
    private final int LEFT = KeyEvent.VK_LEFT;
    private final int RIGHT = KeyEvent.VK_RIGHT;
    private final int UP = KeyEvent.VK_UP;
    private final int DOWN = KeyEvent.VK_DOWN;
    private final int SHOOT = KeyEvent.VK_SPACE;  
    
    private Tank tank;
    private Client client;
    private Map<Integer, Command> commandMap;

    public InputManager(Tank tank) 
    {
        this.client=Client.getGameClient();
        this.tank=tank;
        
        this.commandMap = new HashMap<>();
        
        commandMap.put(LEFT, new MoveLeftCommand(tank));
        commandMap.put(RIGHT, new MoveRightCommand(tank));
        commandMap.put(UP, new MoveForwardCommand(tank));
        commandMap.put(DOWN, new MoveBackwardCommand(tank));
        commandMap.put(SHOOT, new ShootCommand(tank));
    }

    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        System.out.println("KeyPressed: " + KeyEvent.getKeyText(keyCode));
        if (commandMap.containsKey(keyCode)) {
            Command command = commandMap.get(keyCode);
            if (command != null) {
                command.execute();
                client.sendToServer(new Protocol().UpdatePacket(tank.getXposition(),
                        tank.getYposition(), tank.getTankID(), tank.getDirection()));
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}