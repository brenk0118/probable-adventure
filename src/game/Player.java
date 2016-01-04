package game;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static game.Globals.PLAYER_SIZE;
import static game.Globals.PLAYER_COLOR;
import static game.Globals.PLAYER_SPEED;
import static game.Globals.BULLET_COLOR;
import static game.Globals.BULLET_DELAY;
import static game.Globals.BULLET_SIZE;
import static game.Globals.BULLET_SPEED;

public class Player{
    private int nX, nY,
                nBulletDelay;
    
    PanGame panGame = null; //Reference to parent panel
    List<Bullet> alBullets = null; //Player bullets
    
    Player(int _nX, int _nY, PanGame _panGame){
        nX = _nX;
        nY = _nY;
        panGame = _panGame;
        
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
        nX += nDX * PLAYER_SPEED;
        nY += nDY * PLAYER_SPEED;
        
        //Movement bounds checking
        if(nX < 0) nX = 0;
        else if(nX > panGame.nWidth) nX = panGame.nWidth;
        if(nY < 0) nY = 0;
        else if(nY > panGame.nHeight) nY = panGame.nHeight;
        
        //Player bullet spawning
        if(nBulletDelay <= 0){
            int nDirX = 0,
                nDirY = 0;
            if(panGame.setKeys.contains(KeyEvent.VK_UP)) nDirY--;
            if(panGame.setKeys.contains(KeyEvent.VK_DOWN)) nDirY++;
            if(panGame.setKeys.contains(KeyEvent.VK_LEFT)) nDirX--;
            if(panGame.setKeys.contains(KeyEvent.VK_RIGHT)) nDirX++;
            if(nDirX != 0 || nDirY != 0){
                //Center in player
                int nStartX = nX + (PLAYER_SIZE / 2);
                int nStartY = nY + (PLAYER_SIZE / 2);
                
                //Move to edge based on shot direction
                nStartX += nDirX * (PLAYER_SIZE / 2 - BULLET_SIZE);
                nStartY += nDirY * (PLAYER_SIZE / 2 - BULLET_SIZE);
                
                alBullets.add(new Bullet(nStartX, nStartY, nDirX, nDirY));
                nBulletDelay = BULLET_DELAY;
            }
        } else nBulletDelay--;
        
        //Player bullet updating
        Iterator<Bullet> itBullets = alBullets.iterator();
        while(itBullets.hasNext()){
            Bullet bullet = itBullets.next();
            if(bullet.nX < -BULLET_SIZE
            || bullet.nY < -BULLET_SIZE
            || bullet.nX + BULLET_SIZE > panGame.getWidth()
            || bullet.nY + BULLET_SIZE > panGame.getHeight()){
                itBullets.remove();
            } else {
                bullet.update();
            }
        }
    }
    
    public void draw(Graphics2D g2D){
        g2D.setColor(PLAYER_COLOR);
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
            g2D.setColor(BULLET_COLOR);
            g2D.fillRect(nX - BULLET_SIZE / 2, nY - BULLET_SIZE / 2, BULLET_SIZE, BULLET_SIZE);
        }
    }
}