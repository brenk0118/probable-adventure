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
        
        //Using grids to make an interface pretty is easier in JavaFX due to the
        //grid.setHgap and grid.setVgap to add gaps between components.
        //The problem with it is you have to relinquish more control over all
        //the elements by doing this.
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets.bottom = 6; //Add margin between components
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        super.add(txtUpdates, gbc);
        
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        //TODO: Settings
        
        gbc.insets.bottom = 0; //Reset bottom margin for final component
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
