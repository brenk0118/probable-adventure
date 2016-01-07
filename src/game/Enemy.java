package game;

import java.awt.Graphics2D;

import static game.Globals.ENEMY_COLOR;
import static game.Globals.ENEMY_SIZE;
import static game.Globals.ENEMY_SPEED;

public class Enemy{
    
    double dX, dY;
    double dAng; //Angle(radians) that the enemy is rotated
    
    PanGame panGame = null; //Reference to parent panel
    
    Enemy(int nX, int nY, PanGame _panGame){
        dX = nX;
        dY = nY;
        dAng = 0;
        
        panGame = _panGame;
    }
    
    void update(){
        double dRealAng = Math.atan2(dY - panGame.player.nY, dX - panGame.player.nX);
        dAng = dRealAng;
        
        //TODO: Interpolate angle changes
        //double difference = Math.abs(dAng - dRealAng);
        //if(difference > Math.PI){
        //    difference %= Math.PI;
        //}
        
        //dAng += difference > 0? 0.017: -0.017;
        
        //Move based on enemy angle
        dX += ENEMY_SPEED * -Math.cos(dAng);
        dY += ENEMY_SPEED * -Math.sin(dAng);
    }
    
    void draw(Graphics2D g){
        Graphics2D g2 = (Graphics2D)g.create();
        g2.rotate(dAng, dX + (ENEMY_SIZE/2), dY + (ENEMY_SIZE/2));
        g2.setColor(ENEMY_COLOR);
        g2.fillRect((int)Math.round(dX), (int)Math.round(dY), ENEMY_SIZE, ENEMY_SIZE);
        g2.dispose();
    }
}