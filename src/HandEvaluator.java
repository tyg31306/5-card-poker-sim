import java.util.*;

public class HandEvaluator{
  public static HandValue evaluate(List<Card> hand){

    int[] ranks = new int[5];
    Card.Suit[] suits = new Card.Suit[5];

    for(int i = 0; i < 5; i++){
      ranks[i] = hand.get(i).getVal();
      suits[i] = hand.get(i).getSuit();
    }
    Arrays.sort(ranks);

    Map<Integer, Integer> count = new HashMap<>();
    for(int r : ranks){
      count.put(r, count.getOrDefault(r, 0) + 1);
    }

    Collections.sort(hand, (c1, c2) -> { //sorting hand for cleaner display in gui
      int freq1 = count.get(c1.getRank().getValue());
      int freq2 = count.get(c2.getRank().getValue());

      if (freq1 != freq2) return freq2 - freq1; // higher frequency first
      return c2.getRank().getValue() - c1.getRank().getValue(); // then rank
    });

    boolean isFlush = true;
    for(int i = 1; i < 5; i++){
      if(suits[i] != suits[0]){
        isFlush = false;
        break;
       }
    }

    boolean isStraight = true;
    for(int i = 1; i < 5; i++){
      if(ranks[i] != ranks[i - 1] + 1){
        isStraight = false;
        break;
      }
    }

      //wheel straight
    if(!isStraight)
    {
      if(ranks[0] == 2 &&
          ranks[1] == 3 &&
          ranks[2] == 4 &&
          ranks[3] == 5 &&
          ranks[4] == 14) {
            isStraight = true;
            ranks[4] = 1;
            Arrays.sort(ranks);
          }
      }

    int pairs = 0;
    int trips = 0;
    int quads = 0;
    for(int c : count.values()){
      if(c == 2) pairs++;
      if(c == 3) trips++;
      if(c == 4) quads++;
    }

    //returning hand values and tiebreaker arrays 
    if(isStraight && isFlush){ //straight flush
      int[] tb = new int[1];
      tb[0] = ranks[4];
      return new HandValue(HandRank.STRAIGHT_FLUSH, tb);
    } else if(quads == 1){ //quads
      int[] tb = new int[2];
      for(int r : count.keySet()){
        if(count.get(r) == 4){
          tb[0] = r;
        } else if(count.get(r) == 1){
          tb[1] = r;
        }
      }
      return new HandValue(HandRank.QUADS, tb);
    } else if(trips == 1 && pairs == 1){ //full house
      int tb[] = new int[2];
      for(int r : count.keySet()){
        if(count.get(r) == 3){
          tb[0] = r;
        } else if(count.get(r) == 2){
          tb[1] = r;
        }
      }
      return new HandValue(HandRank.FULL_HOUSE, tb);
    } else if (isFlush){ //flush
      int tb[] = new int[1];
      tb[0] = ranks[4];
      return new HandValue(HandRank.FLUSH, tb);
    } else if(isStraight){ //straight
      int tb[] = new int[1];
      tb[0] = ranks[4];
      return new HandValue(HandRank.STRAIGHT, tb);
    } else if(trips == 1){ //trips
      int tb[] = new int[3];
      List<Integer> kickers = new ArrayList<>();
      for(int r : count.keySet()){
        if(count.get(r) == 3){
          tb[0] = r;
        } else if(count.get(r) == 1){
          kickers.add(r);
        }
      }
      Collections.sort(kickers, Collections.reverseOrder());
      tb[1] = kickers.get(0);
      tb[2] = kickers.get(1);
      return new HandValue(HandRank.TRIPS, tb);
    } else if(pairs == 2){ //two pair
      List<Integer> pairsList = new ArrayList<>();
      int kicker = 0;
      for(int r : count.keySet()){
        if(count.get(r) == 2){
          pairsList.add(r);
        } else if(count.get(r) == 1){
          kicker = r;
        }
      }
      Collections.sort(pairsList, Collections.reverseOrder());
      int[] tb = new int[3];
      tb[0] = pairsList.get(0);
      tb[1] = pairsList.get(1);
      tb[2] = kicker;
      return new HandValue(HandRank.TWO_PAIR, tb);
    } else if(pairs == 1){
      int tb[] = new int[4];
      List<Integer> kickers = new ArrayList<>();
      for(int r : count.keySet()){
        if(count.get(r) == 2){
          tb[0] = r;
        } else if(count.get(r) == 1){
          kickers.add(r);
        }
      }
      Collections.sort(kickers, Collections.reverseOrder());
      tb[1] = kickers.get(0);
      tb[2] = kickers.get(1);
      tb[3] = kickers.get(2);
      return new HandValue(HandRank.PAIR, tb);
    }
    // high card
    int[] tb = new int[5];
    for(int i = 0; i < 5; i++) tb[i] = ranks[4 - i];
    return new HandValue(HandRank.HIGH_CARD, tb);
  }
}