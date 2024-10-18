package client;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Obstacle {

    private int x, y, width, height;
    private Image image;
    private static ArrayList<Obstacle> obstacles = obstaclesCoords();

    public Obstacle(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 40;
        this.height = 40;
        this.image = new ImageIcon("Images/obstacle.png").getImage();
    }
    
    public void draw(Graphics2D g) {
        g.drawImage(image, x, y, width, height, null);
    }

    public boolean collidesWith(int objX, int objY, int objWidth, int objHeight) {
        Rectangle2D obstacleRect = new Rectangle2D.Float(x, y, width, height);
        Rectangle2D objRect = new Rectangle2D.Float(objX, objY, objWidth, objHeight);

        return obstacleRect.intersects(objRect);
    }

    public static ArrayList<Obstacle> obstaclesCoords() {
        ArrayList<Obstacle> obstacles = new ArrayList<>();

        // corner top left
        System.out.print("obstacle print");
        obstacles.add(new Obstacle(124, 100));
        obstacles.add(new Obstacle(124, 140));
        obstacles.add(new Obstacle(164, 100));
        // corner top right
        obstacles.add(new Obstacle(484, 100));
        obstacles.add(new Obstacle(484, 140));
        obstacles.add(new Obstacle(444, 100));
        // corner bottom left
        obstacles.add(new Obstacle(124, 433));
        obstacles.add(new Obstacle(164, 433));
        obstacles.add(new Obstacle(124, 393));
        // corner bottom right
        obstacles.add(new Obstacle(484, 433));
        obstacles.add(new Obstacle(444, 433));
        obstacles.add(new Obstacle(484, 393));
        // edges
        obstacles.add(new Obstacle(304, 478));
        obstacles.add(new Obstacle(304, 54));
        obstacles.add(new Obstacle(73, 273));
        obstacles.add(new Obstacle(536, 273));
        // center
        obstacles.add(new Obstacle(304, 273));
        // other
        obstacles.add(new Obstacle(214, 205));
        obstacles.add(new Obstacle(394, 205));
        obstacles.add(new Obstacle(214, 338));
        obstacles.add(new Obstacle(394, 338));

        return obstacles;
    }
}
