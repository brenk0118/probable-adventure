package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Player{
    private int nX, nY,
                nW, nH,
                nBulletDelay;
    
    private final int PLAYER_SIZE = 20;
    private final int BULLET_SIZE = 4;
    private final int BULLET_DELAY = 20;
    private final int BULLET_SPEED = 2;
    
    Color color;
    PanGame panGame;
    List<Bullet> alBullets;
    
    Player(int _nX, int _nY, Color _color, PanGame _panGame){
        nX = _nX;
        nY = _nY;
        color = _color;
        panGame = _panGame; //Reference to game panel for key checking
        
        alBullets = new ArrayList<>();
    }
    
    public void update(){
        //Movement
        int nDX = 0,
            nDY = 0;
        if(panGame.setKeys.contains(KeyEvent.VK_W)) nDY--;
        if(panGame.setKeys.contains(KeyEvent.VK_S)) nDY++;
        if(panGame.setKeys.contains(KeyEvent.VK_A)) nDX--;
        if(panGame.setKeys.contains(KeyEvent.VK_D)) nDX++;
        nX += nDX;
        nY += nDY;
        
        //Movement bounds checking
        if(nX < 0) nX = 0;
        else if(nX > panGame.nWidth) nX = panGame.nWidth;
        if(nY < 0) nY = 0;
        else if(nY > panGame.nHeight) nY = panGame.nHeight;
        
        //Player bullet spawning
        if(nBulletDelay <= 0){
            int nVelX = 0,
                nVelY = 0;
            if(panGame.setKeys.contains(KeyEvent.VK_UP)) nVelY--;
            if(panGame.setKeys.contains(KeyEvent.VK_DOWN)) nVelY++;
            if(panGame.setKeys.contains(KeyEvent.VK_LEFT)) nVelX--;
            if(panGame.setKeys.contains(KeyEvent.VK_RIGHT)) nVelX++;
            if(nVelX != 0 || nVelY != 0){
                alBullets.add(new Bullet(nX + PLAYER_SIZE / 2, nY + PLAYER_SIZE / 2, nVelX, nVelY));
                nBulletDelay = BULLET_DELAY;
            }
        } else nBulletDelay--;
        
        //Player bullet updating
        int i = 0; //Current index
        for(Bullet bullet : alBullets){
            bullet.update();
            if(bullet.nX < -BULLET_SIZE
            || bullet.nY < -BULLET_SIZE) alBullets.remove(i);
            i++;
        }
    }
    
    public void draw(Graphics2D g2D){
        g2D.setColor(color);
        g2D.fillRect(nX, nY, PLAYER_SIZE, PLAYER_SIZE);
        for(Bullet bullet : alBullets) bullet.draw(g2D);
    }
    
    class Bullet{
        int nX, nY;
        int nVelX, nVelY;
        Bullet(int _nX, int _nY, int _nVelX, int _nVelY){
            nX = _nX;
            nY = _nY;
            nVelX = _nVelX * BULLET_SPEED;
            nVelY = _nVelY * BULLET_SPEED;
        }
        
        public void update(){
            nX += nVelX;
            nY += nVelY;
        }
        
        public void draw(Graphics2D g2D){
            g2D.fillRect(nX - BULLET_SIZE / 2, nY - BULLET_SIZE / 2, BULLET_SIZE, BULLET_SIZE);
        }
    }
}