// src/clientSide/Maps/StoneWall.java
package clientSide.Maps;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.io.File;

public class StoneWall implements ObstacleImpl {
    private Image image;
    private boolean destructible;

    public StoneWall() {
        this.destructible = false;
        String imagePath = System.getProperty("user.dir") + "/Images/StoneWall.png";            
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
        return 1.0f; // No slowing effect
    }
}