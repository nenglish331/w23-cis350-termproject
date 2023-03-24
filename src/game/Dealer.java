package game;

/**
 * Initializes the Dealer with an empty Hand to hold cards.
 *
 * @author nenglish331
 * @see    Hand
 */
public class Dealer {
  private Hand hand;

  public Dealer() {
    this.hand = new Hand();
  }

  /**
   * Player requests an extra card from the deck. A card is drawn
   * and added to the current hand
   *
   * @param deck  the current deck of cards
   */
  public void hit(Deck deck) {
    // Draw a card from the deck
    hand.addCard(deck.draw());
  }

  /**
   * Decides if the dealer should hit (draw a card from the deck)
   * based on the hand score
   *
   * @return  the decision of whether the dealer should hit
   */
  public boolean shouldHit() {
    // Decide if dealer should hit based on hand score
    return hand.getScore() < 17;
  }

  /**
   * The player's hand contains two cards.
   *
   * @return  the player's hand
   */
  public Hand getHand() {
    // Get player's hand
    return hand;
  }
  
  /**
   * The player's score is determined by the point total of the cards.
   * Cards 2 through 10 are worth their face value, and face cards
   * (jack, queen, king) are also worth 10.
   *
   * @return  the score based on the point total of the cards in the hand
   */
  public int getScore() {
    // Get player's total score
    return hand.getScore();
  }

  public String toString() {
    Card card = hand.getCards().get(0);
    return card.toString() + " XX Total: " + card.getValue() + " 2nd card hidden";
  }
}
