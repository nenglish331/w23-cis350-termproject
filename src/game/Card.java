package game;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Graphics2D;

public class Card {
  private int suit; // suit of the card
  private int rank; // rank of the card
  private int value; // value of the card between 1 and 11
  private int xPos; // x position of the card
  private int yPos; // y position of the card

  /**
   * This method is a constructor that initializes the suit, rank, and value
   * @param suit the suit of the card
   * @param rank the rank of the card
   * @param value the value of the card
   */
  public Card(int suit, int rank, int value) {
    this.suit = suit;
    this.rank = rank;
    this.value = value;
  }

  /**
   * Getter for the card suit
   *
   * @return the suit of the card
   */
  public int getSuit() {
    return suit;
  }

  /**
   * Getter for the card rank
   *
   * @return the rank of the card
   */
  public int getRank() {
    return rank;
  }

  /**
   * Getter for the card value
   *
   * @return the value of the card
   */
  public int getValue() {
    return value;
  }

  /**
   * This method draws the card to the screen
   * @param g2D brush to draw the images
   * @param dealerTurn tells if it is the dealer's turn
   * @param faceDown tells fi the card should be face down or face up
   * @param cardNum the card to be drawn
   * @throws IOException since we are reading images from a file
   */
  public void printCard(Graphics2D g2D, boolean dealerTurn, boolean faceDown, int cardNum) throws IOException {

    BufferedImage deckImg = ImageIO.read(new File("cardSpriteSheet.png")); // read the image
    int imgWidth = 950; // width of the image in pixels
    int imgHeight = 392; // height of the image in pixels

    BufferedImage[][] cardImg = new BufferedImage[4][13]; // create 2D array to store images
    BufferedImage cardBack = ImageIO.read(new File("backsideOfACard.jpg")); // shows the back of the card

    // assigns the relative card images to the array
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 13; j++) {
        cardImg[i][j] = deckImg.getSubimage(j*imgWidth/13, i*imgHeight/4, imgWidth/13, imgHeight/4);
      }
    }

    // if it is the dealers turn the card prints at the top of the screen
    if (dealerTurn) {
      yPos = 100;
    }
    // if it is the players turn the card prints at the bottom of the screen
    else {
      yPos = 425;
    }

    xPos = 500 + 75*cardNum; // shift the x position to draw the cards next to each other

    // if the cards are face down
    if (faceDown) {
      g2D.drawImage(cardBack, xPos, yPos, null ); // display the back of the card
    }
    // if the cards are face up
    else {
      g2D.drawImage(cardImg[suit][rank], xPos, yPos, null); // display the cards from the array
    }
  }
}