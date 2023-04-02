package game;

import java.util.Scanner;

/**
 * Contains the initialization and main game loop for the game of Blackjack.
 *
 * @author nenglish331, sarahwight14, xanderhall11
 */
public class Blackjack {
  private Deck deck;
  private Player player;
  private Dealer dealer;

  // ADD JFrame, JButtons

  // ADD or MODIFY Constructor
  public Blackjack(int playerChips) {
    player = new Player(playerChips);
  }

  // ADD Method to create game GUI


  // MODIFY to fit GUI
  /**
   * Main loop of the game. Will continue running until the Player is at 0 chips
   * or the Player chooses to quit.
   */
  public void play() {
    Scanner scan = new Scanner(System.in);
    int betAmount = 0;
    System.out.println("You have " + player.getChips() + " chips.");

    while (true) {
      // Start a new round
      System.out.print("Enter bet amount: ");
      betAmount = scan.nextInt();
      if (betAmount > player.getChips()) {
        System.out.println("Not enough chips, enter a lower amount.");
        continue;
      }
      if (betAmount < 5) {
        System.out.println("The minimum amount to bet is $5, enter a higher amount.");
        continue;
      }
      
      // Deal cards
      dealer = new Dealer();
      deck = new Deck();
      deck.shuffle();
      player.hit(deck);
      dealer.hit(deck);
      player.hit(deck);
      dealer.hit(deck);
      
      System.out.println("Your hand: " + player.getHand().toString());
      System.out.println("Dealer's hand: " + dealer.toString());

      // Player's turn
      while (true) {
        System.out.print("Hit or stand? (h/s): ");
        String input = scan.next();
        if (input.equals("h")) {
          player.hit(deck);
          System.out.println("Your hand: " + player.getHand().toString());
          if (player.getScore() > 21) {
            break;
          }
        } else {
          break;
        }
      }
      
      // Dealer's turn
      System.out.println("Dealer's hand: " + dealer.getHand().toString());
      while (dealer.shouldHit()) {
        System.out.println("Dealer hits.");
        dealer.hit(deck);
        System.out.println("Dealer's hand: " + dealer.getHand().toString());
      }

      int playerScore = player.getScore();
      int dealerScore = dealer.getScore();

      // Determine winner
      if (playerScore > 21) {
        System.out.println("Player busts, dealer wins.");
        player.lose(betAmount);
      } else if (dealerScore == playerScore) {
        System.out.println("Push. Bets returned");
        player.win(0);
      } else if (playerScore == 21 && player.handSize() == 2) {
        System.out.println("Blackjack! Player wins 3 to 2.");
        player.win((int) (betAmount * 0.5));
      } else if (dealerScore > 21) {
        System.out.println("Dealer busts, player wins.");
        player.win(betAmount);
      } else if (playerScore > dealerScore) {
        System.out.println("Player wins.");
        player.win(betAmount);
      } else {
        System.out.println("Dealer wins.");
        player.lose(betAmount);
      }
      
      // Check if player still has chips
      if (player.getChips() == 0) {
        System.out.println("You have no more chips, game over.");
        break;
      }
      
      // Ask if player wants to play again
      System.out.println("You have " + player.getChips() + " chips.");
      System.out.print("Play again? (y/n): ");
      String playAgain = scan.next();
      if (playAgain.equals("n")) {
        break;
      }
    }
    scan.close();
  }

  public static void main(String[] args) {
    Blackjack blackjackGame = new Blackjack(100); // Start with 100 chips
    blackjackGame.play();
  }
}
