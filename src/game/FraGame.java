package game;

import java.awt.Dimension;
import javax.swing.JFrame;

public class FraGame extends JFrame{
    PanGame panGame = null;
    
    FraGame(){
        //Figure out optimal resolution
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int nGameWidth = (int)Math.round(screenSize.height * 0.95);
        int nGameHeight = (int)Math.round(nGameWidth / 1.777);
        panGame = new PanGame(nGameWidth, nGameHeight);
        
        super.add(panGame);
        super.setTitle("Game");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setResizable(false);
        super.pack(); //Optimize size of frame to fit around panel
        super.setLocationRelativeTo(null); //Center frame
        super.setVisible(true); //Show frame
    }
}