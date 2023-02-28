package game;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Holds array of cards and allows functionality of deck such
 * as shuffling and drawing.
 *
 * @author nenglish331 
 */
public class Deck {
  private ArrayList<Card> deck;

  /**
   * Initialize deck of cards with a suit of each rank.
   *
   * @see Card
   */
  public Deck() {
    String[] suits = {"♠", "♥", "♦", "♣"};
    String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    deck = new ArrayList<Card>();
    for (String suit : suits) {
      for (String rank : ranks) {
        deck.add(new Card(rank, suit));
      }
    }
  }

  public void shuffle() {
    // Shuffle deck of cards
    Collections.shuffle(deck);
  }

  public Card draw() {
    // Draw a card from the deck
    return deck.remove(0);
  }

  public int size() {
    // Get size of the remaining deck
    return deck.size();
  }

  /**
   * Returns string of all cards in the deck in the current order.
   *
   * @return string of all cards in the deck 
   */
  @Override
  public String toString() {
    String deckString = "";
    for (Card card : deck) {
      deckString += card.toString() + " ";
    }
    return deckString;
  }
}
