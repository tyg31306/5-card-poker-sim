import java.util.Arrays;

public class HandValue implements Comparable<HandValue> {
  private HandRank rank;
  private int[] tiebreak;

  public HandValue(HandRank rank, int[] tiebreak){
    this.rank = rank;
    this.tiebreak = tiebreak;
  }

  @Override
  public int compareTo(HandValue other){
    if(this.rank != other.rank){
      return this.rank.ordinal() - other.rank.ordinal();
    }

    for(int i = 0; i < tiebreak.length; i++){
      if(this.tiebreak[i] != other.tiebreak[i]){
        return this.tiebreak[i] - other.tiebreak[i];
      }
    }
    return 0;
  }

  public HandRank getRank(){
    return rank;
  }
  public String toString(){
    return (rank + " with " + Arrays.toString(tiebreak));
  }
}