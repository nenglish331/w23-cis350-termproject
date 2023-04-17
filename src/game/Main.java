package game;

import javax.swing.JFrame;

/**
 * This class maintains the current state of the game
 *
 * @author Sarah Wight, Nathan English, Xander Hall
 */
public class Main {

    public static JFrame menuFrame = new JFrame(); // frame shown when player opens game
    public static JFrame gameFrame = new JFrame(); // frame shown when player is playing game

    // initialize scores and current balance
    public static int playerScore = 0;
    public static int dealerScore = 0;
    public static int currentBalance = 100; // player starts with 100 chips

    public static Blackjack game = new Blackjack(gameFrame); // initialize a new blackjack game
    private static boolean newGame = true; // checks if it is a new game

    // holds the state of the game
    public enum STATE{
        MENU,
        GAME
    };

    public static STATE currentState = STATE.MENU; // sets state to menu

    /**
     * The main method opens the main menu when the program is run
     */
    public static void main(String[] args) {
        if(currentState == STATE.MENU) {
            displayMenu(); // display main page
        }
    }

    /**
     * This method displays the main menu page when the program is run
     */
    public static void displayMenu() {
        menuFrame.setTitle("Blackjack"); // set title
        menuFrame.setSize(1200, 700); // set size of menu frame
        menuFrame.setLocationRelativeTo(null); // center the frame
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setResizable(false); // frame cannot be resized

        ActionComponent startComponent = new ActionComponent(); // initialize an ActionComponent object to show buttons and title
        menuFrame.add(startComponent); // add the component to the frame
        menuFrame.setVisible(true); // make it visible
    }

    /**
     * This thread constantly refreshes the component
     */
    public static Thread gameRefreshThread = new Thread(() -> {
        while(true){
            // update the current balance, player and dealer score
            game.gComponent.refresh(currentBalance, playerScore, dealerScore-1, game.faceDown);
        }
    });

    /**
     * This thread constantly checks is the round has ended
     */
    public static Thread gameCheckThread = new Thread(() -> {
        while(true) {
            // if it is a new game or the round is ended
            if (newGame || game.endRound) {
                // if the dealer won the round
                if (game.dealerWon){
                    dealerScore++; // increment dealer score
                    currentBalance -= GameComponent.currBet; // remove the bet amount from the balance
                }
                // if the player won the round
                else {
                    playerScore++; // increment player score
                    currentBalance += GameComponent.currBet *2; // add 2x the bet amount to the balance
                }
                gameFrame.getContentPane().removeAll(); // remove everything from frame
                game = new Blackjack(gameFrame); // initialize new game
                game.formGame(); // form new game on the same frame
                newGame = false; // it is not a new game anymore
            }
        }
    });
}
