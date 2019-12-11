public class BetFactory extends PokerFactory{

	public static Bet createBettingStructure(String structure){
		if (structure.equalsIgnoreCase("Limit")){
			return new Limit();
		}
		else if (structure.equalsIgnoreCase("NoLimit")) {
			return new NoLimit();
		}

		else if (structure.equalsIgnoreCase("PotLimit")) {
			return new PotLimit();
		}
		else{
			return null;
		}
	}

	public static Bet createBettingStructure(String structure, double smallBlind, double bigBlind){
		if (structure.equalsIgnoreCase("Limit")){
			return new Limit(smallBlind, bigBlind);
		}
		else if (structure.equalsIgnoreCase("NoLimit")) {
			return new NoLimit(smallBlind, bigBlind);
		}

		else if (structure.equalsIgnoreCase("PotLimit")) {
			return new PotLimit(smallBlind, bigBlind);
		}
		else{
			return null;
		}
	}


}
