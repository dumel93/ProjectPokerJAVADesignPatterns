import java.util.ArrayList;

class PlayerHand{
	private ArrayList<Card> hand ;

	public PlayerHand(){
		this.hand =  new ArrayList<Card>();
	}

	public void setCard(Card card){
		this.hand.add(card);
	}

	public void clearHand()
	{
		hand = new ArrayList<Card>();
	}

	public ArrayList<Card> getCards(){
		return this.hand;
	}

	public String toString(){
        String result = "";
        for(int i=0; i < this.hand.size(); i++){
            result += this.hand.get(i) + "\t";
        }
        return result;
    }
}
