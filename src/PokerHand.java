import java.util.*;

class PokerHand implements Comparable<PokerHand>{
    private PlayerHand playerHand;
    private CommonCards commonCards;
    private HandsCompar hsa;
    private Card[] hand;
    private static  ArrayList<Card> temp;
    private int handStrength;

    public PokerHand(PlayerHand playerHand, CommonCards commonCards){
        this.playerHand = playerHand;
        this.commonCards = commonCards;
        this.hsa = new HandsCompar();
        this.hand = new Card[5];
        this.temp = new ArrayList<>();
    }
  
    @Override public int compareTo(PokerHand pokerHand){
        int equalStrength = 0;

        final int LESS_THAN = -1;
        final int EQUAL =0;
        final int GREATER_THAN = 1;

        if(this.handStrength < pokerHand.getHandStrength()){      
            return LESS_THAN;
        }
        else if(this.handStrength > pokerHand.getHandStrength()){
            return GREATER_THAN;
        }
        else if(this.handStrength == pokerHand.getHandStrength()){
            for(int i = 0; i < this.hand.length; i++){
                if(equalStrength == 0){
                    equalStrength = this.hand[i].getRankNumber() - pokerHand.getHand()[i].getRankNumber();
                }
            }
        }
            
        if(equalStrength < 0){
            return LESS_THAN;
        }
        else if(equalStrength > 0){
            return GREATER_THAN;
        }
        else return EQUAL;      
    }
  

    private int getHandStrength(){
        return handStrength;
    }
  
    private Card[] getHand(){
        return hand;
    }
    
    public void updateBestHand(){
        for(int i = 0; i < playerHand.getCards().size(); i++){
            this.temp.add(playerHand.getCards().get(i));
        }
        for(int i = 0; i < commonCards.getCards().size(); i++){
            this.temp.add(commonCards.getCards().get(i));
        }

        hand =  hsa.getBestHand(temp);
        handStrength = this.hsa.getHandStrengthVal();;
        temp.clear();    
    }
  
    public String toString(){
        String output = "";
        output += " Najlepsza ręka to ";
        for(int i = 0; i < hand.length; i++){
            if(i != hand.length - 1){
                output += (hand[i] + ", ");
            }
            else{
                output += hand[i] + ".";
            }
        }
        return output;
    }

      
}
