package clientSide;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import javax.swing.ImageIcon;

import clientSide.Maps.Map;
import clientSide.Maps.MapAbstractFactory;
import clientSide.Maps.Obstacle;
/*
 * Bomb.java
 *
 * Created on 29, 2008, 06:20
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
public class Bomb {
    
    /** Creates a new instance of Bomb */
    
    private Image bombImg;
    private BufferedImage bombBuffImage;
    
    private int xPosi;
    private int yPosi;
    private int direction;
    public boolean stop=false;
    private float velocityX=0.05f,velocityY=0.05f;
    private Map currentMap;
    
    public Bomb(int x,int y,int direction) {
        
        String Path = System.getProperty("user.dir") + "/boom.wav";
        final SimpleSoundPlayer sound_boom =new SimpleSoundPlayer(Path);
        final InputStream stream_boom =new ByteArrayInputStream(sound_boom.getSamples());
        
        int offset = 10;
        switch (direction) {
            case 1:
                y -= offset;
                break;
            case 2:
                x += offset;
                break;
            case 3:
                y += offset;
                break;
            case 4:
                x -= offset;
                break;
        }
        
        xPosi=x;
        yPosi=y;
        this.direction=direction;
        stop=false;



        String imagePath = System.getProperty("user.dir") + "/Images/bomb.png";            
        File imgFile = new File(imagePath);
        if (imgFile.exists()) {
            //System.out.println("Loading image from: " + imgFile.getAbsolutePath());
            bombImg = new ImageIcon(imgFile.getAbsolutePath()).getImage();
        } else {
            System.err.println("Error: Image not found at path: " + imgFile.getAbsolutePath());
        }


        //bombImg=new ImageIcon("Images/bomb.png").getImage();
        
        bombBuffImage=new BufferedImage(bombImg.getWidth(null),bombImg.getHeight(null),BufferedImage.TYPE_INT_RGB);
        bombBuffImage.createGraphics().drawImage(bombImg,0,0,null);
        Thread t= new Thread(new Runnable() {
        public void run() {
            sound_boom.play(stream_boom);
        }
    }); 
    t.start();
    }
    public int getPosiX() {
        return xPosi;
    }
    public int getPosiY() {
        return yPosi;
    }
    public void setPosiX(int x) {
        xPosi=x;
    }
    public void setPosiY(int y) {
        yPosi=y;
    }
    public BufferedImage getBomBufferdImg() {
        return bombBuffImage;
    }
    
    public BufferedImage getBombBuffImage() {
        return bombBuffImage;
    }
    
    public boolean checkCollision() 
    {
        ArrayList<Tank>clientTanks=GameBoardPanel.getClients();
        int x,y;
        for(int i=1;i<clientTanks.size();i++) {
            if(clientTanks.get(i)!=null) {
                x=clientTanks.get(i).getXposition();
                y=clientTanks.get(i).getYposition();
                
                if((yPosi>=y&&yPosi<=y+43)&&(xPosi>=x&&xPosi<=x+43)) 
                {
                    Client.getGameClient().sendToServer(new Protocol().HitPacket(clientTanks.get(i).getTankID()));
                    
                    ClientGUI.setScore(50);
                    ClientGUI.gameStatusPanel.repaint();
                    
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    
                    return true;
                }
            }
        }
            
        currentMap = MapAbstractFactory.getCurrentMap();
        for (Obstacle obstacle : currentMap.getObstacles()) {
            if (obstacle.collidesWith(xPosi, yPosi, bombBuffImage.getWidth(), bombBuffImage.getHeight())) {
                if (obstacle.isDestructible()) {
                    Client.getGameClient().sendToServer(new Protocol().DestroyObstaclePacket(obstacle.getId()));
                }
                return true;
            }
        }
        
        return false;
    }
    
    public boolean visualizeCollision() 
    {
        ArrayList<Tank>clientTanks=GameBoardPanel.getClients();
        int x,y;
        for(int i=1;i<clientTanks.size();i++) {
            if(clientTanks.get(i)!=null) {
                x=clientTanks.get(i).getXposition();
                y=clientTanks.get(i).getYposition();

                if((yPosi>=y&&yPosi<=y+43)&&(xPosi>=x&&xPosi<=x+43)) 
                {
                    return true;
                }
            }
        }
        currentMap = MapAbstractFactory.getCurrentMap();
        for (Obstacle obstacle : currentMap.getObstacles()) {
            if (obstacle.collidesWith(xPosi, yPosi, bombBuffImage.getWidth(), bombBuffImage.getHeight())) {
                return true;
            }
        }
        
        return false;
    }
    
    public void startBombThread(boolean chekCollision) {
        
            new BombShotThread(chekCollision).start();
            
    }
    
    private class BombShotThread extends Thread 
    {    
        boolean checkCollis;
        public BombShotThread(boolean chCollision)
        {
            checkCollis=chCollision;
        }
        public void run() 
        {
            if(checkCollis) {
                
                if(direction==1) 
                {
                    xPosi=17+xPosi;
                    while(yPosi>50) 
                    {
                        yPosi=(int)(yPosi-yPosi*velocityY);
                        if(checkCollision()) 
                        {
                            break;
                        }
                        try {
                            
                            Thread.sleep(40);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        
                    }
                    
                } 
                else if(direction==2) 
                {
                    yPosi=17+yPosi;
                    xPosi+=30;
                    while(xPosi<564) 
                    {
                        xPosi=(int)(xPosi+xPosi*velocityX);
                        if(checkCollision()) 
                        {
                            break;
                        }
                        try {
                            
                            Thread.sleep(40);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        
                    }
                }
                else if(direction==3) 
                {
                    yPosi+=30;
                    xPosi+=20;
                    while(yPosi<505) 
                    {    
                        yPosi=(int)(yPosi+yPosi*velocityY);
                        if(checkCollision()) 
                        {
                            break;
                        }
                        try {
                            
                            Thread.sleep(40);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        
                    }
                }
                else if(direction==4) 
                {
                    yPosi=21+yPosi;
                    
                    while(xPosi>70) 
                    {
                        xPosi=(int)(xPosi-xPosi*velocityX);
                        if(checkCollision()) 
                        {
                            break;
                        }
                        try {
                            
                            Thread.sleep(40);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        
                    }
                }
                
                stop=true;
            } 
            else 
            {
                 if(direction==1) 
                {
                    xPosi=17+xPosi;
                    while(yPosi>50) 
                    {
                        yPosi=(int)(yPosi-yPosi*velocityY);
                        
                        if(visualizeCollision()) 
                        {
                            break;
                        }
                        try {
                            
                            Thread.sleep(40);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        
                    }
                    
                } 
                else if(direction==2) 
                {
                    yPosi=17+yPosi;
                    xPosi+=30;
                    while(xPosi<564) 
                    {
                        xPosi=(int)(xPosi+xPosi*velocityX);
                        
                        if(visualizeCollision()) 
                        {
                            break;
                        }
                        try {
                            
                            Thread.sleep(40);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        
                    }
                }
                else if(direction==3) 
                {
                    yPosi+=30;
                    xPosi+=20;
                    while(yPosi<505) 
                    {    
                        yPosi=(int)(yPosi+yPosi*velocityY);
                        
                        if(visualizeCollision()) 
                        {
                            break;
                        }
                        try {
                            
                            Thread.sleep(40);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        
                    }
                }
                else if(direction==4) 
                {
                    yPosi=21+yPosi;
                    
                    while(xPosi>70) 
                    {
                        xPosi=(int)(xPosi-xPosi*velocityX);
                        
                        if(visualizeCollision()) 
                        {
                            break;
                        }
                        try {
                            
                            Thread.sleep(40);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        
                    }
                }
                
                stop=true;
            }
        }
    }
}