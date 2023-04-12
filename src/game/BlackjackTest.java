package game;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Contains the tests for the Blackjack game and related classes.
 *
 * @author nenglish331
 */
public class BlackjackTest {
  @Test
  public void testDeckConstructor() {
    Deck deck = new Deck();
    assertEquals(52, deck.size());
  }
  
  @Test
  public void testShuffle() {
    Deck deck = new Deck();
    Deck originalDeck = new Deck();
    deck.shuffle();
    assertNotEquals(originalDeck.toString(), deck.toString());
  }
  
  @Test
  public void testDraw() {
    Deck deck = new Deck();
    Card card = deck.draw();
    assertEquals(51, deck.size());
  }
  
  @Test
  public void testAddCard() {
    Hand hand = new Hand();
    Card card = new Card("A", "♠");
    hand.addCard(card);
    assertEquals(1, hand.getCards().size());
  }
  
  @Test
  public void testGetScore() {
    Hand hand = new Hand();
    Card card = new Card("A", "♠");
    hand.addCard(card);
    assertEquals(11, hand.getScore());
  }
  
  @Test
  public void testGetCards() {
    Hand hand = new Hand();
    Card card = new Card("A", "♠");
    hand.addCard(card);
    assertEquals(card, hand.getCards().get(0));
  }
  
  @Test
  public void testIsBusted() {
    Hand hand = new Hand();
    Card card1 = new Card("J", "♠");
    Card card2 = new Card("Q", "♠");
    Card card3 = new Card("K", "♠");
    hand.addCard(card1);
    hand.addCard(card2);
    hand.addCard(card3);
    assertTrue(hand.isBusted());
  }
  
  @Test
  public void testHandToString() {
    Hand hand = new Hand();
    Card card1 = new Card("J", "♠");
    Card card2 = new Card("Q", "♠");
    hand.addCard(card1);
    hand.addCard(card2);
    String expectedOutput = card1.toString() + " " + card2.toString() + "  Total: 20";
    assertEquals(expectedOutput.trim(), hand.toString().trim());
  }
  
  @Test
  public void testPlayerConstructor() {
    int chips = 100;
    Player player = new Player(chips);
    assertEquals(chips, player.getChips());
    assertEquals(0, player.handSize());
  }

  @Test
  public void testPlayerHit() {
    Deck deck = new Deck();
    int chips = 100;
    Player player = new Player(chips);
    int initialHandSize = player.handSize();
    player.hit(deck);
    assertEquals(initialHandSize + 1, player.handSize());
    assertFalse(player.getHand().getCards().isEmpty());
  }

  @Test
  public void testWin() {
    int chips = 100;
    int amount = 50;
    Player player = new Player(chips);
    int initialChips = player.getChips();
    player.win(amount);
    assertEquals(initialChips + amount, player.getChips());
  }

  @Test
  public void testLose() {
    int chips = 100;
    int amount = 50;
    Player player = new Player(chips);
    int initialChips = player.getChips();
    player.lose(amount);
    assertEquals(initialChips - amount, player.getChips());
  }

  @Test
  public void testDealerConstructor() {
    Dealer dealer = new Dealer();
    assertEquals(0, dealer.getHand().getCards().size());
  }
  
  @Test
  public void testDealerHit() {
    Deck deck = new Deck();
    Dealer dealer = new Dealer();
    int initialHandSize = dealer.getHand().getCards().size();
    dealer.hit(deck);
    assertEquals(initialHandSize + 1, dealer.getHand().getCards().size());
    assertFalse(dealer.getHand().getCards().isEmpty());
  }
  
  @Test
  public void testShouldHit() {
    Deck deck = new Deck();
    deck.shuffle();

    Dealer dealer = new Dealer();
    Hand hand = dealer.getHand();
    hand.addCard(new Card("10", "♥"));
    hand.addCard(new Card("5", "♣"));

    assertTrue(dealer.shouldHit());
    
    hand.addCard(new Card("A", "♣"));

    assertFalse(dealer.shouldHit());
  }

  @Test
  public void testGetValueAllNumbers() {
    for(int i = 2; i <= 10; i++) {
      Card card = new Card(Integer.toString(i), "♥");
      assertEquals(i, card.getValue());
    }
    Card card = new Card("1", "♥");
    assertEquals(0, card.getValue());
  }

  @Test
  public void testGetValueJack() {
    Card card = new Card("J", "♠");
    assertEquals(10, card.getValue());
  }

  @Test
  public void testGetValueQueen() {
    Card card = new Card("Q", "♣");
    assertEquals(10, card.getValue());
  }

  @Test
  public void testGetValueKing() {
    Card card = new Card("K", "♦");
    assertEquals(10, card.getValue());
  }

  @Test
  public void testGetValueAce() {
    Card card = new Card("A", "♠");
    assertEquals(11, card.getValue());
  }

  @Test
  public void testCardToString() {
    Card card = new Card("5", "♣");
    assertEquals("5♣", card.toString());
  }
  
  @Test
  public void testPlayerGetScore() {
    Player player = new Player(100);
    Hand hand = player.getHand();
    hand.addCard(new Card("2", "♦"));
    hand.addCard(new Card("4", "♠"));
    hand.addCard(new Card("K", "♥"));
    assertEquals(16, player.getScore());
    
    hand.addCard(new Card("5", "♣"));
    assertEquals(21, player.getScore());
  }

  @Test
  public void testDealerGetScore() {
    Dealer dealer = new Dealer();
    Hand hand = dealer.getHand();
    hand.addCard(new Card("2", "D"));
    hand.addCard(new Card("4", "S"));
    hand.addCard(new Card("K", "H"));
    assertEquals(16, dealer.getScore());
  }
  
  @Test
  public void testPlayerToString() {
    Player player = new Player(100);
    Deck deck = new Deck();
    deck.shuffle();
    player.hit(deck);
    player.hit(deck);
    String expected = "Chips: 100, Hand: " + player.getHand().toString();
    assertEquals(expected, player.toString());
  }

  @Test
  public void testDealerToString() {
      Dealer dealer = new Dealer();
      Card card1 = new Card("9", "♠");
      Card card2 = new Card("J", "♣");
      dealer.getHand().addCard(card1);
      dealer.getHand().addCard(card2);
      assertEquals("9♠ XX Total: 9 2nd card hidden", dealer.toString());
  }
}
