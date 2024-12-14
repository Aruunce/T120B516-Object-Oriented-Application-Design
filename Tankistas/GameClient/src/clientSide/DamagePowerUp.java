package clientSide;

import javax.swing.ImageIcon;
import java.awt.Image;

public class DamagePowerUp extends PowerUp {
    private int damageIncrease;

    public DamagePowerUp(int x, int y, int damageIncrease) {
        super(x, y);
        this.damageIncrease = damageIncrease;
    }

    @Override
    public void applyEffect(Tank tank) {
        tank.increaseBulletDamage(damageIncrease);
    }

    @Override
    public Image getImage() {
        // Load and return the image for the damage power-up
        String imagePath = System.getProperty("user.dir") + "/Images/bullet.jpg";
        return new ImageIcon(imagePath).getImage();
    }
}