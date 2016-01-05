package game;

import java.awt.Graphics2D;

import static game.Globals.ENEMY_SIZE;
import static game.Globals.ENEMY_COLOR;

public class Enemy{
    private int
        nX, nY,
        nVelX, nVelY;
    
    private double
        dAng; //dAng son
    
    PanGame panGame = null; //Reference to parent panel
    
    Enemy(PanGame _panGame){
        nX = 50;
        nY = 50;
        nVelX = nVelY = 0;
        dAng = 0;
        
        panGame = _panGame;
    }
    
    void update(){
        nX += nVelX;
        nY += nVelY;
        
        dAng = Math.atan2(nY - panGame.player.nY, nX - panGame.player.nX);
    }
    
    void draw(Graphics2D g){
        Graphics2D g2 = (Graphics2D)g.create();
        g2.rotate(dAng, nX + (ENEMY_SIZE/2), nY + (ENEMY_SIZE/2));
        g2.setColor(ENEMY_COLOR);
        g2.fillRect(nX, nY, ENEMY_SIZE, ENEMY_SIZE);
        g2.dispose();
    }
}