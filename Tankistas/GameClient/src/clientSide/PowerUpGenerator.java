package clientSide;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PowerUpGenerator {
    private static final int MAP_WIDTH = 609;
    private static final int MAP_HEIGHT = 523;
    private static final int POWER_UP_SIZE = 32;
    private static final Random random = new Random();

    public static List<PowerUp> generateRandomPowerUps(int count) {
        List<PowerUp> powerUps = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int x = random.nextInt(MAP_WIDTH - POWER_UP_SIZE);
            int y = random.nextInt(MAP_HEIGHT - POWER_UP_SIZE);
            int type = random.nextInt(1); // Add more types as needed
            switch (type) {
                case 0:
                    powerUps.add(new DamagePowerUp(x, y, 5)); // Increase bullet damage by 5
                    break;
                // Add more cases for different power-up types
            }
        }
        return powerUps;
    }
}