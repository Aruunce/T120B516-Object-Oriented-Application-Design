package clientSide;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.util.ArrayList;

public class GameBoardPanel extends JPanel {
    private Tank tank;
    //private int width = 609;
    //private int height = 523;
    private int width = 700;
    private int height = 900;
    private static ArrayList<Tank> tanks;
    private static ArrayList<Obstacle> obstacles;
    private boolean gameStatus;
    private Map currentMap;
    
    public GameBoardPanel(Tank tank, Client client, boolean gameStatus) {
        // Validate tank parameter
        if (tank == null) {
            throw new IllegalArgumentException("Tank cannot be null");
        }
        
        this.tank = tank;
        this.gameStatus = gameStatus;
        setSize(width, height);
        setBounds(-50, 0, width, height);
        addKeyListener(new InputManager(tank));
        setFocusable(true);
        
        // Initialize collections
        tanks = new ArrayList<Tank>(100);
        for (int i = 0; i < 100; i++) {
            tanks.add(null);
        }
        
        // Initialize map and obstacles
        this.currentMap = MapAbstractFactory.getCurrentMap();
        if (this.currentMap != null) {
            this.obstacles = currentMap.getObstacles();
        } else {
            this.obstacles = new ArrayList<>();
        }
    }

    @Override
    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        Graphics2D g = (Graphics2D)gr;
        
        // Draw background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        g.setColor(Color.GREEN);
        g.fillRect(70, 50, getWidth()-100, getHeight());
        


        String imagePath = System.getProperty("user.dir") + "/GameClient/Images/bg.jpg";
        File imgFile = new File(imagePath);
        if (imgFile.exists()) {
            System.out.println("Loading image from: " + imgFile.getAbsolutePath());
            ImageIcon bgImage = new ImageIcon(imgFile.getAbsolutePath());
            g.drawImage(bgImage.getImage(), 70, 50, null);
        } else {
            System.err.println("Error: Image not found at path: " + imgFile.getAbsolutePath());
        }

        
        // Draw title
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
        g.drawString("Tanks 2D Multiplayers Game", 255, 30);
        
        // Draw obstacles
        if (obstacles != null) {
            for (Obstacle obstacle : obstacles) {
                if (obstacle != null) {
                    obstacle.draw(g);
                }
            }
        }
        
        if (gameStatus) {
            // Draw main tank
            if (tank != null && tank.getBuffImage() != null) {
                g.drawImage(tank.getBuffImage(), tank.getXposition(), tank.getYposition(), this);
                
                // Draw bombs for main tank
                Bomb[] bombs = tank.getBomb();
                if (bombs != null) {
                    for (int j = 0; j < bombs.length; j++) {
                        if (bombs[j] != null && !bombs[j].stop && bombs[j].getBomBufferdImg() != null) {
                            g.drawImage(bombs[j].getBomBufferdImg(), bombs[j].getPosiX(), bombs[j].getPosiY(), this);
                        }
                    }
                }
            }
            
            // Draw other tanks
            if (tanks != null) {
                for (int i = 1; i < tanks.size(); i++) {
                    Tank otherTank = tanks.get(i);
                    if (otherTank != null && otherTank.getBuffImage() != null) {
                        g.drawImage(otherTank.getBuffImage(), otherTank.getXposition(), otherTank.getYposition(), this);
                        
                        // Draw bombs for other tanks
                        Bomb[] otherBombs = otherTank.getBomb();
                        if (otherBombs != null) {
                            for (int j = 0; j < otherBombs.length; j++) {
                                if (otherBombs[j] != null && !otherBombs[j].stop && otherBombs[j].getBomBufferdImg() != null) {
                                    g.drawImage(otherBombs[j].getBomBufferdImg(), otherBombs[j].getPosiX(), otherBombs[j].getPosiY(), this);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void registerNewTank(Tank newTank) {
        if (newTank != null) {
            tanks.set(newTank.getTankID(), newTank);
        }
    }

    public void removeTank(int tankID) {
        if (tankID >= 0 && tankID < tanks.size()) {
            tanks.set(tankID, null);
        }
    }

    public Tank getTank(int id) {
        return id >= 0 && id < tanks.size() ? tanks.get(id) : null;
    }
    
    public ArrayList<Tank> getAllTanks() {
        ArrayList<Tank> activeTanks = new ArrayList<>();
        for (Tank t : tanks) {
            if (t != null) {
                activeTanks.add(t);
            }
        }
        return activeTanks;
    }
    
    public Map getCurrentMap() {
        return currentMap;
    }
    
    public void setMap(Map newMap) {
        this.currentMap = newMap;
        if (newMap != null) {
            obstacles = newMap.getObstacles();
            width = newMap.getWidth();
            height = newMap.getHeight();
            setSize(width, height);
            setBounds(-50, 0, width, height);
        }
        repaint();
    }
    
    public void setGameStatus(boolean status) {
        this.gameStatus = status;
    }
    
    public static ArrayList<Tank> getClients() {
        return tanks;
    }
}