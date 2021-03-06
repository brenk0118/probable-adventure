package game;

import javax.swing.JFrame;

public class FraLauncher extends JFrame{
    PanLauncher panel;
    
    FraLauncher(){
        panel = new PanLauncher();
        
        super.add(panel);
        super.setTitle("Launcher");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setResizable(false);
        super.pack(); //Optimize size of frame to fit panel
        super.setLocationRelativeTo(null); //Center frame
        super.setVisible(true); //Show frame
    }
}
