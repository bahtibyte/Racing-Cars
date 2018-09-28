package racingcars;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Map {
    
    private BufferedImage img;
    
    Point MAP1_PLAYER1 = new Point(191,636);
    Point MAP1_PLAYER2 = new Point(263,636);
    Point MAP2_PLAYER1 = new Point(1467,1794);
    Point MAP2_PLAYER2 = new Point(1539, 1794);
    
    Point MAP3_PLAYER1;
    Point MAP3_PLAYER2;
    
    
    private int x,y;
    
    public Map(int maptype){
        if (maptype != 3)
            img = loadImage("res/Maps/map"+maptype+".png");
    }
    
    public Map(Location[][] customMap){
        img = getCustomMap(customMap);
        
        for (int r = 0; r < 10; r++){
            for (int c = 0; c < 10; c++){
                if (customMap[r][c].equals(new Location(1,0))){
                    
                    MAP3_PLAYER1 = new Point((c*192) + 48, (r*192) + 200);
                    
                    MAP3_PLAYER2 = new Point((c*192) + 144, (r*192) + 200);
                    
                    return;
                }
            }
        }
    }
    
    public BufferedImage getCustomMap(Location[][] customMap){
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
        
        
        return full;
    }
    
    public BufferedImage getFullMap(){
        return img;
    }
    
    
    
    public Point getCenter(int player, int mapType){
        if (mapType != 3){
            if (mapType == 1) 
                if (player == 1) 
                    return MAP1_PLAYER1; 
                else 
                    return MAP1_PLAYER2;
            else
                if (player == 1) 
                    return MAP2_PLAYER1; 
                else 
                    return MAP2_PLAYER2;    
        }
        else
            if (player == 1){
                return MAP3_PLAYER1;
            
            }else 
                return MAP3_PLAYER2;
    }
    
    public BufferedImage getMap(Point point){
        
        if (point.getX() < 400)
            x = 0;
        
        if (point.getX() >= 400 && point.getX() <= img.getWidth() - 400)
            x = (int) point.getX() - 400;
        
        if (point.getX() > img.getWidth() - 400)
            x = (int) img.getWidth() - 800;
        
        if (point.getY() < 400)
            y = 0;
        
        if (point.getY() >= 400 && point.getY() <= img.getHeight() - 400)
            y = (int) point.getY() - 400;
        
        if (point.getY() > img.getHeight() - 400)
            y = (int) img.getHeight() - 800;
        
        return img.getSubimage(x,y, 800, 800);
    }
    
    public Point getVision(){
        return new Point(x,y);
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
    
    //Loads a image with given location
    private BufferedImage loadImage(String loc){
        try {
            img = ImageIO.read(getClass().getResource(loc));
        }catch (IOException e){
        }
        return img;
    }
    
}