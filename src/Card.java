public class Card {
  private String imgStr;
  private Suit suit;
  private Rank rank;

  public enum Suit {
    SPADES,
    HEARTS,
    CLUBS,
    DIAMONDS
  }

  public enum Rank {
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(11),
    QUEEN(12),
    KING(13),
    ACE(14);

    private final int value;

    Rank(int value){
      this.value = value;
    }

    public int getValue(){
      return value;
    }
  }

  public Card(Suit s, Rank r, String iStr) {
    suit = s;
    rank = r;
    imgStr = iStr;
  }

  public String getImgStr() {
    return imgStr;
  }

  public int getVal() {
    return rank.getValue();
  }

  public Suit getSuit() {
    return suit;
  }

  public Rank getRank() {
    return rank;
  }

  public String toString() {
    return rank + " of " + suit + " " + imgStr + " " + getVal();
  }
}
