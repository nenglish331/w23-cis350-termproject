package w23_cis350_termproject;

public class Player {
	private Hand hand;
	private int chips;

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

	public void lose(int amount) {
		// Bet a certain amount of chips
		chips -= amount;
	    hand = new Hand();
	}

	public void win(int amount) {
		// Player wins an amount of chips
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