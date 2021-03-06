package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.swing.JPanel;
import javax.swing.Timer;

import static game.Globals.DEBUG;
import static game.Globals.DEBUG_COLOR;
import static game.Globals.UPDATE_DELAY;
import static game.Globals.PLAYER_SIZE;
import static game.Globals.ENEMY_SIZE;

public class PanGame extends JPanel implements ActionListener, KeyListener{
    Player player = null;
    List<Enemy> alEnemies = null; //List of enemies
    Set<Integer> setKeys = null; //Set of key codes which are pressed
    Timer timer = null;
    boolean[] arbMouseButtons;
    
    long nGameTick = 0;
    
    int nWidth, nHeight; //We won't change this value, we won't have to call
                         //getwidth and getheight to get dimensions of this panel
    
    PanGame(int nGameWidth, int nGameHeight){
        nWidth = nGameWidth;
        nHeight = nGameHeight;
        
        player = new Player(0, 0, this);
        alEnemies = new ArrayList<>();
        setKeys = new HashSet<>();
        
        super.setPreferredSize(new Dimension(nWidth, nHeight));
        super.setFocusable(true);
        super.requestFocusInWindow();
        super.addKeyListener(this);
        
        timer = new Timer(UPDATE_DELAY, this);
        timer.start();
        
    }
    
    @Override public void actionPerformed(ActionEvent e){
        player.update();
        
        if(nGameTick % 100 == 0){
            alEnemies.add(new Enemy(nWidth / 2, nHeight / 2, this));
        }
        
        for(Iterator<Enemy> itEnemies = alEnemies.iterator(); itEnemies.hasNext();){
            Enemy enemy = itEnemies.next();
            enemy.update();
            
            if(!( //Player is hit by enemy
                player.dX > enemy.dX + ENEMY_SIZE       //Too far right
                || player.dX + PLAYER_SIZE < enemy.dX   //Too far left
                || player.dY > enemy.dY + ENEMY_SIZE    //Too far down
                || player.dY + PLAYER_SIZE < enemy.dY   //Too far up
            )){
                timer.stop(); //Make-shift game-over
            }
        
            for(Iterator<Player.Bullet> itBullets = player.alBullets.iterator(); itBullets.hasNext();){
                Player.Bullet bullet = itBullets.next();
                
                //Rotate bullet x and y 
                double dTheta = -enemy.dAng; //Rotate oposite direction that enemy would while drawing
                double dCosTheta = Math.cos(dTheta);
                double dSinTheta = Math.sin(dTheta);
                double dDeltaX = bullet.dX - enemy.dX;
                double dDeltaY = bullet.dY - enemy.dY;
                double dRotatedX = (dCosTheta * dDeltaX - dSinTheta * dDeltaY + enemy.dX);
                double dRotatedY = (dSinTheta * dDeltaX + dCosTheta * dDeltaY + enemy.dY);
                
                if( //Enemy is hit by bullet
                  dRotatedX > enemy.dX
                  && dRotatedX < enemy.dX + ENEMY_SIZE
                  && dRotatedY > enemy.dY
                  && dRotatedY < enemy.dY + ENEMY_SIZE
                ){
                    itEnemies.remove();
                    itBullets.remove();
                }
            }
        }
        
        nGameTick++;
        super.repaint(); //Calls paintComponent
    }
    
    @Override public void paintComponent(Graphics g){
        if(!(g instanceof Graphics2D)) return;
        Graphics2D g2D = (Graphics2D)g;
        g2D.setColor(Color.black);
        g2D.fillRect(0, 0, nWidth, nHeight);
        
        player.draw(g2D); //Draw player and bullets belonging to player
        for(Enemy enemy : alEnemies) enemy.draw(g2D);
        
        //Debug
        if(DEBUG){
            g2D.setColor(DEBUG_COLOR);
            g2D.drawString("Game Tick: "+nGameTick, 0, 10);
            g2D.drawString("Player Pos: "+player.dX+", "+player.dY, 0, 25);
            g2D.drawString("Player Bullets: "+player.alBullets.size(), 0, 40);
            g2D.drawString("Enemies: "+alEnemies.size(), 0, 55);
        }
    }
    
    @Override public void keyPressed(KeyEvent e){ setKeys.add(e.getKeyCode()); }
    @Override public void keyReleased(KeyEvent e){ setKeys.remove(new Integer(e.getKeyCode())); }
    @Override public void keyTyped(KeyEvent e){}
}