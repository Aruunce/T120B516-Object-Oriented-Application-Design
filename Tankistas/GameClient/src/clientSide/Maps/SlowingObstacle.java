// src/clientSide/SlowingObstacleImpl.java
package clientSide.Maps;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

import java.io.File;

public class SlowingObstacle implements ObstacleImpl {
    private Image image;
    private int width, height;
    private boolean destructible;
    private float slowFactor;

    public SlowingObstacle() {
        setMaterial("/Images/SlowingTrap.png");
        setSize(35, 35);
        setDestructible(true);
        this.slowFactor = 0.5f;
    }

    @Override
    public void draw(Graphics2D g, int x, int y) {
        if (image != null) {
            g.drawImage(image, x, y, width, height, null);
        }
    }

    @Override
    public void setMaterial(String material) {
        String imagePath = System.getProperty("user.dir") + material;
        File imgFile = new File(imagePath);
        if (imgFile.exists()) {
            this.image = new ImageIcon(imgFile.getAbsolutePath()).getImage();
        }
    }

    @Override
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean isDestructible() {
        return destructible;
    }

    @Override
    public void setDestructible(boolean destructible) {
        this.destructible = destructible;
    }

    public float getSlowFactor() {
        return slowFactor;
    }

    public void setSlowFactor(float slowFactor) {
        this.slowFactor = slowFactor;
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
}