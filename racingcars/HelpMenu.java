package racingcars;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class HelpMenu extends JComponent implements ActionListener{
    
    JFrame frame;
    JButton back;
    
    boolean backClick = false;
    BufferedImage backImg;
    
    public HelpMenu(){
        backImg = loadImage("res/MainMenuGui/helpMenu.png");
        
        frame = new JFrame("Paused");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800,800));
        
        frame.getContentPane().add(this);
        frame.setResizable(false);
        
        back = new JButton("Exit");
        back.setSize(300, 100);
        back.setLocation(500, 700);
        back.setFont(new Font("Ariel",Font.PLAIN, 40));
        back.addActionListener(this);
        add(back);
        
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    @Override
    public void paintComponent(Graphics g){
        g.drawImage(backImg,0,0,800,800,null);
    }
    
    public void resetClick(){
        backClick = false;
    }
    
    public boolean isBack(){
        return backClick;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(back)){
            backClick = true;
        }
    }
    
    private BufferedImage loadImage(String loc){
        
        BufferedImage img = null;
        
        try {
            img = ImageIO.read(getClass().getResource(loc));
        }catch (IOException e){
        }
        return img;
    }

    void hideGui() {
       frame.setVisible(false);
    }

    void showGui() {
        frame.setVisible(true);
    }
    
}