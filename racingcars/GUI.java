package racingcars;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.applet.*;
import java.awt.Font;


public class GUI extends JComponent implements KeyListener {

    private final JFrame frame;
    private boolean gameStarted;
    
    private final Player player1;
    private final Player player2;
    
    private boolean holdingUP;
    private boolean holdingDOWN;
    private boolean holdingLEFT;
    private boolean holdingRIGHT;
    
    private boolean holdingW;
    private boolean holdingS;
    private boolean holdingA;
    private boolean holdingD;
    
    private AudioClip gamePlaySong;
    private boolean paused;
    
    //Sets up the gui
    public GUI(Player player1, Player player2){
        
        frame = new JFrame("Racing Cars");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(this);
        
        setPreferredSize(new Dimension(1600,800));
        frame.setFocusable(true);
        frame.getContentPane().add(this);
        
        frame.pack();
        
        
        this.player1 = player1;
        this.player2 = player2;
        
        gamePlaySong = Applet.newAudioClip(this.getClass().getResource("res/sounds/gamePlayTrack.mov"));
        gamePlaySong.play();
        frame.setVisible(true);
    }
    public void hideGui(){
        frame.setVisible(false);
    }
    public void showGui(){
        frame.setVisible(true);
    }
    
    public void delete(){
        frame.dispose();
    }
    
    @Override
    public void paintComponent(Graphics g){
        
        //Draws player 1
        g.drawImage(player1.getMap(), 0,0,800,800,null);
        drawPlayer(g,player1,player2,0);
        drawPlayer(g,player1,0);
        
        
        
        
        //Draws player 2
        g.drawImage(player2.getMap(),800,0,800,800,null);
        drawPlayer(g,player2,player1,800);
        drawPlayer(g,player2,800);
        
        //Screen divider
        g.fillRect(797, 0, 6, 800);
        g.setFont(new Font("Ariel",Font.PLAIN,50));
        g.drawString("Lap "+player1.getLaps()+" / 3", 5, 50);
        g.drawString("Lap "+player2.getLaps()+" / 3", 805, 50);
    }
    
    //Draws the other person in the same screen
    private void drawPlayer(Graphics g,Player orignal, Player player,int offset){
        
        if (orignal.getPoint(player) == null) return;
        
        int x = (int) (orignal.getPoint(player).getX() - player.getImg().getWidth() / 2) + offset;
        int y = (int) (orignal.getPoint(player).getY() - player.getImg().getHeight() / 2);
        AffineTransform at = AffineTransform.getTranslateInstance(x,y);
        at.rotate(Math.toRadians(player.getRotation()), player.getImg().getWidth() / 2, player.getImg().getHeight() / 2);
        ((Graphics2D)g).drawImage(player.getImg(), at, null);
    }
    
    //Draws the player at given offset
    private void drawPlayer(Graphics g, Player player, int offset){
        int x = (int) (player.getPoint().getX() - player.getImg().getWidth() / 2) + offset;
        int y = (int) (player.getPoint().getY() - player.getImg().getHeight() / 2);
        AffineTransform at = AffineTransform.getTranslateInstance(x,y);
        at.rotate(Math.toRadians(player.getRotation()), player.getImg().getWidth() / 2, player.getImg().getHeight() / 2);
        ((Graphics2D)g).drawImage(player.getImg(), at, null);
        
    }
   
    //Returns the keys being holded
    public boolean[] getKeys(int x){
        
        //if the player is player 1
        if (x == 1){
            boolean[]output = {holdingW,holdingS,holdingA,holdingD};
            return output;
        }
        
        //If the player is player 2
        else {
            boolean[] output = {holdingUP,holdingDOWN,holdingLEFT,holdingRIGHT};
            return output;
        }        
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println("pressed");
        if (e.getKeyCode() == KeyEvent.VK_W){
            holdingW = true;
        }if (e.getKeyCode() == KeyEvent.VK_S){
            holdingS = true;
        }if (e.getKeyCode() == KeyEvent.VK_A){
            holdingA = true;
        }if (e.getKeyCode() == KeyEvent.VK_D){
            holdingD = true;
        }
        
        
        if (e.getKeyCode() == KeyEvent.VK_UP){
            holdingUP = true;
        }if (e.getKeyCode() == KeyEvent.VK_DOWN){
            holdingDOWN = true;
        }if (e.getKeyCode() == KeyEvent.VK_LEFT){
            holdingLEFT = true;
        }if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            holdingRIGHT = true;
        }
        
        if (e.getKeyCode() == KeyEvent.VK_P){
            paused = true;
        }
    }
    
    public void resetPause(){
        paused = false;
    }
    public boolean pause(){
        return paused;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        if (e.getKeyCode() == KeyEvent.VK_W){
            holdingW = false;
        }if (e.getKeyCode() == KeyEvent.VK_S){
            holdingS = false;
        }if (e.getKeyCode() == KeyEvent.VK_A){
            holdingA = false;
        }if (e.getKeyCode() == KeyEvent.VK_D){
            holdingD = false;
        }
        
        if (e.getKeyCode() == KeyEvent.VK_UP){
            holdingUP = false;
        }if (e.getKeyCode() == KeyEvent.VK_DOWN){
            holdingDOWN = false;
        }if (e.getKeyCode() == KeyEvent.VK_LEFT){
            holdingLEFT = false;
        }if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            holdingRIGHT = false;
        }
    }
      
    public void resetKeys(){
        holdingW = false;
        holdingS = false;
        holdingA = false;
        holdingD = false;
        holdingUP = false;
        holdingDOWN = false;
        holdingLEFT = false;
        holdingRIGHT = false;
    }
    
    public void keyTyped(KeyEvent e) {}
    
}