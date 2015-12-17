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

public class PanGame extends JPanel implements ActionListener, KeyListener{
    final int UPDATE_DELAY = 5; //16.66... results in 60fps
    final int PLAYER_SIZE = 20;
    final int ENEMY_SIZE = 10;
    
    Player player;
    List<Enemy> alEnemies; //List of enemies
    Set<Integer> setKeys; //Set of key codes which are pressed
    
    int nWidth, nHeight;
    
    PanGame(int nGameWidth, int nGameHeight){
        nWidth = nGameWidth;
        nHeight = nGameHeight;
        
        player = new Player(0, 0, new Color(240, 240, 240), this);
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
        for(Enemy enemy : alEnemies) enemy.update();
        super.repaint(); //Calls paintComponent
    }
    
    @Override public void paintComponent(Graphics g){
        Graphics2D g2D = (Graphics2D)g;
        g2D.setColor(Color.black);
        g2D.fillRect(0, 0, nWidth, nHeight);
        
        player.draw(g2D);
        for(Enemy enemy : alEnemies) enemy.draw(g2D);
    }
    
    @Override public void keyPressed(KeyEvent e){ setKeys.add(e.getKeyCode()); }
    @Override public void keyReleased(KeyEvent e){ setKeys.remove(new Integer(e.getKeyCode())); }
    //Make this an object or else it defaults to index            ^~~~~~~~~~~~~~~~~~~~~~~~~~~
    @Override public void keyTyped(KeyEvent e){}
}