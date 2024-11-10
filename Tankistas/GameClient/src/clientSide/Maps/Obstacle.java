package clientSide.Maps;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.ArrayList;

public class Obstacle {

    private int x, y, width, height, id;
    private Image image;
    private boolean destructible;

    public Obstacle() {}
    
    public Obstacle(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 40;
        this.height = 40;
        this.destructible = false;

        String imagePath = System.getProperty("user.dir") + "/Images/StoneWall.png";            
        File imgFile = new File(imagePath);  // Use File to construct the path
        if (imgFile.exists()) {
            this.image = new ImageIcon(imgFile.getAbsolutePath()).getImage();  // Load image from file
        } else {
            System.err.println("Error: Image not found at path: " + imgFile.getAbsolutePath());
        }
    }
    
    public void draw(Graphics2D g) {
        g.drawImage(image, x, y, width, height, null);
    }

    public boolean collidesWith(int objX, int objY, int objWidth, int objHeight) {
        Rectangle2D obstacleRect = new Rectangle2D.Float(x, y, width, height);
        Rectangle2D objRect = new Rectangle2D.Float(objX, objY, objWidth, objHeight);

        return obstacleRect.intersects(objRect);
    }
    
    public boolean isDestructible() {
        return destructible;
    }
    
    public void setSize (int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    public void setMaterial (String imgPath) {
        String imagePath = System.getProperty("user.dir") + imgPath;
        File imgFile = new File(imagePath);
        
        if (imgFile.exists()) {
            this.image = new ImageIcon(imgFile.getAbsolutePath()).getImage();
        } else {
            System.err.println("Error: Image not found at path: " + imgFile.getAbsolutePath());
        }
    }
    
    public void setDestructability (Boolean destructability) {
        this.destructible = destructability;
    }
    
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void setId(int id ) {
        this.id = id;
    }
    
    public int getId() {
        return this.id;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
}
