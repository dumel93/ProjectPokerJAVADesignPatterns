import java.util.*;


public abstract class Player implements Observer{


	protected String name;
	protected double chip;
	protected double bet;
	protected PlayerHand playerHand;
	protected Table table;
	protected PokerHand pokerHand;


	public Player(){
		this.name = "";
		this.chip = 0;
		this.bet = 0;
		this.playerHand = new PlayerHand();
		this.table = null;
	}

	public Player(String name, double chip){
		this.name = name;
		this.chip = chip;
		this.bet = 0;
		this.playerHand = new PlayerHand();
		this.table = null;
	}

	public void update(Observable obs, Object obj)
	{
		this.pokerHand.updateBestHand();
	}


	public String getName(){
		return this.name;
	}

	public void setName(String name){
		this.name = name;
	}

	public double getChip(){
		return this.chip;
	}

	public void setChip(double chip){
		this.chip = chip;
	}

	public double getBet(){
		return this.bet;
	}

	public void setBet(double amount){
		this.bet = amount;
	}


	public double bet(double amount)
	{

		this.chip -= amount;
		this.bet += amount;
		table.addPot(amount);
		System.out.println(name + ": Stawianie - $" + amount);

		return amount;
	}



	public double check()
	{
		// no betting action
		return 0.0;
	}

	public double call()
	{

		double amount = table.getHighestBet() - this.bet;
		this.chip -= amount;
		this.bet += amount;
		table.addPot(amount);

		return amount;
	}

	public double fold(){

		getTable().getDealer().getActivePlayers().remove(this);
		this.bet = 0.0;
		clearPlayerHand();

		return 0.0;
	}

	public boolean raise(double amount){

		if( (amount + this.bet) > table.getHighestBet() )
		{
			System.out.println(name + ": Podbicie - $" + amount);
			bet(amount);
			return true;
		}
		else
			return false;
	}

	public void allin(){
		bet(this.chip);
	}

	public void winPot(double amount){
		this.chip += amount;
	}

	public PlayerHand getPlayerHand(){
		return this.playerHand;
	}

	public void clearPlayerHand(){
		this.playerHand.clearHand();
	}

	public PokerHand getPokerHand(){
		return this.pokerHand;
	}
	public Table getTable(){
		return this.table;
	}

	public boolean joinTable(Table reqTable){
		if (reqTable.requestJoin(this)){
			table = reqTable;
			this.pokerHand = new PokerHand( getPlayerHand(), this.table.getCommunityCards());
			return true;
		}
		else
			return false;
	}

	public boolean leaveTable(){
		if (table.requestLeave(this)){
			table = null;
			return true;
		}
		else
			return false;
	}
	public boolean quit(){
		if (table == null){
			return false;
		}
		else{
			return table.requestLeave(this);
		}
	}

	public String toString(){
		String out = "Gracz: " + getName();
		out += "\nStack: " + getChip();
/*		if( table != null )
			out += "\nTable: " + table.getTableNum();
		else
			out += "\nTable: No Table";
*/
		ArrayList<Card> cards = playerHand.getCards();

		if( cards.size() > 0 ){
			out += "\nKarty: ";

			Iterator<Card> cardList = cards.iterator();
			while (cardList.hasNext()){
			  Card next = cardList.next();
			  out += next;

			  if (cardList.hasNext())
			  	out += ", ";
			}
		}
		else
			out += "\nKarty: brak";

		return out;
	}
}
