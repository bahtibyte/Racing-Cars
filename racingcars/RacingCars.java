package racingcars;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class RacingCars{
    
    GUI gui;
    
    Player playerOne;
    Player playerTwo;
    
    int count = 0;
    
    private Timer MainMenuGui;
    private MainMenu mainMenu;
    
    private int player1CarType;
    private int player2CarType;
    
    private Timer gameTimer;
    private ActionListener gameRepeat;
    
    private BufferedImage map;
    
    Location[][] customMap;
    
    int mapType;
    
    private PauseMenu pm;
    private Timer pmTimer;
    
    private boolean opened = false;
    
    //Main Constructor
    public RacingCars(){
        
        //Opens the main menu first
        mainMenu = new MainMenu();
        pm = new PauseMenu();
        listenForMenu();

    }
    
    private void listenForMenu(){
        //Listens for the main menu
        ActionListener MainMenuListener = new ActionListener(){
        public void actionPerformed(ActionEvent event){
            if (mainMenu.gameStarted()){

                //Stops the timer for main menu
                MainMenuGui.stop();

                //Hides the main menu
                mainMenu.hideMainMenu();

                //Gets the car types choosen from the main menu
                player1CarType = mainMenu.getPlayer1Car();
                player2CarType = mainMenu.getPlayer2Car();
                
                mapType = mainMenu.getMapNum();
                
                if (mapType == 3){
                    customMap = mainMenu.getCustomMap();
                }
                
                map = mainMenu.getMap();
                
                //Starts the map settings
                start();
            }
        }
        };
        
        //Repeats the same task every 20 miliseconds
        MainMenuGui = new Timer(20,MainMenuListener);
        MainMenuGui.start();
    }
    
    public void start(){
        log("Game started");
        
        if (mapType == 3){
            playerOne = new Player(1,player1CarType,mapType,customMap);
            playerTwo = new Player(2,player2CarType,mapType,customMap);
        }else {
            playerOne = new Player(1,player1CarType,mapType);
            playerTwo = new Player(2,player2CarType,mapType);
        }
        
        
        gui = new GUI(playerOne, playerTwo);
        gui.repaint();
        
        gameRepeat = new ActionListener() {
            
        @Override
        public void actionPerformed(ActionEvent event){
            
            if (gui.pause()){
                if (!opened){
                    openPauseMenu();
                    gameTimer.stop();
                }
            }else {
            
                playerOne.updatePosition(gui.getKeys(1));
                playerTwo.updatePosition(gui.getKeys(2));

                //Repaints after the coordinates have been updated
                gui.repaint();
                checkForWin();
            }
            
        }};
        
        //Main game timer. Repeats the same Action every 30 milliseconds
        gameTimer = new Timer(15,gameRepeat);
        gameTimer.start();
    }

    private void checkForWin(){
        if (playerOne.getLaps() == 3){
            log("Player 1 wins");
            gameTimer.stop();

            String[] option = new String[] {"Play Again", "Exit"};
            int response = JOptionPane.showOptionDialog(null, "Player 1 Wins", "Racing Cars",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, option, option[0]);

            if (response == 0){
                resetStats();
                gameTimer.start();

            }
            else {
                System.exit(0);
            }

        }else if (playerTwo.getLaps() == 3){
            log("Player 2 wins");
            gameTimer.stop();

            String[] option = new String[] {"Play Again", "Exit"};
            int response = JOptionPane.showOptionDialog(null, "Player 2 Wins", "Racing Cars",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, option, option[0]);


            if (response == 0){
                resetStats();
                gameTimer.start();

            }else {
                System.exit(0);
            }

        }
    }
    
    private void resetStats(){
        gui.resetKeys();
        
        System.out.println("reset");
        playerOne.resetLaps();
        playerOne.resetLocation();
        System.out.println(playerOne.getCenter());
        playerOne.resetRotation();
        
        playerTwo.resetLaps();
        playerTwo.resetLocation();
        playerTwo.resetRotation();
        System.out.println(playerTwo.getCenter());
    }
    
    private void openPauseMenu(){
        pm.showGui();
        opened = true;
        gui.hideGui();
        ActionListener pmListener = new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(pm.resume()){
                    pmTimer.stop();
                    gui.resetPause();
                    pm.reset();
                    pm.hideGui();
                    gameTimer.start();
                    opened = false;
                    gui.showGui();
                }
                
            }  
        };
        
        pmTimer = new Timer(30,pmListener);
        pmTimer.start();
        
    }
    
    //Fancy way to print
    public void log(Object m){
        System.out.println(m);
    }
    
    public static void main(String[]args){
        RacingCars game = new RacingCars();
    }
}