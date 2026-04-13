import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class Poker
{
  private Deck deck;
  public ArrayList<Card> playerHand;
  private ArrayList<Card> cpuHand;
  private ArrayList<Card> table;
  private String winner;
  private String pH;
  private String cpuH;

  public Poker()
  {
    play();
  }
  
  public void play()
  {
    deck = new Deck();
    playerHand = new ArrayList<Card>();
    cpuHand = new ArrayList<Card>();
    table = new ArrayList<Card>();
    playerHand.add(deck.getRandomCard());
    playerHand.add(deck.getRandomCard());
    playerHand.add(deck.getRandomCard());
    playerHand.add(deck.getRandomCard());
    playerHand.add(deck.getRandomCard());
    cpuHand.add(deck.getRandomCard());
    cpuHand.add(deck.getRandomCard());
    cpuHand.add(deck.getRandomCard());
    cpuHand.add(deck.getRandomCard());
    cpuHand.add(deck.getRandomCard());
    getWinner();
    new ImageJFrame(cpuHand, playerHand, winner, pH, cpuH);
  }

  public String getWinner()
  {
    HandValue pVal = HandEvaluator.evaluate(playerHand);
    HandValue cpuVal = HandEvaluator.evaluate(cpuHand);
    pH = pVal.getRank().toDisplayString();
    cpuH = cpuVal.getRank().toDisplayString();
    int cmp = pVal.compareTo(cpuVal);
    if (cmp > 0) {
      winner = "Player";
    } else if (cmp < 0) {
      winner = "Dealer";
    } else {
      winner = "Tie";
    }
    System.out.println("Winner: " + winner + ": (" + pVal + " vs. " + cpuVal);
    return winner;
  }
}
