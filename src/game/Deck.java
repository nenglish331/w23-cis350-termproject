package game;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class holds the deck of cards
 *
 * @author Sarah Wight, Nathan English, Xander Hall
 */
public class Deck {

  private ArrayList<Card> deck; // we will store the cards here

  /**
   * This method is a constructor for the deck of cards
   */
  public Deck() {
    deck = new ArrayList<>(); // make new deck

    // go through all 52 cards and add them to the deck, i represents the suit, j represents the value
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 13; j++) {
        // if j is 0, the card is an ace and will get value of 11
        if (j == 0) {
          Card card = new Card(i, j, 11); // create the card with the suit and value
          deck.add(card); // add card to deck
        }
        // if j is greater than or equal to 10, it is a face card
        else if (j >= 10) {
          Card card = new Card(i, j, 10); // create the card with the suit and value
          deck.add(card); // add card to deck
        }
        // for all other cards
        else {
          Card card = new Card(i, j, j+1); // create card with suit and value
          deck.add(card); // add card to deck
        }
      }
    }
  }

  /**
   * This method shuffles the deck of cards
   */
  public void shuffleDeck() {
    Collections.shuffle(deck); // shuffle
  }

  /**
   * Gets a card in the deck
   *
   * @param i index of the card
   * @return the index of the card
   */
  public Card getCard(int i) {
    return deck.get(i);
  }

  /**
   * Removes a card from the deck
   *
   * @param i index of card to remove
   */
  public void removeCard(int i) { //This method removes the ith (index) card of the deck.
    deck.remove(i);
  }
}
