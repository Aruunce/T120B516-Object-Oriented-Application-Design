package serverSide;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import clientSide.*;
import clientSide.Maps.Map;
import clientSide.Maps.MapAbstractFactory;
import clientSide.Maps.Obstacle;
import clientSide.Maps.ObstacleImpl;
import clientSide.Maps.SlowingObstacle;
import clientSide.Maps.StoneObstacle;
import clientSide.Maps.WoodObstacle;
/*
 * Server.java
 *
 * Created on 21, 2008, 09:41
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
public class Server extends Thread {
    
    /**
     * Creates a new instance of Server
     */
    
    private ArrayList<ClientInfo> clients;
    private ServerSocket serverSocket;
    private int serverPort=11111;
    
   
    private DataInputStream reader;
    private DataOutputStream writer;
   
    private Protocol protocol;
    private boolean running=true;
    
    private int countdownTime = 90;
    private volatile boolean timerRunning = false;
    private Thread timerThread;
    private boolean timerThreadActive = false; 
    
    private Map currentMap;
    private boolean initialMap = false;
    private int initialMapIndex;
    
    public Server() throws SocketException 
    {
        clients=new ArrayList<ClientInfo>();
        protocol=new Protocol();
        currentMap = MapAbstractFactory.getCurrentMap();
        try {
            serverSocket=new ServerSocket(serverPort);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
  
    public void run()
    {
        Socket clientSocket=null;
        while(running)
        {     
            try {
                clientSocket=serverSocket.accept();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            String sentence="";
            try {
                reader=new DataInputStream(clientSocket.getInputStream());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                sentence=reader.readUTF();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
            System.out.println(sentence);
            if(sentence.startsWith("Hello"))
            {
                int pos=sentence.indexOf(',');
                int x=Integer.parseInt(sentence.substring(5,pos));
                int y=Integer.parseInt(sentence.substring(pos+1,sentence.length()));
              
                try {
                    writer=new DataOutputStream(clientSocket.getOutputStream());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                sendToClient(protocol.IDPacket(clients.size()+1));
                try {
                    BroadCastMessage(protocol.NewClientPacket(x,y,1,clients.size()+1));
                    sendAllClients(writer);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                
                clients.add(new ClientInfo(writer,x,y,1));
                
                if (clients.size() == 2) {
                    startTimer();
                }
            }
            
            else if(sentence.startsWith("Update"))
            {
                    int pos1=sentence.indexOf(',');
                    int pos2=sentence.indexOf('-');
                    int pos3=sentence.indexOf('|');
                    int x=Integer.parseInt(sentence.substring(6,pos1));
                    int y=Integer.parseInt(sentence.substring(pos1+1,pos2));
                    int dir=Integer.parseInt(sentence.substring(pos2+1,pos3));
                    int id=Integer.parseInt(sentence.substring(pos3+1,sentence.length()));
                    if(clients.get(id-1)!=null)
                    {
                        clients.get(id-1).setPosX(x);
                        clients.get(id-1).setPosY(y);
                        clients.get(id-1).setDirection(dir);
                        try {
                            BroadCastMessage(sentence);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                    
            }
            else if(sentence.startsWith("Shot"))
            {
                try {
                    BroadCastMessage(sentence);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            else if(sentence.startsWith("Hit"))
            {
                try {
                    BroadCastMessage(sentence);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            else if(sentence.startsWith("Remove"))
            {
                int id=Integer.parseInt(sentence.substring(6));

                try {
                    BroadCastMessage(sentence);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                clients.set(id-1,null);
            }    
            else if(sentence.startsWith("InitialMapIndex"))
            {
                int mapIndex = Integer.parseInt(sentence.substring(16));

                if (initialMap==false) {
                    this.initialMap = true;
                    this.initialMapIndex = mapIndex;
                    try {
                        changeMap(this.initialMapIndex);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    try {
                        updateMap(this.initialMapIndex);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            } 
            else if(sentence.startsWith("DestroyObstacle"))
            {
                int obstacleId = Integer.parseInt(sentence.substring(16));
                
                if (obstacleId >= 0) {
                    for(Obstacle obstacle : currentMap.getObstacles())
                    {
                        if (obstacle.getId() == obstacleId)
                        {
                            currentMap.getObstacles().remove(obstacle);
                            break;
                        }
                    }
                }
                                
                String serializedObstacles = serializeObstacles(currentMap.getObstacles());
                try {
                    BroadCastMessage(serializedObstacles);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            else if(sentence.startsWith("ChangeGameState"))
            {
                String state = sentence.substring(16).trim();

                if (state.equalsIgnoreCase("pause")) {
                    stopTimer();
                    
                    try {
                        BroadCastMessage("SaveGameState");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else if (state.equalsIgnoreCase("unpause")) {
                    startTimer();
                    
                    try {
                        BroadCastMessage("RestoreGameState");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    System.out.println("Unknown game state: " + state);
                }
            } 
            else if(sentence.startsWith("Exit"))
            {
                int id=Integer.parseInt(sentence.substring(4));

                try {
                    BroadCastMessage(sentence);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                if(clients.get(id-1)!=null)
                    clients.set(id-1,null);
            }
        }
        
        try {
            reader.close();
        writer.close();
        serverSocket.close();
            clientSocket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void changeMap(int mapIndex) throws IOException {
        this.currentMap = MapAbstractFactory.createMap(mapIndex);

        BroadCastMessage("MapChange:" + mapIndex);
    }
    public void updateMap(int mapIndex) throws IOException {
        BroadCastMessage("MapUpdate:" + mapIndex);
    }
    
    private void resetMatch() {
        countdownTime = 90;
        clients.clear();
        timerRunning = false;
        initialMap = false;
        
        currentMap = null;
        
        try {
            BroadCastMessage("Reset");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public synchronized void startTimer() {
        if (timerThreadActive) {
            return; // Prevent starting another timer thread
        }

        timerRunning = true;
        timerThreadActive = true;

        timerThread = new Thread(() -> {
            try {
                while (countdownTime >= 0) {
                    if (!timerRunning) {
                        break;
                    }
                    BroadCastMessage("Time: " + countdownTime);
                    Thread.sleep(1000);
                    countdownTime--;
                }
                if (countdownTime < 0) {
                    BroadCastMessage("GameEnd");
                    resetMatch();
                }
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt(); // Restore interrupted status
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                timerThreadActive = false; // Reset flag when thread exits
            }
        });

        timerThread.start();
    }

    public synchronized void stopTimer() {
        timerRunning = false;

        if (timerThread != null && timerThread.isAlive()) {
            timerThread.interrupt(); // Interrupt the thread to exit sleep
        }
    }
    
    public void stopServer() throws IOException
    {
        running=false;
    }
    
    public void BroadCastMessage(String mess) throws IOException
    {
        for(int i=0;i<clients.size();i++)
        {
            if(clients.get(i)!=null)
                clients.get(i).getWriterStream().writeUTF(mess);
        }
    }
    
    public void sendToClient(String message)
    {
         if(message.equals("exit"))
            System.exit(0);
        else
        {    
            try {
                writer.writeUTF(message);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void sendAllClients(DataOutputStream writer)
    {
        int x,y,dir;
        for(int i=0;i<clients.size();i++)
        {
            if(clients.get(i)!=null) {
                x=clients.get(i).getX();
                y=clients.get(i).getY();
                dir=clients.get(i).getDir();
                try {
                    writer.writeUTF(protocol.NewClientPacket(x,y,dir,i+1));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    public class ClientInfo
    {
        DataOutputStream writer;
        int posX,posY,direction;
        
        public ClientInfo(DataOutputStream writer,int posX,int posY,int direction)
        {
           this.writer=writer;
           this.posX=posX;
           this.posY=posY;
           this.direction=direction;
        }
        
        public void setPosX(int x)
        {
            posX=x;
        }
        public void setPosY(int y)
        {
            posY=y;
        }
        public void setDirection(int dir)
        {
            direction=dir;
        }
        public DataOutputStream getWriterStream()
        {
            return writer;
        }
        public int getX()
        {
            return posX;
        }
        public int getY()
        {
            return posY;
        }
        public int getDir()
        {
            return direction;
        }
    }
    
    public String serializeObstacles(ArrayList<Obstacle> obstacles) {
        StringBuilder serialized = new StringBuilder("Obstacles:");
        for (Obstacle obstacle : obstacles) {
            ObstacleImpl impl = obstacle.getImplementation();
            String type = "normal";
            
            // Determine obstacle type from implementation
            if (impl instanceof SlowingObstacle) {
                type = "slowing";
            } else if (impl instanceof WoodObstacle) {
                type = "wood";
            } else if (impl instanceof StoneObstacle) {
                type = "stone";
            }

            serialized.append(obstacle.getId()).append(",")
                    .append(obstacle.getX()).append(",")
                    .append(obstacle.getY()).append(",")
                    .append(obstacle.getWidth()).append(",")
                    .append(obstacle.getHeight()).append(",")
                    .append(obstacle.isDestructible()).append(",")
                    .append(type).append(";");
        }
        return serialized.toString();
    }
}
