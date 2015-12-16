package game;

public class Main{
    static LauncherFrame launcherFrame = null;
    static GameFrame gameFrame = null;
    
    public static void main(String[] args){
        launcherFrame = new LauncherFrame(); //Start the launcher
        
        //gameFrame is instantiated from the launcherFrame -> launcherPanel
        //after the launch button is pressed.
    }
}