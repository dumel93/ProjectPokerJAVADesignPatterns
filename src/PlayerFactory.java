public class PlayerFactory extends PokerFactory{

	public static Player createPlayer(){


			return new HumanPlayer();


		}




	public static Player createPlayer(String name, double chip) {

			return new HumanPlayer(name, chip);


		}

}
