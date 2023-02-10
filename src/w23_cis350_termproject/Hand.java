package w23_cis350_termproject;
import java.util.ArrayList;

public class Hand {
	private ArrayList<Card> hand;
	private int score;

	public Hand() {
	    // Initialize an empty hand
	    hand = new ArrayList<Card>();
	    score = 0;
	}

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
	
	public String toString() {
		String handString = "";
		for (Card card : hand) {
			handString += card.toString() + " ";
		}
		return handString + " Total: " + score;
	}

}