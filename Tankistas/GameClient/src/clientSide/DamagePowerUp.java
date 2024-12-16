package clientSide;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.io.File;

public class DamagePowerUp extends PowerUp {
    private int damageIncrease;
    private Image powerUpImage;

    public DamagePowerUp(int x, int y, int damageIncrease) {
        super(x, y);
        this.damageIncrease = damageIncrease;

        loadImage();
    }

    @Override
    public void applyEffect(Tank tank) {
        tank.increaseBulletDamage(damageIncrease);
    }

    private void loadImage() {
        try {
            String imagePath = System.getProperty("user.dir") + "/Images/bullet.png";
            File imgFile = new File(imagePath);
            if (imgFile.exists()) {
                ImageIcon icon = new ImageIcon(imgFile.getAbsolutePath());
                // Cache the scaled image
                powerUpImage = icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
            } else {
                System.err.println("Power-up image not found at: " + imagePath);
            }
        } catch (Exception e) {
            System.err.println("Error loading power-up image: " + e.getMessage());
        }
    }


     @Override
    public Image getImage() {
        return powerUpImage;
    }
}