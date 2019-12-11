import java.util.*;

public class HoldemDealer extends Dealer{

	public HoldemDealer(){
		super();
	}

	public HoldemDealer(String name){
		super(name);
	}

	public void startHands(){



		setDeck();
		shuffleDeck();
		setActivePlayers();

		if( getAnte() > 0){
			getAnteFromPlayer();
		}

		getBlindsFromPlayer();
		getCard();


		int currentNumPlayer = this.table.getCurrentNumPlayer();
		int buttonPosition = this.table.getButtonPosition();

		for (int i = 0; i < this.pokerGame.getNumPlayerCards(); i ++){
			// Give first player
			for (int j = 1; j <= this.table.getCurrentNumPlayer(); j++){
				Card card = getCard();
				this.table.getCurrentPlayers().get( (buttonPosition + j) % currentNumPlayer ).getPlayerHand().setCard(card);
			}
		}

		setChanged();
   		notifyObservers();
	}


}
