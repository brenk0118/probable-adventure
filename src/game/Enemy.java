package game;

import java.awt.Color;
import java.awt.Graphics2D;

public class Enemy{
    private int
        nX, nY,
        nW, nH,
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
        g2D.setColor(Color.red);
        g2D.fillRect(nX, nY, nW, nH);
    }
}