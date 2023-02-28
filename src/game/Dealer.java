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

  public void hit(Deck deck) {
    // Draw a card from the deck
    hand.addCard(deck.draw());
  }

  public boolean shouldHit() {
    // Decide if dealer should hit based on hand score
    return hand.getScore() < 17;
  }

  public Hand getHand() {
    // Get player's hand
    return hand;
  }

  public int getScore() {
    // Get player's total score
    return hand.getScore();
  }

  public String toString() {
    Card card = hand.getCards().get(0);
    return card.toString() + " XX Total: " + card.getValue() + " 2nd card hidden";
  }
}
