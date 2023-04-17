package game;

import static org.junit.Assert.*;

import java.awt.Frame;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.junit.Assert;
import org.junit.Test;

public class BlackjackTest {
  @Test
  public void testConstructor() {
      JFrame frame = new JFrame();
      Blackjack game = new Blackjack(frame);
      game.formGame();
      game.startGame();
      assertNotNull(game.deck);
      assertNotNull(game.dealerHand);
      assertNotNull(game.playerHand);
      assertNotNull(game.gComponent);
      assertNotNull(game.cardComponent);
      assertNotNull(game.betButton);
      assertNotNull(game.hitButton);
      assertNotNull(game.standButton);
      assertNotNull(game.doubleButton);
      assertNotNull(game.exitButton);
      assertTrue(game.faceDown);
      assertTrue(game.dealerWon);
      assertFalse(game.endRound);
  }

  @Test
  public void testPlayButtonAction() {
      ActionComponent actionComponent = new ActionComponent();
      actionComponent.getPlayButton().doClick();
      Assert.assertEquals(Main.STATE.GAME, Main.currentState);
      Assert.assertTrue(Main.gameRefreshThread.isAlive());
      Assert.assertTrue(Main.gameCheckThread.isAlive());
  }
  
  @Test
  public void testFormGame() {
      JFrame frame = new JFrame();
      Blackjack game = new Blackjack(frame);
      game.formGame();
      game.startGame();
      assertEquals("Blackjack", frame.getTitle());
      assertEquals(1200, frame.getWidth());
      assertEquals(700, frame.getHeight());
      assertNotNull(game.betButton.getActionListeners());
      assertNotNull(game.hitButton.getActionListeners());
      assertNotNull(game.standButton.getActionListeners());
      assertNotNull(game.doubleButton.getActionListeners());
      assertNotNull(game.exitButton.getActionListeners());
  }

  @Test
  public void testHitButton() {
      JFrame frame = new JFrame();
      Blackjack game = new Blackjack(frame);
      game.formGame();
      game.startGame();
      ActionListener[] listeners = game.hitButton.getActionListeners();
      assertTrue(listeners.length > 0);
      listeners[0].actionPerformed(null);
      assertNotEquals(0, game.playerHand.size());
  }

  @Test
  public void testStandButton() {
      JFrame frame = new JFrame();
      Blackjack game = new Blackjack(frame);
      game.formGame();
      game.startGame();
      ActionListener[] listeners = game.standButton.getActionListeners();
      assertTrue(listeners.length > 0);
      listeners[0].actionPerformed(null);
      assertTrue(game.endRound);
  }

  @Test
  public void testDoubleButton() {
      JFrame frame = new JFrame();
      Blackjack game = new Blackjack(frame);
      game.formGame();
      game.startGame();
      ActionListener[] listeners = game.doubleButton.getActionListeners();
      assertTrue(listeners.length > 0);
      listeners[0].actionPerformed(null);
      assertNotEquals(2, game.playerHand.size());
  }


  @Test
  public void testCardGetters() {
      Card card = new Card(0, 1, 2);
      assertEquals(card.getSuit(), 0);
      assertEquals(card.getRank(), 1);
      assertEquals(card.getValue(), 2);
  }
  
  @Test
  public void testDeckShuffle() {
      Deck deck1 = new Deck();
      Deck deck2 = new Deck();

      // check that the two decks are in the same order
      assertEquals(deck1.getCard(0).getValue(), deck2.getCard(0).getValue());

      // shuffle one of the decks
      deck1.shuffleDeck();

      // check that the two decks are no longer in the same order
      assertNotEquals(deck1.getCard(0).getValue(), deck2.getCard(0).getValue());
  }

  @Test
  public void testDeckGetCard() {
      Deck deck = new Deck();

      // check that the first card in the deck has the correct suit, rank, and value
      Card card = deck.getCard(0);
      assertEquals(card.getSuit(), 0);
      assertEquals(card.getRank(), 0);
      assertEquals(card.getValue(), 11);

      // check that the last card in the deck has the correct suit, rank, and value
      card = deck.getCard(51);
      assertEquals(card.getSuit(), 3);
      assertEquals(card.getRank(), 12);
      assertEquals(card.getValue(), 10);
  }
  
  @Test
  public void testDealerHandInitial() {
      Blackjack blackjack = new Blackjack(new JFrame());
      assertTrue(blackjack.dealerHand.isEmpty());
  }

  // Test that the player's hand is initially empty
  @Test
  public void testPlayerHandInitial() {
      Blackjack blackjack = new Blackjack(new JFrame());
      assertTrue(blackjack.playerHand.isEmpty());
  }

  @Test
  public void testBetButton() {
      Blackjack blackjack = new Blackjack(new JFrame());
      blackjack.formGame();
      blackjack.startGame();
      GameComponent.currBalance = 100;
      blackjack.betButton.doClick();
      assertEquals(5, GameComponent.currBet);

      blackjack.betButton.doClick();
      while (GameComponent.currBet != 5) {
        assertEquals(10, GameComponent.currBet);
      }

      blackjack.betButton.doClick();
      while (GameComponent.currBet != 10) {
        assertEquals(25, GameComponent.currBet);
      }

      blackjack.betButton.doClick();
      while (GameComponent.currBet != 25) {
        assertEquals(50, GameComponent.currBet);
      }
        
      blackjack.betButton.doClick();
      while (GameComponent.currBet != 50) {
        assertEquals(100, GameComponent.currBet);
      }
  }
}
