package w23_cis350_termproject;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	private ArrayList<Card> deck;

	public Deck() {
	    // Initialize deck of cards
		String[] suits = {"♠", "♥", "♦", "♣"};
		String[] ranks = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
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
	
	public String toString() {
		String deckString = "";
		for (Card card : deck) {
			deckString += card.toString() + " ";
		}
		return deckString;
	}
}
