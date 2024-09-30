import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClientGUI extends JFrame implements ActionListener, WindowListener {

    private JLabel ipaddressLabel;
    private JLabel portLabel;
    private static JLabel scoreLabel;
    private static JLabel livesLabel; // Lives label
    private JLabel timerLabel;

import javax.swing.SwingUtilities;
/*
 * ClientGUI.java
 *
 * Created on 21, 2008, 02:26
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
    private JTextField ipaddressText;
    private JTextField portText;

    private JButton registerButton;

    private JPanel registerPanel;
    public static JPanel gameStatusPanel;
    private Client client;
    private Tank clientTank;

    private static int score;
    private static int lives; // Track lives
    private static final int INITIAL_LIVES = 3; // Initial lives value

    int width = 790, height = 580;
    boolean isRunning = true;
    private GameBoardPanel boardPanel;

    private SoundManger soundManger;

    public ClientGUI() {
        score = 0;
        lives = INITIAL_LIVES; // Initialize lives
        setTitle("Multiclients Tanks Game");
        setSize(width, height);
        setLocation(60, 100);
        getContentPane().setBackground(Color.BLACK);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        addWindowListener(this);

        registerPanel = new JPanel();
        registerPanel.setBackground(Color.YELLOW);
        registerPanel.setSize(200, 140);
        registerPanel.setBounds(560, 50, 200, 140);
        registerPanel.setLayout(null);

        gameStatusPanel = new JPanel();
        gameStatusPanel.setBackground(Color.YELLOW);
        gameStatusPanel.setSize(200, 300);
        gameStatusPanel.setBounds(560, 210, 200, 311);
        gameStatusPanel.setLayout(null);
      
        ipaddressLabel=new JLabel("IP address: ");
        ipaddressLabel.setBounds(10,25,70,25);
        
        portLabel=new JLabel("Port: ");
        portLabel.setBounds(10,55,50,25);
        
        scoreLabel=new JLabel("Score : 0");
        scoreLabel.setBounds(10,55,100,25);
        timerLabel = new JLabel("Time: N/A");
        timerLabel.setBounds(10, 25, 100, 25);
      
      
        livesLabel = new JLabel("Lives: " + lives); // Initialize lives label
        livesLabel.setBounds(10, 120, 100, 25); // Set position
        
        ipaddressText=new JTextField("localhost");
        ipaddressText.setBounds(90,25,100,25);
        
        portText=new JTextField("11111");
        portText.setBounds(90,55,100,25);
       
        registerButton=new JButton("Register");
        registerButton.setBounds(60,100,90,25);
      
        registerButton.addActionListener(this);
        registerButton.setFocusable(true);

        registerPanel.add(ipaddressLabel);
        registerPanel.add(portLabel);
        registerPanel.add(ipaddressText);
        registerPanel.add(portText);
        registerPanel.add(registerButton);

        gameStatusPanel.add(scoreLabel);
        gameStatusPanel.add(timerLabel);
        gameStatusPanel.add(livesLabel); // Add lives label to the game status panel
            
        client=Client.getGameClient();
         
        clientTank=new Tank();
        boardPanel=new GameBoardPanel(clientTank,client,false);
        
        getContentPane().add(registerPanel);        
        getContentPane().add(gameStatusPanel);
        getContentPane().add(boardPanel);
        setVisible(true);
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int scoreParametar) {
        score += scoreParametar;
        scoreLabel.setText("Score : " + score);
    }

    // Method to reduce lives and update the lives label
    public static void reduceLives() {
        if (lives > 0) {
            lives--;
            livesLabel.setText("Lives: " + lives);
        }
        if (lives == 0) {
            // Handle game over logic
            JOptionPane.showMessageDialog(null, "Game Over! You have no lives left.");
            System.exit(0); // Or restart the game
        }
    }

    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj == registerButton) {
            registerButton.setEnabled(false);

            try {
                client.register(ipaddressText.getText(), Integer.parseInt(portText.getText()), clientTank.getXposition(), clientTank.getYposition());
                soundManger = new SoundManger();
                boardPanel.setGameStatus(true);
                boardPanel.repaint();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                new ClientReceivingThread(client.getSocket()).start();
                registerButton.setFocusable(false);
                boardPanel.setFocusable(true);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "The Server is not running, try again later!", "Tanks 2D Multiplayer Game", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("The Server is not running!");
                registerButton.setEnabled(true);
            }
        }
    }

    public void windowOpened(WindowEvent e) {}

    public void windowClosing(WindowEvent e) {
        Client.getGameClient().sendToServer(new Protocol().ExitMessagePacket(clientTank.getTankID()));
    }

    public void windowClosed(WindowEvent e) {}

    public void windowIconified(WindowEvent e) {}

    public void windowDeiconified(WindowEvent e) {}

    public void windowActivated(WindowEvent e) {}

    public void windowDeactivated(WindowEvent e) { }
    
    private void updateTimerLabel(String timerMessage) {
        SwingUtilities.invokeLater(() -> {
            timerLabel.setText(timerMessage + " seconds");
        });
    }
    
    public class ClientRecivingThread extends Thread
    {
        Socket clientSocket;
        DataInputStream reader;

        public ClientReceivingThread(Socket clientSocket) {
            this.clientSocket = clientSocket;
            try {
                reader = new DataInputStream(clientSocket.getInputStream());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        public void run() {
            while (isRunning) {
                String sentence = "";
                try {
                    sentence = reader.readUTF();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                if (sentence.startsWith("ID")) {
                    int id = Integer.parseInt(sentence.substring(2));
                    clientTank.setTankID(id);
                    System.out.println("My ID= " + id);
                } else if (sentence.startsWith("NewClient")) {
                    int pos1 = sentence.indexOf(',');
                    int pos2 = sentence.indexOf('-');
                    int pos3 = sentence.indexOf('|');
                    int x = Integer.parseInt(sentence.substring(9, pos1));
                    int y = Integer.parseInt(sentence.substring(pos1 + 1, pos2));
                    int dir = Integer.parseInt(sentence.substring(pos2 + 1, pos3));
                    int id = Integer.parseInt(sentence.substring(pos3 + 1, sentence.length()));
                    if (id != clientTank.getTankID()) {
                        boardPanel.registerNewTank(new Tank(x, y, dir, id));
                    }
                } else if (sentence.startsWith("Update")) {
                    int pos1 = sentence.indexOf(',');
                    int pos2 = sentence.indexOf('-');
                    int pos3 = sentence.indexOf('|');
                    int x = Integer.parseInt(sentence.substring(6, pos1));
                    int y = Integer.parseInt(sentence.substring(pos1 + 1, pos2));
                    int dir = Integer.parseInt(sentence.substring(pos2 + 1, pos3));
                    int id = Integer.parseInt(sentence.substring(pos3 + 1, sentence.length()));

                    if (id != clientTank.getTankID()) {
                        boardPanel.getTank(id).setXpoistion(x);
                        boardPanel.getTank(id).setYposition(y);
                        boardPanel.getTank(id).setDirection(dir);
                        boardPanel.repaint();
                    }

                } else if (sentence.startsWith("Shot")) {
                    int id = Integer.parseInt(sentence.substring(4));

                    if (id != clientTank.getTankID()) {
                        boardPanel.getTank(id).Shot();
                    }

                } else if (sentence.startsWith("Remove")) {
                    int id = Integer.parseInt(sentence.substring(6));

                    if (id == clientTank.getTankID()) {
                        reduceLives(); // Reduce lives when the player's tank is destroyed
                        int response = JOptionPane.showConfirmDialog(null, "Sorry, You lost. Do you want to try again?", "Tanks 2D Multiplayer Game", JOptionPane.OK_CANCEL_OPTION);
                        if (response == JOptionPane.OK_OPTION) {
                            setVisible(false);
                            dispose();
                            new ClientGUI();
                        } else {
                            System.exit(0);
                        }

                  }
                  else
                  {
                      boardPanel.removeTank(id);
                  }
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
    }
}
