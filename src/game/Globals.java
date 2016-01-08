package game;

/* 
 * Rules:
 * -> Variables *MUST* be declared final static
 * -> No methods whatsoever
 * -> Don't be stupid
 */

import java.awt.Color;

public class Globals{
    final static boolean DEBUG = true;
    final static Color DEBUG_COLOR = new Color(255, 255, 255);
    final static int UPDATE_DELAY = 10;
    
    //Player
    final static Color PLAYER_COLOR = new Color(0, 255, 255);
    final static int PLAYER_SIZE = 20;
    final static int PLAYER_SPEED = 2;
        
        //Player bullets
        final static Color BULLET_COLOR = PLAYER_COLOR;
        final static int BULLET_SIZE = 5;
        final static int BULLET_DELAY = 20;
        final static int BULLET_SPEED = 3;
    
    //Enemies
    final static Color ENEMY_COLOR = new Color(255, 255, 0);
    final static int ENEMY_SIZE = 20;
    final static int ENEMY_SPEED = 1;
}
