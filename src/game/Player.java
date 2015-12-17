package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Player{
    private int nX, nY,
                nVelX, nVelY;
    Color color;
    PanGame panGame;
    
    Player(int _x, int _y, Color _color, PanGame _panGame){
        nX = _x;
        nY = _y;
        color = _color;
        nVelX = nVelY = 0;
        panGame = _panGame; //Reference to game panel for key checking
    }
    
    public void update(){
        nVelX = nVelY = 0;
        if(panGame.setKeys.contains(KeyEvent.VK_W)) nVelY--;
        if(panGame.setKeys.contains(KeyEvent.VK_S)) nVelY++;
        if(panGame.setKeys.contains(KeyEvent.VK_A)) nVelX--;
        if(panGame.setKeys.contains(KeyEvent.VK_D)) nVelX++;
        
        nX += nVelX;
        nY += nVelY;
        
        if(nX < 0) nX = 0;
        else if(nX > panGame.nWidth) nX = panGame.nWidth;
        if(nY < 0) nY = 0;
        else if(nY > panGame.nHeight) nY = panGame.nHeight;
    }
    
    public void draw(Graphics2D g2D){
        g2D.setColor(color);
        g2D.fillRect(nX, nY, 50, 50);
    }
}