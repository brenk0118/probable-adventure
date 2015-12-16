package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Player{
    private int nX, nY,
                nVelX, nVelY;
    Color color;
    GamePanel panel;
    
    Player(int _x, int _y, Color _color, GamePanel _panel){
        nX = _x;
        nY = _y;
        color = _color;
        nVelX = nVelY = 0;
        panel = _panel; //Reference to panel with player
    }
    
    public void update(){
        nVelX = nVelY = 0;
        if(panel.setKeys.contains(KeyEvent.VK_W)) nVelY--;
        if(panel.setKeys.contains(KeyEvent.VK_S)) nVelY++;
        if(panel.setKeys.contains(KeyEvent.VK_A)) nVelX--;
        if(panel.setKeys.contains(KeyEvent.VK_D)) nVelX++;
        
        nX += nVelX;
        nY += nVelY;
        
        if(nX < 0) nX = 0;
        else if(nX > panel.nGameWidth) nX = panel.nGameWidth;
        if(nY < 0) nY = 0;
        else if(nY > panel.nGameHeight) nY = panel.nGameHeight;
    }
    
    public void draw(Graphics2D g2D){
        g2D.setColor(color);
        g2D.fillRect(nX, nY, 50, 50);
    }
}