package game;

import java.awt.Graphics2D;

import static game.Globals.ENEMY_SIZE;
import static game.Globals.ENEMY_COLOR;

public class Enemy{
    private int
        nX, nY,
        nVelX, nVelY;
    
    Enemy(){
        nX = 0;
        nY = 0;
        nVelX = nVelY = 0;
    }
    
    void update(){
        nX += nVelX;
        nY += nVelY;
    }
    
    void draw(Graphics2D g2D){
        g2D.setColor(ENEMY_COLOR);
        g2D.fillRect(nX, nY, ENEMY_SIZE, ENEMY_SIZE);
    }
}