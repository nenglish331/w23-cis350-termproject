package game;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

/**
 * This class displays buttons on the main page and waits for an action from the user
 *
 * @author Sarah Wight, Nathan English, Xander Hall
 */
public class ActionComponent extends JComponent implements ActionListener{

    // initialize the buttons on the main screen
    private JButton playButton = new JButton("PLAY");
    private JButton exitButton = new JButton("EXIT");
    private JButton helpButton = new JButton("HELP");

    /**
     * This constructor adds action listeners to each of the buttons
     */
    public ActionComponent() {
        getPlayButton().addActionListener(this);
        exitButton.addActionListener(this);
        getHelpButton().addActionListener(this);
    }

    /**
     * This method calls the paint method in order to display the main page
     *
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g; // cast Graphics to Graphics2D to access more methods

        // // set font color, type, and size
        g2D.setColor(Color.DARK_GRAY);
        g2D.setFont(new Font(Font.SERIF, Font.BOLD, 75));

        // display title at the top of the main screen
        g2D.drawString("Blackjack", 400, 100);

        // change font size to display course and semester information below title
        g2D.setFont(new Font(Font.SERIF, Font.BOLD, 50));
        g2D.drawString("CIS 350 - Project Release 2", 275, 180);

        // change font size to display group member names at the bottom of the main screen
        g2D.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        g2D.drawString("By: Sarah Wight, Nathan English, Xander Hall", 250, 580);

        // set the size and location of buttons
        getPlayButton().setBounds(300, 300, 150, 80);
        exitButton.setBounds(475, 300, 150, 80);
        getHelpButton().setBounds(650, 300, 150, 80);

        // set the font type and size of buttons
        getPlayButton().setFont(new Font(Font.SERIF, Font.BOLD, 40));
        exitButton.setFont(new Font(Font.SERIF, Font.BOLD, 40));
        getHelpButton().setFont(new Font(Font.SERIF, Font.BOLD, 40));

        // add the buttons to the component
        super.add(getPlayButton());
        super.add(exitButton);
        super.add(getHelpButton());
    }

    /**
     * This method controls what the buttons do when there is an action performed
     *
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e) {
        JButton selectedButton = (JButton)e.getSource(); // assign the clicked button to selectedButton


        // if the play button was selected, start the game
        if(selectedButton == getPlayButton()) {
            Main.currentState = Main.STATE.GAME; // jump from main menu to game state
            Main.menuFrame.dispose(); // dispose of the main menu frame
            Main.gameRefreshThread.start(); // start thread to refresh game
            Main.gameCheckThread.start(); // start thread to check if the round is over
        }
        // else if the exit button was selected, exit the program
        else if(selectedButton == exitButton) {
            System.exit(0);
        }
        // else if help button was selected, display information and rules of blackjack
        else if(selectedButton == getHelpButton()) {
            JOptionPane.showMessageDialog(this, "show rules here", "Rules",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public JButton getHelpButton() {
      return helpButton;
    }

    public JButton getPlayButton() {
      return playButton;
    }
}
