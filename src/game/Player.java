package game;

/**
 * This class initializes the Player, giving them an empty Hand as well as 
 * storing the value of chips for betting.
 *
 * @author nenglish331
 * @see    Hand
 */
public class Player {
  private Hand hand;
  private int chips;

  /**
   * Initialize the Player, giving them a empty Hand and chips to start with.
   *
   * @param chips amount of chips to give the player initially 
   */
  public Player(int chips) {
    this.hand = new Hand();
    this.chips = chips;
    hand = new Hand();
  }

  public void hit(Deck deck) {
    // Draw a card from the deck
    hand.addCard(deck.draw());
  }

  public int getChips() {
    // Get number of chips player has
    return chips;
  }

  /**
   * Subtracts the bet amount from the current chips and empties Hand.
   *
   * @param amount the amount of chips to subtract from total
   */
  public void lose(int amount) {
    chips -= amount;
    hand = new Hand();
  }

  /**
   * Adds the bet amount to the current chips and empties Hand.
   *
   * @param amount the amount of chips to add to total
   */
  public void win(int amount) {
    chips += amount;
    hand = new Hand();
  }

  public int getScore() {
    // Get player's total score
    return hand.getScore();
  }

  public Hand getHand() {
    // Get player's hand
    return hand;
  }

  public int handSize() {
    // Get player's hand size
    return hand.getCards().size();
  }

  public String toString() {
    return "Chips: " + chips + ", Hand: " + hand.toString();
  }
}