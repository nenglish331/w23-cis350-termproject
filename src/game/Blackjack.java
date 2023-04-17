package game;

import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Font;

public class Blackjack {

  ArrayList<Card> dealerHand; // dealer's hand
  ArrayList<Card> playerHand; // player's hand
  Deck deck; // deck of cards
  public boolean faceDown; // keeping track of whether the cards should be face down or face up
  public boolean dealerWon; // this is true if the dealer wins the round
  public volatile boolean endRound; // keeps track of if the round is over


  JFrame frame; // create JFrame
  GameComponent gComponent; // GameComponent for the game
  GameComponent cardComponent; // GameComponent for the cards

  // initializing the buttons on the game screen
  JButton betButton;
  JButton hitButton;
  JButton standButton;
  JButton doubleButton;
  JButton exitButton;

  /**
   * This constructor takes a JFrame as a parameter and initializes all the instance variables
   * @param f JFrame for new blackjack game
   */
  public Blackjack(JFrame f) {
    deck = new Deck(); // create Deck object
    deck.shuffleDeck(); // shuffle the deck
    dealerHand = new ArrayList<Card>(); // create dealer's hand
    playerHand = new ArrayList<Card>(); // create player's hand
    gComponent = new GameComponent(dealerHand, playerHand); // create new component to display the current status of the game
    frame = f; // initialize frame
    faceDown = true; // initialize the status of the cards
    dealerWon = true; // initialize the dealers status
    endRound = false; // initialize round status
  }

  /**
   * This method will create the background of the game screen
   */
  public void formGame() {

    frame.setTitle("Blackjack"); // title of the JFrame

    // set size and location of JFrame
    frame.setSize(1200, 700);
    frame.setLocationRelativeTo(null); // center the frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false); // player cannot change the size

    // Bet Button
    betButton = new JButton("Place Bet"); // initialize
    betButton.setBounds(50, 250, 150, 150); // set location and size
    betButton.setFont(new Font(Font.SERIF, Font.BOLD, 20));  // set font type and size

    // Hit Button
    hitButton = new JButton("HIT"); // initialize
    hitButton.setBounds(340, 300, 150, 50); // set location and size
    hitButton.setFont(new Font(Font.SERIF, Font.BOLD, 16));  // set font type and size

    // Stand Button
    standButton = new JButton("STAND"); // initialize
    standButton.setBounds(500, 300, 150, 50); // set location and size
    standButton.setFont(new Font(Font.SERIF, Font.BOLD, 16)); // set font type and size

    // Double Button
    doubleButton = new JButton("DOUBLE"); // initialize
    doubleButton.setBounds(660, 300, 150, 50); // set location and size
    doubleButton.setFont(new Font(Font.SERIF, Font.BOLD, 16)); // set font type and size

    // Exit Button
    exitButton = new JButton("EXIT"); // initialize
    exitButton.setBounds(1000, 550, 150, 100); // set location and size
    exitButton.setFont(new Font(Font.SERIF, Font.BOLD, 20)); // set font type and size

    // add all the buttons to the JFrame
    frame.add(betButton);
    frame.add(hitButton);
    frame.add(standButton);
    frame.add(doubleButton);
    frame.add(exitButton);

    // Action Listener for Bet Button
    betButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String[] options = new String[] {"5", "10", "25", "50", "100"}; // betting options for the player
        int selection = JOptionPane.showOptionDialog(null, "Enter amount to bet:", "BET", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        // if the first option is selected
        if(selection == 0) {
          GameComponent.currBet = 5; // set current bet to 5
          GameComponent.currBalance -= 5; // remove 5 from the current balance
        }
        // if the second option is selected
        else if(selection == 1) {
          GameComponent.currBet = 10; // set current bet to 10
          GameComponent.currBalance -= 10; // remove 10 from the current balance
        }
        // if the third option is selected
        else if(selection == 2) {
          GameComponent.currBet = 25; // set current bet to 25
          GameComponent.currBalance -= 25; // remove 25 from the current balance
        }
        // if the fourth option is selected
        else if(selection == 3) {
          GameComponent.currBet = 50; // set current bet to 50
          GameComponent.currBalance -= 50; // remove 50 from the current balance
        }
        // if the fifth option is selected
        else if(selection == 4) {
          GameComponent.currBet = 100; // set current bet to 100
          GameComponent.currBalance -= 100; // remove 100 from the current balance
        }
        // assign 5 as default
        else {
          GameComponent.currBet = 5; // set current bet to 5
          GameComponent.currBalance -= 5; // remove 100 from the current balance
          System.out.println("Default bet is 5");
        }
        Main.game.startGame(); // start game
      }
    });

    // Action Listener for Exit Button
    exitButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(frame, "You ended with " +  Main.currentBalance + " chips.");
        System.exit(0); // exit
      }
    });

    // creating the frame for the game
    gComponent = new GameComponent(dealerHand, playerHand); // initialize new game component with current values
    gComponent.setBounds(0, 0, 1200, 700);  // set size and location of component
    frame.add(gComponent); // add the game component to the frame
    frame.setVisible(true); // make visible
  }

  /**
   * Method to start the blackjack game by drawing the cards
   */
  public void startGame() {

    // draw 2 cards for the dealer's hand
    for(int i = 0; i<2; i++) {
      dealerHand.add(deck.getCard(i));
    }

    // draw 2 more cards for the player's hand
    for(int i = 2; i<4; i++) {
      playerHand.add(deck.getCard(i));
    }

    // remove the drawn cards from the deck
    for (int i = 0; i < 4; i++) {
      deck.removeCard(0);
    }

    // creating the frame for the cards
    cardComponent = new GameComponent(dealerHand, playerHand); // initialize the game component with the dealer and player hands
    cardComponent.setBounds(0, 0, 1200, 700); // set size and location of component
    frame.add(cardComponent); // add the card component to the frame
    frame.setVisible(true); // set visible

    checkHand(dealerHand); // check dealer's hand for a blackjack
    checkHand(playerHand); // check player's hand for a blackjack

    // Action Listener for Hit Button
    hitButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        addCard(playerHand); // add card to player's hand
        checkHand(playerHand); // check player's hand

        // if round is not over and the total value of dealer's hand is less than 17
        if (handSum(playerHand)<17 && handSum(dealerHand)<17){
          addCard(dealerHand); // add card to dealer's hand
          checkHand(dealerHand); // check dealer's hand
        }
      }
    });

    // Action Listener for Double Button
    doubleButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        // add 2 cards to the player's hand
        addCard(playerHand);
        addCard(playerHand);
        checkHand(playerHand); // check player's hand.

        // if round is not over and the total value of dealer's hand and player's hand is less than 17
        if (handSum(playerHand)<17 && handSum(dealerHand)<17){
          addCard(dealerHand); // add card to dealer's hand
          checkHand(dealerHand); // check dealer's hand
        }
      }
    });

    // Action Listener for Stand Button
    standButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        // while the total value of dealer's hand is less than 17
        while (handSum(dealerHand)<17) {
          addCard(dealerHand); // add a card to dealer's hand
          checkHand(dealerHand); // check dealer's hand
        }

        // if the player's and dealer's hands are each less than 21
        if ((handSum(dealerHand)<21) && handSum(playerHand)<21) {
          // check which hand is closer to 21
          if(handSum(playerHand) > handSum(dealerHand)) {
            faceDown = false; // set cards to face up
            dealerWon = false; // player wins
            JOptionPane.showMessageDialog(frame, "PLAYER WINS\nPlayer had a better hand");
            endRound = true; // round is ended
          }
          else {
            faceDown = false; // set cards to face up
            JOptionPane.showMessageDialog(frame, "DEALER WINS\nDealer had a better hand");
            endRound = true; // round is ended
          }
        }
      }
    });
  }

  /**
   * This method checks the given hand for a blackjack or a bust
   *
   * @param hand either the player or the dealer's current hand
   */
  public void checkHand (ArrayList<Card> hand) {

    // if the hand is the player's hand
    if (hand.equals(playerHand)) {
      // if the total value is 21 the player has a blackjack
      if(handSum(hand) == 21){
        faceDown = false; // set cards to face up
        dealerWon = false; // player wins
        JOptionPane.showMessageDialog(frame, "PLAYER WINS\nPlayer has a blackjack");
        endRound = true; // round is ended
      }
      // if the total value is greater than 21 it is a bust and the dealer wins
      else if (handSum(hand) > 21) {
        faceDown = false; JOptionPane.showMessageDialog(frame, "DEALER WINS\nPlayer busts");
        endRound = true; // round is ended
      }
    }
    // if the hand is the dealer's hand
    else {
      // if the total value is 21 the dealer has a blackjack
      if(handSum(hand) == 21) {
        faceDown = false; // set cards to face up
        JOptionPane.showMessageDialog(frame, "DEALER WINS\nDealer has a blackjack");
        endRound = true; // round ia ended
      }
      // if the total value is greater than 21 it is a bust and the player wins
      else if (handSum(hand) > 21) {
        faceDown = false; // set cards to face up
        dealerWon = false; // player wins
        JOptionPane.showMessageDialog(frame, "PLAYER WINS\nDealer busts");
        endRound = true; // round is ended
      }
    }
  }

  /**
   * This method adds a card to the hand
   *
   * @param hand the cards in either the player's or dealer's hand
   */
  public void addCard(ArrayList<Card> hand) {
    hand.add(deck.getCard(0)); // gets card from deck and adds it to the hand
    deck.removeCard(0); // remove card from the deck
    faceDown = true; // set face down to true
  }

  /**
   * This method checks for an ace in the hand
   *
   * @param hand the cards in either the player's or dealer's hand
   * @return a boolean that is true if an ace is found, false if it is not found
   */
  public boolean hasAce(ArrayList<Card> hand) {
    // for each card in the hand check if it has a value of 11
    for (Card card : hand) {
      if (card.getValue() == 11) {
        return true; // return true if an ace is found
      }
    }
    return false; // return false if an ace is not found
  }

  /**
   * This method finds the total number of aces in a hand
   *
   * @param hand the cards in either the player's or dealer's hand
   * @return an integer with the number of aces found in the hand
   */
  public int numAces(ArrayList<Card> hand){
    int count = 0; // initialize count to 0

    // loop through the cards in the hand
    for (Card card : hand) {
      // if the value of the card is 11 increase the ace count
      if (card.getValue() == 11) {
        count++;
      }
    }
    return count; // return total number of aces in the hand
  }

  /**
   * This method gets the value of the hand where the ace counts as an 11
   *
   * @param hand the player's or dealer's current hand
   * @return the total value of the hand
   */
  public int highAceSum(ArrayList<Card> hand) {
    int sum = 0; // set initial sum to 0

    // loop through the cards in the hand
    for (Card card : hand) {
      sum = sum + card.getValue(); // add to the sum
    }
    return sum;
  }

  /**
   * This method gets the total value of the hand
   *
   * @param hand the player's or dealer's current hand
   * @return the total value of the hand
   */
  public int handSum(ArrayList<Card> hand) {
    // if the hand contains an ace
    if(hasAce(hand)) {
      // if the sum of the hand is less than or equal to 21 with high aces get the sum
      if(highAceSum(hand) <= 21) {
        return highAceSum(hand);
      }
      // if the sum of the hand is greater than 21 with high aces
      else{
        // loop through the aces in the hand
        for (int i = 0; i < numAces(hand); i++) {
          int sum = highAceSum(hand)-(i+1)*10; // change the value of the ace from 11 to 1
          // if the sum is less than or equal to 21, return the sum
          if(sum <= 21) {
            return sum;
          }
        }
      }
    }
    // if the hand does not contain an ace find the regular sum
    else {
      int sum = 0; // initialize sum to 0

      // loop through each card in the hand
      for (Card card : hand) {
        sum = sum + card.getValue(); // add to the sum
      }
      return sum;
    }
    return 22; // set to bust if the sum is over 21
  }
}
