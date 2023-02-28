package game;

import java.util.ArrayList;

/**
 * Initializes the Hand with an empty array of Cards and integer score to
 * track the numerical score for the game.
 *
 * @author nenglish331
 * @see    Card
 */
public class Hand {
  private ArrayList<Card> hand;
  private int score;

  /**
   * Initializes an empty array of Cards.
   *
   * @see Card
   */
  public Hand() {
    // Initialize an empty hand
    hand = new ArrayList<Card>();
    score = 0;
  }

  /**
   * Adds a card to this hand and updates the hand score.
   *
   * @param card the card to be added to the hand
   */
  public void addCard(Card card) {
    // Add a card to the hand
    hand.add(card);
    score += card.getValue();
  }

  public int getScore() {
    // Get total score of hand
    return score;
  }

  public ArrayList<Card> getCards() {
    // Returns ArrayList of cards in hand
    return hand;
  }

  public boolean isBusted() {
    // Check if hand is over 21
    return score > 21;
  }

  
  /**
   * Creates and returns string of the cards in the current hand 
   * and the total score.
   *
   * @return string of cards in hand and total score
   */
  @Override
  public String toString() {
    String handString = "";
    for (Card card : hand) {
      handString += card.toString() + " ";
    }
    return handString + " Total: " + score;
  }
}
