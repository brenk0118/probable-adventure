package game;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PanLauncher extends JPanel{
    JTextArea txtUpdates;
    JButton  btnLaunch;
    
    PanLauncher(){
        //Update notes
        txtUpdates = new JTextArea();
        txtUpdates.setColumns(40);
        txtUpdates.setEditable(false);
        txtUpdates.setFocusable(false);
        txtUpdates.setText("Update Name [DD/MM/YYYY]\n"
                          +"- Did stuff\n"
                          +"- Did more stuff");
        
        btnLaunch = new JButton("Launch");
        btnLaunch.setFocusable(false);
        LaunchListener launchListener = new LaunchListener();
        btnLaunch.addActionListener(launchListener);
        
        super.setBorder(new EmptyBorder(6, 6, 6, 6));
        super.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        super.add(txtUpdates, gbc);
        
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        //Checkboxes for options
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        super.add(btnLaunch, gbc);
        
    }
    
    class LaunchListener implements ActionListener{
        @Override public void actionPerformed(ActionEvent event){
            Main.startGame(); //Kill launcher frame and start game
        }
    }
}
