public abstract class PokerGame{
	int numPlayerCards;
	int numCommunityCards;
	Bet bet;
	GameType gameType;

	public PokerGame(){

	}



  	public PokerGame(Bet bet, GameType gameType){
	    this.gameType = gameType;
	    this.bet = bet;
  	}

	public int getNumPlayerCards(){
		return this.numPlayerCards;
	}

	public int getNumCommunityCards(){
		return this.numCommunityCards;
	}

	public GameType getGameType(){
		return this.gameType;
	}
	public void setGameType(GameType gameType){
		this.gameType = gameType;
	}

	public Bet getBettingStructure(){
		return this.bet;
	}

	public void setBettingStructure(Bet bet){
		this.bet = bet;
	}

	public abstract String toString();

	public boolean repOK(){
		return (numPlayerCards > 0 && numCommunityCards >= 0 && bet != null && gameType != null);
	}

}
