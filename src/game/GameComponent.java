package game;

import javax.swing.JComponent;
import java.util.ArrayList;
import java.awt.*;
import java.io.*;

/**
 * This class repaints the component with the current scores, balance, and cards
 *
 * @author Sarah Wight, Nathan Englilsh, Xander Hall
 */
public class GameComponent extends JComponent {

    private ArrayList<Card> dHand; // dealer's hand
    private ArrayList<Card> pHand; // player's hand
    private int dScore; // dealer's score
    private int pScore; // player's score
    public boolean faceDown = true; // keeping track of whether the cards should be face down or face up
    public static int currBalance; // player's current balance
    public static int currBet; // player's current bet

    /**
     * This constructor takes the dealer's hand and player's hand as parameters.
     *
     * @param dealerHand the dealer's current hand
     * @param playerHand the player's current hand
     */
    public GameComponent(ArrayList<Card> dealerHand, ArrayList<Card> playerHand) {
        dHand = dealerHand; // updating the dealer's hand
        pHand = playerHand; // updating the player's hand
        dScore = Main.dealerScore; // updating the dealer's score
        pScore = Main.playerScore; // updating the player's score
        currBalance = Main.currentBalance; // updating the current balance
    }

    /**
     * This method calls the paint method in order to display the game screen
     *
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g; // cast Graphics to Graphics2D to access more methods

        // set font color, type, and size
        g2D.setColor(Color.DARK_GRAY);
        g2D.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));

        // display dealer score at the top of the screen
        g2D.drawString("DEALER: ", 475, 50);
        g2D.drawString(Integer.toString(dScore), 625, 50);

        // display player score at the bottom of the screen
        g2D.drawString("PLAYER: ", 475, 600);
        g2D.drawString(Integer.toString(pScore), 625, 600);

        // display the current balance on the top left of the screen
        g2D.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        g2D.drawString("CURRENT BALANCE: " + currBalance, 50, 50);

        // printing out the dealer's cards
        try {
            // for each card in the dealer's hand
            for (int i = 0; i < dHand.size(); i++) {
                // if it is the first card
                if (i == 0) {
                    // check if the card is face down and draw each card
                    dHand.get(i).printCard(g2D, true, faceDown, i);
                }
                else {
                    // if not the first card set face down to false and draw each card
                    dHand.get(i).printCard(g2D, true, false, i);
                }
            }
        }
        catch (IOException e) {

        }

        // printing out the player's cards
        try {
            // for each card in the player's hand
            for (int i = 0; i < pHand.size(); i++) {
                // draw each card
                pHand.get(i).printCard(g2D, false, false, i);
            }
        }
        catch (IOException e) {

        }
    }

    /**
     * This method refreshes the GameComponent with the current values
     *
     * @param currentBalance player's current balance
     * @param playerScore player's score
     * @param dealerScore dealer's score
     * @param fDown tells whether the card is face down or face up
     */
    public void refresh(int currentBalance, int playerScore, int dealerScore, boolean fDown) {
        currBalance = currentBalance; // update current balance
        pScore = playerScore; // update player's score
        dScore = dealerScore; // update dealer's score
        faceDown = fDown; // update status of cards
        this.repaint(); // repaint the component with updated values
    }
}
