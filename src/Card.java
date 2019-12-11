public class Card {
    
    private String rank,
                   suit;
  
    private int rankNumber,
             suitNumber;


    public Card(){

    } 
     

    public Card(String suit, String rank, int suitNumber, int rankNumber){

        this.suit = suit;
        this.rank = rank;
        this.suitNumber = suitNumber;
        this.rankNumber = rankNumber;        
    } 

    public String getRank(){

        return this.rank;
    }

    public String getSuit(){

        return this.suit;
    }

    public int getRankNumber(){

        return this.rankNumber;
    }

    public int getSuitNumber(){

        return this.suitNumber;
    }
    
    public void setRankNumber(int rankNum){

        this.rankNumber = rankNumber;
    }

    public String toString(){
    return(this.rank + String.valueOf(this.suit.charAt(0)));

    }
    public boolean repOK(){
        // poprawnosc logiczna
        return ((this.rankNumber >= 2 && this.rankNumber <= 14) &&
              (this.suitNumber >= 1 && this.suitNumber <= 4) &&
              (this.suit != null && this.rank != null));
    }  
     
}
