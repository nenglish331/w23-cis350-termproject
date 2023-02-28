package game;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Contains the graphical components of the game as well as the event
 * listeners to give the buttons function.
 *
 * @author nenglish331
 */
public class BlackjackGUI extends JFrame {
  private Player player;
  private Dealer dealer;
  private Deck deck;

  private JPanel mainPanel;
  private JLabel playerScoreLabel;
  private JLabel dealerScoreLabel;
  private JButton hitButton;
  private JButton stayButton;
  private JButton newGameButton;

  public BlackjackGUI() {
    // Initialize components and set up GUI
  }

  private void initComponents() {
    // Create and initialize components
  }

  private void setupLayout() {
    // Arrange components in the GUI
  }

  private void hitButtonActionPerformed(ActionEvent evt) {
    // Player hits
  }

  private void stayButtonActionPerformed(ActionEvent evt) {
    // Player stays
  }

  private void newGameButtonActionPerformed(ActionEvent evt) {
    // Start a new game
  }

  public static void main(String[] args) {
    // Run the GUI
  }
}
