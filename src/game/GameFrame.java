package game;

import java.awt.Dimension;
import javax.swing.JFrame;

public class GameFrame extends JFrame{
    GamePanel panel = null;
    
    GameFrame(){
        //Figure out optimal resolution
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int nGameWidth, nGameHeight;
        
        //Width is 90% width of screen, forced to 16x9
        nGameWidth = (int)Math.round(screenSize.height * 0.9);
        nGameHeight = (int)Math.round(nGameWidth / 1.777);
        panel = new GamePanel(nGameWidth, nGameHeight);
        
        super.add(panel);
        super.setTitle("Game");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setResizable(false);
        super.pack(); //Optimize size of frame to fit around panel
        super.setLocationRelativeTo(null); //Center frame
        super.setVisible(true); //Show frame
    }
}