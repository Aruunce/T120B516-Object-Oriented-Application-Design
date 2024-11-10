// src/clientSide/Maps/SlowingObstacle.java
package clientSide.Maps;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.io.File;

public class SlowingObstacle implements ObstacleImpl {
    private Image image;
    private boolean destructible;
    private float slowFactor;

    public SlowingObstacle() {
        this.destructible = true;
        this.slowFactor = 0.5f; // Example slow factor
        String imagePath = System.getProperty("user.dir") + "/Images/SlowingObstacle.png";            
        File imgFile = new File(imagePath);
        if (imgFile.exists()) {
            this.image = new ImageIcon(imgFile.getAbsolutePath()).getImage();
        } else {
            System.err.println("Error: Image not found at path: " + imgFile.getAbsolutePath());
        }
    }

    @Override
    public void draw(Graphics2D g, int x, int y, int width, int height) {
        g.drawImage(image, x, y, width, height, null);
    }

    @Override
    public boolean isDestructible() {
        return destructible;
    }

    @Override
    public float getSlowFactor() {
        return slowFactor;
    }
}