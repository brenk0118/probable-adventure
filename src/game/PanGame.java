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
import java.util.List;
import java.util.Set;
import javax.swing.JPanel;
import javax.swing.Timer;

import static game.Globals.DEBUG;
import static game.Globals.DEBUG_COLOR;

public class PanGame extends JPanel implements ActionListener, KeyListener{
    final int UPDATE_DELAY = 5;
    final int PLAYER_SIZE = 20;
    final int ENEMY_SIZE = 10;
    
    Player player = null;
    List<Enemy> alEnemies = null; //List of enemies
    Set<Integer> setKeys = null; //Set of key codes which are pressed
    boolean[] arbMouseButtons;
    
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
        
        Timer timer = new Timer(UPDATE_DELAY, this);
        timer.start();
    }
    
    @Override public void actionPerformed(ActionEvent e){
        player.update();
        for(Enemy enemy : alEnemies) enemy.update(); //Update enemies
        
        //if(System.currentTimeMillis() % 100 == 0){ //Sketchy af lmao
            alEnemies.add(new Enemy(nWidth / 2, nHeight / 2, this));
        //}
        
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
            g2D.drawString("Player pos: "+player.nX+", "+player.nY, 0, 10);
            g2D.drawString("Player Bullets: "+player.alBullets.size(), 0, 25);
            
            
            g2D.drawString("Enemies: "+alEnemies.size(), 0, 45);
        }
    }
    
    @Override public void keyPressed(KeyEvent e){ setKeys.add(e.getKeyCode()); }
    @Override public void keyReleased(KeyEvent e){ setKeys.remove(new Integer(e.getKeyCode())); }
    @Override public void keyTyped(KeyEvent e){}
}