import java.util.Collections;
import java.util.ArrayList;

public class Deck
{
  private ArrayList<Card> deck;
  public Deck()
  {
    setup();
  }
  public void setup()
  {
    deck = new ArrayList<Card>();
    for(int i = 0;i < 4;i++)
    {
      for(int k = 0;k < 13;k++)
      {
        Card newCard = new Card(Card.Suit.values()[i], Card.Rank.values()[k], "assets/cards/" + Card.Suit.values()[i].name().toLowerCase() + "_" + (k + 2) + ".png");
        deck.add(newCard);
      }
    }
  }
  public Card getRandomCard()
  {
     return deck.remove((int)(Math.random() * deck.size()));
  }
}