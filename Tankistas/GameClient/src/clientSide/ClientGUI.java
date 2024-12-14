package clientSide;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import clientSide.Maps.Map;
import clientSide.Maps.MapAbstractFactory;
import clientSide.Maps.Obstacle;
import clientSide.Maps.ObstacleFacade;
import clientSide.Maps.ObstacleFactory;
import clientSide.Maps.ObstacleType;

import java.util.ArrayList;
import clientSide.Memento.GameStateMemento;
import clientSide.Memento.Caretaker;

public class ClientGUI extends JFrame implements ActionListener,WindowListener 
{
    private JLabel ipaddressLabel;
    private JLabel portLabel;
    private static JLabel scoreLabel;
    private JLabel timerLabel;
    private JLabel livesLabel;
    private JLabel mapLabel;
    
    private JTextField ipaddressText;
    private JTextField portText;
    private JComboBox<String> mapSelector;
    
    private JButton registerButton;
    private JButton gameStateButton;
    
    
    private JPanel registerPanel;
    public static JPanel gameStatusPanel;
    private Client client;
    private Tank clientTank;
    
    private static int score;
    
    int width=790,height=580;
    boolean isRunning=true;
    private GameBoardPanel boardPanel;
    
    private SoundManger soundManger;
    private InputManager inputManager;
    
    private Map currentMap;
    private int mapIndex;
    	
    private Caretaker ct = new Caretaker();
    
    private static boolean isFirstClient = false;

    public ClientGUI() 
    {
        // this.powerUps = powerUps;
        score=0;
        setTitle("Multiclients Tanks Game");
        setSize(width,height);
        setLocation(60,100);
        getContentPane().setBackground(Color.BLACK);
        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        addWindowListener(this);
        registerPanel=new JPanel();
        registerPanel.setBackground(Color.YELLOW);
        registerPanel.setSize(200,180);
        registerPanel.setBounds(560,50,200,170);
        registerPanel.setLayout(null);
        
        gameStatusPanel=new JPanel();
        gameStatusPanel.setBackground(Color.YELLOW);
        gameStatusPanel.setSize(200,300);
        gameStatusPanel.setBounds(560,250,200,271);
        gameStatusPanel.setLayout(null);
     
        ipaddressLabel=new JLabel("IP address: ");
        ipaddressLabel.setBounds(10,25,70,25);
        
        portLabel=new JLabel("Port: ");
        portLabel.setBounds(10,55,50,25);
        
        mapLabel = new JLabel("Map Size: ");
        mapLabel.setBounds(10, 85, 70, 25);
        
        scoreLabel=new JLabel("Score : 0");
        scoreLabel.setBounds(10,55,100,25);
        timerLabel = new JLabel("Time: N/A");
        timerLabel.setBounds(10, 25, 100, 25);
        livesLabel = new JLabel("Lives: 4");
        livesLabel.setBounds(10, 85, 100, 25);
        
        ipaddressText=new JTextField("localhost");
        ipaddressText.setBounds(90,25,100,25);
        
        portText=new JTextField("11111");
        portText.setBounds(90,55,100,25);
        
        
        String[] mapSizes = {"Level 1", "Level 2", "Level 3"};
        mapSelector = new JComboBox<>(mapSizes);
        mapSelector.setBounds(90, 85, 100, 25);
        mapSelector.addActionListener(e -> {
            String selectedSize = (String) mapSelector.getSelectedItem();
            int mapIndex;
            switch (selectedSize) {
                case "Level 1":
                    mapIndex = 0;
                    break;
                case "Level 2":
                    mapIndex = 1;
                    break;
                case "Level 3":
                    mapIndex = 2;
                    break;
                default:
                    mapIndex = 0;
            }
            currentMap = MapAbstractFactory.createMap(mapIndex);
            if (boardPanel != null) {
                boardPanel.setMap(currentMap);
            }
        });
       
        registerButton=new JButton("Register");
        registerButton.setBounds(60,130,90,25);
        registerButton.addActionListener(this);
        registerButton.setFocusable(true);
        
        gameStateButton=new JButton("Pause game");
        gameStateButton.setBounds(10,120,120,25);
        gameStateButton.addActionListener(this);
        gameStateButton.setFocusable(false);
        gameStateButton.setVisible(false);
        
        registerPanel.add(ipaddressLabel);
        registerPanel.add(portLabel);
        registerPanel.add(ipaddressText);
        registerPanel.add(portText);
        registerPanel.add(mapSelector);
        registerPanel.add(registerButton);
       
        gameStatusPanel.add(scoreLabel);
        gameStatusPanel.add(timerLabel);
        gameStatusPanel.add(livesLabel);
        gameStatusPanel.add(gameStateButton);
            
        client=Client.getGameClient();
        
        clientTank=new Tank();
        boardPanel = new GameBoardPanel(clientTank, client, false);
    
        
        getContentPane().add(registerPanel);        
        getContentPane().add(gameStatusPanel);
        getContentPane().add(boardPanel);        
        setVisible(true);

    }
    
    public static int getScore()
    {
        return score;
    }
    
    public static void setScore(int scoreParametar)
    {
        score+=scoreParametar;
        scoreLabel.setText("Score : "+score);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        
        if (obj == registerButton) {
            registerButton.setEnabled(false);
            mapSelector.setEnabled(false);  // Disable map selection after registration
            
            try {
                // Send selected map info to server
                String selectedSize = (String) mapSelector.getSelectedItem();
                switch (selectedSize) {
                    case "Level 1":
                        mapIndex = 0;
                        break;
                    case "Level 2":
                        mapIndex = 1;
                        break;
                    case "Level 3":
                        mapIndex = 2;
                        break;
                    default:
                        mapIndex = 0;
                }
                
                client.register(ipaddressText.getText(), 
                              Integer.parseInt(portText.getText()),
                              clientTank.getXposition(),
                              clientTank.getYposition());
                
                if (isFirstClient) {
                    gameStateButton.setVisible(true);
                }
                
                soundManger = new SoundManger();
                boardPanel.setGameStatus(true);
                boardPanel.repaint();
                
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                
                new ClientRecivingThread(client.getSocket()).start();
                registerButton.setFocusable(false);
                boardPanel.setFocusable(true);
                
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,
                    "The Server is not running, try again later!",
                    "Tanks 2D Multiplayer Game",
                    JOptionPane.INFORMATION_MESSAGE);
                System.out.println("The Server is not running!");
                registerButton.setEnabled(true);
                mapSelector.setEnabled(true);  // Re-enable map selection if registration fails
            }
        }
        
        if (obj == gameStateButton) {
            if (gameStateButton.getText() == "Pause game"){
                gameStateButton.setText("Unpause game");
                Client.getGameClient().sendToServer(new Protocol().ChangeGameState("Pause"));
            }
            else {
                gameStateButton.setText("Pause game");
                Client.getGameClient().sendToServer(new Protocol().ChangeGameState("Unpause"));
            }
        }
    }

    public void windowOpened(WindowEvent e) 
    {

    }

    public void windowClosing(WindowEvent e) 
    {
        
       // int response=JOptionPane.showConfirmDialog(this,"Are you sure you want to exit ?","Tanks 2D Multiplayer Game!",JOptionPane.YES_NO_OPTION);
        
     
     Client.getGameClient().sendToServer(new Protocol().ExitMessagePacket(clientTank.getTankID()));
        
        
    }
    public void windowClosed(WindowEvent e) {
        
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }
    
    private void updateTimerLabel(String timerMessage) {
        SwingUtilities.invokeLater(() -> {
            timerLabel.setText(timerMessage + " seconds");
        });
    }
    
    public void updateLivesLabel(int lives) {
        SwingUtilities.invokeLater(() -> {
            livesLabel.setText("Lives: " + lives);
        });
    }
    
    
    public class ClientRecivingThread extends Thread
    {
        Socket clientSocket;
        DataInputStream reader;
        public ClientRecivingThread(Socket clientSocket)
        {
            this.clientSocket=clientSocket;
            try {
                reader=new DataInputStream(clientSocket.getInputStream());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
        }
        public void run()
        {
            while(isRunning) 
            {
                String sentence="";
                try {
                    sentence=reader.readUTF();                
                } catch (IOException ex) {
                    ex.printStackTrace();
                }                
               if(sentence.startsWith("ID"))
               {
                    int id=Integer.parseInt(sentence.substring(2));
                    clientTank.setTankID(id);
                    System.out.println("My ID= "+id);
                    
                    boardPanel.registerNewTank(clientTank);
                    
                    Client.getGameClient().sendToServer(new Protocol().InitialMapIndexPacket(mapIndex));
               }
               else if (sentence.startsWith("SaveGameState")) {
                    GameStateMemento backup = boardPanel.saveMemento();
                    ct.add(backup);

                    clientTank.updateState("Destroyed");
               }
               else if (sentence.startsWith("RestoreGameState")) {
                    GameStateMemento restoreMemento = ct.get(ct.size() - 1);
                    
                    ArrayList<Tank> restoredTanks = boardPanel.restoreTanks(restoreMemento);
                    
                    for (Tank tank : restoredTanks) {
                        if(tank.getTankID() == clientTank.getTankID()) {
                            clientTank.updateState(tank.getState());
                        }
                    }
               }
               else if (sentence.startsWith("ShowPauseButton")) {
                   isFirstClient = true;
                   gameStateButton.setVisible(true);
               }
               else if(sentence.startsWith("NewClient"))
               {
                    int pos1=sentence.indexOf(',');
                    int pos2=sentence.indexOf('-');
                    int pos3=sentence.indexOf('|');
                    int x=Integer.parseInt(sentence.substring(9,pos1));
                    int y=Integer.parseInt(sentence.substring(pos1+1,pos2));
                    int dir=Integer.parseInt(sentence.substring(pos2+1,pos3));
                    int id=Integer.parseInt(sentence.substring(pos3+1,sentence.length()));
                    if(id!=clientTank.getTankID())
                        boardPanel.registerNewTank(new Tank(x,y,dir,id));
               }   
               else if(sentence.startsWith("Update"))
               {
                    Client.getGameClient().sendToServer(new Protocol().DestroyObstaclePacket(-1)); // LAIKINAI
                    int pos1=sentence.indexOf(',');
                    int pos2=sentence.indexOf('-');
                    int pos3=sentence.indexOf('|');
                    int x=Integer.parseInt(sentence.substring(6,pos1));
                    int y=Integer.parseInt(sentence.substring(pos1+1,pos2));
                    int dir=Integer.parseInt(sentence.substring(pos2+1,pos3));
                    int id=Integer.parseInt(sentence.substring(pos3+1,sentence.length()));
                
                    if(id!=clientTank.getTankID())
                    {
                        boardPanel.getTank(id).setXpoistion(x);
                        boardPanel.getTank(id).setYposition(y);
                        boardPanel.getTank(id).setDirection(dir);
                        boardPanel.repaint();
                    }
                    
               }
               else if(sentence.startsWith("Shot"))
               {
                    int id=Integer.parseInt(sentence.substring(4));
                
                    if(id!=clientTank.getTankID())
                    {
                        boardPanel.getTank(id).shot(false);
                    }
                    
               }
               else if(sentence.startsWith("Hit"))
               {
                    int hitTankID = Integer.parseInt(sentence.substring(3));

                    if (hitTankID == clientTank.getTankID()) {
                        clientTank.reduceLives();
                        updateLivesLabel(clientTank.getLives());

                        if(clientTank.isDead()) {
                            Client.getGameClient().sendToServer(new Protocol().RemoveClientPacket(clientTank.getTankID()));  
                        }
                    }
                    
                    boardPanel.repaint();
               }
               else if(sentence.startsWith("Remove"))
               {
                  int id=Integer.parseInt(sentence.substring(6));
                  
                  if(id==clientTank.getTankID())
                  {
                        int response=JOptionPane.showConfirmDialog(null,"Sorry, You lost. Do you want to try again ?","Tanks 2D Multiplayer Game",JOptionPane.OK_CANCEL_OPTION);
                        if(response==JOptionPane.OK_OPTION)
                        {
                            //client.closeAll();
                            setVisible(false);
                            dispose();
                            
                            new ClientGUI();
                        }
                        else
                        {
                            System.exit(0);
                        }
                  }
                  else
                  {
                      boardPanel.removeTank(id);
                  }
               }
               else if (sentence.startsWith("MapChange")) {
                    int mapIndex = Integer.parseInt(sentence.substring(10));
                    handleMapChange(mapIndex);
                }
               else if (sentence.startsWith("MapUpdate")) {
                   int mapIndex = Integer.parseInt(sentence.substring(10));
                    handleMapUpdate(mapIndex);
                }
               else if (sentence.startsWith("Obstacles:")) {
                    String obstaclesData = sentence.substring(10);
                    ArrayList<Obstacle> obstacles = deserializeObstacles(obstaclesData);

                    currentMap.setObstacles(obstacles);
                    boardPanel.revalidate();
                    boardPanel.repaint();
                    
                    //System.out.println("Client received obstacles count: " + obstacles.size());
                }
                else if (sentence.startsWith("Reset")) {
                    handleGameReset();
                }
               else if(sentence.startsWith("Exit"))
               {
                   int id=Integer.parseInt(sentence.substring(4));
                  
                  if(id!=clientTank.getTankID())
                  {
                      boardPanel.removeTank(id);
                  }
               }
               else if (sentence.startsWith("Time:")) {
                      updateTimerLabel(sentence);
               }
               else if (sentence.startsWith("GameEnd")) {
                   isFirstClient = false;
                   
                    int response = JOptionPane.showConfirmDialog(null, "Time is up! Do you want to play again?", "Tanks 2D Multiplayer Game", JOptionPane.OK_CANCEL_OPTION);
                    if (response == JOptionPane.OK_OPTION) {
                        setVisible(false);
                        dispose();
                        
                        new ClientGUI();
                    } else {
                        System.exit(0);
                    }
                }
         
            }
           
            try {
                reader.close();
                clientSocket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
        }
        private void handleMapChange(int mapIndex) {
            SwingUtilities.invokeLater(() -> {
                try {
                    // Get new map from abstract factory
                    Map newMap = MapAbstractFactory.createMap(mapIndex);
                    currentMap = newMap;
                    
                    // Update board panel with new map
                    boardPanel.setMap(newMap);
                    
                    // Reset tank positions to valid positions on new map
                    for (Tank tank : boardPanel.getAllTanks()) {
                        // Assuming Map class has method to get valid position
                        Position spawnPos = newMap.getRandomValidPosition();
                        tank.setXpoistion(spawnPos.x);
                        tank.setYposition(spawnPos.y);
                    }
                    
                    // Refresh display
                    boardPanel.repaint();
                    
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(ClientGUI.this, 
                        "Error loading new map: " + e.getMessage(),
                        "Map Change Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            });
        }
        private void handleMapUpdate(int mapIndex) {
            SwingUtilities.invokeLater(() -> {
                try {
                    Map newMap = MapAbstractFactory.createMap(mapIndex);
                    currentMap = newMap;
                    
                    boardPanel.setMap(newMap);
                    boardPanel.repaint();
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(ClientGUI.this, 
                        "Error loading new map: " + e.getMessage(),
                        "Map Change Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            });
        }
        private void handleGameReset() {
            SwingUtilities.invokeLater(() -> {
                // Reset score
                score = 0;
                scoreLabel.setText("Score: 0");
                gameStateButton.setVisible(false);
                
                // Reset lives
                clientTank.resetLives();
                updateLivesLabel(clientTank.getLives());
                
                // Reset tank positions on current map
                for (Tank tank : boardPanel.getAllTanks()) {
                    Position spawnPos = currentMap.getRandomValidPosition();
                    tank.setXpoistion(spawnPos.x);
                    tank.setYposition(spawnPos.y);
                }
                
                // Refresh display
                boardPanel.repaint();
            });
        }
        
        private ArrayList<Obstacle> deserializeObstacles(String data) {
            ObstacleFacade facade = new ObstacleFacade();
            return facade.createObstacles(data);
        }
    }
}