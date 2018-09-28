package racingcars;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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

public class MapSelection extends JComponent implements ActionListener{

    private final JFrame frame;
    private final JButton map1;
    private final JButton map2;
    private final JButton map3;
    private final JButton back;
    
    
    private Location[][] customMap;
    private BufferedImage map;
    
    private boolean backClick;
    private boolean mapSelected;
    private int type;
    
    
    
    public MapSelection(Location[][] customMap){
        
        this.customMap = customMap;
        createMap();
        
        
        frame = new JFrame("Select Map");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1400,600));
        
        frame.getContentPane().add(this);
        frame.setResizable(false);
        
        frame.pack();
        frame.setLocationRelativeTo(null);
        
        Image map1Img = loadImage("res/Maps/map1.png").getScaledInstance(500,-1,Image.SCALE_DEFAULT);
        Image map2Img = loadImage("res/Maps/map2.png").getScaledInstance(350,-1,Image.SCALE_DEFAULT);
        Image map3Img = map.getScaledInstance(500,-1,Image.SCALE_DEFAULT);
        
        map1 = new JButton();
        map1.setSize(500, 500);
        map1.setLocation(0, 0);
        map1.setIcon(new ImageIcon(map1Img));
        map1.addActionListener(this);
        add(map1);
        
        map2 = new JButton();
        map2.setSize(350, 500);
        map2.setLocation(525, 0);
        map2.setIcon(new ImageIcon(map2Img));
        map2.addActionListener(this);
        add(map2);
        
        map3 = new JButton();
        map3.setSize(500, 500);
        map3.setLocation(900, 0);
        map3.addActionListener(this);
        map3.setIcon(new ImageIcon(map3Img));
        add(map3);
        
        back = new JButton("Back");
        back.setSize(1350,100);
        back.setLocation(0,500);
        back.setFont(new Font("Ariel", Font.PLAIN, 50));
        back.addActionListener(this);
        add(back);
        
        backClick = false;
        mapSelected = false;
        
    }
    
    public void updateMap(Location[][] customMap){
        this.customMap = customMap;
        createMap();
        map3.setIcon(new ImageIcon(map.getScaledInstance(500,-1,Image.SCALE_DEFAULT)));
    }
    
    public void showGui(){
        frame.setVisible(true);
    }
    
    public void hideGui(){
        frame.setVisible(false);
    }
    
    public void createMap(){
        
        BufferedImage[] rows = new BufferedImage[10];
        
        
        for (int r = 0; r < 10; r++){
            
            BufferedImage temp = loadImage("res/tilesets/tile"+customMap[r][0].getRow()+customMap[r][0].getCol()+".png");
            
            for (int c = 1; c < 10; c++){

                BufferedImage img = loadImage("res/tilesets/tile"+customMap[r][c].getRow()+customMap[r][c].getCol()+".png");

                temp = combine(temp,img,true);

            }

            rows[r] = temp;
        }
        
        BufferedImage full = rows[0];
        
        for (int i = 1; i < 10; i++){
            
            BufferedImage img = rows[i];
            
            full = combine(full,img,false);
            
        }
        
        
        map = full;
    }
    
    public BufferedImage combine(BufferedImage img1, BufferedImage img2, boolean side){
        
        if (side){
            //do some calculate first
            int offset  = 0;
            int wid = img1.getWidth()+img2.getWidth()+offset;
            int height = Math.max(img1.getHeight(),img2.getHeight())+offset;
            //create a new buffer and draw two image into the new image
            BufferedImage newImage = new BufferedImage(wid,height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = newImage.createGraphics();
            Color oldColor = g2.getColor();
            //fill background
            g2.setPaint(Color.WHITE);
            g2.fillRect(0, 0, wid, height);
            //draw image
            g2.setColor(oldColor);
            g2.drawImage(img1, null, 0, 0);
            g2.drawImage(img2, null, img1.getWidth()+offset, 0);
            g2.dispose();
        return newImage;
        }else {
            
            int wid = img1.getWidth();
            int height = img1.getHeight()+img2.getHeight();
            //create a new buffer and draw two image into the new image
            BufferedImage newImage = new BufferedImage(wid,height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = newImage.createGraphics();
            Color oldColor = g2.getColor();
            //fill background
            g2.setPaint(Color.WHITE);
            g2.fillRect(0, 0, wid, height);
            //draw image
            g2.setColor(oldColor);
            g2.drawImage(img1, null, 0, 0);
            g2.drawImage(img2, null, 0, img1.getHeight());
            g2.dispose();
            return newImage;
            
        }
        
    }
    
    public BufferedImage getMap(){
        if (type == 1){
            return loadImage("res/Maps/map1.png");
        }else if (type == 2){
            return loadImage("res/Maps/map2.png");
        }else {
            return map;
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
    
    public void resetBack(){
        backClick = false;
    }    
    
    public boolean isBack(){
        return backClick;
    }
    
    public boolean isMapSelected(){
        return mapSelected;
    }
    
    public void resetMap(){
        mapSelected = false;
    }
    
    public int getMapNum(){
        return type;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(back)){
            backClick = true;
        }
        
        if (e.getSource().equals(map1)){
            mapSelected = true;
            type = 1;
        }else if (e.getSource().equals(map2)){
            mapSelected = true;
            type = 2;
        }else if (e.getSource().equals(map3)){
            mapSelected = true;
            type = 3;
        }
    }
    
}