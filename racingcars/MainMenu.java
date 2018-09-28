package racingcars;

import java.awt.Dimension;
import java.awt.Graphics;
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
import javax.swing.Timer;

public class MainMenu extends JComponent implements  ActionListener{
    
    private final JFrame frame;
    private JButton start;
    private JButton options;
    private JButton custom;
    private JButton help;
    private JButton exit;
    
    private final BufferedImage background;
    private final Image startImg;
    private final Image optionsImg;
    private final Image customImg;
    private final Image helpImg;
    private final Image exitImg;
    
    private boolean startClicked;
    
    private final Options optionGUI;
    private Timer optionTimer;
    
    private int player1Car;
    private int player2Car;
    private int mapType;
    
    private  TileSelector tile;
    private  Grid grid;
    private  ActionListener tileListener;
    private  Timer tileTimer;
    
    private Location[][] map;
    
    private MapSelection mapSelection;
    private ActionListener mapListener;
    private Timer mapTimer;
    
    private HelpMenu hm;
    
    int mapNum;
    
    private BufferedImage playerMap;
    
    public MainMenu(){
        
        optionGUI = new Options();
        hm = new HelpMenu();
        
        player1Car = optionGUI.getPlayer1Car();
        player2Car = optionGUI.getPlayer2Car();

        background = loadImage("res/MainMenuGui/startMenu.png");
        startImg = loadImage("res/MainMenuGui/start.png");
        optionsImg = loadImage("res/MainMenuGui/options.png");
        customImg = loadImage("res/MainMenuGui/custom.png");
        helpImg = loadImage("res/MainMenuGui/help.png");
        exitImg = loadImage("res/MainMenuGui/exit.png");
        
        startClicked = false;
        
        frame = new JFrame("Start Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setPreferredSize(new Dimension(550,350));
        
        frame.getContentPane().add(this);
        frame.setLocationRelativeTo(null);
        showButtons();
        
        
        tile = new TileSelector();
        grid = new Grid(10,10);
        
        map = new Location[10][10];
        
        for (int r = 0; r <map.length;  r++){
            for (int c = 0; c < map[r].length; c++){
                map[r][c] = new Location(1,1);
            }
        }

        mapSelection = new MapSelection(map);

        
        frame.pack();
        frame.setVisible(true);
        
        
    }
    
    @Override
    public void paintComponent(Graphics g){
        g.drawImage(background,0, 0, frame.getWidth(), frame.getHeight(), null);
    }
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(start))
            openMapSelection();
        
        if (e.getSource().equals(help)){
            openHelpGui();
        }
        
        if(e.getSource().equals(options))
            openOptionGui();
        
        if (e.getSource().equals(custom))
            openCustomGui();
        
        if(e.getSource().equals(exit))
            System.exit(0);
        
        
    }
    
    
    private boolean checkForLoop(){
        
        Location start = null;
        
        for (int r = 0; r < 10; r++){
            for (int c = 0; c < 10; c++){
               if (map[r][c].equals(new Location(1,0))){
                   start = new Location(r,c);
               }
            }
        }
        
        
        int up = 1;
        int right = 2;
        int down = 3;
        int left = 4;
        
        int direction = up;
        
        boolean fullTrack = false;
        
        Location current = new Location(start.getRow(),start.getCol());
        while(!fullTrack){
            if (direction == up){
                Location next = new Location(current.getRow()-1,current.getCol());
                
                if (isValid(next)){
                    
                    if (map[next.getRow()][next.getCol()].equals(new Location(1,0))){
                        fullTrack = true;
                        break;
                    }else if (map[next.getRow()][next.getCol()].equals(new Location(0,0))){
                        direction = right;
                        current = new Location(next.getRow(),next.getCol());
                    }else if (map[next.getRow()][next.getCol()].equals(new Location(1,2))){
                        direction = up;
                        current = new Location(next.getRow(),next.getCol());
                    }else if (map[next.getRow()][next.getCol()].equals(new Location(0,2))){
                        direction = left;
                        current = new Location(next.getRow(),next.getCol());
                    }else{
                        break;
                    }
                    
                }else {
                    fullTrack = false;
                    break;
                }
                
                
            }else if (direction == right){
                Location next = new Location(current.getRow(),current.getCol()+1);
                
                if (isValid(next)){
                    
                    if (map[next.getRow()][next.getCol()].equals(new Location(0,2))){
                        direction = down;
                        current = new Location(next.getRow(),next.getCol());
                    }else if (map[next.getRow()][next.getCol()].equals(new Location(0,1))){
                        direction = right;
                        current = new Location(next.getRow(),next.getCol());
                    }else if (map[next.getRow()][next.getCol()].equals(new Location(2,1))){
                        direction = right;
                        current = new Location(next.getRow(),next.getCol());
                    }else if (map[next.getRow()][next.getCol()].equals(new Location(2,2))){
                        direction = up;
                        current = new Location(next.getRow(),next.getCol());
                    }else{
                        break;
                    }
                    
                }else {
                    fullTrack = false;
                    break;
                }
            }else if (direction == down){
                 Location next = new Location(current.getRow()+1,current.getCol());
                
                if (isValid(next)){
                    
                    if (map[next.getRow()][next.getCol()].equals(new Location(1,2))){
                        direction = down;
                        current = new Location(next.getRow(),next.getCol());
                    }else if (map[next.getRow()][next.getCol()].equals(new Location(2,2))){
                        direction = left;
                        current = new Location(next.getRow(),next.getCol());
                    }else if (map[next.getRow()][next.getCol()].equals(new Location(2,0))){
                        direction = right;
                        current = new Location(next.getRow(),next.getCol());
                    }else{
                        break;
                    }
                    
                }else {
                    fullTrack = false;
                    break;
                }
            }else if (direction == left) {
                 Location next = new Location(current.getRow(),current.getCol()-1);
                
                if (isValid(next)){
                    
                    if (map[next.getRow()][next.getCol()].equals(new Location(0,0))){
                        direction = down;
                        current = new Location(next.getRow(),next.getCol());
                    }else if (map[next.getRow()][next.getCol()].equals(new Location(0,1))){
                        direction = left;
                        current = new Location(next.getRow(),next.getCol());
                    }else if (map[next.getRow()][next.getCol()].equals(new Location(2,1))){
                        direction = left;
                        current = new Location(next.getRow(),next.getCol());
                    }else if (map[next.getRow()][next.getCol()].equals(new Location(2,0))){
                        direction = up;
                        current = new Location(next.getRow(),next.getCol());
                    }else{
                        break;
                    }
                    
                }else {
                    fullTrack = false;
                    break;
                }
            }
            
        }
        
        
        return fullTrack;
        
    }
    
    private boolean isValid(Location loc){
        return (loc.getRow() >= 0 && loc.getRow() < 10) && (loc.getCol() >= 0 && loc.getCol() < 10); 
    }
    
    private void openCustomGui() {
        
        hideMainMenu();
        
        grid.showGui();
        tile.showGui();
        
        for (int r = 0; r < 10; r++){
            for (int c = 0; c < 10; c++){
                grid.setImage(new Location(r,c), "res/tilesets/tile"+map[r][c].getRow()+map[r][c].getCol()+".png");
            }
        }
        
        grid.setImage(new Location(0,0), "res/tilesets/tile10.png");
        map[0][0] = new Location(1,0);
        
        tileListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                if (tile.isBack()){
                    if (checkForLoop()){
                        tile.reset();
                        tileTimer.stop();
                        grid.hideGui();
                        tile.hideGui();
                        showMainMenu();
                    }else {
                        tile.reset();
                    }
                }
                
                Location type = tile.getTile();
                Location loc = grid.checkLastLocationClicked();
                
                if (loc != null){
                    
                    boolean temp = true;
                    
                    if (type.equals(new Location(1,0))){
                        
                        
                        for (int r = 0; r < 10 && temp; r++){
                            for (int c = 0; c < 10 && temp; c++){
                                
                                
                                if (map[r][c].equals(new Location(1,0))){
                                    
                                   
                                    map[r][c] = new Location(1,1);
                                    grid.setImage(new Location(r,c), "res/tilesets/tile11.png");
                                    
                                    map[loc.getRow()][loc.getCol()] = type;
                                    
                                    grid.setImage(loc, "res/tilesets/tile"+type.getRow()+type.getCol()+".png");
                                    temp = false;
                                    
                                }
                                
                            }
                        }
                    }
                    
                    if (temp){
                        map[loc.getRow()][loc.getCol()] = type;
                        grid.setImage(loc, "res/tilesets/tile"+type.getRow()+type.getCol()+".png");
                    }
                }
                
            }
        };
        
        tileTimer = new Timer(20,tileListener);
        tileTimer.start();
    }

    private void openOptionGui(){
        optionGUI.showGui();
        
        hideMainMenu();
        
        ActionListener optionListener  = new ActionListener(){
        public void actionPerformed(ActionEvent event){
            if (optionGUI.backClicked()){
                optionTimer.stop();
                optionGUI.hideGui();

                player1Car = optionGUI.getPlayer1Car();
                player2Car = optionGUI.getPlayer2Car();

                showMainMenu();

                optionGUI.resetBackClick();

                log("Back To Main Menu "+player1Car+ " "+player2Car+" "+mapType);
            }
            }
        };
        
        optionTimer = new Timer(20,optionListener);
        optionTimer.start();
        
    }
    
    private void openMapSelection(){
        hideMainMenu();
        mapSelection.showGui();
        
        mapSelection.updateMap(map);
        
        mapListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (mapSelection.isBack()){
                    mapTimer.stop();
                    mapSelection.resetBack();
                    showMainMenu();
                    mapSelection.hideGui();
                }
                
                else if (mapSelection.isMapSelected()){
                    mapTimer.stop();
                    mapSelection.resetMap();
                    mapSelection.hideGui();
                    
                    playerMap = mapSelection.getMap();
                    
                    mapNum = mapSelection.getMapNum();
                    
                    startClicked = true;
                }
            }
        };
        
        mapTimer = new Timer(20,mapListener);
        mapTimer.start();
        
        
        
    }   
    
    public Location[][] getCustomMap(){
        return map;
    }
    
    public void reset(){
        startClicked = false;
    }
    
    public int getPlayer1Car(){
        return player1Car;
    }
    public int getPlayer2Car(){
        return player2Car;
    }
    
    public BufferedImage getMapType(){
        return playerMap;
    }
    
    public int getMapNum(){
        return mapNum;
    }
    public BufferedImage getMap(){
        return playerMap;
    }
    
    public void hideMainMenu(){
        frame.setVisible(false);
    }
    
    public void showMainMenu(){
        frame.setVisible(true);
    }
    
    public boolean gameStarted(){
        return startClicked;
    }
    
    private void log(Object m){
        System.out.println(m);
    }
    
    private void showButtons(){
        start = new JButton("Start");
        start.setSize(250,50);
        start.setLocation(300,100);
        start.setContentAreaFilled(false);
        start.setBorderPainted(false);
        start.setIcon(new ImageIcon(startImg));
        start.addActionListener(this);
        add(start);
        
        options = new JButton("Options");
        options.setSize(250, 50);
        options.setLocation(300, 150);
        options.setBorderPainted(false);
        options.setContentAreaFilled(false);
        options.setIcon(new ImageIcon(optionsImg));
        options.addActionListener(this);
        add(options);
        
        help = new JButton("Help");
        help.setSize(250, 50);
        help.setLocation(300,200);
        help.setContentAreaFilled(false);
        help.setBorderPainted(false);
        help.addActionListener(this);
        help.setIcon(new ImageIcon(helpImg));
        add(help);
        
        custom = new JButton("Custom");
        custom.setSize(250, 50);
        custom.setLocation(300,250);
        custom.setContentAreaFilled(false);
        custom.setBorderPainted(false);
        custom.addActionListener(this);
        custom.setIcon(new ImageIcon(customImg));
        add(custom);
        
        exit = new JButton("Exit");
        exit.setSize(250, 50);
        exit.setLocation(300,300);
        exit.setContentAreaFilled(false);
        exit.setBorderPainted(false);
        exit.addActionListener(this);
        exit.setIcon(new ImageIcon(exitImg));
        add(exit);
        
        
    }
     
    private BufferedImage loadImage(String loc){
        
        BufferedImage img = null;
        
        try {
            img = ImageIO.read(getClass().getResource(loc));
        }catch (IOException e){
        }
        return img;
    }

    Timer helpTimer;
    private void openHelpGui() {
        hm.showGui();
        hideMainMenu();
        ActionListener helpListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (hm.isBack()){
                    hm.hideGui();
                    showMainMenu();
                    helpTimer.stop();
                    hm.resetClick();
                }
            }
        };
        
        helpTimer = new Timer(30,helpListener);
        helpTimer.start();
        
    }
    
    
    
}