package game;

public class Main{
    static FraLauncher fraLauncher = null;
    static FraGame fraGame = null;
    
    public static void main(String[] args){
        fraLauncher = new FraLauncher(); //Start the launcher
        //Launcher frame will call startGame when the launch button is pressed
    }
    
    public static void startGame(){
        fraLauncher.dispose();    //Kill launcher
        fraGame = new FraGame();  //Start game
    }
}