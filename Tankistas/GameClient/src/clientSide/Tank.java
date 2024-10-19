package clientSide;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Tank {
    
    private Image[] tankImg;
    private BufferedImage ImageBuff;
    private Bomb bomb[] = new Bomb[1000];
    private int curBomb = 0;
    private int tankID;
    private int posiX = -1, posiY = -1;
    private int direction = 1;
    private float velocityX = 0.03125f, velocityY = 0.03125f;
    private int width = 559, height = 473;
    private int lives = 3;

    public int getLives() {
        return lives;
    }

    public void reduceLives() {
        lives--;
    }

    public void resetLives() {
        lives = 3;
    }

    public boolean isDead() {
        return lives <= 0;
    }

    public int getDirection() {
        return direction;
    }

    /** Creates a new instance of Tank */
    public Tank() {
        Map currentMap = MapAbstractFactory.getCurrentMap();
        do {
            posiX = (int)(Math.random() * currentMap.getWidth());
            posiY = (int)(Math.random() * currentMap.getHeight());
        } while (posiX < 70 || posiY < 50 || posiY > currentMap.getHeight() - 43 || posiX > currentMap.getWidth() - 43 || checkCollision(posiX, posiY));

        loadImage(4);
    }

    public Tank(int x, int y, int dir, int id) {
        posiX = x;
        posiY = y;
        tankID = id;
        direction = dir;
        loadImage(0);
    }

    public void loadImage(int a) {
        tankImg = new Image[4];  // Initialize the tank image array
        for (int i = a; i < tankImg.length + a; i++) {

            String imagePath = System.getProperty("user.dir") + "/Images/" + i + ".PNG";  // Correct relative path based on your structure
            
            File imgFile = new File(imagePath);  // Use File to construct the path
            if (imgFile.exists()) {
                System.out.println("Loading image from: " + imgFile.getAbsolutePath());
                tankImg[i - a] = new ImageIcon(imgFile.getAbsolutePath()).getImage();  // Load image from file
            } else {
                // Print out the image path that is being searched
                System.err.println("Error: Image not found at path: " + imgFile.getAbsolutePath());
            }
        }

        // Validate that the image exists before proceeding
        if (tankImg[direction - 1] != null) {
            int imgWidth = tankImg[direction - 1].getWidth(null);
            int imgHeight = tankImg[direction - 1].getHeight(null);

            if (imgWidth > 0 && imgHeight > 0) {
                // Create the BufferedImage using the loaded image's dimensions
                ImageBuff = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
                // Draw the image onto the BufferedImage
                ImageBuff.createGraphics().drawImage(tankImg[direction - 1], 0, 0, null);
            } else {
                System.err.println("Error: Invalid image dimensions for direction " + (direction - 1));
            }
        } else {
            System.err.println("Error: No valid image for direction " + (direction - 1));
        }
    }

    public BufferedImage getBuffImage() {
        return ImageBuff;
    }

    private void updateTankImage() {
        if (tankImg[direction - 1] != null) {
            // Update the BufferedImage based on the current direction
            int imgWidth = tankImg[direction - 1].getWidth(null);
            int imgHeight = tankImg[direction - 1].getHeight(null);
    
            if (imgWidth > 0 && imgHeight > 0) {
                ImageBuff = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
                ImageBuff.createGraphics().drawImage(tankImg[direction - 1], 0, 0, null);
            } else {
                System.err.println("Error: Invalid image dimensions for direction " + direction);
            }
        } else {
            System.err.println("Error: No valid image for direction " + direction);
        }
    }

    public int getXposition() {
        return posiX;
    }

    public int getYposition() {
        return posiY;
    }

    public void setXpoistion(int x) {
        posiX = x;
    }

    public void setYposition(int y) {
        posiY = y;
    }

    public void moveLeft() {
        if (direction == 1 || direction == 3) {
            direction = 4;
            updateTankImage();
        } else {
            int temp = (int)(posiX - velocityX * posiX);
            if (!checkCollision(temp, posiY) && temp < 70) {
                posiX = 70;
            } else if (!checkCollision(temp, posiY)) {
                posiX = temp;
            }
        }
    }

    public void moveRight() {
        if (direction == 1 || direction == 3) {
            direction = 2;
            updateTankImage();
        } else {       
            int temp = (int)(posiX + velocityX * posiX);
            if (!checkCollision(temp, posiY) && temp > width - 43 + 20) {
                posiX = width - 43 + 20;
            } else if (!checkCollision(temp, posiY)) {
                posiX = temp;
            }
        }
    }

    public void moveForward() {
        if (direction == 2 || direction == 4) {
            direction = 1;
            updateTankImage();
        } else {
            int temp = (int)(posiY - velocityY * posiY);
            if (!checkCollision(posiX, temp) && temp < 50) {
                posiY = 50;
            } else if (!checkCollision(posiX, temp)) {
                posiY = temp;
            }
        }
    }

    public void moveBackward() {
        if (direction == 2 || direction == 4) {
            direction = 3;
            updateTankImage();
        } else {
            int temp = (int)(posiY + velocityY * posiY);   
            if (!checkCollision(posiX, temp) && temp > height - 43 + 50) {
                posiY = height - 43 + 50;
            } else if (!checkCollision(posiX, temp)) {
                posiY = temp;
            }
        }
    }

    public void shot() {
        bomb[curBomb] = new Bomb(this.getXposition(), this.getYposition(), direction);
        bomb[curBomb].startBombThread(true);
        curBomb++;
    }

    public Bomb[] getBomb() {
        return bomb;
    }

    public void setTankID(int id) {
        tankID = id;
    }

    public int getTankID() {
        return tankID;
    }

    public void setDirection(int dir) {
        direction = dir;
        updateTankImage();
    }

    public boolean checkCollision(int xP, int yP) {
        ArrayList<Tank> clientTanks = GameBoardPanel.getClients();
        int x, y;

        if (clientTanks != null) {
            for (int i = 1; i < clientTanks.size(); i++) {
                if (clientTanks.get(i) != null) {
                    x = clientTanks.get(i).getXposition();
                    y = clientTanks.get(i).getYposition();
                    if (direction == 1) {       
                        if (((yP <= y + 43) && yP >= y) && ((xP <= x + 43 && xP >= x) || (xP + 43 >= x && xP + 43 <= x + 43))) { 
                            return true;
                        }
                    } else if (direction == 2) {
                        if (((xP + 43 >= x) && xP + 43 <= x + 43) && ((yP <= y + 43 && yP >= y) || (yP + 43 >= y && yP + 43 <= y + 43))) { 
                            return true;
                        }
                    } else if (direction == 3) {
                        if (((yP + 43 >= y) && yP + 43 <= y + 43) && ((xP <= x + 43 && xP >= x) || (xP + 43 >= x && xP + 43 <= x + 43))) { 
                            return true;
                        }
                    } else if (direction == 4) {
                        if (((xP <= x + 43) && xP >= x) && ((yP <= y + 43 && yP >= y) || (yP + 43 >= y && yP + 43 <= y + 43))) { 
                            return true;
                        }
                    }
                }
            }
        }

        int imageWidth = (ImageBuff != null) ? ImageBuff.getWidth() : 43;
        int imageHeight = (ImageBuff != null) ? ImageBuff.getHeight() : 43;

        Map currentMap = MapAbstractFactory.getCurrentMap();

        for (Obstacle obstacle : currentMap.getObstacles()) {
            if (obstacle.collidesWith(xP, yP, imageWidth, imageHeight)) {
                if (obstacle instanceof SlowingObstacle) {
                    velocityX *= ((SlowingObstacle) obstacle).getSlowFactor();
                    velocityY *= ((SlowingObstacle) obstacle).getSlowFactor();
                }
                return true;
            }
        }

        return false;
    }
}
