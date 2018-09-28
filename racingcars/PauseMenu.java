package racingcars;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class PauseMenu extends JComponent implements ActionListener{

    JFrame frame;
    JButton resume;
    JButton menu;
    JButton exit;
    
    private boolean resumeClick = false;
    private boolean menuCliked = false;
    
    public PauseMenu(){
        frame = new JFrame("Paused");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(300,200));
        
        frame.getContentPane().add(this);
        frame.setResizable(false);
        
        resume = new JButton("Resume");
        resume.setSize(300, 100);
        resume.setLocation(0, 0);
        resume.setFont(new Font("Ariel",Font.PLAIN, 40));
        resume.addActionListener(this);
        add(resume);
        
        exit = new JButton("Exit");
        exit.setSize(300, 100);
        exit.setLocation(0, 100);
        exit.setFont(new Font("Ariel",Font.PLAIN, 40));
        exit.addActionListener(this);
        add(exit);
        
        frame.pack();
        frame.setLocationRelativeTo(null);
        //frame.setVisible(true);
    }
    
    
    public void showGui(){
        frame.setVisible(true);
    }
    public void hideGui(){
        frame.setVisible(false);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource().equals(resume)){
            resumeClick = true;
        }
        if (e.getSource().equals(exit)){
            System.exit(0);
        }
        
    }
    
    public boolean resume(){
        return resumeClick;
    }
    
    public void reset(){
        resumeClick = false;
    }
    
    public void deleteGui(){
        frame.dispose();
    }
    
}