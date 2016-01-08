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
    double dX, dY,
        nBulletDelay;
    
    PanGame panGame = null; //Reference to parent panel
    List<Bullet> alBullets = null; //Player bullets
    
    Player(double _dX, double _dY, PanGame _panGame){
        dX = _dX;
        dY = _dY;
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
        dX += nDX * PLAYER_SPEED;
        dY += nDY * PLAYER_SPEED;
        
        //Movement bounds checking
        if(dX < 0) dX = 0;
        else if(dX > panGame.nWidth - PLAYER_SIZE) dX = panGame.nWidth - PLAYER_SIZE;
        if(dY < 0) dY = 0;
        else if(dY > panGame.nHeight - PLAYER_SIZE) dY = panGame.nHeight - PLAYER_SIZE;
        
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
                double dStartX = dX + (PLAYER_SIZE / 2);
                double dStartY = dY + (PLAYER_SIZE / 2);
                
                //Move to edge based on shot direction
                dStartX += nDirX * (PLAYER_SIZE / 2 - BULLET_SIZE);
                dStartY += nDirY * (PLAYER_SIZE / 2 - BULLET_SIZE);
                
                alBullets.add(new Bullet(dStartX, dStartY, nDirX, nDirY));
                nBulletDelay = BULLET_DELAY;
            }
        } else nBulletDelay--;
        
        //Player bullet updating
        for(Iterator<Bullet> itBullets = alBullets.iterator(); itBullets.hasNext(); ){
            Bullet bullet = itBullets.next();
            if(bullet.dX < -BULLET_SIZE
            || bullet.dY < -BULLET_SIZE
            || bullet.dX > panGame.nWidth
            || bullet.dY > panGame.nHeight){
                itBullets.remove();
            } else {
                bullet.update();
            }
        }
    }
    
    public void draw(Graphics2D g){
        g.setColor(PLAYER_COLOR);
        g.fillRect((int)dX, (int)dY, PLAYER_SIZE, PLAYER_SIZE);
        for(Bullet bullet : alBullets) bullet.draw(g);
    }
    
    class Bullet{
        double dX, dY;
        int nVelX, nVelY;
        Bullet(double _dX, double _dY, int _nVelX, int _nVelY){
            dX = _dX;
            dY = _dY;
            nVelX = _nVelX * BULLET_SPEED;
            nVelY = _nVelY * BULLET_SPEED;
        }
        
        public void update(){
            dX += nVelX;
            dY += nVelY;
        }
        
        public void draw(Graphics2D g){
            g.setColor(BULLET_COLOR);
            g.fillRect((int)(dX - BULLET_SIZE / 2), (int)(dY - BULLET_SIZE / 2), BULLET_SIZE, BULLET_SIZE);
        }
    }
}