package w23_cis350_termproject;

public class Card {
    private String rank;
	private String suit;

	public Card(String rank, String suit) {
		this.rank = rank;
		this.suit = suit;
	}
	
	public int getValue() {
		// Get numerical value of card for scoring
		int value;
		switch(this.rank) {
			case "2": 
				value = 2;
		  		break;
		  	case "3": 
		  		value = 3; 
		  		break;
		  	case "4": 
		  		value = 4; 
		  		break;
		  	case "5": 
		  		value = 5; 
		  		break;
		  	case "6": 
		  		value = 6; 
		  		break;
		  	case "7": 
		  		value = 7; 
		  		break;
		  	case "8": 
		  		value = 8; 
		  		break;
		  	case "9": 
		  		value = 9; 
		  		break;
		  	case "10":
		  	case "J":
		  	case "Q":
		  	case "K": 
		  		value = 10; 
		  		break;
		  	case "A": 
		  		// Equals 1 or 11 based on current card total
		  		value = 11; 
		  		break;
		    default:
		  		value = 0;
		}
		return value;
	}

	public String toString() {
		// Return string representation of card
		return this.rank + this.suit;
	}
}
