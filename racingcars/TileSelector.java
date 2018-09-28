package racingcars;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TileSelector extends JComponent implements ActionListener{

    private final JFrame frame;
    private final JPanel panel;
    private final JButton[][] tiles;
    private final JButton save;
    
    private boolean exit = false;
    
    private Location location;
    
    public TileSelector(){
        tiles = new JButton[3][3];
        
        frame = new JFrame("Tile Selector");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500,600));
        
        frame.getContentPane().add(this);
        frame.setResizable(false);
        
        panel = new JPanel();
        panel.setSize(500, 500);
        panel.setLocation(0,0);
        panel.setOpaque(false);
        panel.setLayout(new GridLayout(3,3));
        
        
        for (int  r = 0; r < 3; r++){
            for (int c = 0; c < 3; c++){
                
                Image tile = loadImage("res/tilesets/tile"+r+c+".png").getScaledInstance(160,-1,Image.SCALE_DEFAULT);;
                
                tiles[r][c] = new JButton();
                tiles[r][c].setLocation(300, 150);
                tiles[r][c].setBorderPainted(false);
                tiles[r][c].setContentAreaFilled(false);
                tiles[r][c].setIcon(new ImageIcon(tile));
                tiles[r][c].addActionListener(this);
                
                panel.add(tiles[r][c]);
            }
        }
        
        save = new JButton("Save and Go Back");
        save.setSize(500, 100);
        save.setLocation(0, 500);
        save.setFont(new Font("Ariel",Font.PLAIN, 40));
        save.addActionListener(this);
        add(save);
        
        add(panel);
        
        frame.pack();
        frame.setLocationRelativeTo(null);
        
        location = new Location(1,1);
    }
    
    private void checkForLoop(){
        Location start;
        for (int r = 0; r < 10; r++){
            for (int c = 0; c < 10; c++){
                
            }
        }
        
    }
    
    public void showGui(){
        frame.setVisible(true);
    }
    
    public void hideGui(){
        frame.setVisible(false);
    }
    
    public boolean isBack(){
        return exit;
    }
    
    public void reset(){
        exit = false;
    }
    
    private BufferedImage loadImage(String loc){
        
        BufferedImage img = null;
        
        try {
            img = ImageIO.read(getClass().getResource(loc));
        }catch (IOException e){
        }
        
        return img;
    }
    
    public Location getTile(){
        return location;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int r = 0; r < 3; r++){
            for (int c = 0; c < 3; c++){
                if (e.getSource().equals(tiles[r][c])){
                    location = new Location(r,c);
                }
            }
        }
        if (e.getSource().equals(save))
            exit = true;
            
    }
    
}