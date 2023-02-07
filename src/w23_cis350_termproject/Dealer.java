package w23_cis350_termproject;

public class Dealer {
	private Hand hand;

	public Dealer() {
	   this.hand = new Hand();
	}

	public void hit(Deck deck) {
	   // Draw a card from the deck
	}

	public int getScore() {
	   // Get total score of hand
	}

	public boolean shouldHit() {
		// Decide if dealer should hit based on hand score
	}
}
