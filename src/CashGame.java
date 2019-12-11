public class CashGame extends GameType{


	public CashGame(){

		this.ante = 0;
	}
	public CashGame(double ante){
		this.ante = ante;
	}

	public void setAnte(double ante){

	}

	public String toString(){

		return ("CashGame ma $" +  this.ante + " ante.");
	}

	public boolean repOK(){

		return this.ante == 0 ;
	}
}
