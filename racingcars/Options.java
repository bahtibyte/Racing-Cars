package racingcars;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Options extends JComponent implements MouseListener, ActionListener{

    private final JFrame frame;
    
    private JPanel player1Panel;
    private JButton blackCar1;
    private JButton blueCar1;
    private JButton greenCar1;
    private JButton orangeCar1;
    private JButton yellowCar1;
    
    private JPanel player2Panel;
    private JButton blackCar2;
    private JButton blueCar2;
    private JButton greenCar2;
    private JButton orangeCar2;
    private JButton yellowCar2;
    
    private JButton back;
    private final Image backImg;
    
    private final Image blackCar;
    private final Image blueCar;
    private final Image greenCar;
    private final Image orangeCar;
    private final Image yellowCar;
    private final BufferedImage selected;

    private int player1Car;
    private int player2Car;
    private final int black = 1;
    private final int blue = 2;
    private final int green = 3;
    private final int orange = 4;
    private final int yellow = 5;
    
    private boolean backClicked;
    
    private final BufferedImage background;
    
    public Options(){
        
        background = loadImage("res/OptionsGui/back.png");
        blackCar = loadImage("res/OptionsGui/black.png");
        blueCar = loadImage("res/OptionsGui/blue.png");
        greenCar = loadImage("res/OptionsGui/green.png");
        orangeCar = loadImage("res/OptionsGui/orange.png");
        yellowCar = loadImage("res/OptionsGui/yellow.png");
        selected = loadImage("res/OptionsGui/selected.png");
        
        backImg = loadImage("res/OptionsGui/goBack.png");
        
        player1Car = black;
        player2Car = blue;

        backClicked = false;
        
        frame = new JFrame("Options");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setPreferredSize(new Dimension(800,600));
        addMouseListener(this);
        
        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(this);
        
        init();
        
        frame.pack();
    }
    
    private void init(){
        
        player1Panel = new JPanel();
        player1Panel.setSize(700, 150);
        player1Panel.setLocation(50, 75);
        player1Panel.setLayout(new GridLayout(1,5));
        player1Panel.setOpaque(false);
        
        blackCar1 = new JButton();
        blackCar1.setContentAreaFilled(false);
        blackCar1.setBorderPainted(false);
        blackCar1.setIcon(new ImageIcon(blackCar));
        blackCar1.addActionListener(this);
        player1Panel.add(blackCar1);
        
        blueCar1 = new JButton();
        blueCar1.setContentAreaFilled(false);
        blueCar1.setBorderPainted(false);
        blueCar1.setIcon(new ImageIcon(blueCar));
        blueCar1.addActionListener(this);
        player1Panel.add(blueCar1);
        
        greenCar1 = new JButton();
        greenCar1.setContentAreaFilled(false);
        greenCar1.setBorderPainted(false);
        greenCar1.setIcon(new ImageIcon(greenCar));
        greenCar1.addActionListener(this);
        player1Panel.add(greenCar1);
        
        orangeCar1 = new JButton();
        orangeCar1.setContentAreaFilled(false);
        orangeCar1.setBorderPainted(false);
        orangeCar1.setIcon(new ImageIcon(orangeCar));
        orangeCar1.addActionListener(this);
        player1Panel.add(orangeCar1);
        
        yellowCar1 = new JButton();
        yellowCar1.setContentAreaFilled(false);
        yellowCar1.setBorderPainted(false);
        yellowCar1.setIcon(new ImageIcon(yellowCar));
        yellowCar1.addActionListener(this);
        player1Panel.add(yellowCar1);
        
        add(player1Panel);
        
        
        //Player 2
        player2Panel = new JPanel();
        player2Panel.setSize(700, 150);
        player2Panel.setLocation(50, 300);
        player2Panel.setLayout(new GridLayout(1,5));
        player2Panel.setOpaque(false);
        
        blackCar2 = new JButton();
        blackCar2.setContentAreaFilled(false);
        blackCar2.setBorderPainted(false);
        blackCar2.setIcon(new ImageIcon(blackCar));
        blackCar2.addActionListener(this);
        player2Panel.add(blackCar2);
        
        blueCar2 = new JButton();
        blueCar2.setContentAreaFilled(false);
        blueCar2.setBorderPainted(false);
        blueCar2.setIcon(new ImageIcon(blueCar));
        blueCar2.addActionListener(this);
        player2Panel.add(blueCar2);
        
        greenCar2 = new JButton();
        greenCar2.setContentAreaFilled(false);
        greenCar2.setBorderPainted(false);
        greenCar2.setIcon(new ImageIcon(greenCar));
        greenCar2.addActionListener(this);
        player2Panel.add(greenCar2);
        
        orangeCar2 = new JButton();
        orangeCar2.setContentAreaFilled(false);
        orangeCar2.setBorderPainted(false);
        orangeCar2.setIcon(new ImageIcon(orangeCar));
        orangeCar2.addActionListener(this);
        player2Panel.add(orangeCar2);
        
        yellowCar2 = new JButton();
        yellowCar2.setContentAreaFilled(false);
        yellowCar2.setBorderPainted(false);
        yellowCar2.setIcon(new ImageIcon(yellowCar));
        yellowCar2.addActionListener(this);
        player2Panel.add(yellowCar2);
        
        add(player2Panel);
        
        
        back = new JButton();
        back.setSize(250, 75);
        back.setLocation(550,525);
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setIcon(new ImageIcon(backImg));
        back.addActionListener(this);
        add(back);
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g){
        g.drawImage(background, 0, 0, frame.getWidth(), frame.getHeight(), null);
        
        switch(player1Car){
            case black:
                g.drawImage(selected, 70, 75, null);
                break;
                
            case blue:
                g.drawImage(selected, 210, 75, null);
                break;
                
            case green:
                g.drawImage(selected, 350, 75, null);
                break;
                
            case orange:
                g.drawImage(selected, 490, 75, null);
                break;
                
            case yellow:
               g.drawImage(selected, 630, 75, null);
               break;   
                
        }
        
        switch(player2Car){
            case black:
                g.drawImage(selected, 70, 300, null);
                break;
                
            case blue:
                g.drawImage(selected, 210, 300, null);
                break;
                
            case green:
                g.drawImage(selected, 350, 300, null);
                break;
                
            case orange:
                g.drawImage(selected, 490, 300, null);
                break;
                
            case yellow:
               g.drawImage(selected, 630, 300, null);
               break;   
                
        }
    }
    
    public boolean backClicked(){
        return backClicked;
    }
    
    public void showGui(){
        frame.setVisible(true);
    }
    
    public void hideGui(){
        frame.setVisible(false);
    }
    
    public int getPlayer1Car(){
        return player1Car;
    }
    
    public int getPlayer2Car(){
        return player2Car;
    }
    
    public void resetBackClick(){
        backClicked = false;
    }
    
    private BufferedImage loadImage(String loc){
        
        BufferedImage img = null;
        
        try {
            img = ImageIO.read(getClass().getResource(loc));
        }catch (IOException e){
        }
        return img;
    }
    
    private void log(Object m){
        System.out.println(m);
    }
    
    //Sets up the correct car types in option gui
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(blackCar1)){
            log("Player 1 Car color set to: Black");
            player1Car = black;
        }if(e.getSource().equals(blueCar1)){
            log("Player 1 Car color set to: Blue");
            player1Car = blue;
        }if(e.getSource().equals(greenCar1)){
            log("Player 1 Car color set to: Green");
            player1Car = green;
        }if(e.getSource().equals(orangeCar1)){
            log("Player 1 Car color set to: Orange");
            player1Car = orange;
        }if(e.getSource().equals(yellowCar1)){
            log("Player 1 Car color set to: Yellow");
            player1Car = yellow;
        }
        
        if(e.getSource().equals(blackCar2)){
            log("Player 2 Car color set to: Black");
            player2Car = black;
        }if(e.getSource().equals(blueCar2)){
            log("Player 2 Car color set to: Blue");
            player2Car = blue;
        }if(e.getSource().equals(greenCar2)){
            log("Player 2 Car color set to: Green");
            player2Car = green;
        }if(e.getSource().equals(orangeCar2)){
            log("Player 2 Car color set to: Orange");
            player2Car = orange;
        }if(e.getSource().equals(yellowCar2)){
            log("Player 2 Car color set to: Yellow");
            player2Car = yellow;
        }
        
        if(e.getSource().equals(back)){
            backClicked = true;
        }
        
        repaint();
    }

    //Ignore
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
} 