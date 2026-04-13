public enum HandRank {
  HIGH_CARD,
  PAIR,
  TWO_PAIR,
  TRIPS,
  STRAIGHT,
  FLUSH,
  FULL_HOUSE,
  QUADS, 
  STRAIGHT_FLUSH;

  public String toDisplayString() {
    switch (this) {
        case HIGH_CARD: return "High Card";
        case PAIR: return "Pair";
        case TWO_PAIR: return "Two Pair";
        case TRIPS: return "Trips";
        case STRAIGHT: return "Straight";
        case FLUSH: return "Flush";
        case FULL_HOUSE: return "Full House";
        case QUADS: return "Quads";
        case STRAIGHT_FLUSH: return "Straight Flush";
        default: return this.name();
    }
  }
}