package clientSide.Interpreter;

import java.util.HashMap;
import java.util.Map;

public class Console {
    private Map<String, Class<? extends Expression>> commands = new HashMap<>();

    public Console() {
        // Register command classes
        commands.put("heal", HealTankCommand.class);
        commands.put("kill", KillTankCommand.class);
        commands.put("changeState", ChangeStateCommand.class);
    }

    public void executeCommand(String command, Context context) {
        String[] parts = command.split(" ");
        if (parts.length < 3) {
            System.out.println("Invalid command.");
            return;
        }

        String action = parts[0];
        int tankId;
        try {
            tankId = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid tank ID.");
            return;
        }

        String newState = parts[2];

        Class<? extends Expression> commandClass = commands.get(action);
        if (commandClass != null) {
            try {
                Expression expression = commandClass.getConstructor(int.class, String.class).newInstance(tankId, newState);
                expression.interpret(context);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error executing command.");
            }
        } else {
            System.out.println("Unknown command.");
        }
    }
}